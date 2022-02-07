package entities.users;

import entities.bookings.Booking;
import exceptions.Exceptions;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseUser {
    private long id;
    private String username;
    private String password;
    private String role;// might be user or admin
    List<Booking> bookingList;

    public BaseUser(String username, String password, String role) {
        id = 0;
        this.username = username;
        this.password = password;
        this.role = role;
        this.bookingList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() >= 5) {
            this.username = username;
        } else {
            throw new IllegalArgumentException(String.format(Exceptions.NOT_ENOUGH_LENGTH, "username", 5));
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() >= 5) {
            this.password = password;
        }else{
            throw new IllegalArgumentException(String.format(Exceptions.NOT_ENOUGH_LENGTH, "password", 5));
        }

    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public boolean isAdminOrIsUser(){
        return isAdmin() || isUser();
    }

    public boolean isAdmin(){
        return this.getRole().equals("venueAdmin");
    }

    public boolean isUser(){
        return this.getRole().equals("user");
    }
}
