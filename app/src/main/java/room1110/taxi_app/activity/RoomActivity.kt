package room1110.taxi_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import room1110.taxi_app.R
import room1110.taxi_app.data.Ride
import room1110.taxi_app.databinding.ActivityMainBinding

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        val ride = intent.getSerializableExtra("ride") as Ride
        val text = findViewById<TextView>(R.id.Textt)
        text.text = ride.toString()

    }
}