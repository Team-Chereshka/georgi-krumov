import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForecastRepository {
    private List<Forecast> forecasts;

    public ForecastRepository() {
        this.forecasts = new ArrayList<>();
    }

    public void addForecast(Forecast forecast) {
        this.forecasts.add(forecast);
    }

    public List<Forecast> getAllForSpecificLongitudeAndLatitude(double longitude, double latitude) {
        List<Forecast> resultForecast = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            if (forecast.getLongitude() == longitude && forecast.getLatitude() == latitude) {
                resultForecast.add(forecast);
            }
        }
        return resultForecast;
    }

    public List<Forecast> getAllForLongitudeAndLatitudeInRange
            (double minLongitude, double maxLongitude, double minLatitude, double maxLatitude) {

        List<Forecast> resultForecast = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            if (forecast.getLongitude() >= minLongitude && forecast.getLongitude() <= maxLongitude
                    && forecast.getLatitude() >= minLatitude && forecast.getLatitude() <= maxLatitude) {
                resultForecast.add(forecast);
            }
        }
        return resultForecast;
    }

    public List<Forecast> getAllWhereRainChanceIsMoreThan50Percent() {
        List<Forecast> resultList = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            if (forecast.getRainChancePercentage() > 50) {
                resultList.add(forecast);
            }
        }
        return resultList;
    }

    public List<Forecast> getAllWhereSnowChanceIsMoreThan25PercentAndRainChanceIsMoreThan75Percent() {
        List<Forecast> resultList = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            if (forecast.getSnowChancePercentage() > 25 && forecast.getRainChancePercentage() > 75) {
                resultList.add(forecast);
            }
        }
        return resultList;
    }

    public List<Forecast> getAllWhereRainChanceIsMoreThan85PercentAndThunderstormChanceIsMoreThan50Percent() {
        List<Forecast> resultList = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            if (forecast.getRainChancePercentage() > 85 && forecast.getThunderstormPercentage() > 50) {
                resultList.add(forecast);
            }
        }
        return resultList;

    }

    public List<Forecast> getAllWhereWindVelocityIsInRange(int minVelocity, int maxVelocity) {
        List<Forecast> resultList = new ArrayList<>();

        for (Forecast forecast : forecasts) {
            if (forecast.getWindVelocityInKilometersPerHour() > minVelocity && forecast.getWindVelocityInKilometersPerHour() < maxVelocity) {
                resultList.add(forecast);
            }
        }

        return resultList;
    }

    public void getHumidityOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfRain
            (double deltaLongitude, double deltaLatitude) {
        for (Forecast forecast : forecasts) {
            if (forecast.getRainChancePercentage() > 85
                    && forecast.getLongitude() >= deltaLongitude - 10
                    && forecast.getLongitude() <= deltaLongitude + 10
                    && forecast.getLatitude() >= deltaLatitude - 10
                    && forecast.getLatitude() <= deltaLatitude + 10) {
                System.out.printf("%.0f%n", forecast.getHumidity());
            }
        }
    }

    public void getFeelsLikeOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfSnow
            (double deltaLongitude, double deltaLatitude) {
        for (Forecast forecast : forecasts) {
            if(forecast.getSnowChancePercentage() == 89
                    && forecast.getLongitude() >= deltaLongitude - 10
                    && forecast.getLongitude() <= deltaLongitude + 10
                    && forecast.getLatitude() >= deltaLatitude - 10
                    && forecast.getLatitude() <= deltaLatitude + 10){
                System.out.printf("%.0f%n", forecast.getTemperatureFeelsLike());
            }
        }
    }

}
