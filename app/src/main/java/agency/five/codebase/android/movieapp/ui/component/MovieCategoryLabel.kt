package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

sealed class MovieCategoryLabelTextViewState {
    class StringText(val text: String) : MovieCategoryLabelTextViewState()
    class ResourceText(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

@Composable
fun MovieCategoryLabel(
    state: MovieCategoryLabelViewState
) {
    if (state.isSelected) {
        Selected(state)
    } else {
        Unselected(state)
    }
}

@Composable
fun Selected(state: MovieCategoryLabelViewState) {
    Column {
        Text(
            text = getSource(state = state),
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
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
fun Unselected(state: MovieCategoryLabelViewState) {
    Text(
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

    val textFromString = MovieCategoryLabelTextViewState.StringText("Movies")
    val selected = MovieCategoryLabelViewState(1, true, textFromString)
    val unselected = MovieCategoryLabelViewState(2, false, textFromString)

    Surface (modifier = Modifier.size(100.dp)){
        Column() {
            MovieCategoryLabel(state = selected);
            MovieCategoryLabel(state = unselected)
        }
    }
}