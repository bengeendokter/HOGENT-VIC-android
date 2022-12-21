package be.hogent.vic.database // ktlint-disable filename

import android.content.Context
import androidx.room.* // ktlint-disable no-wildcard-imports
import be.hogent.vic.util.Converters

@Database(entities = [VirtualMachineDatabaseDto::class, VirtualMachineRequestDatabaseDto::class, ClientDatabaseDto::class], version = 1)
@TypeConverters(Converters::class)
abstract class VicDatabase : RoomDatabase() {
    abstract val virtualMachineDao: VirtualMachineDao
    abstract val virtualMachineRequestDao: VirtualMachineRequestDao
    abstract val clientDao: ClientDao
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
