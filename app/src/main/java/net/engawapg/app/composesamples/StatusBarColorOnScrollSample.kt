package net.engawapg.app.composesamples

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusBarColorOnScrollSample() {
    val topAppBarState = rememberTopAppBarState()
    val colorTransitionFraction by remember {
        derivedStateOf { topAppBarState.overlappedFraction }
    }
    val statusBarColor = TopAppBarDefaults.centerAlignedTopAppBarColors()
        .containerColor(colorTransitionFraction).value
    val systemUiController = rememberSystemUiController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("TopAppBar") },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(100) { count ->
                Text(
                    text = "Item ${count + 1}",
                    modifier = Modifier.fillMaxWidth().height(30.dp).padding(20.dp, 4.dp)
                )
            }
        }
    }
}