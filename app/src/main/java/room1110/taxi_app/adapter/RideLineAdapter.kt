package room1110.taxi_app.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import room1110.taxi_app.R
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.User
import room1110.taxi_app.util.AvatarConvert.editAvatarBitmap
import java.time.format.DateTimeFormatter

class RideLineAdapter(private val rideList: List<Ride>, var listener: ItemListener) :
    RecyclerView.Adapter<RideLineAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val dtFrom: TextView = itemView.findViewById(R.id.dtFrom)
        private val price: TextView = itemView.findViewById(R.id.ridePrice)
        private val membersCount: TextView = itemView.findViewById(R.id.rideMembersCount)
        private val status: TextView = itemView.findViewById(R.id.rideStatus)

        private val addressFrom: TextView = itemView.findViewById(R.id.rideAddressFrom)
        private val addressTo: TextView = itemView.findViewById(R.id.rideAddressTo)

        private val owner: ImageView = itemView.findViewById(R.id.rideOwner)
        private val member1: ImageView = itemView.findViewById(R.id.rideMember1)
        private val member2: ImageView = itemView.findViewById(R.id.rideMember2)
        private val member3: ImageView = itemView.findViewById(R.id.rideMember3)
        private val members = arrayListOf(member1, member2, member3)

        @SuppressLint("SetTextI18n")
        fun bind(ride: Ride, listener: ItemListener) {
            dtFrom.text = ride.getDtFrom()!!.format(DateTimeFormatter.ofPattern("HH:mm"))
            if (ride.price == 0) {
                price.visibility = View.GONE
            } else {
                price.text = "${ride.price} ₽"
            }
            membersCount.text = "${ride.getMembersCount()}/${ride.rideSize}"
            addressFrom.text = ride.addressFrom
            addressTo.text = ride.addressTo
            status.text = ride.status

            ride.owner?.let { editAvatarBitmap(owner, it) }

            for ((i, member) in members.withIndex()) {
                ride.members?.getOrNull(i)?.let { editAvatarBitmap(member, it) }
            }

            // доделать динамическое кол-во members
            // member1.text = ride.members.

            itemView.setOnClickListener {
                listener.onClickItem(ride)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ride_line_item, parent, false)
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
