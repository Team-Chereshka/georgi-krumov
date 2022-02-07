package views;

import entities.bookings.Booking;
import entities.rooms.Room;
import repositories.Database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RoomView {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public static void addRoom(long roomId) {
        System.out.printf(
                "The room with ID %d has been created successfully.%n", roomId);
    }

    public static void addPeriod(long roomId) {
        System.out.printf(
                "The period has been added to room with ID %d.%n", roomId);
    }

    public static void successfulBooking(LocalDate startDate, LocalDate endDate, double totalPrice) {
        System.out.printf(
                "Room booked from %s to %s for $%.2f%n",
                startDate.format(formatter), endDate.format(formatter), totalPrice);
    }

    public static void viewBookings(long roomId){
        Room currentRoom =
                Database.getRoomRepository().getItemById(roomId);

        if (currentRoom.getBookingList().isEmpty()){
            System.out.printf("There are no bookings for this room.%n");
        }else {
            System.out.printf("Room bookings:%n");
            double totalBookingsPrice = 0;
            for (Booking booking : currentRoom.getBookingList()) {
                String startDate = booking.getStartDate().format(formatter);
                String endDate = booking.getEndDate().format(formatter);
                double totalPrice = booking.getTotalPrice();
                totalBookingsPrice += totalPrice;
                System.out.printf("* %s - %s ($%.2f)%n", startDate, endDate, totalPrice);
            }
            System.out.printf("Total booking price: $%.2f%n", totalBookingsPrice);
        }
    }

}

