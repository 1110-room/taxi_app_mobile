package room1110.taxi_app.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        Log.d("UserCard", user!!.cardNumber.toString())

        // Listeners
        button.setOnClickListener {
            if (user != null && user!!.cardNumber != null) {
                user!!.cardNumber = newCardNumber.text.toString()
                changeCardRequest(user!!)
                val intent = Intent(this@EditCardNumberActivity, ProfileActivity::class.java)
                startActivity(intent)
                this.finish()
            }
        }
    }
    private fun changeCardRequest(user: User) {
        api.changeCard(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                val dialog: AlertDialog = this@EditCardNumberActivity.let {
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
            ) {}
        })
    }
}