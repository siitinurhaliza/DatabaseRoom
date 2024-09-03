package com.example.sitinurhaliza.uts.databaseroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sitinurhaliza.uts.databaseroom.adapter.ProdukAdapter;
import com.example.sitinurhaliza.uts.databaseroom.dao.ProdukDao;
import com.example.sitinurhaliza.uts.databaseroom.model.Produk;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ListView lvProduk;
    FloatingActionButton fabTambah;
    AppDatabase db;
    ProdukDao produkDao;

    ProdukAdapter adapter;
    List<Produk> produkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProduk = findViewById(R.id.lv_produk);
        fabTambah = findViewById(R.id.fab_Tambah);

        // Inisialisasi database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "produk_db").build();
        produkDao = db.produkDao();

        // Penggunaan Executor untuk operasi database
        Executor executor = Executors.newSingleThreadExecutor();

        fabTambah.setOnClickListener(v -> bukaDialogTambah(executor));

        tampilkanSemuaData(executor);

        lvProduk.setOnItemClickListener((parent, view, position, id) -> {
            Produk produk = produkList.get(position);
            bukaDialogUpdateData(produk, executor);
        });

    }

    private void bukaDialogUpdateData(Produk produk, Executor executor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update atau Hapus Produk");
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.from_update, null);
        builder.setView(dialogView);

        EditText etNama = dialogView.findViewById(R.id.lv_produk);
        EditText etDeskripsi = dialogView.findViewById(R.id.tv_deskripsi);
        Button btnUpdate = dialogView.findViewById(R.id.btn_update);
        Button btnHapus = dialogView.findViewById(R.id.btn_delete);

        etNama.setText(produk.nama);
        etDeskripsi.setText(produk.deskripsi);

        AlertDialog dialog = builder.create();

        btnUpdate.setOnClickListener(v -> {
            if (etNama.getText().toString().trim().isEmpty() ||
                    etDeskripsi.getText().toString().trim().isEmpty()) {
                if (etNama.getText().toString().trim().isEmpty()) {
                    etNama.setError("Nama Harus Diisi");
                }
                if (etDeskripsi.getText().toString().trim().isEmpty()) {
                    etDeskripsi.setError("Deskripsi Harus Diisi");
                }
                return;
            }

            produk.nama = etNama.getText().toString();
            produk.deskripsi = etDeskripsi.getText().toString();

            executor.execute(() -> {
                produkDao.update(produk);
                runOnUiThread(() -> {
                    tampilkanSemuaData(executor);
                    dialog.dismiss();
                });
            });
        });

        btnHapus.setOnClickListener(v -> {
            executor.execute(() -> {
                produkDao.delete(produk);
                runOnUiThread(() -> {
                    tampilkanSemuaData(executor);
                    dialog.dismiss();
                });
            });
        });

        dialog.show();
    }


    private void bukaDialogTambah(Executor executor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Produk");
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.from_tambah, null);
        builder.setView(dialogView);

        EditText etNama = dialogView.findViewById(R.id.lv_produk);
        EditText etDeskripsi = dialogView.findViewById(R.id.tv_deskripsi);
        Button btnSimpan = dialogView.findViewById(R.id.btn_tambah);

        AlertDialog dialog = builder.create();
        btnSimpan.setOnClickListener(v -> {
            if (etNama.getText().toString().trim().isEmpty() ||
                    etDeskripsi.getText().toString().trim().isEmpty()) {
                if (etNama.getText().toString().trim().isEmpty()) {
                    etNama.setError("Nama Harus Diisi");
                }
                if (etDeskripsi.getText().toString().trim().isEmpty()) {
                    etDeskripsi.setError("Deskripsi Harus Diisi");
                }
                return;
            }

            Produk produk = new Produk();
            produk.nama = etNama.getText().toString();
            produk.deskripsi = etDeskripsi.getText().toString();

            executor.execute(() -> {
                produkDao.insert(produk);
                runOnUiThread(() -> {
                    tampilkanSemuaData(executor);
                    dialog.dismiss();
                });
            });
        });
        dialog.show();
    }

    private void tampilkanSemuaData(Executor executor) {
        executor.execute(() -> {
            produkList = produkDao.getAll();
            runOnUiThread(() -> {
                adapter = new ProdukAdapter(this, produkList);
                lvProduk.setAdapter(adapter);
            });
        });
    }


}
