package room1110.taxi_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import room1110.taxi_app.R
import room1110.taxi_app.data.Ride
import room1110.taxi_app.databinding.ActivityMainBinding

class RoomActivity : AppCompatActivity() {
    private lateinit var addressTo: TextView
    private lateinit var addressFrom: TextView
    private lateinit var members: TextView
    private lateinit var price: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        addressFrom = findViewById(R.id.roomAddressFrom)
        addressTo = findViewById(R.id.roomAddressTo)
        members = findViewById(R.id.members)
        price = findViewById(R.id.roomPrice)
        val ride = intent.getSerializableExtra("ride") as Ride?
        if (ride != null){
            addressTo.text = ride.addressTo
            addressFrom.text = ride.addressFrom
            members.text = "${ride.membersCount}/${ride.rideSize}"
            price.text = ride.price.toString()
        }


    }
}