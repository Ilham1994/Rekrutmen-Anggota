package com.example.klmpk7.rekrut_or.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.klmpk7.rekrut_or.Anggota;
import com.example.klmpk7.rekrut_or.R;

import java.util.ArrayList;

public class AnggotaFavAdapter extends RecyclerView.Adapter<AnggotaFavAdapter.AnggotaFavHolder>
{
    ArrayList<Anggota> dataAnggota;

    public void setDataAnggota(ArrayList<Anggota> anggotas)
    {
        this.dataAnggota = anggotas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnggotaFavHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View favview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.anggota_favorit, viewGroup, false);
        AnggotaFavHolder favHolder = new AnggotaFavHolder(favview);
        return favHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnggotaFavHolder favHolder, int i)
    {
        Anggota anggota = dataAnggota.get(i);
        favHolder.nama.setText(anggota.nama);
        favHolder.nim.setText(anggota.nim);
    }

    public class AnggotaFavHolder extends RecyclerView.ViewHolder
    {
        TextView nama, nim;

        public AnggotaFavHolder(View itemView)
        {
            super(itemView);
            nama = itemView.findViewById(R.id.textView4);
            nim = itemView.findViewById(R.id.textView5);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
