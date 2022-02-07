package controllers;

import controllers.interfaces.Controller;
import entities.venues.Venue;
import exceptions.AuthorizationFailedException;
import exceptions.Exceptions;
import repositories.Database;
import views.CommonView;
import views.VenueView;

public class VenueController implements Controller {

    private UserController userController;

    public VenueController(UserController userController) {
        this.userController = userController;
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
                case "All" -> showAll();
                case "Details" -> showDetails(args);
                case "Rooms" -> viewRooms(args);
                case "Add" -> addVenue(args);
            }
        } catch (IllegalArgumentException | AuthorizationFailedException e) {
            CommonView.printDefaultMessage(e.getMessage());
        }
    }

    private void addVenue(String args) throws AuthorizationFailedException {
        String[] argsArray = args.split("&");
        String venueName = argsArray[0].split("=")[1].replace("%20", " ");
        String venueAddress = argsArray[1].split("=")[1];
        String venueDescription = argsArray[2].split("=")[1].replace("%20", " ");
        if (!userController.hasLoggedInUser()) {
            throw new IllegalArgumentException(Exceptions.NO_LOGGED_USER);
        } else if (!this.userController.getCurrentUser().isAdmin()) {
            throw new AuthorizationFailedException(Exceptions.ILLEGAL_RIGHTS);
        } else {
            Venue venue = new Venue(venueName, venueAddress, venueDescription);
            long id = Database.getVenueRepository().getIdCounter();
            venue.setId(id);
            Database.getVenueRepository().addItem(venue);
            VenueView.addVenue(venueName, id);
        }
    }

    private void viewRooms(String args) {
        long venueId = Long.parseLong(args.split("=")[1]);
        if (!userController.hasLoggedInUser()) {
            throw new IllegalArgumentException(Exceptions.NO_LOGGED_USER);
        } else if (!this.userController.getCurrentUser().isAdminOrIsUser()) {
            throw new IllegalArgumentException(Exceptions.ILLEGAL_RIGHTS);
        } else if (!Database.getVenueRepository().getItems().containsKey(venueId)) {
            throw new IllegalArgumentException(String.format(Exceptions.NON_EXISTING_VENUE_ID, venueId));
        } else {
            VenueView.showAllRoomsInVenue(venueId);
        }
    }

    private void showDetails(String args) throws AuthorizationFailedException {
        long venueId = Long.parseLong(args.split("=")[1]);
        if (!userController.hasLoggedInUser()) {
            throw new IllegalArgumentException(Exceptions.NO_LOGGED_USER);
        } else if (!this.userController.getCurrentUser().isAdmin()) {
            throw new IllegalArgumentException(Exceptions.ILLEGAL_RIGHTS);
        } else if (Database.getVenueRepository().getItemById(venueId) == null) {
            throw new IllegalArgumentException(String.format(Exceptions.NON_EXISTING_VENUE_ID, venueId));
        } else {
            VenueView.showVenueDetails(venueId);
        }
    }

    private void showAll() {
        VenueView.showAll();
    }
}


