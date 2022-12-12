package room1110.taxi_app.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.ToggleButton
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

class CreateRideActivity : AppCompatActivity() {
    private final val taxiServices = arrayListOf<String>("yandex")

    private val api: ApiInterface = APIBuilder.apiService
    private lateinit var buttonService1: ToggleButton
    private lateinit var buttonService2: ToggleButton
    private lateinit var curSize: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ride)

        // View Elements
        val seekBar: SeekBar = findViewById(R.id.sizeSeekBar)
        buttonService1 = findViewById(R.id.service1)
        buttonService2 = findViewById(R.id.service2)
        curSize = findViewById(R.id.curSizeText)

        if (taxiServices.getOrNull(0) != null) {
            buttonService1.text = taxiServices[0]
        } else {
            buttonService1.visibility = View.GONE
        }
        if (taxiServices.getOrNull(1) != null) {
            buttonService2.text = taxiServices[1]
        } else {
            buttonService2.visibility = View.GONE
        }

        // Listeners
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, position: Int, p2: Boolean) {
                // positions = 3 from 0 to 2
                val rideSize = position + 2
                val thumbImage = when (rideSize) {
                    2 -> R.drawable.users_2
                    3 -> R.drawable.users_3
                    4 -> R.drawable.users_4
                    else -> {
                        throw Exception("Can be only 4 states in SeekBar")
                    }
                }
                seekBar?.thumb = ContextCompat.getDrawable(this@CreateRideActivity, thumbImage)
                curSize.text = rideSize.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        buttonService1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { button, checked ->
            if (checked)
                buttonService2.isChecked = false
            else if (!buttonService2.isChecked)
                button.isChecked = true
        })

        buttonService2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { button, checked ->
            if (checked)
                buttonService1.isChecked = false
            else if (!buttonService1.isChecked)
                button.isChecked = true
        })
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
            ) {}
        })
    }

}