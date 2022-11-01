package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MovieCategoryLabelViewState(
    val itemId: Int,
    var isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState,
)

sealed class MovieCategoryLabelTextViewState {
    class StringText(val text: String) : MovieCategoryLabelTextViewState()
    class ResourceText(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

@Composable
fun MovieCategoryLabel(
    modifier: Modifier = Modifier,
    state: MovieCategoryLabelViewState,
    onClick: () -> Unit,
) {
    modifier.clickable {
        onClick()
    }
    if (state.isSelected) {
        Selected(state, onClick = { state.isSelected = true })
    } else {
        Unselected(state, onClick = { state.isSelected = false })
    }
}

@Composable
fun Selected(
    state: MovieCategoryLabelViewState,
    onClick: (MovieCategoryLabelViewState) -> Unit,
) {
    MovieCategoryLabel(state = state, modifier = Modifier) {}
    Column {
        Text(
            text = getSource(state = state),
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(state) },
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier
                .size(1.dp)
        )
        Divider(
            thickness = 2.dp,
            color = Color.Black,
        )
    }
}

@Composable
fun Unselected(
    state: MovieCategoryLabelViewState,
    onClick: (MovieCategoryLabelViewState) -> Unit,
) {
    MovieCategoryLabel(state = state, modifier = Modifier) {}
    Text(
        modifier = Modifier
            .clickable { onClick(state) },
        text = getSource(state = state),
        fontSize = 12.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun getSource(state: MovieCategoryLabelViewState): String {
    return when (val categoryText = state.categoryText) {
        is MovieCategoryLabelTextViewState.StringText -> categoryText.text
        is MovieCategoryLabelTextViewState.ResourceText -> stringResource(id = categoryText.textRes)
    }
}

@Preview
@Composable
fun MovieCategoryLabelPreview() {
    var isSelected by remember { mutableStateOf(false) }
    val textFromString = MovieCategoryLabelTextViewState.StringText("Movies")
    val selected = MovieCategoryLabelViewState(1, true, textFromString)
    val unselected = MovieCategoryLabelViewState(2, false, textFromString)
    Surface(modifier = Modifier.size(100.dp)) {
        Column {
            MovieCategoryLabel(state = selected, onClick = { selected.isSelected })
            MovieCategoryLabel(state = unselected, onClick = { unselected.isSelected })
        }
    }
}
