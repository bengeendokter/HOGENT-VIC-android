package be.hogent.vic.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface VirtualMachineDao {
    @Query("SELECT * FROM virtual_machines")
    fun getVirtualMachines(): LiveData<List<VirtualMachineDatabaseDto>>

    @Query("SELECT * FROM virtual_machines WHERE id = :id")
    fun getVirtualMachine(id: Int): VirtualMachineDatabaseDto?

    @Update
    fun update(vm: VirtualMachineDatabaseDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg virtualMachines: VirtualMachineDatabaseDto)
}