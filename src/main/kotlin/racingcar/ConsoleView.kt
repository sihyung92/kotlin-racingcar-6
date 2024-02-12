package racingcar

import camp.nextstep.edu.missionutils.Console
import java.lang.NumberFormatException
import java.lang.StringBuilder
import kotlin.io.println as sysOutPrintln

object ConsoleView {

    object OutputView {
        fun println(message: Any) {
            sysOutPrintln(message)
        }

        fun printErr(message: Any) {
            System.err.println(message)
        }

        fun println(cars: Cars) {
            cars.forEach {
                println(
                    // ex. car : ----
                    "${it.carName} : ${forwardToBarView(it.positions.last().position)}"
                )
            }
            enterLine()
        }

        fun inputNameMessage() {
            println("경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준으로 구분)")
        }

        fun inputNameMessageAfter() {
            enterLine()
        }

        private fun enterLine() {
            sysOutPrintln()
        }

        private fun forwardToBarView(position: Position): String {
            return forwardToBarView(position.value)
        }

        private fun forwardToBarView(position: Int): String {
            val str = StringBuilder();
            for (i in 0..<position) {
                str.append("-")
            }

            return str.toString()
        }
    }

    object InputView {

        fun readLine(): String {
            return Console.readLine()
        }

        fun readCarNames() : List<String> {
            return readLine().split(",")
        }

        fun readInt(): Int {
            val readLine = Console.readLine()
            try {
                val parseInt = Integer.parseInt(readLine)
                if (parseInt <= 0) {
                    throw NumberFormatException("0보다 큰 값을 입력해주세요.")
                }
                return parseInt
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("숫자가 아닌 값이 입력되었습니다. 0보다 큰 값을 입력해주세요")
            }
        }

    }

}