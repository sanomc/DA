package at.htlle.da.backend.exceptions;

import at.htlle.da.backend.entities.UserEntity;

public class WrongUserException extends RuntimeException {
    public WrongUserException(UserEntity wrongUser, UserEntity rightUser)
    {
        super("Wrong User tries to manipulate Data. Right user would be " + rightUser.getUsername() + ", but " + wrongUser.getUsername() + " sent the Request");
    }
}
