package room1110.taxi_app.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import room1110.taxi_app.R
import room1110.taxi_app.data.Ride

class RideActivity : AppCompatActivity() {
    private lateinit var addressTo: TextView
    private lateinit var addressFrom: TextView
    private lateinit var members: TextView
    private lateinit var price: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        addressFrom = findViewById(R.id.roomAddressFrom)
        addressTo = findViewById(R.id.roomAddressTo)
        members = findViewById(R.id.members)
        price = findViewById(R.id.roomPrice)
        val ride = intent.getSerializableExtra("ride") as Ride?
        if (ride != null){
            addressFrom.text = ride.addressFrom
            addressTo.text = ride.addressTo
            members.text = "${ride.getMembersCount()}/${ride.rideSize}"
            price.text = "${ride.price} ₽"
        }
    }
    
}