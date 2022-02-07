package entities.bookings;

import entities.rooms.Room;
import repositories.Database;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Booking {

    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private String comment;

    public Booking(long roomId, LocalDate startDate, LocalDate endDate) {
        this.setRoom(roomId);
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        double pricePerDay = this.room.getPricePerDay();
        long numberOfDays = DAYS.between(startDate, endDate);
        return pricePerDay * numberOfDays;

    }

    public void setRoom(long roomId) {
        this.room = Database.getRoomRepository().getItemById(roomId);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
