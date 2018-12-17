package com.example.klmpk7.rekrut_or.Adapter;

import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

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
        holder.id.setText(anggota.id);
        holder.nama.setText(anggota.nama);

        //gambar
//        Glide.with(holder.itemView).load
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
        TextView id, nama;

        public AnggotaHolder(@NonNull View itemView)
        {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            nama = itemView.findViewById(R.id.nama);

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

    private interface OnItemClick
    {
        void click(Anggota anggota);
    }
}
