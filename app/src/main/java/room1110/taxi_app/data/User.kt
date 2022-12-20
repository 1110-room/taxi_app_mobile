package room1110.taxi_app.data

import android.util.Base64
import com.google.gson.annotations.SerializedName

class User() : java.io.Serializable {
    var id: Long = 0

    var vkId: Long = 0

    var name: String = ""

    var surname: String = ""

    @SerializedName("card_number")
    var cardNumber: String? = null

    var role: String = ""

    var ready: Boolean = false

    @SerializedName("ride_status")
    var rideStatus: Int = 0

    private var avatar: String? = null

    var rides: List<Ride> = listOf()

    @SerializedName("received_reviews")
    var receivedReviews: List<String> = listOf()

    @SerializedName("leaved_reviews")
    var leavedReviews: List<String> = listOf()

    constructor(id: Long) : this() {
        this.id = id
    }

    fun getAvatar(): ByteArray? {
        return if (this.avatar != null)
            Base64.decode(this.avatar, Base64.DEFAULT)
        else
            null
    }

    override fun toString(): String {
        return "User(id=$id, vkId=$vkId, name='$name', surname='$surname', cardNumber=$cardNumber, role='$role', ready=$ready, rideStatus=$rideStatus, avatar=$avatar, rides=$rides, receivedReviews=$receivedReviews, leavedReviews=$leavedReviews)"
    }

}
