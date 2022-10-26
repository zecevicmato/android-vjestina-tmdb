package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Card
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


data class ActorCardViewState(
    val imageUrl: String?,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 6.dp,
        modifier = modifier
            .padding(8.dp)
            .height(220.dp)
            .width(135.dp)
            .clip(RoundedCornerShape(10.dp)),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column {
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(145.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Text(
                modifier = modifier
                    .padding(top = 5.dp, start = 10.dp)
                    .width(80.dp),
                text = actorCardViewState.name,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Black
            )

            Text(
                modifier = modifier.padding(start = 10.dp, top = 5.dp),
                text = actorCardViewState.character,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
private fun CardPreview() {
    val recalledActor = MoviesMock.getActor()
    val actorCardViewState = ActorCardViewState(
        //imageUrl = "https://www.themoviedb.org/t/p/w200/5qHNjhtjMD4YWH3UP0rm4tKwxCL.jpg",
        imageUrl=recalledActor.imageUrl,
        name = recalledActor.name,
        character = recalledActor.character
    )
    ActorCard(actorCardViewState=actorCardViewState)
}




