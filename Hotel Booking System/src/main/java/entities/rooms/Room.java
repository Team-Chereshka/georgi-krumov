package entities.rooms;

import entities.bookings.Booking;
import exceptions.Exceptions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private long id;
    private int vacantSpots;
    private double pricePerDay;
    private List<Booking> bookingList;
    private Map<LocalDate, LocalDate> availableBetween;


    public Room(int vacantSpots, double pricePerDay) {
        this.vacantSpots = vacantSpots;
        this.pricePerDay = pricePerDay;
        this.bookingList = new ArrayList<>();
        this.availableBetween = new LinkedHashMap<>();
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVacantSpots() {
        return vacantSpots;
    }

    public void setVacantSpots(int vacantSpots) {
        if (vacantSpots > 0) {
            this.vacantSpots = vacantSpots;
        } else {
            throw new IllegalArgumentException(String.format(Exceptions.ILLEGAL_VALUE, "number of places", 0));
        }
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        if (pricePerDay > 0) {
            this.pricePerDay = pricePerDay;
        }else{
            throw  new IllegalArgumentException(String.format(Exceptions.NOT_ENOUGH_LENGTH, "price per day", 0));
        }
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public Map<LocalDate, LocalDate> getAvailableBetween() {
        return availableBetween;
    }


    public void setAvailableBetween(LocalDate start, LocalDate end) {
       if (availableBetween.containsKey(start) && availableBetween.get(start).isBefore(end)){
           availableBetween.put(start, end);
       }else{
           availableBetween.put(start, end);
       }
    }

    //MIGHT BREAK!!!!!!!!!!!!!!!
    public boolean isRoomAvailable(LocalDate start, LocalDate end){
        boolean isAvailable = false;

        for (Map.Entry<LocalDate, LocalDate> entry : availableBetween.entrySet()) {
            LocalDate startAvailabilityDate = entry.getKey();
            LocalDate endAvailabilityDate = entry.getValue();
            if (start.isAfter(startAvailabilityDate) || start.isEqual(startAvailabilityDate) &&
                    end.isBefore(endAvailabilityDate) || end.isEqual(endAvailabilityDate)){
                    isAvailable = true;
                    break;

            }

        }
        return isAvailable;
    }

    public void addBooking(Booking booking){
        this.bookingList.add(booking);
    }

    //MIGHT BREAK X2!!!!!!!!!!!!!!!
    public void updateAvailability(LocalDate start, LocalDate end){
        for (Map.Entry<LocalDate, LocalDate> entry : availableBetween.entrySet()) {
            LocalDate startAvailabilityDate = entry.getKey();
            LocalDate endAvailabilityDate = entry.getValue();
            if (isRoomAvailable(start, end)) {
                    this.availableBetween.remove(startAvailabilityDate);
                    this.availableBetween.put(startAvailabilityDate, start);
                    this.availableBetween.put(end, endAvailabilityDate);

            }
            break;
        }
    }

}
