package room1110.taxi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import room1110.taxi_app.data.Ride

class RideLineAdapter(private val rideList: ArrayList<Ride>): RecyclerView.Adapter<RideLineAdapter.RideViewHolder>()
{
    class RideViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val dtFrom: TextView = itemView.findViewById(R.id.dtFrom)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val membersCount: TextView = itemView.findViewById(R.id.membersCount)

        private val addressFrom: TextView = itemView.findViewById(R.id.addressFrom)
        private val addressTo: TextView = itemView.findViewById(R.id.addressTo)

        private val owner: TextView = itemView.findViewById(R.id.owner)
        private val member1: TextView = itemView.findViewById(R.id.member1)
        private val member2: TextView = itemView.findViewById(R.id.member2)
        private val member3: TextView = itemView.findViewById(R.id.member3)

        fun bind(ride: Ride) {
            dtFrom.text = ride.dtFrom.toString()
            price.text = ride.price.toString()
            membersCount.text = ride.members.size.toString()
            addressFrom.text = ride.addressFrom
            addressTo.text = ride.addressTo
            owner.text = ride.owner.toString()
            // доделать динамическое кол-во members
//            member1.text = ride.members.
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ride_line_item, parent, false)
        return RideViewHolder(view)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride = rideList[position]
        holder.bind(ride)
    }

    override fun getItemCount() = rideList.size
}
