package net.holosen.onlineshop.vm

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.holosen.onlineshop.model.customer.LoginRequestDto
import net.holosen.onlineshop.model.customer.UserDto
import net.holosen.onlineshop.model.customer.mapper.toEntity
import net.holosen.onlineshop.repository.customer.UserEntityRepository
import net.holosen.onlineshop.repository.customer.UserRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userEntityRepository: UserEntityRepository
) : BaseViewModel() {

    fun login(
        username: String,
        password: String,
        onLoading: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: (UserDto) -> Unit
    ) {
        val data = LoginRequestDto(
            username = username,
            password = password
        )

        loadData({
            when {
                it.isLoading -> onLoading()
                it.error != null -> onError(it.error)
                it.data != null -> {
                    val user = it.data[0]
                    viewModelScope.launch(Dispatchers.IO) {
                        userEntityRepository.insert(it.data[0].toEntity())
                    }
                    onSuccess(user)
                }
            }
        }) {
            userRepository.login(data)
        }
    }
}