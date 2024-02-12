package racingcar

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GameRoundTest{

    @Test
    fun `전진 임계값에 도달못한 round는 전진하지 못한다`(){
        val forward = GameRound(GameRound.FORWARD_POSITION)
        val notForward = GameRound(GameRound.FORWARD_POSITION - 1)

        assertThat(forward.forward).isTrue()
        assertThat(notForward.forward).isFalse()
    }

    @Nested
    @DisplayName("Position을 생성할 때")
    inner class PositionTest{

        @Test
        fun `포지션 생성에 성공한다`(){
            val minPosition = Position(Position.MIN_POSITION)
            val maxPosition = Position(Position.MAX_POSITION)

            assertThat(minPosition.value).isEqualTo(Position.MIN_POSITION)
            assertThat(maxPosition.value).isEqualTo(Position.MAX_POSITION)
        }

        @ValueSource(ints = [Position.MIN_POSITION - 1, Position.MAX_POSITION + 1])
        @ParameterizedTest
        fun `포지션 하한, 상한 값이 옳지 않으면 실패한다`(givenPosition: Int){
            assertThatThrownBy { Position(givenPosition) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}