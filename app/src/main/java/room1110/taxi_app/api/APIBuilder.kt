package room1110.taxi_app.api

import android.content.Context
import room1110.taxi_app.R

class APIBuilder(context: Context) {
    private val BASE_URL = "http://${context.getString(R.string.API_IP)}:8080/"
    val apiService: ApiInterface
        get() {
            return RetrofitBuilder.generateConverter(BASE_URL).create(ApiInterface::class.java)
        }
}