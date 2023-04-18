package woowacourse.movie.service

import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Screening
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.ReservationDto
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDateTime

object MovieQueryService {

    fun findAllMovies(): List<MovieDto> {
        val movies = MovieRepository.findAll()
        return movies.map(MovieDto::from)
    }

    fun findMovieById(id: Long): MovieDto {
        val movie = MovieRepository.findById(id)
        return MovieDto.from(movie)
    }

    fun getReservation(movieId: Long, screeningDateTime: LocalDateTime): ReservationDto {
        val movie = MovieRepository.findById(movieId)
        val reservation = movie.screenings.screenings[Screening(screeningDateTime)]
            ?: throw IllegalArgumentException("해당 상영은 예매되지 않았습니다.")
        return ReservationDto.create(movie, reservation)
    }

    fun getMinAudienceCount(): Int {
        return Reservation.MIN_AUDIENCE_COUNT
    }

    fun getMaxAudienceCount(): Int {
        return Reservation.MAX_AUDIENCE_COUNT
    }
}
