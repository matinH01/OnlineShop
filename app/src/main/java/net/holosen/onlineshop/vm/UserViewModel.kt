package net.holosen.onlineshop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.holosen.onlineshop.model.customer.UserDto
import net.holosen.onlineshop.repository.customer.UserEntityRepository
import net.holosen.onlineshop.repository.customer.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val entityRepository: UserEntityRepository,
    private val repository: UserRepository
) : ViewModel() {

    val currentUser = entityRepository.getCurrentUser()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            entityRepository.deleteAll()
        }
    }

    fun changePassword(user: UserDto, token: String) {
        viewModelScope.launch {
            repository.update(
                user,
                token
            )
        }
    }
}