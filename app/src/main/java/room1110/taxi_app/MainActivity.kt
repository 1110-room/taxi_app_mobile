package room1110.taxi_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, RideLineActivity::class.java)
        startActivity(intent)
    }

    fun onClickProfile(view: View){
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }
}