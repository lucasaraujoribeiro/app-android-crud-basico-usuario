package br.com.lucasaraujo.trabalhopratico03crudusuario.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios ORDER BY nome ASC")
    fun getAllUsuarios(): Flow<List<Usuario>>

    @Query("SELECT * FROM usuarios WHERE id = :id")
    fun getUsuario(id: Int): Flow<Usuario?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsuario(usuario: Usuario)

    @Update
    suspend fun updateUsuario(usuario: Usuario)

    @Delete
    suspend fun deleteUsuario(usuario: Usuario)
}