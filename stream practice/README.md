# Forecasts

## Forecast properties
* longitude
* latitude
* averageTemperature
* temperatureFeelsLike
* rainChancePercentage
* snowChancePercentage
* thunderstormPercentage
* windVelocityInKilometersPerHour
* humidity
* atmosphericPressureInBars
## Forecast querying
* getAllForSpecificLongitudeAndLatitude - parameters (longitude, latitude)
* getAllForLongitudeAndLatitudeRange - parameters (minLongitude, maxLongitude, minLatitude, maxLatitude)
* getAllWhereRainChanceIsMoreThan50Percent ()
* getAllWhereSnowChanceIsMoreThan25PercentAndRainChanceIsMoreThan75Percent ()
* getAllWhereRainChanceIsMoreThan85PercentAndThunderstormChanceIsMoreThan50Percent ()
* getAllWhereWindVelocityIsInRange - parameters (minVelocity, maxVelocity)
* getHumidityOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfRain (deltaLongitude, deltaLatitude)
  - Area1 has 89% percent chance of rain
  - Specified range is 10
  - Get all forecasts in longitude and latitude range less than or more than 10
  - Get the forecast humidity (as list of double)
* getFeelsLikeOfAreasInRangeOfAnAreaThatHasMoreThan85PercentChanceOfSnow (deltaLongitude, deltaLatitude)
  - Area1 has 89% percent chance of snow
  - Specified range is 10
  - Get all forecasts in longitude and latitude range less than or more than 10
  - Get the forecast feelsLIkeTemperature (as list of double)
* getAverageFeelsLikeTemperatureForSpecificRangeOfLongitudeAndLatitude - parameters (minLongitude, maxLongitude, minLatitude, maxLatitude)



## TASKS
First implement all methods only with iterations (only with for and foreach loops and ifs and elses and ifelses)
Second. Create a class ForecastStreamRepository, which is a direct copy of ForecastRepository - and implement all methods with stream
no fors and no foreaches
