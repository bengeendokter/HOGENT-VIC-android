package be.hogent.vic.database

import androidx.lifecycle.LiveData
import androidx.room.* // ktlint-disable no-wildcard-imports

@Dao
interface ClientDao {
    @Query("SELECT * FROM clients")
    fun getAll(): LiveData<List<ClientDatabaseDto>>

    @Query("SELECT * FROM clients WHERE id = :id")
    fun getById(id: Int): ClientDatabaseDto?

    @Update
    fun update(client: ClientDatabaseDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg clients: ClientDatabaseDto)
}
