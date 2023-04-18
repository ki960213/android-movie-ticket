package woowacourse.movie.domain

import org.junit.Assert.assertThrows
import org.junit.Test
import java.time.LocalDateTime

internal class ScreeningsTest {

    @Test
    fun `상영 모음에 상영을 추가하면 상영이 추가된다`() {
        val screenings = Screenings()
        val screening = Screening(LocalDateTime.of(2021, 3, 1, 5, 1))
        screenings.addScreening(screening)

        val actual = screenings.screenings

        val expected = mapOf<Screening, Reservation?>(screening to null)
        assert(actual == expected)
    }

    @Test
    fun `상영 모음에 존재하지 않는 상영을 예매하면 에러가 발생한다`() {
        val screenings = Screenings()

        assertThrows(
            "존재하지 않는 상영을 예매할 수 없습니다.",
            IllegalArgumentException::class.java
        ) {
            screenings.reserve(Screening(LocalDateTime.now()), Reservation(LocalDateTime.now(), 1))
        }
    }

    @Test
    fun `상영 모음에 상영을 예매하면 해당 상영과 예매가 매핑된다`() {
        val screenings = Screenings()
        val screeningDateTime = LocalDateTime.of(2021,3,1,10,0)
        val screening = Screening(screeningDateTime)
        screenings.addScreening(screening)
        val reservation = Reservation(screeningDateTime, 1)

        screenings.reserve(screening, reservation)
        val actual = screenings.screenings[screening]

        assert(actual == reservation)
    }
}
