package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.ActorCard
import agency.five.codebase.android.movieapp.ui.component.CrewItem
import agency.five.codebase.android.movieapp.ui.component.FavoriteButton
import agency.five.codebase.android.movieapp.ui.component.UserScoreProgressBar
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

private val movieDetailsViewState =
    movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute() {
    val details by remember {
        mutableStateOf(movieDetailsViewState)
    }
    MovieDetailsScreen(movieDetailsViewState = details, onFavoriteButtonClick = {})
}

@Composable
fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
    onFavoriteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.background(MaterialTheme.colors.background),
    ) {
        item {
            ImageSection(movieDetailsViewState, onFavoriteButtonClick)
        }
        item {
            OverviewSection(movieDetailsViewState)
        }
        item {
            CrewmanSection(movieDetailsViewState)
        }
        item {
            CastSection(movieDetailsViewState)
        }
    }
}

@Composable
fun CastSection(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.background(MaterialTheme.colors.onPrimary)) {
        Text(
            text = stringResource(id = R.string.cast_title),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        ) {
            items(movieDetailsViewState.cast.size) { actor ->
                ActorCard(
                    actorCardViewState = movieDetailsViewState.cast[actor].actorCardViewState,
                )
            }
        }
    }
}

@Composable
fun CrewmanSection(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(MaterialTheme.spacing.large),
        modifier = modifier.padding(start = MaterialTheme.spacing.small)
    ) {
        items(
            movieDetailsViewState.crew
        ) { crewman ->
            CrewItem(
                crewItemViewState = crewman.crewItemViewState,
            )
        }
    }
}

@Composable
fun OverviewSection(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = R.string.overview),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(top = MaterialTheme.spacing.medium)
        )
        Text(
            text = movieDetailsViewState.overview,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun ImageSection(
    movieDetailsViewState: MovieDetailsViewState,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(model = movieDetailsViewState.imageUrl,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            ) {
                UserScoreProgressBar(score = movieDetailsViewState.voteAverage)
                Text(
                    text = stringResource(id = R.string.user_score),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.body2
                )
            }
            Text(
                text = movieDetailsViewState.title,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(top = MaterialTheme.spacing.medium)
            )
            FavoriteButton(
                isFavorite = movieDetailsViewState.isFavorite,
                onClick = onFavoriteClick,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = MaterialTheme.spacing.medium)
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieAppTheme {
        MovieDetailsScreen(movieDetailsViewState = movieDetailsViewState,
            onFavoriteButtonClick = { }
        )
    }
}
