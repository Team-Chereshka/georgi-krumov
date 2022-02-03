public class Program {
    public static void main(String[] args) {
        ForecastRepository forecastRepository = new ForecastRepository();
        ForecastStreamRepository forecastStreamRepository = new ForecastStreamRepository();


        //Business park Varna - 43.22854718475701, 27.857073846032314
        //veliko tarnovo mall - 43.078934280225035, 25.60016770171013
        ///Juno, ALaska - 58.30175661830393, -134.41958708694202

        Forecast vertigoTower = new Forecast("Vertigo Tower", 42.65707648066472, 23.28597410513164,
                10.1, 13, 35, 65,
                10, 18, 68, 12);

        Forecast velikoTarnovoMall = new Forecast("Mall Veliko Tarnovo", 43.078934280225035, 25.60016770171013,
                11.8, 12, 2, 10,
                18, 14, 38, 30);

        Forecast businessParkVarna = new Forecast("Business Park Varna", 43.22854718475701, 27.857073846032314,
                12.9, 15, 64, 22,
                25, 23, 87, 11);

        Forecast juneauAlaska = new Forecast("Juneau , Alaska", 58.30175661830393, -134.41958708694202,
                2.3, -1, 82, 28,
                12, 28, 48, 10);

        Forecast testForSeventhQuery = new Forecast("Test area", 53.30175661830393, -131.41958708694202,
                2.3, -1, 89, 28,
                12, 28, 48, 10);

        //location 1
        forecastRepository.addForecast(vertigoTower);
        forecastStreamRepository.addForecast(vertigoTower);
        //location 2(in between)
        forecastRepository.addForecast(velikoTarnovoMall);
        forecastStreamRepository.addForecast(velikoTarnovoMall);
        //location 3
        forecastRepository.addForecast(businessParkVarna);
        forecastStreamRepository.addForecast(businessParkVarna);
        //location 4
        forecastRepository.addForecast(juneauAlaska);
        forecastStreamRepository.addForecast(juneauAlaska);
        //location 5
        forecastRepository.addForecast(testForSeventhQuery);
        forecastStreamRepository.addForecast(testForSeventhQuery);

        System.out.println("FIRST QUERY:");
        forecastRepository.getAllForSpecificLongitudeAndLatitude(42.65707648066472, 23.28597410513164)
                .forEach(s -> System.out.printf("Location: %s%n" +
                        "Longitude: %.14f%n" +
                        "Latitude: %.14f%n", s.getLocationName(), s.getLongitude(), s.getLatitude()));
        System.out.printf("%nUsing the stream method:%n%n");

        forecastStreamRepository.getAllForSpecificLongitudeAndLatitude(42.65707648066472, 23.28597410513164)
                .forEach(
                        forecast -> System.out.printf("Location: %s%n" +
                                        "Longitude: %.14f%n" +
                                        "Latitude: %.14f", forecast.getLocationName(),
                                forecast.getLongitude(), forecast.getLatitude())
                );

        System.out.println();
        System.out.printf("%nSECOND QUERY:%n");

        forecastRepository.getAllForLongitudeAndLatitudeInRange
                (42.65707648066472, 43.22854718475701,
                        23.28597410513164, 27.857073846032314)
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f%n", forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude())
                );

        System.out.printf("%nUsing the stream method:%n%n");


        forecastStreamRepository.getAllForLongitudeAndLatitudeInRange
                (42.65707648066472, 43.22854718475701,
                        23.28597410513164, 27.857073846032314)
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f", forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude())
                );

        System.out.println();
        System.out.printf("%nTHIRD QUERY:%n");
        forecastRepository.getAllWhereRainChanceIsMoreThan50Percent()
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f%n" +
                                "Rain Chance: %d%n", forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude(), forecast.getRainChancePercentage())
                );

        System.out.printf("%nUsing the stream method:%n%n");

        forecastStreamRepository.getAllWhereRainChanceIsMoreThan50Percent()
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f%n" +
                                "Rain Chance: %d", forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude(), forecast.getRainChancePercentage())
                );

        System.out.println();
        System.out.printf("%nFOURTH QUERY:%n");
        forecastRepository.getAllWhereSnowChanceIsMoreThan25PercentAndRainChanceIsMoreThan75Percent()
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f%n" +
                                "Snow Chance: %d%n" +
                                "Rain Chance: %d%n"
                        , forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude(),
                        forecast.getSnowChancePercentage(), forecast.getRainChancePercentage())
                );

        System.out.printf("%nUsing the stream method:%n%n");

        forecastStreamRepository.getAllWhereSnowChanceIsMoreThan25PercentAndRainChanceIsMoreThan75Percent()
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f%n" +
                                "Snow Chance: %d%n" +
                                "Rain Chance: %d%n"
                        , forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude(),
                        forecast.getSnowChancePercentage(), forecast.getRainChancePercentage())
                );


        System.out.println();
        System.out.printf("%nFIFTH QUERY:%n");
        forecastRepository.getAllWhereRainChanceIsMoreThan85PercentAndThunderstormChanceIsMoreThan50Percent()
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f%n" +
                                "Rain Chance: %d%n" +
                                "ThunderstormChance: %d%n"
                        , forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude(),
                        forecast.getRainChancePercentage(), forecast.getThunderstormPercentage())
                );

        System.out.printf("%nUsing the stream method:%n%n");

        forecastStreamRepository.getAllWhereRainChanceIsMoreThan85PercentAndThunderstormChanceIsMoreThan50Percent()
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f%n" +
                                "Rain Chance: %d%n" +
                                "ThunderstormChance: %d%n"
                        , forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude(),
                        forecast.getRainChancePercentage(), forecast.getThunderstormPercentage())
                );


        System.out.println();
        System.out.printf("%nSIXTH QUERY:%n");
        forecastRepository.getAllWhereWindVelocityIsInRange(15, 20)
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f%n" +
                                "Wind Velocity: %.0f%n"
                        , forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude(),
                        forecast.getWindVelocityInKilometersPerHour())
                );

        System.out.printf("%nUsing the stream method:%n%n");

        forecastStreamRepository.getAllWhereWindVelocityIsInRange(15, 20)
                .forEach(forecast -> System.out.printf("Location: %s%n" +
                                "Longitude: %.14f%n" +
                                "Latitude: %.14f%n" +
                                "Wind Velocity: %.0f%n"
                        , forecast.getLocationName(),
                        forecast.getLongitude(), forecast.getLatitude(),
                        forecast.getWindVelocityInKilometersPerHour())
                );


        System.out.println();
        System.out.printf("%nSEVENTH QUERY:%n");
        System.out.println("Humidity Level:");
        forecastRepository
                .getHumidityOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfRain(53, -132);


        System.out.printf("%nUsing the stream method:%n%n");

        forecastStreamRepository
                .getHumidityOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfRain(53, -132);


        System.out.println();
        System.out.printf("%nEIGHT QUERY:%n");
        System.out.println("Temperature Feels Like:");
        forecastRepository
                .getFeelsLikeOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfSnow(53, -132);


        System.out.printf("%nUsing the stream method:%n%n");

        forecastStreamRepository
                .getFeelsLikeOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfSnow(53, -132);
    }
}
