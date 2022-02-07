package io;

import controllers.RoomController;
import controllers.UserController;
import controllers.VenueController;
import exceptions.AuthorizationFailedException;
import exceptions.Exceptions;

public class InputReader {

    private String input;
    private RoomController roomController;
    private VenueController venueController;
    private UserController userController;


    public InputReader() {
       this.userController = new UserController();
       this.venueController = new VenueController(userController);
       this.roomController = new RoomController(userController, venueController);
    }

    public void readLine(String input) throws AuthorizationFailedException{
        this.input = input;
        giveCommand();
    }

    private void giveCommand() throws AuthorizationFailedException {
        String[] args = this.input.split("/");
        String controller = args[1];
        String url = args[2];

        switch (controller){
            case "Users" -> this.userController.receiveCommand(url);
            case "Venues" -> this.venueController.receiveCommand(url);
            case "Rooms" -> this.roomController.receiveCommand(url);
            default -> throw new IllegalArgumentException(Exceptions.ILLEGAL_URL);
        }
    }
}
