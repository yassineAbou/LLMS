import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import kotlinx.coroutines.flow.asStateFlow


class ProfileViewModel: ViewModel() {
    private val _userUiState = MutableStateFlow<UserUiState?>(viewModelScope,null)
    val userUiState =  _userUiState.asStateFlow()

    private val _showBottomSheet = MutableStateFlow(viewModelScope,false)
    val showBottomSheet = _showBottomSheet.asStateFlow()

    fun onLogin() {
        _showBottomSheet.value = true
    }

    fun onLogout() {
        _userUiState.value = null
    }

    fun onAuthenticated() {
        _userUiState.value = prototypeUser
    }

    fun onDismissBottomSheet() {
        _showBottomSheet.value = false
    }

}