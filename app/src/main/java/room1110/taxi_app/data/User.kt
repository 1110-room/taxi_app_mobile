package room1110.taxi_app.data

class User(): java.io.Serializable {
    var id: Long = 0
    var vkId: Long = 0
    var name: String = ""
    var surname: String = ""
    var cardNumber: String = ""
    var role: String = ""
    var ready: Boolean = false
    lateinit var ride: Ride
    lateinit var ownersRide: Ride
    val receivedReviews: List<String?> = null
    var leavedReviews: MutableList<String?> = null

    constructor(id: Long) : this() {
        this.id = id
    }

    constructor(id: Long, name: String, surname: String) : this() {
        this.id = id
        this.name = name
        this.surname = surname
    }

    constructor(id: Long, vkId: Long, name: String, surname: String, cardNumber: String) : this() {
        this.id = id
        this.vkId = vkId
        this.name = name
        this.surname = surname
        this.cardNumber = cardNumber
        this.ready = false
    }

    override fun toString(): String {
        return "User(id=$id, vkId=$vkId, name='$name', surname='$surname', cardNumber='$cardNumber', role='$role', ready=$ready, ride=$ride, ownersRide=$ownersRide)"
    }
}