package com.example.klmpk7.rekrut_or;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        Button button = (Button) findViewById(R.id.share);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                shareText(v);
            }
        });
    }

    public void shareText(View view){
        String dataNim = nim.getText().toString();
        String dataNama= nama.getText().toString();
        String dataTempat= tmptLahir.getText().toString();
        String dataTanggal= tglLahir.getText().toString();
        String dataAlamat= alamat.getText().toString();
        String dataMotivasi= motivasi.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "NIM: " + dataNim + "\n"
                + "Nama: " + dataNama + "\n"
                + "Tempat Lahir: " + dataTempat + "\n"
                + "Tanggal Lahir: " + dataTanggal+ "\n"
                + "Alamat: " + dataAlamat + "\n"
                + "Motivasi: " + dataMotivasi + "\n");
        if (shareIntent.resolveActivity(getPackageManager()) != null){
            startActivity(shareIntent);
        }
    }
}