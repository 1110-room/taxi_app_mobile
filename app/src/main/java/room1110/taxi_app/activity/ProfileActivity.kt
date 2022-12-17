package room1110.taxi_app.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.*
import room1110.taxi_app.R
import room1110.taxi_app.adapter.RideHistoryAdapter
import room1110.taxi_app.api.APIBuilder
import room1110.taxi_app.api.ApiInterface
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.User

class ProfileActivity : AppCompatActivity(), RideHistoryAdapter.ItemListener {
    private lateinit var api: ApiInterface
    private lateinit var adapter: RideHistoryAdapter
    private lateinit var rcView: RecyclerView

    private val color = "#FFB300"
    private lateinit var profileText: TextView
    private lateinit var cardNumber: TextView
    private lateinit var avatar: ImageView
    private lateinit var logoutButton: Button
    private lateinit var changeCardNumberButton: Button
    private lateinit var avgReview: TextView
    private var user = User()

    private lateinit var startForResult: ActivityResultLauncher<Intent>


    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        api = APIBuilder(baseContext).apiService

        // View Elements
        val refreshLayout: SwipeRefreshLayout = findViewById(R.id.historyRefreshLayout)

        rcView = findViewById(R.id.historyRC)
        rcView.layoutManager = LinearLayoutManager(this)

        profileText = findViewById(R.id.profileName)
        cardNumber = findViewById(R.id.cardNumber)
        avatar = findViewById(R.id.profileAvatar)
        logoutButton = findViewById(R.id.logoutButton)
        changeCardNumberButton = findViewById(R.id.changeCardNumberButton)

        logoutButton.setBackgroundColor(Color.parseColor(color))
        changeCardNumberButton.setBackgroundColor(Color.parseColor(color))

        // Listeners
        refreshLayout.setOnRefreshListener {
            updateRideHistory()
            Handler().postDelayed({
                refreshLayout.isRefreshing = false
            }, 500)
        }

        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val newCardNumber = data?.getStringExtra("newCardNumber")

                    newCardNumber?.let {
                        cardNumber.text = newCardNumber
                        cardNumber.invalidate()
                    } ?: run {
                        val dialog: AlertDialog = this@ProfileActivity.let {
                            AlertDialog.Builder(it)
                                .setMessage("Error updating card number! Refresh the page.")
                                .setTitle("Error")
                                .create()
                        }
                        dialog.show()
                    }

                }
            }
    }

    override fun onStart() {
        super.onStart()
        // из всех отзывов оставленных этому юзеру считаем средний
        getUserById(1)
        avgReview = findViewById(R.id.avgReview)
        val review = 1.5f
        avgReview.text = "Средняя оценка: $review"

        updateRideHistory()
    }


    // API Requests
    private fun updateRideHistory() {
        // Get User Id from Auth User
        val userId: Long = 1

        api.getUserRideHistory(userId).enqueue(object : Callback<MutableList<Ride>> {
            override fun onFailure(call: Call<MutableList<Ride>>, t: Throwable) {
                val dialog: AlertDialog = this@ProfileActivity.let {
                    AlertDialog.Builder(it)
                        .setMessage(t.message.toString())
                        .setTitle("Error")
                        .create()
                }
                dialog.show()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<MutableList<Ride>>,
                response: Response<MutableList<Ride>>
            ) {
                adapter = RideHistoryAdapter(
                    response.body() as MutableList<Ride>,
                    this@ProfileActivity
                )
                adapter.notifyDataSetChanged()
                rcView.adapter = adapter
            }
        })
    }

    // Getting user from db
    @SuppressLint("NotifyDataSetChanged")
    private fun getUserById(id: Long) {
        api.getUserById(id).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                val dialog: AlertDialog = this@ProfileActivity.let {
                    AlertDialog.Builder(it)
                        .setMessage(t.message.toString())
                        .setTitle("Error")
                        .create()
                }
                dialog.show()
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                user = response.body() as User
                profileText.text = user.name + " " + user.surname
                cardNumber.text = user.cardNumber
                editAvatarBitmap(avatar, user)

                changeCardNumberButton.setOnClickListener {
                    startForResult.launch(
                        Intent(
                            this@ProfileActivity,
                            EditCardNumberActivity::class.java
                        ).putExtra("user", user)
                    )
                }
            }
        })
    }

    private fun editAvatarBitmap(userView: ImageView, user: User?) {
        if (user != null) {
            val avatarBytes = user.getAvatar()
            if (avatarBytes != null) {
                userView.setImageBitmap(byteArrayToBitmap(avatarBytes))
            }
        }
    }

    private fun byteArrayToBitmap(data: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }

    override fun onClickItem(ride: Ride) {
        // set RideArchivedActivity
        val intent = Intent(
            this@ProfileActivity, RideActivity::class.java
        ).putExtra("ride", ride)

        startActivity(intent)
    }
}

