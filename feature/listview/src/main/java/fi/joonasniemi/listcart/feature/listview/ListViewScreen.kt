package fi.joonasniemi.listcart.feature.listview

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fi.joonasniemi.listcart.core.model.data.ShoppingItem
import fi.joonasniemi.listcart.core.model.data.ShoppingList
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListViewRoot(
    viewModel: ListViewViewModel = koinViewModel(),
) {
    val listInfo by viewModel.listInfo.collectAsStateWithLifecycle()
    val listItems by viewModel.listItems.collectAsStateWithLifecycle()

    ListViewScreen(
        listInfo = listInfo,
        listItems = listItems,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListViewScreen(
    listInfo: ListInfo,
    listItems: List<ShoppingItem>,
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
                    Text(listInfo.name)
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(listItems) {
                ListItem(
                    item = it,
                )
            }
        }
    }
}

private val itemShape = RoundedCornerShape(8.dp)

@Composable
private fun ListItem(
    item: ShoppingItem,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(itemShape)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .border(2.dp, MaterialTheme.colorScheme.outline, itemShape)
            .defaultMinSize(minHeight = 48.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = item.name,
        )
    }
}