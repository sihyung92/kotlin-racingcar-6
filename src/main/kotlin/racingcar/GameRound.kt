package racingcar

data class GameRound(val position: Position) {
    val forward: Boolean = FORWARD_POSITION <= position

    constructor(position: Int) : this(Position(position))

    companion object {
        val FORWARD_POSITION = Position(4)
    }

    override fun toString(): String {
        return "GameRound(position=$position, forward=$forward)"
    }
}

data class Position(val value: Int) {

    init {
        require(value in MIN_POSITION..MAX_POSITION) { "$MIN_POSITION ~ $MAX_POSITION 사이여야 합니다. 입력값 : $value" }
    }

    operator fun compareTo(other: Int): Int {
        return value.compareTo(other)
    }

    operator fun compareTo(other: Position): Int {
        return value.compareTo(other.value)
    }

    operator fun plus(other:Int) : Position{
        return Position(value + other)
    }

    operator fun minus(other:Int) : Position{
        return Position(value - other)
    }


    companion object {
        const val MIN_POSITION = 0
        const val MAX_POSITION = 9
    }
}

