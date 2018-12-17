package com.example.klmpk7.rekrut_or.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ListAnggota.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract ListAnggotaDao ListAnggotadao();
}
