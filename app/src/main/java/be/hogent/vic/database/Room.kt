package be.hogent.vic.database

import android.content.Context
import androidx.room.*
import be.hogent.vic.util.Converters

@Database(entities = [VirtualMachineDatabaseDto::class, VirtualMachineRequestDatabaseDto::class], version = 1)
@TypeConverters(Converters::class)
abstract class VicDatabase: RoomDatabase() {
    abstract val virtualMachineDao: VirtualMachineDao
    abstract val virtualMachineRequestDao: VirtualMachineRequestDao
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