package repositories;

import entities.users.BaseUser;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserRepository implements Repository<BaseUser> {

    //map to hold user by id
    private Map<Long, BaseUser> users;
    private long idCount;

    public UserRepository() {
        this.users = new LinkedHashMap<>();
        this.idCount = 1;
    }

    @Override
    public Map<Long, BaseUser> getItems() {
        return Collections.unmodifiableMap(this.users);
    }

    @Override
    public BaseUser getItemById(long id) {
        BaseUser userToGet = null;
        if (this.users.containsKey(id)) {
            userToGet = this.users.get(id);
        }
        return userToGet;
    }

    @Override
    public void addItem(BaseUser item) {
        this.users.put(idCount, item);
        idCount++;
    }

    @Override
    public long getIdCounter() {
        return this.idCount;
    }

    public BaseUser getUserByUsername(String username) {
        BaseUser userToReturn = null;
        for (BaseUser user : users.values()) {
            if (user.getUsername().equals(username)) {
                userToReturn = user;
                break;
            }
        }
        return userToReturn;
    }
}
