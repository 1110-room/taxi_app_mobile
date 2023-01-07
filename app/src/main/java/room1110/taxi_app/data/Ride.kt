package room1110.taxi_app.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class Ride() : java.io.Serializable {
    @SerializedName("id")
    var id: Long = 0

    @SerializedName("price")
    var price: Int = 0

    @SerializedName("distance")
    var distance: Double = 0.0

    @SerializedName("owner")
    var owner = User()

    @SerializedName("ride_size")
    var rideSize: Int = 0

    @SerializedName("members")
    var members: ArrayList<User> = arrayListOf()

    @SerializedName("address_from")
    var addressFrom: String = ""

    @SerializedName("address_to")
    var addressTo: String = ""

    @SerializedName("status")
    var status: String = ""

    @SerializedName("taxi_service")
    var taxiService: String = ""

    @SerializedName("dt_from")
    private var dtFrom: String = ""

    @SerializedName("dt_to")
    private var dtTo: String? = null

    fun getMembersCount(): Int {
        return if (members.isEmpty()) 1 else members.size + 1
    }

    fun getDtFrom(): LocalDateTime? {
        return LocalDateTime.parse(dtFrom)
    }

    fun getDtTo(): LocalDateTime? {
        return if (dtTo == null)
            null
        else
            LocalDateTime.parse(dtTo)
    }

    fun isRideMember(user: User): Boolean {
        return this.owner.id == user.id || this.members.any { m -> m.id == user.id }
    }

    override fun toString(): String {
        return "Ride(id=$id, price=$price, distance=$distance, owner=$owner, rideSize=$rideSize, members=$members, addressFrom='$addressFrom', addressTo='$addressTo', status='$status', taxiService='$taxiService', dtFrom='$dtFrom', dtTo=$dtTo)"
    }

    // New Ride Constructor
    constructor(
        rideSize: Int,
        addressFrom: String,
        addressTo: String,
        taxiService: String
    ) : this() {
        this.rideSize = rideSize
        this.addressFrom = addressFrom
        this.addressTo = addressTo
        this.taxiService = taxiService
    }
}

data class RideRequest(
    @SerializedName("owner")
    var owner: User,

    @SerializedName("ride_size")
    var rideSize: Int,

    @SerializedName("address_from")
    var addressFrom: String,

    @SerializedName("address_to")
    var addressTo: String,

    @SerializedName("taxi_service")
    var taxiService: String
)

data class UserRideRequest(
    var user: User,
    var ride: Ride
)

/*
{
    "id": 1,
    "price": 0,
    "distance": 9.0,
    "status": "OPEN",
    "members": [],
    "owner": {
      "id": 1,
      "name": "Дамир",
      "surname": "Гарифуллин",
      "avatar": null
    },
    "ride_size": 4,
    "taxi_service": "yandex",
    "address_from": "Деревня Универсиады 5",
    "address_to": "Кремлевская 35",
    "dt_from": "2022-11-30T15:44:40",
    "dt_to": null
  }
*/
