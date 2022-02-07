package views;

import entities.rooms.Room;
import entities.venues.Venue;
import repositories.Database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VenueView {

    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void showAll() {
        if (Database.getVenueRepository().getItems().isEmpty()) {
            System.out.printf("There are currently no venues to show.%n");
        } else {
            for (Map.Entry<Long, Venue> entry : Database.getVenueRepository().getItems().entrySet()) {
                String venueName = entry.getValue().getName();
                String venueAddress = entry.getValue().getAddress();
                int numberOfRooms = entry.getValue().getFreeRooms().size();
                System.out.printf("*[%d] %s, located at %s%n" +
                                "Free rooms: %d%n",
                        entry.getKey(), venueName, venueAddress, numberOfRooms);
            }
        }
    }

    public static void showVenueDetails(long venueId) {
        Venue venue =
                Database.getVenueRepository().getItemById(venueId);
        String venueName = venue.getName();
        String venueAddress = venue.getAddress();
        String venueDescription = venue.getDescription();
        List<Room> roomList = venue.getFreeRooms();

        System.out.printf("%s%n" +
                "Located at %s%n" +
                "Description: %s%n", venueName, venueAddress, venueDescription);
        if (roomList.isEmpty()) {
            System.out.printf("No rooms are currently available.%n");
        } else {
            for (Room room : roomList) {
                int numberOfPlaces = room.getVacantSpots();
                double pricePErDay = room.getPricePerDay();
                System.out.printf("Available rooms:%n");
                System.out.printf("* %d places ($%.2f per day)%n"
                        , numberOfPlaces, pricePErDay);
            }
        }
    }


    public static void addVenue(String name, long id) {
        System.out.printf("The venue %s with ID %d has been created successfully.%n", name, id);
    }

    public static void showAllRoomsInVenue(long venueId) {
        Venue venue = Database.getVenueRepository().getItemById(venueId);
        List<Room> roomList = venue.getFreeRooms();
        String venueName = venue.getName();

        boolean foundAtLeastOneEmpty = false;

        if (!roomList.isEmpty()) {
            foundAtLeastOneEmpty = true;
        }

        System.out.printf("Available rooms for venue %s:%n", venueName);
        if (foundAtLeastOneEmpty) {
            for (Room room : roomList) {
                long roomId = room.getId();
                int vacantSpots = room.getVacantSpots();
                double pricePerDay = room.getPricePerDay();
                System.out.printf(" *[%d] %d places, $%.2f per day%n", roomId, vacantSpots, pricePerDay);
                if (room.getAvailableBetween().isEmpty()) {
                    System.out.printf("This room is not currently available.%n");
                } else {
                    Map<LocalDate, LocalDate> roomAvailabilitySortedMap = room.getAvailableBetween().entrySet().stream().sorted((e1, e2) -> {
                        LocalDate e1Start = e1.getKey();
                        LocalDate e2start = e2.getKey();
                        return e1Start.compareTo(e2start);
                    }).collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (x, y) -> null,
                            LinkedHashMap::new
                    ));
                    System.out.printf("Available Dates:%n");

                    for (Map.Entry<LocalDate, LocalDate> entry : roomAvailabilitySortedMap.entrySet()) {
                        String startDate = entry.getKey().format(formatter);
                        String endDate = entry.getValue().format(formatter);
                        System.out.printf("- %s - %s%n", startDate, endDate);
                    }
                }
            }
        } else {
            System.out.printf("No rooms are currently available.%n");
        }
    }

}
