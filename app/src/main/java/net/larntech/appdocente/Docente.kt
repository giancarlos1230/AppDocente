package net.larntech.appdocente

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "tbdocentes")
class Docente(
    val nombre: String,
    val salario: Double,
    val direccion: String,

    @PrimaryKey(autoGenerate = true)
    var idDocente: Int = 0
) : Serializable