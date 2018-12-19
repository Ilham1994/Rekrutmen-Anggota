package com.example.klmpk7.rekrut_or;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TambahData extends AppCompatActivity implements View.OnClickListener
{
    private EditText mtambahNama,mtambahNim,mtambahTempat,mtambahTanggal,mtambahAlamat,mtambahMotivasi;
    private ImageButton btnFoto;
    private Button save;
    //private TextView tambahNama,tambahNim,tambahTempat,tambahTanggal,tambahAlamat,tambahMotivasi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_anggota);

//        try {
//            String hNama = mtambahNama.getText().toString();//getdata
//            String hNim = mtambahNim.getText().toString();
//            String hTempat = mtambahTempat.getText().toString();
//            String hTanggal = mtambahTanggal.getText().toString();
//            String hAlamat = mtambahAlamat.getText().toString();
//            String hMotivasi = mtambahMotivasi.getText().toString();
//
//        } catch (NumberFormatException e) {
//            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
//        }

    }

    public void inputAnggota(View v) {
        mtambahNama = findViewById(R.id.edit_nama);
        mtambahNim = findViewById(R.id.edit_nim);
        mtambahTempat = findViewById(R.id.edit_tempat_lahir);
        mtambahTanggal = findViewById(R.id.edit_tanggal_lahir);
        mtambahAlamat = findViewById(R.id.edit_alamat);
        mtambahMotivasi = findViewById(R.id.edit_motivasi);
        btnFoto = findViewById(R.id.button_foto);

        save = findViewById(R.id.simpan);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {

    }
}
