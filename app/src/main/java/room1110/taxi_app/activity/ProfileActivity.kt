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
import room1110.taxi_app.R
import room1110.taxi_app.data.User

class ProfileActivity : AppCompatActivity() {
    private val color = "#FFB300"
    private lateinit var profileText: TextView
    private lateinit var cardNumber: TextView
    private lateinit var avatar: ImageView
//    private lateinit var star: ImageView
    private lateinit var logoutButton: Button
    private lateinit var changeCardNumberButton: Button
    private lateinit var avgReview: TextView
    private var user = User()
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

        user.name = "Дамир"
        user.surname = "Гарифуллин"
        user.cardNumber = "1234567890123456"

        profileText.text = user.name + " " + user.surname

        cardNumber.text = user.cardNumber

        avatar.setImageResource(R.drawable.my_avatar)
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



}

