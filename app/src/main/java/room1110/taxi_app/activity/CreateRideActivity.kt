package room1110.taxi_app.activity

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room1110.taxi_app.R
import room1110.taxi_app.api.ApiInterface
import room1110.taxi_app.api.APIBuilder
import room1110.taxi_app.data.Ride

class CreateRideActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    private val api: ApiInterface = APIBuilder.apiService
    private lateinit var curSize: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ride)

        // View Elements
        val seekBar: SeekBar = findViewById(R.id.sizeSeekBar)
        curSize = findViewById(R.id.curSizeText)

        // Listeners
        seekBar.setOnSeekBarChangeListener(this@CreateRideActivity)
    }

    // API Requests
    private fun createRideRequest() {
        api.createRide().enqueue(object : Callback<Ride> {
            override fun onFailure(call: Call<Ride>, t: Throwable) {
                val dialog: AlertDialog = this@CreateRideActivity.let {
                    AlertDialog.Builder(it)
                        .setMessage(t.message.toString())
                        .setTitle("Error")
                        .create()
                }
                dialog.show()
            }

            override fun onResponse(
                call: Call<Ride>,
                response: Response<Ride>
            ) {

            }
        })
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        // positions = 3 from 0 to 2
        val rideSize = p1 + 2
        val thumbImage = when (rideSize) {
            2 -> R.drawable.users_2
            3 -> R.drawable.users_3
            4 -> R.drawable.users_4
            else -> {
                throw Exception("Can be only 4 states in SeekBar")
            }
        }
        p0?.thumb = ContextCompat.getDrawable(this@CreateRideActivity, thumbImage)
        curSize.text = rideSize.toString()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }

}