package room1110.taxi_app.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.User

interface ApiInterface {

    // ----- GET -----
    @Headers("Accept: application/json")
    @GET("ride/open-line")
    fun getRideList(): Call<MutableList<Ride>>

    @Headers("Accept: application/json")
    @GET("users/{id}")
    fun getUserById(@Path("id") id: Long): Call<User>


    // ----- POST -----
    @Headers("Content-type: application/json")
    @POST("ride/create")
    fun createRide(): Call<Ride>

    @Headers("Content-type: application/json")
    @POST("users/change-card")
    fun changeCard(@Body user: User?): Response<User>
}