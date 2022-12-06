package room1110.taxi_app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room1110.taxi_app.R
import room1110.taxi_app.adapter.RideLineAdapter
import room1110.taxi_app.api.Common
import room1110.taxi_app.api.RideApiInterface
import room1110.taxi_app.data.RideSerialized
import room1110.taxi_app.data.RideSerializer
import room1110.taxi_app.data.User

class ProfileActivity : AppCompatActivity() {
    private val api: RideApiInterface = Common.retrofitService

    private val color = "#FFB300"
    private lateinit var profileText: TextView
    private lateinit var cardNumber: TextView
    private lateinit var avatar: ImageView
//    private lateinit var star: ImageView
    private lateinit var logoutButton: Button
    private lateinit var changeCardNumberButton: Button
    private lateinit var avgReview: TextView
    private lateinit var user: User
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
        getUserById(1)
//        user.name = "Дамир"
//        user.surname = "Гарифуллин"
//        user.cardNumber = "1234567890123456"
    }


    override fun onStart() {
        super.onStart()
        // TODO
        // из всех отзывов оставленных этому юзеру считаем средний
        avgReview = findViewById(R.id.avgReview)
        val review = 1.5f
        avgReview.text = "Средняя оценка: $review"
    }

    fun changeCardNumberClick(view: View){
        val intent = Intent(this, EditCardNumberActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getUserById(id: Long) {
        api.getUserById(id).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(
                    this@ProfileActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ){
                user = response.body() as User
//                profileText.text = user.name + " " + user.surname

                cardNumber.text = user.toString()

//                avatar.setImageResource(R.drawable.my_avatar)
            }
        })
    }



}

