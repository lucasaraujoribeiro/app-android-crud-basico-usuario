package br.com.lucasaraujo.trabalhopratico03crudusuario.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.lucasaraujo.trabalhopratico03crudusuario.data.Usuario

sealed class Screen(val route: String) {
    object UsuarioList : Screen("usuario_list")
    object AddUsuario : Screen("add_usuario")
}

@Composable
fun UsuarioNavigation(
    usuarios: List<Usuario>,
    onAddUsuario: (String, String, String) -> Unit,
    onEditUsuario: (Usuario) -> Unit,
    onDeleteUsuario: (Usuario) -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.UsuarioList.route,
        modifier = modifier
    ) {
        composable(Screen.UsuarioList.route) {
            UsuarioScreen(
                usuarios = usuarios,
                onAddUsuario = onAddUsuario,
                onEditUsuario = onEditUsuario,
                onDeleteUsuario = onDeleteUsuario,
                onNavigateToAdd = { navController.navigate(Screen.AddUsuario.route) }
            )
        }
        
        composable(Screen.AddUsuario.route) {
            AddUsuarioScreen(
                onNavigateBack = { navController.popBackStack() },
                onAddUsuario = { nome, email, idade ->
                    onAddUsuario(nome, email, idade)
                    navController.popBackStack()
                }
            )
        }
    }
} 