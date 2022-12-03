package room1110.taxi_app.data

class User() {
    var id: Long = 0
    var vkId: Long = 0
    var name: String = ""
    var surname: String = ""
    var cardNumber: String = ""
//    var role: String = USER
    var ready: Boolean = false
//    val receivedReviews: List<Review>? = null
//    var leavedReviews: MutableList<Review?>? = null
//    var ride: MutableList<Ride?>? = null
//    var ownersRide: Ride
    constructor(id: Long, vkId: Long, name: String, surname: String, cardNumber: String): this(){
        this.id = id
        this.vkId = vkId
        this.name = name
        this.surname = surname
        this.cardNumber = cardNumber
    }
}