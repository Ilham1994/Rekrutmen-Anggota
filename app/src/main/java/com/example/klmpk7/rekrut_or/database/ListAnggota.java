package com.example.klmpk7.rekrut_or.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "anggota_sekarang")
public class ListAnggota
{
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "tmpt_lahir")
    public String tmpt_lahir;

    @ColumnInfo(name = "tgl_lahir")
    public String tgl_lahir;

    @ColumnInfo(name = "jurusan")
    public String jurusan;

    @ColumnInfo(name = "angkatan")
    public String angkatan;

    @ColumnInfo(name = "target_divisi")
    public int target_divisi;

    @ColumnInfo(name = "motivasi")
    public String motivasi;
}
