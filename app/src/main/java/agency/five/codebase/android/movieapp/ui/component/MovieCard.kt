package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieCardViewState(
    val imageUrl: String?,
    val title: String,
    val isFavorite: Boolean,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
) {
    Card(
        elevation = 6.dp,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
    ) {
        Box {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = movieCardViewState.title
            )
            FavoriteButton(
                isFavorite = movieCardViewState.isFavorite,
                modifier = Modifier.padding(8.dp),
                onClick = onFavoriteClick
            )
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    var isFavorite by remember { mutableStateOf(true) }
    val recalledMovie = MoviesMock.getMoviesList()[0]
    val movieCardViewState = MovieCardViewState(
        imageUrl = recalledMovie.imageUrl,
        title = recalledMovie.title,
        isFavorite = recalledMovie.isFavorite
    )
    MovieCard(movieCardViewState = movieCardViewState,
        onFavoriteClick = { isFavorite = !isFavorite })
}
