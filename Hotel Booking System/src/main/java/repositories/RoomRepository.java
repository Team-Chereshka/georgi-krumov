package repositories;

import entities.rooms.Room;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomRepository implements Repository<Room> {

    private Map<Long, Room> rooms;
    private long idCount;

    public RoomRepository() {
        this.rooms = new LinkedHashMap<>();
        this.idCount = 1;
    }

    @Override
    public Map<Long, Room> getItems() {
        return Collections.unmodifiableMap(rooms);
    }

    @Override
    public Room getItemById(long id) {
        Room roomToReturn = null;
        if (rooms.containsKey(id)){
            roomToReturn = rooms.get(id);
        }
        return roomToReturn;
    }

    @Override
    public void addItem(Room item) {
        this.rooms.put(idCount, item);
        idCount++;
    }

    @Override
    public long getIdCounter() {
        return this.idCount;
    }
}
