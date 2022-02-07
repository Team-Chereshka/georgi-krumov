package controllers;

import controllers.interfaces.Controller;
import entities.bookings.Booking;
import entities.rooms.Room;
import exceptions.AuthorizationFailedException;
import exceptions.Exceptions;
import repositories.Database;
import views.CommonView;
import views.RoomView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RoomController implements Controller {

    private UserController userController;
    private VenueController venueController;

    public RoomController(UserController userController, VenueController venueController) {
        this.userController = userController;
        this.venueController = venueController;
    }

    @Override
    public void receiveCommand(String url) throws AuthorizationFailedException {
        String command = "";
        String args = "";
        if (url.contains("?")) {
            command = url.split("\\?")[0];
            args = url.split("\\?")[1];
        } else {
            command = url;
        }
        try {
            switch (command) {
                case "Add" -> addRoom(args);
                case "AddPeriod" -> setAvailableDates(args);
                case "ViewBookings" -> viewBookings(args);
                case "Book" -> bookRoom(args);
            }
        } catch (IllegalArgumentException | AuthorizationFailedException e) {
            CommonView.printDefaultMessage(e.getMessage());
        }
    }

    private void bookRoom(String args) throws AuthorizationFailedException {
        long roomID = Long.parseLong(args.split("&")[0].split("=")[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(args.split("&")[1].split("=")[1], formatter);
        LocalDate endDate = LocalDate.parse(args.split("&")[2].split("=")[1], formatter);
        String comments = args.split("&")[3].split("=")[1].replace("%20", " ");
        Room room = null;
        if (!Database.getRoomRepository().getItems().containsKey(roomID)) {
            throw new IllegalArgumentException(String.format(Exceptions.ROOM_DOES_NOT_EXIST, roomID));
        } else {
            room = Database.getRoomRepository().getItemById(roomID);
        }

        if (!userController.hasLoggedInUser()) {
            throw new IllegalArgumentException(Exceptions.NO_LOGGED_USER);
        } else if (!userController.getCurrentUser().isAdminOrIsUser()) {
            throw new AuthorizationFailedException(Exceptions.ILLEGAL_RIGHTS);
        } else if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(Exceptions.ILLEGAL_DATE_RANGE_MESSAGE);
        } else if (!room.isRoomAvailable(startDate, endDate)) {
            throw new IllegalArgumentException(String.format(Exceptions.ROOM_NOT_AVAILABLE, startDate, endDate));
        } else {
            Booking booking = new Booking(roomID, startDate, endDate);
            booking.setComment(comments);
            Database.getBookingRepository().addItem(booking);
            room.addBooking(booking);
            room.updateAvailability(startDate, endDate);
            userController.getCurrentUser().getBookingList().add(booking);
            RoomView.successfulBooking(startDate, endDate, booking.getTotalPrice());
        }
    }

    private void viewBookings(String args) throws AuthorizationFailedException {
        long roomID = Long.parseLong(args.split("=")[1]);
        if (!userController.hasLoggedInUser()) {
            throw new IllegalArgumentException(Exceptions.NO_LOGGED_USER);
        } else if (!userController.getCurrentUser().isAdmin()) {
            throw new AuthorizationFailedException(Exceptions.ILLEGAL_RIGHTS);
        } else if (!Database.getRoomRepository().getItems().containsKey(roomID)) {
            throw new IllegalArgumentException(String.format(Exceptions.ROOM_DOES_NOT_EXIST, roomID));
        } else {
            RoomView.viewBookings(roomID);
        }
    }

    private void setAvailableDates(String args) throws AuthorizationFailedException {
        long roomID = Long.parseLong(args.split("&")[0].split("=")[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(args.split("&")[1].split("=")[1], formatter);
        LocalDate endDate = LocalDate.parse(args.split("&")[2].split("=")[1], formatter);

        if (!userController.hasLoggedInUser()) {
            throw new IllegalArgumentException(Exceptions.NO_LOGGED_USER);
        } else if (!userController.getCurrentUser().isAdmin()) {
            throw new AuthorizationFailedException(Exceptions.ILLEGAL_RIGHTS);
        } else if (!Database.getRoomRepository().getItems().containsKey(roomID)) {
            throw new IllegalArgumentException(String.format(Exceptions.ROOM_DOES_NOT_EXIST, roomID));
        } else if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(Exceptions.ILLEGAL_DATE_RANGE_MESSAGE);
        } else {
            Room currentRoom = Database.getRoomRepository().getItemById(roomID);
            currentRoom.setAvailableBetween(startDate, endDate);
            RoomView.addPeriod(roomID);
        }
    }

    private void addRoom(String args) throws AuthorizationFailedException {
        long venueId = Long.parseLong(args.split("&")[0].split("=")[1]);
        int numOfVacantSpots = Integer.parseInt(args.split("&")[1].split("=")[1]);
        double pricePerDay = Double.parseDouble(args.split("&")[2].split("=")[1]);
        if (!userController.hasLoggedInUser()) {
            throw new IllegalArgumentException(Exceptions.NO_LOGGED_USER);
        } else if (!userController.getCurrentUser().isAdmin()) {
            throw new AuthorizationFailedException(Exceptions.ILLEGAL_RIGHTS);
        } else if (!Database.getVenueRepository().getItems().containsKey(venueId)) {
            throw new IllegalArgumentException(String.format(Exceptions.NON_EXISTING_VENUE_ID, venueId));
        } else {
            Room room = new Room(numOfVacantSpots, pricePerDay);
            long roomId = Database.getRoomRepository().getIdCounter();
            room.setId(roomId);
            Database.getRoomRepository().addItem(room);
            Database.getVenueRepository().getItemById(venueId).addRoom(room);
            RoomView.addRoom(roomId);
        }
    }
}
