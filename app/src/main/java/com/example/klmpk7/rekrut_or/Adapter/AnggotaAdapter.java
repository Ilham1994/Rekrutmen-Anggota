package com.example.klmpk7.rekrut_or.Adapter;

import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.klmpk7.rekrut_or.Anggota;
import com.example.klmpk7.rekrut_or.MainActivity;
import com.example.klmpk7.rekrut_or.R;

import java.util.ArrayList;

public class AnggotaAdapter extends RecyclerView.Adapter<AnggotaAdapter.AnggotaHolder>
{

    ArrayList<Anggota> dataAnggota;
    OnItemClick handler;

    public void setDataAnggota(ArrayList<Anggota> anggotas)
    {
        this.dataAnggota = anggotas;
        notifyDataSetChanged();
    }

    public void setHandler(OnItemClick clickHandler)
    {
        handler = clickHandler;
    }

    @NonNull
    @Override
    public AnggotaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.anggota_row, viewGroup, false);
        AnggotaHolder holder = new AnggotaHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnggotaHolder holder, int i)
    {
        Anggota anggota = dataAnggota.get(i);
        holder.nama.setText(anggota.nama);
        holder.nim.setText(anggota.nim);
        //gambar
        String url_gambar = "https://cryptic-ridge-20830.herokuapp.com/img/" + anggota.foto;
        Glide.with(holder.itemView)
                .load(url_gambar)
                .into(holder.fotoAnggota);
    }

    @Override
    public int getItemCount()
    {
        if(dataAnggota != null)
        {
            return dataAnggota.size();
        }
        return 0;
    }

    public class AnggotaHolder extends RecyclerView.ViewHolder
    {
        TextView  nama,nim;
        ImageView fotoAnggota;

        public AnggotaHolder(@NonNull View itemView)
        {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            nim = itemView.findViewById(R.id.nim);
            fotoAnggota = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int position = getAdapterPosition();
                    Anggota anggota = dataAnggota.get(position);
                    handler.click(anggota);
                }
            });
        }
    }

    public interface OnItemClick
    {
        void click(Anggota anggota);
    }
}
