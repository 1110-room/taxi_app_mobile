package room1110.taxi_app.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.User

interface ApiInterface {

    // ----- GET -----
    @Headers("Accept: application/json")
    @GET("ride/open-line")
    fun getRideList(): Call<MutableList<Ride>>

    @Headers("Accept: application/json")
    @GET("users/{id}")
    fun fwtUser(): Call<User>


    // ----- POST -----
    @Headers("Content-type: application/json")
    @POST("ride/create")
    fun createRide(): Call<Ride>
}