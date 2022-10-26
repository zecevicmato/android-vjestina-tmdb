package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
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
    modifier: Modifier = Modifier
){
    Card(
        elevation = 6.dp,
        modifier = modifier
            .padding(8.dp)
            .height(220.dp)
            .width(135.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable{},
        //backgroundColor = MaterialTheme.colors.background
    ){
        Box{

            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = movieCardViewState.title
            )

            FavoriteButton(isFavorite = false,modifier.padding(8.dp))
        }
    }
}

@Preview
@Composable
fun MovieCardPreview(){
    val recalledMovie = MoviesMock.getMoviesList()[0]
    val movieCardViewState = MovieCardViewState(
        imageUrl = recalledMovie.imageUrl,
        title = recalledMovie.title,
        isFavorite = recalledMovie.isFavorite
    )
    MovieCard(movieCardViewState = movieCardViewState)
}