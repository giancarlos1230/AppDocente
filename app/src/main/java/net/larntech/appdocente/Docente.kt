package net.larntech.appdocente

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "tbdocentes")
class Docente(
    val nombre: String,
    val apellido: String,
    val sexo: String,
    val fechaNacimiento: String,
    val ciudad: String,
    val direccion: String,
    val padecimiento: String,
    val celular: Double,
    val fechaIngreso :String,
    val salario: Double,

    @PrimaryKey(autoGenerate = true)
    var idDocente: Int = 0
) : Serializable