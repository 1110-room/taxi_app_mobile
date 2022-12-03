package room1110.taxi_app

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class EditCardNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cardnumber)

        var button = findViewById<Button>(R.id.saveChangesButton)
        button.setBackgroundColor(Color.parseColor("#FFB300"))
    }

    fun onClickSaveButton(view: View){
        // TODO
        // запрос на сервак на изменение карты

        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}