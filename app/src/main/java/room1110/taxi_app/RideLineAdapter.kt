package room1110.taxi_app

import room1110.taxi_app.data.Ride
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RideLineAdapter: RecyclerView.Adapter<RideLineAdapter.ViewHolder>() {
    private val rideList = ArrayList<Ride>()
    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = item.findViewById(R.id.rideText)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.ride_line_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = rideList[position].toString()
    }

    override fun getItemCount(): Int {
        return rideList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addPlant(ride: Ride){
        rideList.add(ride)
        notifyDataSetChanged()
    }
}