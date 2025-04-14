package br.com.lucasaraujo.trabalhopratico03crudusuario.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class UsuariosDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var Instance: UsuariosDatabase? = null

        fun getDatabase(context: Context): UsuariosDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UsuariosDatabase::class.java, "usuario_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}