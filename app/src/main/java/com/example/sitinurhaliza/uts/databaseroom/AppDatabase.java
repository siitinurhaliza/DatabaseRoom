package com.example.sitinurhaliza.uts.databaseroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sitinurhaliza.uts.databaseroom.dao.ProdukDao;
import com.example.sitinurhaliza.uts.databaseroom.model.Produk;

@Database(entities = {Produk.class}, version = 1)

public abstract  class AppDatabase extends RoomDatabase {

    public abstract ProdukDao produkDao();
}
