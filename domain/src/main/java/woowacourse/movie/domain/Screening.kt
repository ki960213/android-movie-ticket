package woowacourse.movie.domain

import kotlin.properties.Delegates

class Screening(
    private val screeningRange: ScreeningRange,
    private val theater: Theater,
    private val movie: Movie
) {
    var id: Long? by Delegates.vetoable(null) { _, old, new ->
        old == null && new != null
    }
}