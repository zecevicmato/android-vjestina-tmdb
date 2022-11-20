package agency.five.codebase.android.movieapp.ui.home.mapper

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelTextViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieCategoryViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeMovieCategoryViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory,
        movies: List<Movie>,
    ): HomeMovieCategoryViewState {
        return HomeMovieCategoryViewState(movieCategories.map { category ->
            MovieCategoryLabelViewState(
                itemId = category.ordinal,
                categoryText = MovieCategoryLabelTextViewState.ResourceText(
                    getResourceStringFromMovieCategory(category)
                ),
                isSelected = category == selectedMovieCategory
            )
        }, movies.map { movie ->
            HomeMovieViewState(
                id = movie.id,
                movieCardViewState = MovieCardViewState(
                    imageUrl = movie.imageUrl,
                    isFavorite = movie.isFavorite,
                    title = movie.title
                )
            )
        })
    }

    private fun getResourceStringFromMovieCategory(movieCategory: MovieCategory): Int {
        return when (movieCategory) {
            MovieCategory.POPULAR_STREAMING -> R.string.streaming
            MovieCategory.POPULAR_ON_TV -> R.string.on_tv
            MovieCategory.POPULAR_FOR_RENT -> R.string.for_rent
            MovieCategory.NOW_PLAYING_TV -> R.string.tv
            MovieCategory.NOW_PLAYING_MOVIES -> R.string.movies
            MovieCategory.UPCOMING_THIS_WEEK -> R.string.this_week
            MovieCategory.POPULAR_IN_THEATERS -> R.string.in_theatres
            MovieCategory.UPCOMING_TODAY -> R.string.today
        }
    }
}
