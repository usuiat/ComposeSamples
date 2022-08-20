package net.engawapg.app.composesamples

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowRepository {
    val sharedFlow = MutableSharedFlow<Int>()
    private var emitValue = 1

    suspend fun load() {
        sharedFlow.emit(emitValue)
        emitValue++
    }
}

class FlowViewModel: ViewModel() {
    private val repository = FlowRepository()
    val stateFlow: StateFlow<String> = repository.sharedFlow.map { value ->
        "Loaded: $value"
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "Loading"
    )

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                repository.load()
            }
        }
    }
}

@Composable
fun FlowSample(viewModel: FlowViewModel = viewModel()) {
    val text by viewModel.stateFlow.collectAsState()
    Text(
        text = text,
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth().padding(50.dp)
    )
}