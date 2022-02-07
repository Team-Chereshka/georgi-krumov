package repositories;

import entities.venues.Venue;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class VenueRepository implements Repository<Venue>{

    private Map<Long, Venue> venues;
    private  long idCount;

    public VenueRepository() {
        this.venues = new LinkedHashMap<>();
        this.idCount = 1;
    }

    @Override
    public Map<Long, Venue> getItems() {
        return Collections.unmodifiableMap(venues);
    }

    @Override
    public Venue getItemById(long id) {
        Venue venueToReturn = null;
        if (venues.containsKey(id)){
            venueToReturn = venues.get(id);
        }
        return venueToReturn;
    }

    @Override
    public void addItem(Venue item) {
    this.venues.put(idCount, item);
    idCount++;
    }

    @Override
    public long getIdCounter() {
        return this.idCount;
    }
}
