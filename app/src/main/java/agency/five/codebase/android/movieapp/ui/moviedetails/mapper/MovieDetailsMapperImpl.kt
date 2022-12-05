package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewItemViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.ActorViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.CrewmanViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        val crewmen = mutableListOf<CrewmanViewState>()
        val actors = mutableListOf<ActorViewState>()

        for (crewman in movieDetails.crew)
            crewmen.add(CrewmanViewState(CrewItemViewState(crewman.name, crewman.job)))

        for (actor in movieDetails.cast)
            actors.add(ActorViewState(
                id = actor.id,
                ActorCardViewState(
                    name = actor.name,
                    imageUrl = actor.imageUrl,
                    character = actor.character,
                )))

        return MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl.toString(),
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crewmen,
            actors
        )
    }
}
