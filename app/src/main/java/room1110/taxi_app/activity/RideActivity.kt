package room1110.taxi_app.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import room1110.taxi_app.R
import room1110.taxi_app.data.Ride
import room1110.taxi_app.util.AvatarConvert.editAvatarBitmap

class RideActivity : AppCompatActivity() {
    private lateinit var addressTo: TextView
    private lateinit var addressFrom: TextView
    private lateinit var price: TextView
    private lateinit var size: TextView
    private lateinit var status: TextView
    private lateinit var service: TextView

    private lateinit var owner: ImageView
    private lateinit var member1: ImageView
    private lateinit var member2: ImageView
    private lateinit var member3: ImageView
    private lateinit var members: ArrayList<ImageView>

    private lateinit var ownerName: TextView
    private lateinit var memberName1: TextView
    private lateinit var memberName2: TextView
    private lateinit var memberName3: TextView
    private lateinit var membersNames: ArrayList<TextView>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride)

        // View Elements
        addressFrom = findViewById(R.id.rideAddressFrom)
        addressTo = findViewById(R.id.rideAddressTo)
        price = findViewById(R.id.ridePrice)
        size = findViewById(R.id.rideMembersCount)
        status = findViewById(R.id.rideStatus)
        service = findViewById(R.id.rideService)

        owner = findViewById(R.id.rideOwner)
        member1 = findViewById(R.id.rideMember1)
        member2 = findViewById(R.id.rideMember2)
        member3 = findViewById(R.id.rideMember3)
        members = arrayListOf(member1, member2, member3)

        ownerName = findViewById(R.id.rideOwnerName)
        memberName1 = findViewById(R.id.rideMemberName1)
        memberName2 = findViewById(R.id.rideMemberName2)
        memberName3 = findViewById(R.id.rideMemberName3)
        membersNames = arrayListOf(memberName1, memberName2, memberName3)

        ownerName.visibility = View.INVISIBLE
        for (name in membersNames) {
            name.visibility = View.INVISIBLE
        }
    }

    override fun onStart() {
        super.onStart()

        val ride = intent.getSerializableExtra("ride") as Ride?
        if (ride != null){
            addressFrom.text = ride.addressFrom
            addressTo.text = ride.addressTo
            price.text = "${ride.price} ₽"
            size.text = "${ride.getMembersCount()}/${ride.rideSize}"
            status.text = ride.status
            service.text = ride.taxiService


            ride.owner?.let {
                editAvatarBitmap(owner, it)
                ownerName.text = it.name
                ownerName.visibility = View.VISIBLE
            }

            for ((i, member) in members.withIndex()) {
                ride.members?.getOrNull(i)?.let {
                    membersNames[i].text = it.name
                    membersNames[i].visibility = View.VISIBLE
                    editAvatarBitmap(member, it)
                }
            }
        }
    }

}