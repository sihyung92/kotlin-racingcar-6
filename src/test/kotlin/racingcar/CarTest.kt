package racingcar

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class CarTest {

    @Test
    fun `차를 생성한다`() {
        val car = Car(VALID_CAR_NAME)

        assertThat(car.carName).isNotNull()
    }

    @Test
    fun `차에 게임 라운드 정보를 기록한다`() {
        val car = Car(VALID_CAR_NAME)
            .also {
                it.recordRound(Position.MIN_POSITION)
            }

        assertThat(car.positions).isNotEmpty
    }

    @DisplayName("차 이름을 생성할 때")
    @Nested
    inner class CarNameTest {

        @ValueSource(strings = ["차이름", "내차", "붕붕이최고", "킹"])
        @ParameterizedTest
        fun `차 이름이 제대로 생성된다`(givenName: String) {
            println(givenName)
            assertThatCode { CarName(givenName) }.doesNotThrowAnyException()
        }

        @ValueSource(strings = ["일이삼사오륙칠팔구십", "여섯글자예요", "1234567890124567890"])
        @EmptySource
        @ParameterizedTest
        fun `차 이름은 비거나 5자 초과일 수 없다`(givenName: String) {
            assertThatThrownBy { CarName(givenName) }.isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    companion object {
        const val VALID_CAR_NAME = "붕붕카"
    }
}