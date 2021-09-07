package net.larntech.appdocente

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DocenteDao {
    @Query( "SELECT * FROM tbdocentes")
    fun getAll() :  LiveData<List<Docente>>

    @Query( "SELECT * FROM tbdocentes where idDocente = :id")
    fun get(id: Int): LiveData<Docente>

    @Insert
    fun insertAll(vararg docentes: Docente)

    @Update
    fun update(docente: Docente)

    @Delete
    fun delete(docente: Docente)
}