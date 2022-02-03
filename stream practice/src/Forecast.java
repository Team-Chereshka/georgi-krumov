public class Forecast {

    private String locationName;

    private double longitude;

    private double latitude;

    private double averageTemperature;

    private double temperatureFeelsLike;

    private int rainChancePercentage;

    private int snowChancePercentage;

    private int thunderstormPercentage;

    private double windVelocityInKilometersPerHour;

    private double humidity;

    private double atmosphericPressureInBars;

    public Forecast(String locationName, double longitude, double latitude, double averageTemperature,
                    double temperatureFeelsLike, int rainChancePercentage, int snowChancePercentage,
                    int thunderstormPercentage, double windVelocityInKilometersPerHour,
                    double humidity, double atmosphericPressureInBars) {
        this.locationName = locationName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.averageTemperature = averageTemperature;
        this.temperatureFeelsLike = temperatureFeelsLike;
        this.rainChancePercentage = rainChancePercentage;
        this.snowChancePercentage = snowChancePercentage;
        this.thunderstormPercentage = thunderstormPercentage;
        this.windVelocityInKilometersPerHour = windVelocityInKilometersPerHour;
        this.humidity = humidity;
        this.atmosphericPressureInBars = atmosphericPressureInBars;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getTemperatureFeelsLike() {
        return temperatureFeelsLike;
    }

    public void setTemperatureFeelsLike(double temperatureFeelsLike) {
        this.temperatureFeelsLike = temperatureFeelsLike;
    }

    public int getRainChancePercentage() {
        return rainChancePercentage;
    }

    public void setRainChancePercentage(int rainChancePercentage) {
        this.rainChancePercentage = rainChancePercentage;
    }

    public int getSnowChancePercentage() {
        return snowChancePercentage;
    }

    public void setSnowChancePercentage(int snowChancePercentage) {
        this.snowChancePercentage = snowChancePercentage;
    }

    public int getThunderstormPercentage() {
        return thunderstormPercentage;
    }

    public void setThunderstormPercentage(int thunderstormPercentage) {
        this.thunderstormPercentage = thunderstormPercentage;
    }

    public double getWindVelocityInKilometersPerHour() {
        return windVelocityInKilometersPerHour;
    }

    public void setWindVelocityInKilometersPerHour(double windVelocityInKilometersPerHour) {
        this.windVelocityInKilometersPerHour = windVelocityInKilometersPerHour;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getAtmosphericPressureInBars() {
        return atmosphericPressureInBars;
    }

    public void setAtmosphericPressureInBars(double atmosphericPressureInBars) {
        this.atmosphericPressureInBars = atmosphericPressureInBars;
    }
}
