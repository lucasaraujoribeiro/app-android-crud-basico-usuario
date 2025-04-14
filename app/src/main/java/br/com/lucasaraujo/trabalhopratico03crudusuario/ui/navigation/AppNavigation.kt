package br.com.lucasaraujo.trabalhopratico03crudusuario.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.lucasaraujo.trabalhopratico03crudusuario.data.Usuario
import br.com.lucasaraujo.trabalhopratico03crudusuario.ui.UserFormScreen
import br.com.lucasaraujo.trabalhopratico03crudusuario.ui.UserListScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    users: List<Usuario>,
    onSaveUser: (Usuario) -> Unit,
    onDeleteUser: (Usuario) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "user_list"
    ) {
        composable("user_list") {
            UserListScreen(
                navController = navController,
                users = users,
                onDeleteUser = onDeleteUser
            )
        }

        composable("user_form") {
            UserFormScreen(
                navController = navController,
                onSaveUser = onSaveUser
            )
        }

        composable("user_form/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            val user = users.find { it.id == userId }
            UserFormScreen(
                navController = navController,
                user = user,
                onSaveUser = onSaveUser
            )
        }
    }
} 