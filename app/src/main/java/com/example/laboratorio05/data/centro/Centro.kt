package com.example.laboratorio05.data.centro

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "centro")
class Centro(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "latitud")
    val latitud: Double,
    @ColumnInfo(name = "longitud")
    val longitud: Double,
    @ColumnInfo(name = "departamento")
    val departamento: String,
    @ColumnInfo(name = "horario")
    val horario: String,
    @ColumnInfo(name = "direccion")
    val direccion: String
) : Parcelable