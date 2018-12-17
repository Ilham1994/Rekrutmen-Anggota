package com.example.klmpk7.rekrut_or.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ListAnggotaDao
{
    @Query("SELECT * FROM anggota_sekarang")
    List<ListAnggota> getDataAnggota();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnggotaSekarang(ListAnggota listAnggota);

    @Delete
    void delete(ListAnggota listAnggota);
}
