package room1110.taxi_app.data

class Ride(
    private var id: Int, private var cost: Int, private var distance: Double
    ) {
    //    var taxiService: String,
    //    var addressFrom: String,
    //    var addressTo: String,
    //    var dtFrom: Date,
    //    var dtTo: Date,
    //    var status: String,
    //    var members: List<Int>,
    //    var owner: Int

    override fun toString(): String {
        return "room1110.taxi_app.data.Ride(id=$id, cost=$cost, distance=$distance)"
    }
}