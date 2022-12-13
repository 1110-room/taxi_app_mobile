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
    var owner: User? = null

    @SerializedName("ride_size")
    var rideSize: Int = 0

    @SerializedName("members")
    var members: List<User>? = listOf()

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
        return if (members == null) 1 else members!!.size + 1
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

data class  RideRequest(
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

//data class RideSerialized(
//    @SerializedName("id") val id: Long,
//    @SerializedName("price") var price: Int,
//    @SerializedName("distance") var distance: Double,
//    @SerializedName("owner") var owner: UserSerialized,
//    @SerializedName("ride_size") var rideSize: Int,
//    @SerializedName("members") var members: List<UserSerialized>,
//    @SerializedName("address_from") var addressFrom: String,
//    @SerializedName("address_to") var addressTo: String,
//    @SerializedName("status") var status: String,
//    @SerializedName("taxi_service") var taxiService: String,
//    @SerializedName("dt_from") var dtFrom: String,
//    @SerializedName("dt_to") var dtTo: String?
//)

//class RideSerializer {
//    companion object {
//        fun serialize(ride: Ride): RideSerialized {
//            return RideSerialized(
//                ride.id,
//                ride.price,
//                ride.distance,
//                UserSerializer.serialize(ride.owner),
//                ride.rideSize,
//                UserSerializer.serialize(ride.members),
//                ride.addressFrom,
//                ride.addressTo,
//                ride.status,
//                ride.taxiService,
//                ride.dtFrom.toString(),
//                ride.dtTo.toString()
//            )
//        }
//
//        fun deserialize(ride: RideSerialized): Ride {
//            return Ride(
//                ride.id,
//                ride.price,
//                ride.distance,
//                UserSerializer.deserialize(ride.owner),
//                ride.rideSize,
//                UserSerializer.deserialize(ride.members),
//                ride.addressFrom,
//                ride.addressTo,
//                ride.status,
//                ride.taxiService,
//                LocalDateTime.parse(ride.dtFrom),
//                if (ride.dtTo != null) LocalDateTime.parse(ride.dtTo) else null
//            )
//        }
//    }
//}

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
