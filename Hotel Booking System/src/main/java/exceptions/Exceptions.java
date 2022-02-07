package exceptions;

public class Exceptions {
    public static final String PASSWORDS_NOT_MATCHING = "The provided passwords do not match.";
    public static final String USER_ALREADY_LOGGED_IN = "There is already a logged in user.";
    public static final String USERNAME_ALREADY_EXISTS = "A user with username %s already exists.";
    public static final String USERNAME_DOES_NOT_EXIST = "A user with username %s does not exist.";
    public static final String WRONG_PASSWORD = "The provided password is wrong.";
    public static final String NO_LOGGED_USER = "There is no currently logged in user.";
    public static final String ROOM_NOT_AVAILABLE = "The room is not available to book in the period %s - %s";
    public static String ILLEGAL_URL = "Something happened!!!";
    public static String ILLEGAL_RIGHTS = "The currently logged in user doesn't have sufficient rights to perform this operation.";
    public static String NOT_ENOUGH_LENGTH = "The %s must be at least %d symbols long!";
    public static String ILLEGAL_VALUE = "The %s must not be less than %d.";
    public static String NON_EXISTING_VENUE_ID = "The venue with ID %d does not exist.";
    public static String ROOM_DOES_NOT_EXIST = "The room with ID %d does not exist";
    public static String ILLEGAL_DATE_RANGE_MESSAGE = "The date range is invalid.";
    public static String ILLEGAL_VALUE_MESSAGE = "The %s must not be less than %d.";
}
