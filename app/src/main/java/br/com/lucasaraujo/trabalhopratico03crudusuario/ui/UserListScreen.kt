package br.com.lucasaraujo.trabalhopratico03crudusuario.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.lucasaraujo.trabalhopratico03crudusuario.data.Usuario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navController: NavController,
    users: List<Usuario>,
    onDeleteUser: (Usuario) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Usuários") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("user_form") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar usuário")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(users) { user ->
                UserListItem(
                    user = user,
                    onEdit = { navController.navigate("user_form/${user.id}") },
                    onDelete = { onDeleteUser(user) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListItem(
    user: Usuario,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = user.nome,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Idade: ${user.idade}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Deletar")
                }
            }
        }
    }
} 