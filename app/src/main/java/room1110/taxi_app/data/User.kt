package room1110.taxi_app.data

import android.util.Base64

class User() : java.io.Serializable {
    var id: Long = 0
    var vkId: Long = 0
    var name: String = ""
    var surname: String = ""
    var cardNumber: String = ""
    var role: String = ""
    var ready: Boolean = false
    private var avatar: String? = null
    lateinit var ride: Ride
    lateinit var ownersRide: Ride
//        val receivedReviews: List<Review>? = null
//        var leavedReviews: MutableList<Review?>? = null

    constructor(id: Long) : this() {
        this.id = id
    }

    constructor(
        id: Long,
        vkId: Long,
        name: String,
        surname: String,
        cardNumber: String,
        role: String,
        avatar: String,
        ride: Ride,
        ownersRide: Ride
    ) : this() {
        this.id = id
        this.vkId = vkId
        this.name = name
        this.surname = surname
        this.cardNumber = cardNumber
        this.ready = false
        this.role = role
        this.avatar = avatar
        this.ride = ride
        this.ownersRide = ownersRide
    }

    fun getAvatar(): ByteArray? {
//        return avatar?.toByteArray()
        return if (this.avatar != null)
            Base64.decode(this.avatar, Base64.DEFAULT)
        else
            null
    }

    override fun toString(): String {
        return "User(id=$id, vkId=$vkId, name='$name', surname='$surname', cardNumber='$cardNumber', role='$role', ready=$ready, ride=$ride, ownersRide=$ownersRide)"
    }
}

//data class UserSerialized(
//    @SerializedName("id") val id: Long,
//    @SerializedName("vk_id") var vkId: Long,
//    @SerializedName("name") var name: String,
//    @SerializedName("surname") var surname: String,
//    @SerializedName("card_number") var cardNumber: String,
//    @SerializedName("avatar") var avatar: String,
//    @SerializedName("role") var role: String,
//    @SerializedName("ready") var ready: Boolean,
//    @SerializedName("ride") var ride: RideSerialized,
//    @SerializedName("owners_ride") var ownersRide: RideSerialized
//)

//class UserSerializer {
//    companion object {
//        fun serialize(user: User): UserSerialized {
//            return UserSerialized(
//                user.id,
//                user.vkId,
//                user.name,
//                user.surname,
//                user.cardNumber,
//                String(user.avatar),
//                user.role,
//                user.ready,
//                RideSerializer.serialize(user.ride),
//                RideSerializer.serialize(user.ownersRide)
//            )
//        }
//
//        fun serialize(members: List<User>): List<UserSerialized> {
//            return members.map { user -> serialize(user) }
//        }
//
//        fun deserialize(user: UserSerialized): User {
//            return User(
//                user.id,
//                user.vkId,
//                user.name,
//                user.surname,
//                user.cardNumber,
//                user.role,
//                user.avatar.toByteArray(),
//                RideSerializer.deserialize(user.ride),
//                RideSerializer.deserialize(user.ownersRide)
//            )
//        }
//
//        fun deserialize(members: List<UserSerialized>): List<User> {
//            return members.map { userSerialized -> deserialize(userSerialized) }
//        }
//    }
//}