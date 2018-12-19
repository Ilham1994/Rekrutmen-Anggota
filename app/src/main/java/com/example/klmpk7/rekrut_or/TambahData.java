package com.example.klmpk7.rekrut_or;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TambahData extends AppCompatActivity implements View.OnClickListener
{
    private EditText mtambahNama,mtambahNim,mtambahTempat,mtambahTanggal,mtambahAlamat,mtambahMotivasi;
    private ImageButton btnFoto;
    private Button save;
    //private TextView tambahNama,tambahNim,tambahTempat,tambahTanggal,tambahAlamat,tambahMotivasi;

    private static int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;
    ImageView imgView;
    String imagebase64string;

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

        ImageView button = (ImageView) findViewById(R.id.edit_foto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

    }

    public void inputAnggota(View v) {
        mtambahNama = findViewById(R.id.edit_nama);
        mtambahNim = findViewById(R.id.edit_nim);
        mtambahTempat = findViewById(R.id.edit_tempat_lahir);
        mtambahTanggal = findViewById(R.id.edit_tanggal_lahir);
        mtambahAlamat = findViewById(R.id.edit_alamat);
        mtambahMotivasi = findViewById(R.id.edit_motivasi);
//        btnFoto = findViewById(R.id.button_foto);

        save = findViewById(R.id.simpan);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {

    }

    public void takePicture(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imgView = (ImageView) findViewById(R.id.edit_foto);
            imgView.setImageBitmap(imageBitmap);
            setPhoto(imageBitmap);
        }
    }

    private void setPhoto(Bitmap bitmapm) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmapm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

            byte[] byteArrayImage = baos.toByteArray();
            imagebase64string = Base64.encodeToString(byteArrayImage,Base64.DEFAULT);
            Toast.makeText(this, "Upload Gambar Berhasil!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFavoriteToServer(View v){
//        String API_BASE_URL = "https://cryptic-ridge-20830.herokuapp.com/api/";
        String API_BASE_URL = "https://cryptic-ridge-20830.herokuapp.com/api/";

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(API_BASE_URL) //Setting the Root URL
                .addConverterFactory(GsonConverterFactory.create())
                .build(); //Finally building the adapter


        mApiService = adapter.create(apiAnggotaClient.class);

        mApiService.storeAnggota().addFavorite(1).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("success")){

                            if(jsonRESULTS.getString("data").equals(1)){
                                favoriteImage = findViewById(R.id.unfavorite);
                                favoriteImage.setImageResource(R.drawable.unfavorite);
                                Toast.makeText(mContext, "Anggota Ditambahkan ke Favorit!", Toast.LENGTH_LONG).show();
                            }else{
                                favoriteImage = findViewById(R.id.unfavorite);
                                favoriteImage.setImageResource(R.drawable.unfavorite);
                                Toast.makeText(mContext, "Anggota Dihapus dari Favorit!", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            String error_message = jsonRESULTS.getString("error_msg");
                            Toast.makeText(mContext, error_message, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(mContext, "Terdapat Masalah!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(mContext, "Masalah Koneksi Jaringan!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext, "Server Sedang Maintenance!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Masalah Koneksi Jaringan!", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}
