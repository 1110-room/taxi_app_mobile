package room1110.taxi_app.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class Ride(

) : java.io.Serializable {
    @SerializedName("id")
    var id: Long = 0

    @SerializedName("price")
    var price: Int = 0

    @SerializedName("distance")
    var distance: Double = 0.0

    @SerializedName("owner")
    lateinit var owner: User

    @SerializedName("ride_size")
    var rideSize: Int = 0

    @SerializedName("members")
    lateinit var members: List<User>

    @SerializedName("address_from")
    lateinit var addressFrom: String

    @SerializedName("address_to")
    lateinit var addressTo: String

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("taxi_service")
    lateinit var taxiService: String

    @SerializedName("dt_from")
    private lateinit var dtFrom: String

    @SerializedName("dt_to")
    private var dtTo: String? = null

    constructor(
        id: Long,
        price: Int,
        distance: Double,
        owner: User,
        rideSize: Int,
        members: List<User>,
        addressFrom: String,
        addressTo: String,
        status: String,
        taxiService: String,
        dtFrom: String,
        dtTo: String?
    ) : this() {
        this.id = id
        this.price = price
        this.distance = distance
        this.owner = owner
        this.rideSize = rideSize
        this.members = members
        this.addressFrom = addressFrom
        this.addressTo = addressTo
        this.status = status
        this.taxiService = taxiService
        this.dtFrom = dtFrom
        this.dtTo = dtTo
    }

    fun getDtFrom(): LocalDateTime? {
        return LocalDateTime.parse(dtFrom)
    }

    fun getDtTo(): LocalDateTime? {
        return LocalDateTime.parse(dtTo)
    }
}

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
[
  {
    "id": 1,
    "price": 0,
    "distance": 0.0,
    "status": "OPEN",
    "members": [],
    "owner": {
      "id": 1,
      "name": "Максим",
      "surname": "Абашин"
    },
    "ride_size": 4,
    "taxi_service": "yandex",
    "address_from": "ДУ 5",
    "address_to": "Кремлевская 35",
    "dt_from": "2022-11-30T15:44:40.928+00:00",
    "dt_to": "2022-11-30T16:44:40.928+00:00"
  }
]
*/
