package racingcar

class Car(val carName: CarName, var positions:MutableList<GameRound> = arrayListOf()) {

    constructor(carName: String) : this(CarName(carName))

    fun recordRound(round: GameRound) {
        positions.add(round)
    }

    fun recordRound(position: Int) {
        recordRound(GameRound(position))
    }

    fun forwardCount() :Int{
        return positions.count { it.forward }
    }
}

data class CarName(val value: String) {

    init {
        require(value.isNotBlank()) { "자동차 이름은 비어있을 수 없습니다" }
        require(value.length <= MAX_LENGTH) { "자동차 이름은 ${MAX_LENGTH}글자 이상일 수 없습니다. 입력명 : ${value}" }
    }

    companion object{
        const val MAX_LENGTH = 5
    }

    override fun toString(): String {
        return value
    }
}

class Cars(cars: List<Car> = arrayListOf()) : Iterable<Car> {

    private val cars : MutableList<Car> = cars.toMutableList()

    override operator fun iterator() : Iterator<Car>{
        return cars.iterator()
    }

    fun add(newCar: Car) {
        cars.add(newCar)
    }

    fun findWinners() : List<Car> {
        val winnerCount = cars.maxBy { it.forwardCount() }.forwardCount()
        return cars.filter {
            it.forwardCount() == winnerCount
        }
    }
}
