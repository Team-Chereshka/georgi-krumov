package views;

import entities.bookings.Booking;
import entities.users.BaseUser;

import java.time.format.DateTimeFormatter;

public class UserView {

    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void viewMyProfile(BaseUser user) {
        System.out.printf("%s%n", user.getUsername());

        if (user.getBookingList().isEmpty()) {
            System.out.printf("You have not made any bookings yet.%n");
        } else {
            System.out.printf("Your bookings:%n");
            for (Booking booking : user.getBookingList()) {
                String startDate = booking.getStartDate().format(formatter);
                String endDate = booking.getEndDate().format(formatter);
                double totalPrice = booking.getTotalPrice();
                System.out.printf("* %s - %s ($%.2f)%n", startDate, endDate, totalPrice);
            }
        }
    }


    public static void login(String username) {
        System.out.printf("The user %s has logged in.%n", username);
    }

    public static void registrationSuccessful(String username){
        System.out.printf("The user %s has been registered and may login.%n", username);
    }
    public static void logout(String username){
        System.out.printf("The user %s has logged out.%n", username);
    }

}
