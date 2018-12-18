package com.example.klmpk7.rekrut_or;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity
{
    TextView nim, nama, tmptLahir, tglLahir, alamat, motivasi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        tmptLahir = findViewById(R.id.tmpt_lahir);
        tglLahir = findViewById(R.id.tgl_lahir);
        alamat = findViewById(R.id.alamat);
        motivasi = findViewById(R.id.motivasi);

        Intent intent = getIntent();
        if(intent != null)
        {
            Anggota anggota = intent.getParcelableExtra("anggota_extra_key");
            nim.setText(anggota.nim);
            nama.setText(anggota.nama);
            tmptLahir.setText(anggota.tmpt_lahir);
            tglLahir.setText(anggota.tgl_lahir);
            alamat.setText(anggota.alamat);
            motivasi.setText(anggota.motivasi);
        }
    }
}