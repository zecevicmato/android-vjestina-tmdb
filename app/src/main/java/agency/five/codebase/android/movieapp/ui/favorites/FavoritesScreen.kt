package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())


@Composable
fun FavoritesRoute(

) {

}

@Composable
fun FavoritesScreen(
    favoritesViewState: FavoritesViewState,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.favorites_width)),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = modifier
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            FavoritesHeader()
        }
        items(
            items = favoritesViewState.favoriteMovies,
            key = { favoriteMovies -> favoriteMovies.id }) { favoriteMovies ->
            MovieCard(movieCardViewState = favoriteMovies.movieCardViewState,
                onFavoriteClick = {},
                modifier = Modifier.shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium)
            )
        }
    }
}

@Composable
fun FavoritesHeader() {
    Text(
        text = stringResource(id = R.string.favorites),
        style = MaterialTheme.typography.h3,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.medium
            )
    )
    Spacer(
        modifier = Modifier
            .height(
                MaterialTheme.spacing.medium
            )
    )
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    MovieAppTheme {
        FavoritesScreen(
            favoritesViewState = favoritesViewState,
            modifier = Modifier.fillMaxSize()
        )
    }
}
