package room1110.taxi_app.data

import com.google.gson.annotations.SerializedName

class Review() : java.io.Serializable {

    var id: Long = 0

    var score: Int = 0

    @SerializedName("leaving_user")
    var leavingUser = User()

    @SerializedName("receiving_user")
    var receivingUser = User()

}
