package room1110.taxi_app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room1110.taxi_app.R
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

    //    private lateinit var star: ImageView
    //    private var avgReview: Float = 0f

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        api = APIBuilder(baseContext).apiService

        // View Elements
        val refreshLayout: SwipeRefreshLayout = findViewById(R.id.refreshLayout)

        rcView = findViewById(R.id.historyRC)
        rcView.layoutManager = LinearLayoutManager(this)

        profileText = findViewById(R.id.profileInfo)
        cardNumber = findViewById(R.id.cardNumber)
        avatar = findViewById(R.id.avatar)
        logoutButton = findViewById(R.id.logoutButton)
        changeCardNumberButton = findViewById(R.id.changeCardNumber)


        logoutButton.setBackgroundColor(Color.parseColor(color))
        changeCardNumberButton.setBackgroundColor(Color.parseColor(color))
        user = User()
        getUserById(2)

        avatar.setImageResource(R.drawable.my_avatar)

        // Listeners
        refreshLayout.setOnRefreshListener {
            updateRideHistory()
            Handler().postDelayed({
                refreshLayout.isRefreshing = false
            }, 500)
        }
    }

    override fun onStart() {
        super.onStart()
        // TODO
        // из всех отзывов оставленных этому юзеру считаем средний
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

    override fun onClickItem(ride: Ride) {
        // set RideArchivedActivity
        val intent = Intent(
            this@ProfileActivity, RideActivity::class.java
        ).putExtra("ride", ride)

//    fun changeCardNumberClick(user: User){
//        val intent = Intent(this, EditCardNumberActivity::class.java).putExtra("user", user)
//        startActivity(intent)
//    }


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
//                user.id = responseUser.id
//                user.cardNumber = responseUser.cardNumber
                    profileText.text = user.name + " " + user.surname

                    cardNumber.text = user.cardNumber

                    editAvatarBitmap(avatar, user)
//                Log.d("user", user.toString())
                    changeCardNumberButton.setOnClickListener {
//                    Log.d("user", user.toString())
                        val intent = Intent(
                            this@ProfileActivity,
                            EditCardNumberActivity::class.java
                        ).putExtra("user", user)
                        startActivity(intent)
                    }
//                Log.d("user", user.toString())
                }
            })
        }

        private fun editAvatarBitmap(userView: ImageView, user: User?) {
            if (user != null) {
                val avatarBytes = user.getAvatar()
                if (avatarBytes != null) {
                    userView.setImageBitmap(byteArrayToBitmap(avatarBytes))
//                owner.setPadding(5)
//                owner.setBackgroundColor(Color.BLACK)
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

