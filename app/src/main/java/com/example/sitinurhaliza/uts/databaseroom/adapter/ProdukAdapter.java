package com.example.sitinurhaliza.uts.databaseroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sitinurhaliza.uts.databaseroom.R;
import com.example.sitinurhaliza.uts.databaseroom.model.Produk;

import java.util.List;

public class ProdukAdapter extends BaseAdapter {

    Context context;
    List<Produk> produkList;

    public ProdukAdapter(Context context, List<Produk> produkList) {
        this.context = context;
        this.produkList = produkList;
    }

    @Override
    public int getCount() {
        return produkList.size();
    }

    @Override
    public Object getItem(int position) {
        return produkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }

        TextView tvNama = convertView.findViewById(R.id.tv_nama);
        TextView tvDeskripsi = convertView.findViewById(R.id.tv_deskripsi);

        Produk produk = produkList.get(position);
        tvNama.setText(produk.nama);
        tvDeskripsi.setText(produk.deskripsi);

        return convertView;
    }
}
