//package room1110.taxi_app.api.service
//
//import android.annotation.SuppressLint
//import android.widget.Toast
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import room1110.taxi_app.adapter.RideLineAdapter
//import room1110.taxi_app.data.RideSerialized
//import room1110.taxi_app.data.RideSerializer
//
//class UserApiService {
//    @SuppressLint("NotifyDataSetChanged")
//    private fun updateRideList() {
//        api.getRideList().enqueue(object : Callback<MutableList<RideSerialized>> {
//            override fun onFailure(call: Call<MutableList<RideSerialized>>, t: Throwable) {
//                Toast.makeText(
//                    this@RideLineActivity,
//                    t.message.toString(),
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//
//            override fun onResponse(
//                call: Call<MutableList<RideSerialized>>,
//                response: Response<MutableList<RideSerialized>>
//            ) {
//                adapter =
//                    RideLineAdapter((response.body() as MutableList<RideSerialized>).map { rideSerialized ->
//                        RideSerializer.deserialize(rideSerialized)
//                    }, this@RideLineActivity)
//                adapter.notifyDataSetChanged()
//                rcView.adapter = adapter
//            }
//        })
//    }
//}