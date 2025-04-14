package br.com.lucasaraujo.trabalhopratico03crudusuario.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.lucasaraujo.trabalhopratico03crudusuario.data.Usuario
import br.com.lucasaraujo.trabalhopratico03crudusuario.data.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios.asStateFlow()

    init {
        carregarUsuarios()
    }

    private fun carregarUsuarios() {
        viewModelScope.launch {
            repository.getAllUsuarios().collect { lista ->
                _usuarios.value = lista
            }
        }
    }

    fun addUsuario(nome: String, email: String, idade: String) {
        viewModelScope.launch {
            val usuario = Usuario(
                nome = nome,
                email = email,
                idade = idade.toIntOrNull() ?: 0
            )
            repository.insertUsuario(usuario)
        }
    }

    fun updateUsuario(usuario: Usuario) {
        viewModelScope.launch {
            repository.updateUsuario(usuario)
        }
    }

    fun deleteUsuario(usuario: Usuario) {
        viewModelScope.launch {
            repository.deleteUsuario(usuario)
        }
    }
}

class UsuarioViewModelFactory(private val repository: UsuarioRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsuarioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 