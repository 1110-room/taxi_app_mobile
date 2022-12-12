package room1110.taxi_app.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room1110.taxi_app.R
import room1110.taxi_app.api.APIBuilder
import room1110.taxi_app.api.ApiInterface
import room1110.taxi_app.data.Ride
import room1110.taxi_app.data.User

class EditCardNumberActivity : AppCompatActivity() {
    private lateinit var api: ApiInterface
    var user: User? = null
    lateinit var newCardNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cardnumber)

        api = APIBuilder(baseContext).apiService

        // View Elements
        var button = findViewById<Button>(R.id.saveChangesButton)
        button.setBackgroundColor(Color.parseColor("#FFB300"))
        newCardNumber = findViewById(R.id.newCard)
        user = intent.getSerializableExtra("user") as User?

        // Listeners
        button.setOnClickListener {
            if (user != null) {
                user!!.cardNumber = newCardNumber.text.toString()
                api.changeCard(user)
                val intent = Intent(this@EditCardNumberActivity, ProfileActivity::class.java)
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
//    private fun changeCardRequest() {
//        api.createRide().enqueue(object : Callback<User> {
//            override fun onFailure(call: Call<Ride>, t: Throwable) {
//                val dialog: AlertDialog = this@EditCardNumberActivity.let {
//                    AlertDialog.Builder(it)
//                        .setMessage(t.message.toString())
//                        .setTitle("Error")
//                        .create()
//                }
//                dialog.show()
//            }
//
//            override fun onResponse(
//                call: Call<Ride>,
//                response: Response<Ride>
//            ) {}
//        })
//    }
}