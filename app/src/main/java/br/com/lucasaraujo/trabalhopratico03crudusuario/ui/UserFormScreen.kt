package br.com.lucasaraujo.trabalhopratico03crudusuario.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.lucasaraujo.trabalhopratico03crudusuario.data.Usuario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen(
    navController: NavController,
    user: Usuario? = null,
    onSaveUser: (Usuario) -> Unit
) {
    var nome by remember { mutableStateOf(user?.nome ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var idade by remember { mutableStateOf(user?.idade?.toString() ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (user == null) "Novo Usuário" else "Editar Usuário") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text("Idade") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val idadeInt = idade.toIntOrNull() ?: 0
                    val newUser = Usuario(
                        id = user?.id ?: 0,
                        nome = nome,
                        email = email,
                        idade = idadeInt
                    )
                    onSaveUser(newUser)
                    navController.navigateUp()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar")
            }
        }
    }
} 