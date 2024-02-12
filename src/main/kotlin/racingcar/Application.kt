package racingcar

import camp.nextstep.edu.missionutils.Randoms
import racingcar.ConsoleView.InputView.readCarNames
import racingcar.ConsoleView.InputView.readInt
import racingcar.ConsoleView.OutputView.inputNameMessage
import racingcar.ConsoleView.OutputView.inputNameMessageAfter
import racingcar.ConsoleView.OutputView.printErr
import racingcar.ConsoleView.OutputView.println

fun main() {
    val cars = inputCarNames()
    val tryCountInput = inputTryCount()
    doGameAndPrintRound(tryCountInput, cars)
    announceWinner(cars)
}

private fun inputCarNames(): Cars {
    inputNameMessage()
    val split = readCarNames()
    inputNameMessageAfter()
    return createCars(split)
}

private fun createCars(split: List<String>): Cars {
    val cars = Cars()
    val carCreateErrors = arrayListOf<IllegalArgumentException>()

    for (carNameInput in split) {
        try {
            val newCar = Car(carNameInput)
            cars.add(newCar)
        } catch (e: IllegalArgumentException) {
            carCreateErrors.add(e)
        }
    }

    if (carCreateErrors.isNotEmpty()) {
        printErr(carCreateErrors)
        throw IllegalArgumentException("차 생성 중 오류로 인해 종료합니다.")
    }
    return cars
}

private fun inputTryCount(): Int {
    println("시도할 횟수를 입력하세요.")
    val tryCountInput = readInt()
    return tryCountInput
}

private fun doGameAndPrintRound(tryCountInput: Int, cars: Cars) {
    println("실행결과")
    for (i in 1..tryCountInput) {
        cars.forEach {
            val userPosition = Randoms.pickNumberInRange(Position.MIN_POSITION, Position.MAX_POSITION)
            val currentRound = GameRound(userPosition)
            it.recordRound(currentRound)
        }
        println(cars)
    }
}

private fun announceWinner(cars: Cars) {
    println("최종 우승자 : ${cars.findWinners().map { it.carName.value }.joinToString()}");
}

