package com.example.laboratorio05.data.estadistica

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="estadistica")
class Estadistica(

    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

    @ColumnInfo(name="distrito")
    val distrito: String,
    @ColumnInfo(name="positivosVivos")
    val positivosVivos: Int,
    @ColumnInfo(name="positivosDefuncion")
    val positivosDefucion: Int,
    @ColumnInfo(name="negativo")
    val negativos: Int,
    @ColumnInfo(name="pendientes")
    val pendientes: Int


) : Parcelable