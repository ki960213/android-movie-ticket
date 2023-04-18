package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.service.MovieQueryService

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        MovieMockDateInitiator.initMovieMockData()

        val movies = MovieQueryService.findAllMovies()
        val movieAdapter = MovieListAdapter(this, movies)
        val movieListView = findViewById<ListView>(R.id.movie_listview)
        movieListView.adapter = movieAdapter
    }
}
