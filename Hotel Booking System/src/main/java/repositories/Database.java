package repositories;

public class Database {

    private static BookingRepository bookingRepository;
    private static RoomRepository roomRepository;
    private static UserRepository userRepository;
    private static VenueRepository venueRepository;


    public Database() {
        userRepository = new UserRepository();
        bookingRepository = new BookingRepository();
        roomRepository = new RoomRepository();
        venueRepository = new VenueRepository();
    }


    public static BookingRepository getBookingRepository() {
        return bookingRepository;
    }

    public static RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static VenueRepository getVenueRepository() {
        return venueRepository;
    }
}
