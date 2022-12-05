package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewItemViewState

data class MovieDetailsViewState(
    val id: Int,
    val imageUrl: String,
    val voteAverage: Float,
    val title: String,
    val overview: String,
    val isFavorite: Boolean,
    val crew: MutableList<CrewmanViewState>,
    val cast: MutableList<ActorViewState>,
)

data class CrewmanViewState(
    val crewItemViewState: CrewItemViewState,
)

data class ActorViewState(
    val id: Int,
    val actorCardViewState: ActorCardViewState,
)
