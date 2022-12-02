package room1110.taxi_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import room1110.taxi_app.data.Ride
import java.util.*


class RideLineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_line)

        val rideList = arrayListOf<Ride>()
        rideList.add(Ride(1, 350, 2.5, "Кремлевская 11", "Деревня Универсиады 5", "OPEN", "yandex", Date(), Date(), arrayListOf(1, 2, 3), 1))
        rideList.add(Ride(1, 111, 1.5, "Кремлевская 35", "Деревня Универсиады 1", "FULL", "yandex", Date(), Date(), arrayListOf(2, 2, 4), 4))
        rideList.add(Ride(1, 90, 1.0, "Кольцо", "Дубравная 11", "OPEN", "yandex", Date(), Date(), arrayListOf(11, 8, 4), 6))

        val recyclerView: RecyclerView = findViewById(R.id.rideLineRC)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RideLineAdapter(rideList)
    }

}