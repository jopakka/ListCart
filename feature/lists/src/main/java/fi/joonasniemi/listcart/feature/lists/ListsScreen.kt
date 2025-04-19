package fi.joonasniemi.listcart.feature.lists

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fi.joonasniemi.listcart.core.designsystem.theme.ListCartTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListsRoot(
    viewModel: ListsViewModel = koinViewModel(),
) {
    ListsScreen()
}

@Composable
internal fun ListsScreen() {

}

@Preview
@Composable
private fun ListsScreenPreview() {
    ListCartTheme {
        ListsScreen()
    }
}