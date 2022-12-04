package room1110.taxi_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import room1110.taxi_app.R
import room1110.taxi_app.data.Ride
import room1110.taxi_app.util.RideLineAdapter

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
    }
}