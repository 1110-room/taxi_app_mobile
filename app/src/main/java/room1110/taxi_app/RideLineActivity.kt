package room1110.taxi_app

import room1110.taxi_app.data.Ride
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class RideLineActivity : AppCompatActivity() {
    private val adapter = RideLineAdapter()
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_line)

        init()
//        rideLineView = findViewById(R.id.rideLineView)
//        adapter = ArrayAdapter<String?>(
//            this,
//            android.R.layout.list_content,
//            rideList as List<String?>
//        )
//        rideLineView.adapter = adapter
    }

    private fun init() {
        val rcView = findViewById<RecyclerView>(R.id.rideLineRC)
        rcView.adapter = adapter
        if(index > 4) index = 0
        val ride = Ride(index, 350, 3.5)
        adapter.addPlant(ride)
        index++
    }

//    override fun onStart() {
//        super.onStart()
//        addRideInLine("Elem1")
//        addRideInLine("room1110.taxi_app.data.Ride 2")
//    }

}