package room1110.taxi_app.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
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
    private val taxiServices = arrayListOf("yandex")

    private lateinit var api: ApiInterface
    private lateinit var seekBar: SeekBar
    private lateinit var addressFrom: EditText
    private lateinit var addressTo: EditText
    private lateinit var buttonService1: ToggleButton
    private lateinit var buttonService2: ToggleButton
    private lateinit var curSize: TextView
    private lateinit var finalCreateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ride)

        api = APIBuilder(baseContext).apiService

        // View Elements
        seekBar = findViewById(R.id.sizeSeekBar)

        addressFrom = findViewById(R.id.addressFromEditText)
        addressTo = findViewById(R.id.addressToEditText)

        buttonService1 = findViewById(R.id.service1)
        buttonService2 = findViewById(R.id.service2)

        curSize = findViewById(R.id.curSizeText)
        finalCreateButton = findViewById(R.id.finalCreateButton)

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

        addressFrom.validate("Укажите адрес") { s -> s.isValidAddress() }
        addressTo.validate("Укажите адрес") { s -> s.isValidAddress() }

        finalCreateButton.setOnClickListener {
            if (addressFrom.error == null && addressTo.error == null) {
                val service = if (buttonService1.isChecked) taxiServices[0] else taxiServices[1]
                val newRide = RideRequest(
                    User(1),
                    seekBar.progress + 2,
                    addressFrom.text.toString(),
                    addressTo.text.toString(),
                    service
                )
                createRideRequest(newRide)
            }
        }
    }

    // API Requests
    private fun createRideRequest(newRide: RideRequest) {
        api.createRide(newRide).enqueue(object : Callback<Ride> {
            override fun onFailure(call: Call<Ride>, t: Throwable) {
                Log.e("createRide", "Failure Create Ride: " + t.message.toString())
                createErrorAlert("Проверьте введённые данные.")
            }

            override fun onResponse(
                call: Call<Ride>,
                response: Response<Ride>
            ) {
                if (response.isSuccessful) {
                    val ride = response.body() as Ride
                    val intent = Intent(
                        this@CreateRideActivity,
                        RideActivity::class.java
                    ).putExtra("ride", ride)
                    startActivity(intent)
                    this@CreateRideActivity.finish()
                } else {
                    Log.e("createRide", "Response 400 bad request: " + response.raw())
                    createErrorAlert("Проверьте введённые данные.")
                }
            }
        })
    }

    fun EditText.validate(message: String, validator: (String) -> Boolean) {
        this.doAfterTextChanged {
            this.error = if (validator(it.toString())) null else message
        }
        this.error = if (validator(this.text.toString())) null else message
    }

    private fun String.isValidAddress(): Boolean {
        val pattern = Regex("^[а-яА-Я0-9,.\\s]+$")
        return pattern.containsMatchIn(this)
    }

    private fun createErrorAlert(msg: String) {
        this@CreateRideActivity.let {
            AlertDialog.Builder(it)
                .setMessage(msg)
                .setTitle("Ошибка!")
                .create()
        }.show()
    }
}