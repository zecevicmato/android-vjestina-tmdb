package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImplementation : FavoritesMapper {
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState {
        return FavoritesViewState(
            favoriteMovies.map { movie ->
                FavoritesMovieViewState(
                    movie.id,
                    MovieCardViewState(imageUrl = movie.imageUrl,
                        isFavorite = movie.isFavorite,
                        title = movie.title)
                )
            })
    }
}
