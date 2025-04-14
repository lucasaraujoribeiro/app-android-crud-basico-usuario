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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.lucasaraujo.trabalhopratico03crudusuario.data.Usuario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioScreen(
    usuarios: List<Usuario>,
    onAddUsuario: (String, String, String) -> Unit,
    onEditUsuario: (Usuario) -> Unit,
    onDeleteUsuario: (Usuario) -> Unit,
    onNavigateToAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showEditDialog by remember { mutableStateOf<Usuario?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Usuários") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar usuário")
            }
        }
    ) { padding ->
        if (usuarios.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nenhum usuário cadastrado.\nClique no botão + para adicionar.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(usuarios) { usuario ->
                    UsuarioItem(
                        usuario = usuario,
                        onEdit = { showEditDialog = usuario },
                        onDelete = { onDeleteUsuario(usuario) }
                    )
                }
            }
        }
    }

    showEditDialog?.let { usuario ->
        var editNome by remember { mutableStateOf(usuario.nome) }
        var editEmail by remember { mutableStateOf(usuario.email) }
        var editIdade by remember { mutableStateOf(usuario.idade.toString()) }

        AlertDialog(
            onDismissRequest = { showEditDialog = null },
            title = { Text("Editar Usuário") },
            text = {
                Column {
                    TextField(
                        value = editNome,
                        onValueChange = { editNome = it },
                        label = { Text("Nome") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = editEmail,
                        onValueChange = { editEmail = it },
                        label = { Text("Email") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = editIdade,
                        onValueChange = { editIdade = it },
                        label = { Text("Idade") }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (editNome.isNotBlank() && editEmail.isNotBlank() && editIdade.isNotBlank()) {
                            val usuarioAtualizado = usuario.copy(
                                nome = editNome,
                                email = editEmail,
                                idade = editIdade.toIntOrNull() ?: usuario.idade
                            )
                            onEditUsuario(usuarioAtualizado)
                            showEditDialog = null
                        }
                    }
                ) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioItem(
    usuario: Usuario,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                    text = usuario.nome,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = usuario.email,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Idade: ${usuario.idade}",
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