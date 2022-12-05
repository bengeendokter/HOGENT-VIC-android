package be.hogent.vic.database

import android.content.Context
import androidx.room.*

@Database(entities = [VirtualMachineDatabaseDto::class], version = 1)
abstract class VicDatabase: RoomDatabase() {
    abstract val virtualMachineDao: VirtualMachineDao
}

private lateinit var INSTANCE: VicDatabase

fun getDatabase(context: Context): VicDatabase {
    synchronized(VicDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                VicDatabase::class.java,
                "vic"
            ).build()
        }

        return INSTANCE
    }
}