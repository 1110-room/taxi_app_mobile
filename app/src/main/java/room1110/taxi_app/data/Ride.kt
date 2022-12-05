package room1110.taxi_app.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class Ride(
    var id: Long,
    var price: Int,
    var distance: Double,
    var owner: User,
    var rideSize: Int,
    var members: List<User>,
    var addressFrom: String,
    var addressTo: String,
    var status: String,
    var taxiService: String,
    var dtFrom: LocalDateTime,
    var dtTo: LocalDateTime?
           ): java.io.Serializable {

    override fun toString(): String {
        return "Ride(id=$id, price=$price, distance=$distance, owner=$owner, rideSize=$rideSize, members=$members, addressFrom='$addressFrom', addressTo='$addressTo', status='$status', taxiService='$taxiService', dtFrom=$dtFrom, dtTo=$dtTo)"
    }

}

data class RideSerialized(
    @SerializedName("id") val id: Long,
    @SerializedName("price") var price: Int,
    @SerializedName("distance") var distance: Double,
    @SerializedName("owner") var owner: User,
    @SerializedName("ride_size") var rideSize: Int,
    @SerializedName("members") var members: List<User>,
    @SerializedName("address_from") var addressFrom: String,
    @SerializedName("address_to") var addressTo: String,
    @SerializedName("status") var status: String,
    @SerializedName("taxi_service") var taxiService: String,
    @SerializedName("dt_from") var dtFrom: String,
    @SerializedName("dt_to") var dtTo: String?
)

class RideSerializer {
    companion object {
        fun serialize(ride: Ride): RideSerialized {
            return RideSerialized(
                ride.id,
                ride.price,
                ride.distance,
                ride.owner,
                ride.rideSize,
                ride.members,
                ride.addressFrom,
                ride.addressTo,
                ride.status,
                ride.taxiService,
                ride.dtFrom.toString(),
                ride.dtTo.toString()
            )
        }

        fun deserialize(ride: RideSerialized): Ride {
            return Ride(
                ride.id,
                ride.price,
                ride.distance,
                ride.owner,
                ride.rideSize,
                ride.members,
                ride.addressFrom,
                ride.addressTo,
                ride.status,
                ride.taxiService,
                LocalDateTime.parse(ride.dtFrom),
                if (ride.dtTo != null) LocalDateTime.parse(ride.dtTo) else null
            )
        }
    }
}

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
