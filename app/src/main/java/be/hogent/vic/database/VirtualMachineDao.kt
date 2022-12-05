package be.hogent.vic.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VirtualMachineDao {
    @Query("SELECT * FROM virtual_machines")
    fun getVideos(): LiveData<List<VirtualMachineDatabaseDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg virtualMachines: VirtualMachineDatabaseDto)
}