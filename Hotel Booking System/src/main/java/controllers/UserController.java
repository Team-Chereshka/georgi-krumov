package controllers;

import controllers.interfaces.Controller;
import entities.users.*;
import exceptions.Exceptions;
import repositories.Database;
import views.CommonView;
import views.UserView;

public class UserController implements Controller {

    private boolean hasLoggedInUser;
    private BaseUser currentUser;

    public UserController() {
        this.hasLoggedInUser = false;
        this.currentUser = null;
    }

    @Override
    public void receiveCommand(String url) {
        String command = "";
        String args = "";
        if (url.contains("?")) {
            command = url.split("\\?")[0];
            args = url.split("\\?")[1];
        } else {
            command = url;
        }
        try {
            switch (command) {
                case "Register" -> register(args);
                case "Login" -> login(args);
                case "MyProfile" -> viewProfile();
                case "Logout" -> logout();
            }
        } catch (IllegalArgumentException e) {
            CommonView.printDefaultMessage(e.getMessage());
        }
    }

    private void logout() {
        if (!hasLoggedInUser) {
            throw new IllegalArgumentException(Exceptions.NO_LOGGED_USER);
        } else {
            UserView.logout(this.currentUser.getUsername());
            this.hasLoggedInUser = false;
            this.currentUser = null;
        }
    }

    private void viewProfile() {
        if (!this.hasLoggedInUser) {
            throw new IllegalArgumentException(Exceptions.NO_LOGGED_USER);
        } else {
            UserView.viewMyProfile(this.currentUser);
        }
    }

    private void register(String args) {
        String[] argsArr = args.split("&");
        String username = argsArr[0].split("=")[1];
        String pass = argsArr[1].split("=")[1];
        String confirmedPassword = argsArr[2].split("=")[1];
        String role = argsArr[3].split("=")[1];

        if (!pass.equals(confirmedPassword)) {
            throw new IllegalArgumentException(Exceptions.PASSWORDS_NOT_MATCHING);
        } else if (this.hasLoggedInUser) {
            throw new IllegalArgumentException(Exceptions.USER_ALREADY_LOGGED_IN);
        } else if (Database.getUserRepository().getUserByUsername(username) != null) {
            throw new IllegalArgumentException(String.format(Exceptions.USERNAME_ALREADY_EXISTS, username));
        } else {
            BaseUser registeredUser = null;
            switch (role) {
                case "user" -> registeredUser = new NormalUser(username, pass, role);
                case "venueAdmin" -> registeredUser = new VenueAdmin(username, pass, role);
            }
            if (registeredUser != null) {
                Database.getUserRepository().addItem(registeredUser);
                long id = Database.getUserRepository().getIdCounter();
                registeredUser.setId(id);
                UserView.registrationSuccessful(username);
            }
        }
    }

    private void login(String args) {
        String[] argsArr = args.split("&");
        String username = argsArr[0].split("=")[1];
        String pass = argsArr[1].split("=")[1];
        String passHash = String.valueOf(pass.hashCode());

        if (hasLoggedInUser) {
            throw new IllegalArgumentException(Exceptions.USER_ALREADY_LOGGED_IN);
        } else if (Database.getUserRepository().getUserByUsername(username) == null) {
            throw new IllegalArgumentException(String.format(Exceptions.USERNAME_DOES_NOT_EXIST, username));
            //MIGHT BREAK DUE TO PARSING FROM INT TO STRING!
        } else if (((Database.getUserRepository().getUserByUsername(username)) != null) &&
                (!pass.equals(Database.getUserRepository().getUserByUsername(username).getPassword()))) {
            throw new IllegalArgumentException(Exceptions.WRONG_PASSWORD);
        } else {
            this.hasLoggedInUser = true;
            this.currentUser = Database.getUserRepository().getUserByUsername(username);
            UserView.login(username);
        }
    }

    public boolean hasLoggedInUser() {
        return this.hasLoggedInUser;
    }

    public BaseUser getCurrentUser() {
        return currentUser;
    }
}

