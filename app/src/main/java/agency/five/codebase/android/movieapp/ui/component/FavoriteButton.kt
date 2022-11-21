package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Image(
        painter = painterResource(id = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite),
        contentDescription = null,
        modifier = modifier
            .size(30.dp)
            .background(Color.Gray, CircleShape)
            .padding(8.dp)
            .clickable {
                onClick()
            }
    )
}

@Preview
@Composable
fun FavoriteButtonPreview() {
    var isFavorite by remember { mutableStateOf(true) }
    FavoriteButton(isFavorite = isFavorite, onClick = { isFavorite = !isFavorite })
}
