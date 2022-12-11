package room1110.taxi_app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room1110.taxi_app.R
import room1110.taxi_app.adapter.RideLineAdapter
import room1110.taxi_app.api.ApiInterface
import room1110.taxi_app.api.APIBuilder
import room1110.taxi_app.data.Ride


class RideLineActivity : AppCompatActivity(), RideLineAdapter.ItemListener {
    // Global vars
    private val api: ApiInterface = APIBuilder.apiService
    lateinit var adapter: RideLineAdapter
    lateinit var rcView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_line)

        // View Elements
        val refreshLayout: SwipeRefreshLayout = findViewById(R.id.refreshLayout)
        val createRideButton: Button = findViewById(R.id.createRide)

        rcView = findViewById(R.id.rideLineRC)
        rcView.layoutManager = LinearLayoutManager(this)
        /////////////////

        // Listeners
        createRideButton.setOnClickListener {
            val intent = Intent(this, CreateRideActivity::class.java)
            startActivity(intent)
        }

        refreshLayout.setOnRefreshListener {
            updateRideList()
            Handler().postDelayed(Runnable {
                refreshLayout.isRefreshing = false
            }, 500)
        }
        /////////////////
    }

    override fun onStart() {
        super.onStart()
        updateRideList()
    }

    // API Requests
    private fun updateRideList() {
        api.getRideList().enqueue(object : Callback<MutableList<Ride>> {
            override fun onFailure(call: Call<MutableList<Ride>>, t: Throwable) {
                /*
                adapter = RideLineAdapter(
                    listOf(
                        Ride(
                            1,
                            90,
                            1.0,
                            User(1, "Max", "Abashin"),
                            4,
                            arrayListOf(User(11)),
                            call.request().body().toString(),
                            t.message.toString(),
                            "OPEN",
                            "yandex",
                            LocalDateTime.now().toString(),
                            LocalDateTime.now().toString()
                        )
                    )
                )
                adapter.notifyDataSetChanged()
                rcView.adapter = adapter
                */

                val dialog: AlertDialog = this@RideLineActivity.let {
                    AlertDialog.Builder(it)
                        .setMessage(t.message.toString())
                        .setTitle("Error")
                        .create()
                }
                dialog.show()
            }

            @SuppressLint("NotifyDataSetChanged")
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

    // Ride Item OnClick
    override fun onClickItem(ride: Ride) {
        val intent = Intent(this, RideActivity::class.java).putExtra("ride", ride)
        startActivity(intent)
    }
}