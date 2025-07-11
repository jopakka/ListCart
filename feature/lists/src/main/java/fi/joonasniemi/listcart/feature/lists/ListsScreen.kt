package fi.joonasniemi.listcart.feature.lists

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fi.joonasniemi.listcart.core.designsystem.theme.ListCartTheme
import fi.joonasniemi.listcart.core.model.data.ShoppingList
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.koinViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
fun ListsRoot(
    onNavigateToList: (ShoppingList) -> Unit,
    viewModel: ListsViewModel = koinViewModel(),
) {
    val lists by viewModel.shoppingLists.collectAsStateWithLifecycle()

    ListsScreen(
        lists = lists,
        onNavigateToList = {
            Napier.d("Navigate to list $it")
            onNavigateToList(it)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListsScreen(
    lists: List<ShoppingList>,
    onNavigateToList: (ShoppingList) -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text("List Cart")
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(lists) {
                ListItem(
                    shoppingList = it,
                    onNavigateToList = onNavigateToList,
                )
            }
        }
    }
}

private val itemShape = RoundedCornerShape(8.dp)

@Composable
private fun ListItem(
    shoppingList: ShoppingList,
    onNavigateToList: (ShoppingList) -> Unit,
) {
    val createdAt = rememberDateTime(shoppingList.createdAt)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(itemShape)
            .clickable(onClick = {
                onNavigateToList(shoppingList)
            })
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .border(2.dp, MaterialTheme.colorScheme.outline, itemShape)
            .defaultMinSize(minHeight = 48.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = shoppingList.name,
        )
        Text(
            text = createdAt,
        )
    }
}

@Composable
private fun rememberDateTime(value: Long): String = remember(value) {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    formatter.format(value)
}

@Preview
@Composable
private fun ListsScreenPreview() {
    ListCartTheme {
        ListsScreen(
            lists = buildList {
                repeat(10) {
                    add(ShoppingList(it.toString(), "List $it", it - 1L))
                }
            },
            onNavigateToList = {},
        )
    }
}