package com.example.remotehome.network

import com.squareup.moshi.Json

data class SensorData(
    @Json( name ="data") val data : List<Data>,
    @Json( name ="count") val count : Int
)

data class Data (

    @Json( name ="wind_cdir") val windDirection : String,
    @Json( name ="rh") val relativeHumidity : Double,
    @Json( name ="pod") val partOfTheDay : String,
    @Json( name ="lon") val longitude : Double,
    @Json( name ="pres") val pressure : Double,
    @Json( name ="timezone") val timezone : String,
    @Json( name ="ob_time") val observationTime : String,
    @Json( name ="country_code") val countryCode : String,
    @Json( name ="clouds") val clouds : Double,
    @Json( name ="vis") val visibility : Double,
    @Json( name ="wind_spd") val windSpeed : Double,
    @Json( name ="wind_cdir_full") val windDirectionFull : String,
    @Json( name ="app_temp") val apparentTemperature : Double,
    @Json( name ="state_code") val stateCode : String,
    @Json( name ="ts") val timestamp : Double,
    @Json( name ="h_angle") val solarHourAngle : Double,
    @Json( name ="dewpt") val dewPoint : Double,
    @Json( name ="weather") val weather : Weather,
    @Json( name ="uv") val uvIndex : Double,
    @Json( name ="aqi") val airQualityIndex : Double,
    @Json( name ="station") val station : String,
    @Json( name ="wind_dir") val windDirectionAbreviated : Double,
    @Json( name ="elev_angle") val solarElevationAngle : Double,
    @Json( name ="datetime") val dateTime : String,
    @Json( name ="precip") val precipitation : Double,
    @Json( name ="ghi") val globalHorizontalIrradiance : Double,
    @Json( name ="dni") val directNormalIrradiance : Double,
    @Json( name ="dhi") val directHorizontalIrradiance : Double,
    @Json( name ="solar_rad") val solarRad : Double,
    @Json( name ="city_name") val cityName : String,
    @Json( name ="sunrise") val sunrise : String,
    @Json( name ="sunset") val sunset : String,
    @Json( name ="temp") val temperature : Double,
    @Json( name ="lat") val latitude : Double,
    @Json( name ="slp") val seaLevelPressure : Double
)

data class Weather (

    @Json( name ="icon") val icon : String,
    @Json( name ="code") val code : Int,
    @Json( name ="description") val description : String
)