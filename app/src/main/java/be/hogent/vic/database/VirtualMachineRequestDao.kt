package be.hogent.vic.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface VirtualMachineRequestDao {
    @Query("SELECT * FROM virtual_machine_requests")
    fun getAll(): LiveData<List<VirtualMachineRequestDatabaseDto>>

    @Query("SELECT * FROM virtual_machine_requests WHERE id = :id")
    fun getById(id: Int): VirtualMachineRequestDatabaseDto?

    @Update
    fun update(request: VirtualMachineRequestDatabaseDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg virtualMachineRequests: VirtualMachineRequestDatabaseDto)
}
