package room1110.taxi_app.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import room1110.taxi_app.data.Ride
import retrofit2.http.*
import room1110.taxi_app.data.RideRequest
import room1110.taxi_app.data.User

interface ApiInterface {

    // ----- GET -----
    @GET("ride/open-line")
    @Headers("Accept: application/json")
    fun getRideList(): Call<MutableList<Ride>>

    @GET("users")
    fun getUserById(@Query("id") id: Long): Call<User>

    @GET("users/vk")
    fun getUserByVkId(@Query("id") vkId: Long): Call<User>

    @GET("ride/history/{user_id}")
    @Headers("Accept: application/json")
    fun getUserRideHistory(@Path("user_id") userId: Long): Call<MutableList<Ride>>


    // ----- POST -----
    @POST("ride/create")
    @Headers("Content-type: application/json")
    // do suspend (async)
    fun createRide(@Body newRide: RideRequest): Call<Ride>

    @PUT("users/change-card")
    @Headers("Content-type: application/json")
    fun changeCard(@Body user: User): Call<User>
}