package room1110.taxi_app

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Profile : AppCompatActivity() {
    private val color = "#FFB300"
    private lateinit var profileText: TextView
    private lateinit var cardNumber: TextView
    private lateinit var avatar: ImageView
//    private lateinit var star: ImageView
    private lateinit var logoutButton: Button
    private lateinit var changeCardNumberButton: Button
    private lateinit var avgReview: TextView
//    private var avgReview: Float = 0f

    @SuppressLint("MissingInflatedId")
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

        profileText.text = "Гарифуллин Дамир"

        cardNumber.text = 1234567890123456.toString()

        avatar.setImageResource(R.drawable.my_avatar)
    }

    override fun onStart() {
        super.onStart()
        // TODO
        // из всех отзывов оставленных этому юзеру считаем средний
        avgReview = findViewById(R.id.avgReview)
        var review = 1.5f
        avgReview.text = "Средняя оценка: $review"
    }



}

