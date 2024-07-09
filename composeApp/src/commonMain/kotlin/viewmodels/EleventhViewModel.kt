package viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EleventhViewModel : ViewModel() {
    private val _countDownText = MutableStateFlow("")
    val countDownText: StateFlow<String> = _countDownText.asStateFlow()

    init {
        viewModelScope.launch {
            for (i in 10 downTo 0) {
                _countDownText.value = i.toString()
                delay(1000)
            }
        }
    }
}