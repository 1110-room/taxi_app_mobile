package room1110.taxi_app.api

object APIBuilder {
    private const val BASE_URL = "http://192.168.31.248:8080/"
    val apiService: ApiInterface
        get() = RetrofitBuilder.generateConverter(BASE_URL).create(ApiInterface::class.java)
}