package room1110.taxi_app.data

import java.time.LocalDateTime

class Ride() {
    var id: Long = 0
    var price: Int = 0
    var distance: Double = 0.0
    var owner: Int = 0
    var rideSize: Int = 0
    lateinit var members: List<Int>
    lateinit var addressFrom: String
    lateinit var addressTo: String
    lateinit var status: String
    lateinit var taxiService: String
    lateinit var dtFrom: LocalDateTime
    lateinit var dtTo: LocalDateTime

    constructor(
        id: Long,
        price: Int,
        distance: Double,
        addressFrom: String,
        addressTo: String,
        status: String,
        taxiService: String,
        dtFrom: LocalDateTime,
        dtTo: LocalDateTime,
        members: List<Int>,
        rideSize: Int,
        owner: Int
    ) : this() {
        this.id = id
        this.price = price
        this.distance = distance
        this.addressFrom = addressFrom
        this.addressTo = addressTo
        this.status = status
        this.taxiService = taxiService
        this.dtFrom = dtFrom
        this.dtTo = dtTo
        this.members = members
        this.owner = owner
        this.rideSize = rideSize
    }

}