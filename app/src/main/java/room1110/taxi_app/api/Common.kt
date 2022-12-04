package room1110.taxi_app.api

import room1110.taxi_app.R

object Common {
//    private const val BASE_URL = "http://${R.string.API_IP}:8080/"
    private const val BASE_URL = "http://192.168.31.248:8080/"
    val retrofitService: RideApiInterface
        get() = RetrofitRide.getRide(BASE_URL).create(RideApiInterface::class.java)
}