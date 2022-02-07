package controllers.interfaces;

import exceptions.AuthorizationFailedException;

public interface Controller {

    void receiveCommand(String url) throws AuthorizationFailedException;
}
