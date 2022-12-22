package room1110.taxi_app.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import room1110.taxi_app.R
import room1110.taxi_app.api.APIBuilder
import room1110.taxi_app.api.ApiInterface
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.User
import room1110.taxi_app.util.AvatarConvert.editAvatarBitmap
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

class RideActivity : AppCompatActivity() {
    private lateinit var api: ApiInterface
    private lateinit var user: User
    private var ride: Ride? = null

    private lateinit var activeButton: Button

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

        api = APIBuilder(baseContext).apiService

        // View Elements
        activeButton = findViewById(R.id.rideActiveButton)

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

        for (name in membersNames) {
            name.visibility = View.INVISIBLE
        }

        // Listeners
        activeButton.setOnClickListener {
            when (user.rideStatus) {
                0 -> {
                    user.rideStatus = 1
                    ride?.members?.let {
                        it.add(user)
                    } ?: run {
                        ride?.members = arrayListOf(user)
                    }
                    // update user & ride request (web-socket)
                }
                1 -> {
                    if (ride?.getMembersCount() == ride?.rideSize) {
                        user.ready = !user.ready
                        // update user request (web-socket)
                    }
                }
            }

            invalidate()
        }
    }

    override fun onStart() {
        super.onStart()

        getUser(1)
        ride = intent.getSerializableExtra("ride") as Ride?

        invalidate()
    }

    private fun invalidate() {
        ride?.let { ride ->
            invalidateActiveButton(ride)
            invalidateText(ride)
            invalidateMembers(ride)
        }
    }

    private fun invalidateActiveButton(ride: Ride) {
        when (user.rideStatus) {
            0 -> activeButton.text = "Присоединиться"
            1 -> {
                val readyText = if (user.ready) "Не готов" else "Готов"
                if (ride.getMembersCount() == ride.rideSize)
                    activeButton.text = readyText
                else
                    activeButton.text = "Ожидание"
            }
        }
    }

    private fun invalidateText(ride: Ride) {
        addressFrom.text = ride.addressFrom
        addressTo.text = ride.addressTo
        price.text = "${ride.price} ₽"
        size.text = "${ride.getMembersCount()}/${ride.rideSize}"
        status.text = ride.status
        service.text = ride.taxiService.uppercase()
    }

    private fun invalidateMembers(ride: Ride) {
        ride.owner?.let {
            editAvatarBitmap(owner, it)
            ownerName.text = it.name
        }

        for ((i, member) in members.withIndex()) {
            ride.members?.getOrNull(i)?.let {
                membersNames[i].text = it.name
                membersNames[i].visibility = View.VISIBLE
                member.visibility = View.VISIBLE
                editAvatarBitmap(member, it)
            } ?: run {
                members[i].visibility = View.GONE
                member.visibility = View.GONE
            }
        }
    }

    // API Requests
    private fun getUser(id: Long) = runBlocking(Dispatchers.IO) {
        try {
            val response = api.getUserById(id).execute()
            if (response.isSuccessful) {
                user = response.body() as User
            } else {
                val dialog: AlertDialog = this@RideActivity.let {
                    AlertDialog.Builder(it)
                        .setMessage("Ошибка получения пользователя! Обратитесь в поддержку.")
                        .setTitle("Error")
                        .create()
                }
                dialog.show()
            }
        } catch (e: Throwable) {
            Log.e("api_error", "Error get user: ${e.message}")
        }
    }

}