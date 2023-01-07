package room1110.taxi_app.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room1110.taxi_app.R
import room1110.taxi_app.api.APIBuilder
import room1110.taxi_app.api.ApiInterface
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
        val button = findViewById<Button>(R.id.saveCardButton)
        button.setBackgroundColor(Color.parseColor("#FFB300"))
        newCardNumber = findViewById(R.id.newCardEditText)
        user = intent.getSerializableExtra("user") as User?

        // Listeners
        button.setOnClickListener {
            user?.let {
                user!!.cardNumber = newCardNumber.text.toString()
                changeCardRequest(user!!)
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
            ) {
                val intent = Intent().putExtra("newCardNumber", user.cardNumber)
                setResult(RESULT_OK, intent)
                this@EditCardNumberActivity.finish()
            }
        })
    }
}