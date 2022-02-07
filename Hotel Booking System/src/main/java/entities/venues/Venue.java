package entities.venues;

import entities.rooms.Room;
import exceptions.Exceptions;

import java.util.ArrayList;
import java.util.List;

public class Venue {

    private long id;
    private String name;
    private String address;
    private String description;
    private List<Room> freeRooms;


    public Venue(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.freeRooms = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() >= 3) {
            this.name = name;
        } else {
            throw new IllegalArgumentException(String.format(
                    Exceptions.NOT_ENOUGH_LENGTH, "venue name", 3));
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() >= 3) {
            this.address = address;
        } else {
            throw new IllegalArgumentException(String.format(
                    Exceptions.NOT_ENOUGH_LENGTH, "venue address", 3));
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Room> getFreeRooms() {
        return freeRooms;
    }

    public void addRoom(Room room) {
        this.freeRooms.add(room);
    }
}
