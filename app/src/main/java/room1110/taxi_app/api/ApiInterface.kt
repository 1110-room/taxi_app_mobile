package room1110.taxi_app.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.RideRequest
import room1110.taxi_app.data.User

interface ApiInterface {

    // ----- GET -----
    @GET("ride/open-line")
    @Headers("Accept: application/json")
    fun getRideList(): Call<MutableList<Ride>>

    @GET("users/{id}")
    @Headers("Accept: application/json")
    fun fwtUser(): Call<User>

    @GET("ride/history/{user_id}")
    @Headers("Accept: application/json")
    fun getUserRideHistory(@Path("user_id") userId: Long): Call<MutableList<Ride>>


    // ----- POST -----
    @POST("ride/create")
    @Headers("Content-type: application/json")
    // do suspend (async)
    fun createRide(@Body newRide: RideRequest): Call<Ride>
}