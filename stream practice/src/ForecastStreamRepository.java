import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForecastStreamRepository {

    private List<Forecast> forecastList;

    public ForecastStreamRepository() {
        this.forecastList = new ArrayList<>();
    }

    public void addForecast(Forecast forecast) {
        this.forecastList.add(forecast);
    }


    public List<Forecast> getAllForSpecificLongitudeAndLatitude(double longitude, double latitude) {
        return this.forecastList
                .stream()
                .filter(forecast -> forecast.getLongitude() == longitude && forecast.getLatitude() == latitude)
                .collect(Collectors.toList());
    }

    public List<Forecast> getAllForLongitudeAndLatitudeInRange
            (double minLongitude, double maxLongitude, double minLatitude, double maxLatitude) {
        return this.forecastList
                .stream()
                .filter(forecast ->
                        forecast.getLongitude() >= minLongitude && forecast.getLongitude() <= maxLongitude
                                && forecast.getLatitude() >= minLatitude && forecast.getLatitude() <= maxLatitude
                )
                .collect(Collectors.toList());
    }

    public List<Forecast> getAllWhereRainChanceIsMoreThan50Percent() {
        return this.forecastList
                .stream()
                .filter(forecast -> forecast.getRainChancePercentage() > 50)
                .collect(Collectors.toList());
    }

    public List<Forecast> getAllWhereSnowChanceIsMoreThan25PercentAndRainChanceIsMoreThan75Percent() {
        return this.forecastList
                .stream()
                .filter(forecast -> forecast.getSnowChancePercentage() > 25
                        && forecast.getRainChancePercentage() > 75)
                .collect(Collectors.toList());
    }

    public List<Forecast> getAllWhereRainChanceIsMoreThan85PercentAndThunderstormChanceIsMoreThan50Percent() {
        return this.forecastList
                .stream()
                .filter(forecast -> forecast.getRainChancePercentage() > 85
                        && forecast.getThunderstormPercentage() > 50)
                .collect(Collectors.toList());
    }

    public List<Forecast> getAllWhereWindVelocityIsInRange(int minVelocity, int maxVelocity) {
        return this.forecastList
                .stream()
                .filter(forecast -> forecast.getWindVelocityInKilometersPerHour() > minVelocity
                        && forecast.getWindVelocityInKilometersPerHour() < maxVelocity)
                .collect(Collectors.toList());
    }


    public void getHumidityOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfRain
            (double deltaLongitude, double deltaLatitude) {
        this.forecastList
                .stream()
                .filter(forecast -> forecast.getRainChancePercentage() > 85
                        && forecast.getLongitude() >= deltaLongitude - 10
                        && forecast.getLongitude() <= deltaLongitude + 10
                        && forecast.getLatitude() >= deltaLatitude - 10
                        && forecast.getLatitude() <= deltaLatitude + 10)
                .map(Forecast::getHumidity).forEach(x -> System.out.printf("Humidity Level:%n" +
                "%.0f%n", x));
    }

    public List<Double> getFeelsLikeOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfSnow
            (double deltaLongitude, double deltaLatitude) {
        return forecastList.stream()
                .filter(forecast -> forecast.getSnowChancePercentage() == 89
                        && forecast.getLongitude() >= deltaLongitude - 10
                        && forecast.getLongitude() <= deltaLongitude + 10
                        && forecast.getLatitude() >= deltaLatitude - 10
                        && forecast.getLatitude() <= deltaLatitude + 10)
                .map(Forecast::getTemperatureFeelsLike)
                .collect(Collectors.toList());
    }
}
