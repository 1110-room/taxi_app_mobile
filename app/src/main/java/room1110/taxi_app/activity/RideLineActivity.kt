package room1110.taxi_app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room1110.taxi_app.R
import room1110.taxi_app.adapter.RideLineAdapter
import room1110.taxi_app.api.Common
import room1110.taxi_app.api.RideApiInterface
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.RideSerialized
import room1110.taxi_app.data.RideSerializer


class RideLineActivity : AppCompatActivity(), RideLineAdapter.ItemListener {

    lateinit var api: RideApiInterface
    lateinit var adapter: RideLineAdapter
    lateinit var rcView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_line)

        val refreshLayout: SwipeRefreshLayout = findViewById(R.id.refreshLayout)
        refreshLayout.setOnRefreshListener {
            updateRideList()
            Handler().postDelayed(Runnable {
                refreshLayout.isRefreshing = false
            }, 500)
        }

        rcView = findViewById(R.id.rideLineRC)
        rcView.layoutManager = LinearLayoutManager(this)
        api = Common.retrofitService
    }

    override fun onStart() {
        super.onStart()

        updateRideList()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRideList() {
        api.getRideList().enqueue(object : Callback<MutableList<Ride>> {
            override fun onFailure(call: Call<MutableList<Ride>>, t: Throwable) {
//                adapter = RideLineAdapter(
//                    listOf(
//                        Ride(
//                            1,
//                            90,
//                            1.0,
//                            User(1, "Max", "Abashin"),
//                            4,
//                            arrayListOf(User(11)),
//                            call.request().body().toString(),
//                            t.message.toString(),
//                            "OPEN",
//                            "yandex",
//                            LocalDateTime.now().toString(),
//                            LocalDateTime.now().toString()
//                        )
//                    )
//                )
//                adapter.notifyDataSetChanged()
//                rcView.adapter = adapter

                Toast.makeText(
                    this@RideLineActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<MutableList<Ride>>,
                response: Response<MutableList<Ride>>
            ) {
                adapter = RideLineAdapter(
                    response.body() as MutableList<Ride>,
                    this@RideLineActivity
                )
                adapter.notifyDataSetChanged()
                rcView.adapter = adapter
            }
        })
    }

    override fun onClickItem(ride: Ride?) {
//        Log.d("item clicked", ride.toString())
        val intent = Intent(this, RoomActivity::class.java).putExtra("ride", ride)
        startActivity(intent)
    }

}