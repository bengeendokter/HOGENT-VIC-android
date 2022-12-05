package be.hogent.vic.database

import android.content.Context
import androidx.room.*

@Database(entities = [VirtualMachineDatabaseDto::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract val virtualMachineDao: VirtualMachineDao
}

private lateinit var INSTANCE: Database

fun getDatabase(context: Context): Database {
    synchronized(Database::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                "vic"
            ).build()
        }

        return INSTANCE
    }
}