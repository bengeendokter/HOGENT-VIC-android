package be.hogent.vic.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.hogent.vic.domain.VirtualMachineRequest

@Dao
interface VirtualMachineRequestDao {
    @Query("SELECT * FROM virtual_machine_requests")
    fun getVirtualMachineRequests(): LiveData<List<VirtualMachineRequestDatabaseDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg virtualMachineRequests: VirtualMachineRequestDatabaseDto)
}