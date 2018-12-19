package com.example.klmpk7.rekrut_or;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity
{
    TextView nim, nama, tmptLahir, tglLahir, alamat, motivasi;
    ImageView favoriteImage;
    apiAnggotaClient mApiService;
    Integer id;
    Boolean favorit;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        mContext = this;

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
            id = anggota.id;
            nim.setText(anggota.nim);
            nama.setText(anggota.nama);
            tmptLahir.setText(anggota.tmpt_lahir);
            tglLahir.setText(anggota.tgl_lahir);
            alamat.setText(anggota.alamat);
            motivasi.setText(anggota.motivasi);
            favorit = anggota.favorit;
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

        changeIconFavorite();
    }

    public void changeIconFavorite(){
        if(favorit.equals(true)){
            favoriteImage = findViewById(R.id.unfavorite);
            favoriteImage.setImageResource(R.drawable.favorite);
        }else{
            favoriteImage = findViewById(R.id.unfavorite);
            favoriteImage.setImageResource(R.drawable.unfavorite);
        }
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
        if (shareIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(shareIntent);
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

        mApiService.addFavorite(1).enqueue(new Callback<ResponseBody>() {

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