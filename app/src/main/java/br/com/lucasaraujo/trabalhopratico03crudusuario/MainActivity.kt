package br.com.lucasaraujo.trabalhopratico03crudusuario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import br.com.lucasaraujo.trabalhopratico03crudusuario.data.UsuarioRepository
import br.com.lucasaraujo.trabalhopratico03crudusuario.data.UsuariosDatabase
import br.com.lucasaraujo.trabalhopratico03crudusuario.ui.navigation.AppNavigation
import br.com.lucasaraujo.trabalhopratico03crudusuario.ui.theme.TrabalhoPratico03CrudUsuarioTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var usuarioRepository: UsuarioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = UsuariosDatabase.getDatabase(this)
        usuarioRepository = UsuarioRepository(database.usuarioDao())

        setContent {
            TrabalhoPratico03CrudUsuarioTheme {
                val navController = rememberNavController()
                val users by usuarioRepository.getAllUsuarios().collectAsStateWithLifecycle(initialValue = emptyList())
                val scope = rememberCoroutineScope()

                AppNavigation(
                    navController = navController,
                    users = users,
                    onSaveUser = { user ->
                        scope.launch {
                            if (user.id == 0) {
                                usuarioRepository.insertUsuario(user)
                            } else {
                                usuarioRepository.updateUsuario(user)
                            }
                        }
                    },
                    onDeleteUser = { user ->
                        scope.launch {
                            usuarioRepository.deleteUsuario(user)
                        }
                    }
                )
            }
        }
    }
}