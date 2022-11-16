package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

private val movieDetailsViewState =
    movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
    onFavoriteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {

    }
}
