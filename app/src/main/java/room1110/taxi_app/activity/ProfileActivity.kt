package room1110.taxi_app.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room1110.taxi_app.R
import room1110.taxi_app.api.APIBuilder
import room1110.taxi_app.api.ApiInterface
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.User

class ProfileActivity : AppCompatActivity() {
    private val api: ApiInterface = APIBuilder.apiService

    private val color = "#FFB300"
    private lateinit var profileText: TextView
    private lateinit var cardNumber: TextView
    private lateinit var avatar: ImageView
//    private lateinit var star: ImageView
    private lateinit var logoutButton: Button
    private lateinit var changeCardNumberButton: Button
    private lateinit var avgReview: TextView
    private lateinit var user: User
//    lateinit var responseUser: User

//    private var avgReview: Float = 0f

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileText = findViewById(R.id.profileInfo)
        cardNumber = findViewById(R.id.cardNumber)
        avatar = findViewById(R.id.avatar)
        logoutButton = findViewById(R.id.logoutButton)
        changeCardNumberButton = findViewById(R.id.changeCardNumber)


        logoutButton.setBackgroundColor(Color.parseColor(color))
        changeCardNumberButton.setBackgroundColor(Color.parseColor(color))
        user = User()
        getUserById(2)

        // Listeners
    }


    override fun onStart() {
        super.onStart()
        // TODO
        // из всех отзывов оставленных этому юзеру считаем средний
        avgReview = findViewById(R.id.avgReview)
        val review = 1.5f
        avgReview.text = "Средняя оценка: $review"
    }

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
            ){
                user = response.body() as User
//                user.id = responseUser.id
//                user.cardNumber = responseUser.cardNumber
                profileText.text = user.name + " " + user.surname

                cardNumber.text = user.cardNumber

                editAvatarBitmap(avatar, user)
//                Log.d("user", user.toString())
                changeCardNumberButton.setOnClickListener {
//                    Log.d("user", user.toString())
                    val intent = Intent(this@ProfileActivity, EditCardNumberActivity::class.java).putExtra("user", user)
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
    fun onClickItem(user: User) {
        val intent = Intent(this, RoomActivity::class.java).putExtra("user", user)
        startActivity(intent)
    }
}

