package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CrewItemViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = crewItemViewState.name,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Black
            )
            Text(
                modifier = Modifier
                    .padding(start = 5.dp),
                text = crewItemViewState.job,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun CrewItemPreview() {
    val recalledCrewman = MoviesMock.getCrewman()
    val crewItemViewState = CrewItemViewState(
        name = recalledCrewman.name,
        job = recalledCrewman.job
    )
    CrewItem(
        modifier = Modifier.size(width = 100.dp, height = 25.dp),
        crewItemViewState = crewItemViewState
    )
}
