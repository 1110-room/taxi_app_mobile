package room1110.taxi_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import room1110.taxi_app.R
import room1110.taxi_app.data.Ride
import java.time.format.DateTimeFormatter

class RideHistoryAdapter(private val rideList: List<Ride>, var listener: ItemListener) :
    RecyclerView.Adapter<RideHistoryAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val dtFrom: TextView = itemView.findViewById(R.id.dtFrom)
        private val dtTo: TextView = itemView.findViewById(R.id.dtTo)
        private val price: TextView = itemView.findViewById(R.id.ridePrice)
        private val membersCount: TextView = itemView.findViewById(R.id.rideMembersCount)
        private val status: TextView = itemView.findViewById(R.id.rideStatus)
        private val date: TextView = itemView.findViewById(R.id.rideDate)

        private val addressFrom: TextView = itemView.findViewById(R.id.rideAddressFrom)
        private val addressTo: TextView = itemView.findViewById(R.id.rideAddressTo)

        @SuppressLint("SetTextI18n")
        fun bind(ride: Ride, listener: ItemListener) {
            dtFrom.text = ride.getDtFrom()!!.format(DateTimeFormatter.ofPattern("HH:mm"))
            if (ride.getDtTo() == null) {
                dtTo.visibility = View.INVISIBLE
            } else {
                dtTo.text = ride.getDtTo()?.format(DateTimeFormatter.ofPattern("HH:mm"))
            }
            price.text = "${ride.price} ₽"
            membersCount.text = "${ride.getMembersCount()}/${ride.rideSize}"
            addressFrom.text = ride.addressFrom
            addressTo.text = ride.addressTo
            status.text = ride.status
            date.text = ride.getDtFrom()!!.format(DateTimeFormatter.ofPattern("dd.MM.yy"))

            itemView.setOnClickListener {
                listener.onClickItem(ride)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ride_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ride = rideList[position]
        holder.bind(ride, listener)
    }

    override fun getItemCount() = rideList.size

    interface ItemListener {
        fun onClickItem(ride: Ride)
    }

}
