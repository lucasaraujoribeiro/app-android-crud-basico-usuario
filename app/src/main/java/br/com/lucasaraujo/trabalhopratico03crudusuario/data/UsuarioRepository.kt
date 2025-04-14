package br.com.lucasaraujo.trabalhopratico03crudusuario.data

import kotlinx.coroutines.flow.Flow

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    fun getAllUsuarios(): Flow<List<Usuario>> = usuarioDao.getAllUsuarios()

    fun getUsuarioStream(id: Int): Flow<Usuario?> = usuarioDao.getUsuario(id)

    suspend fun insertUsuario(usuario: Usuario) {
        usuarioDao.insertUsuario(usuario)
    }

    suspend fun deleteUsuario(usuario: Usuario) {
        usuarioDao.deleteUsuario(usuario)
    }

    suspend fun updateUsuario(usuario: Usuario) {
        usuarioDao.updateUsuario(usuario)
    }
} 