package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()
val movies = MoviesMock.getMoviesList()

val popular = listOf(
    MovieCategory.POPULAR_STREAMING,
    MovieCategory.POPULAR_ON_TV,
    MovieCategory.POPULAR_FOR_RENT,
    MovieCategory.POPULAR_IN_THEATERS
)
val nowPlaying = listOf(
    MovieCategory.NOW_PLAYING_MOVIES,
    MovieCategory.NOW_PLAYING_TV
)
val upcoming = listOf(
    MovieCategory.UPCOMING_TODAY,
    MovieCategory.UPCOMING_THIS_WEEK
)
var popularCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(popular, MovieCategory.POPULAR_STREAMING, movies)
var nowPlayingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(nowPlaying,
        MovieCategory.NOW_PLAYING_MOVIES,
        movies)
var upcomingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(upcoming, MovieCategory.UPCOMING_TODAY, movies)

@Composable
fun HomeScreenRoute(
    onNavigateToMovieDetails: (Int) -> Unit,
) {
    val popularCategory by remember {
        mutableStateOf(popularCategoryViewState)
    }
    val nowPlayingCategory by remember {
        mutableStateOf(nowPlayingCategoryViewState)
    }
    val upcomingCategory by remember {
        mutableStateOf(upcomingCategoryViewState)
    }
    HomeScreen(
        popular = popularCategory,
        nowPlaying = nowPlayingCategory,
        upcoming = upcomingCategory,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onCategoryClick = { categoryId ->
            switchSelectedCategory(categoryId)
        }
    )
}

fun switchSelectedCategory(categoryId: Int) {
    when (categoryId) {
        0, 1, 2, 3 -> {
            popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                popular,
                MovieCategory.values()[categoryId],
                movies
            )
        }
        4, 5 -> {
            nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                nowPlaying,
                MovieCategory.values()[categoryId],
                movies
            )
        }
        else -> {
            upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                upcoming,
                MovieCategory.values()[categoryId],
                movies
            )
        }
    }
}

@Composable
fun HomeScreen(
    popular: HomeMovieCategoryViewState,
    nowPlaying: HomeMovieCategoryViewState,
    upcoming: HomeMovieCategoryViewState,
    onNavigateToMovieDetails: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier.verticalScroll(scrollState)) {
        HomeScreenSection(
            viewState = popular,
            headerTitle = stringResource(id = R.string.whats_popular),
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onCategoryClick = onCategoryClick
        )
        HomeScreenSection(
            viewState = nowPlaying,
            headerTitle = stringResource(id = R.string.now_playing),
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onCategoryClick = onCategoryClick
        )
        HomeScreenSection(
            viewState = upcoming,
            headerTitle = stringResource(id = R.string.upcoming),
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onCategoryClick = onCategoryClick
        )
    }
}

@Composable
fun HomeScreenSection(
    viewState: HomeMovieCategoryViewState,
    headerTitle: String,
    onNavigateToMovieDetails: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        HomeScreenHeader(headerTitle)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        LazyRow(
            horizontalArrangement =
            Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(
                items = viewState.movieCategories,
                key = { category ->
                    category.itemId
                }
            ) { category ->
                MovieCategoryLabel(
                    state = category,
                    onClick = { onCategoryClick(category.itemId) }
                )
            }
        }
        Spacer(
            modifier =
            Modifier.height(MaterialTheme.spacing.medium)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            items(
                items = viewState.movies,
                key = { movie ->
                    movie.id
                }
            ) { movie ->
                MovieCard(
                    movieCardViewState = MovieCardViewState(
                        imageUrl = movie.movieCardViewState.imageUrl,
                        title = movie.movieCardViewState.title,
                        isFavorite = movie.movieCardViewState.isFavorite
                    ),
                    onFavoriteClick = {}
                )
            }
        }
    }
}

@Composable
fun HomeScreenHeader(
    headerTitle: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = headerTitle,
        style = MaterialTheme.typography.h5,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.large
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
fun HomeScreenPreview() {
    MovieAppTheme {
        HomeScreen(
            popular = popularCategoryViewState,
            nowPlaying = nowPlayingCategoryViewState,
            upcoming = upcomingCategoryViewState,
            onNavigateToMovieDetails = {},
            onCategoryClick = {}
        )
    }
}