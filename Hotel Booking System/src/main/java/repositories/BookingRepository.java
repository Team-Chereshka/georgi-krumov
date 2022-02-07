package repositories;

import entities.bookings.Booking;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookingRepository implements Repository<Booking> {

    private Map<Long, Booking> bookings;
    private long idCount;


    public BookingRepository() {
        this.bookings = new LinkedHashMap<>();
        this.idCount = 1;
    }

    @Override
    public Map<Long, Booking> getItems() {
        return Collections.unmodifiableMap(this.bookings);
    }

    @Override
    public Booking getItemById(long id) {
        Booking bookingToReturn = null;
        if (this.bookings.containsKey(id)) {
            bookingToReturn = bookings.get(id);
        }
        return bookingToReturn;
    }

    @Override
    public void addItem(Booking item) {
        this.bookings.put(idCount, item);
        idCount++;
    }

    @Override
    public long getIdCounter() {
        return this.idCount;
    }
}
