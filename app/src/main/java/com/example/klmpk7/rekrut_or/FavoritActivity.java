package com.example.klmpk7.rekrut_or;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.klmpk7.rekrut_or.Adapter.AnggotaFavAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoritActivity extends AppCompatActivity
{
    RecyclerView favRecyclerView;
    AnggotaFavAdapter afAdapter;
    ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anggota_favorit);

        afAdapter = new AnggotaFavAdapter();

        //set recyclerview
        favRecyclerView = findViewById(R.id.rv_anggota_favorit);
        favRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favRecyclerView.setAdapter(afAdapter);

        getAnggotaFavSekarangList();
    }
    private void getAnggotaFavSekarangList()
    {
        if(isConnected())
        {
            //Ambil Data
            apiAnggotaClient client = (new Retrofit.Builder()
                    .baseUrl("https://cryptic-ridge-20830.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build())
                    .create(apiAnggotaClient.class);

            Call<DataAnggota> call = client.getAnggotaSekarang("api/anggota/favorit");

            call.enqueue(new Callback<DataAnggota>() {
                @Override
                public void onResponse(Call<DataAnggota> call, Response<DataAnggota> response) {
                    DataAnggota dataAnggota = response.body();
                    List<Anggota> data = dataAnggota.data;
                    Log.d("sample","aa" + data);
                    afAdapter.setDataAnggota(new ArrayList<Anggota>(data));

                    Log.d("url","a");
//                    saveAnggotaKeDB(data);
                }

                @Override
                public void onFailure(Call<DataAnggota> call, Throwable t)
                {
                    Toast.makeText(FavoritActivity.this, "Data gagal diambil", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isConnected()
    {
        ConnectivityManager connectManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectManager.getActiveNetworkInfo();
//        boolean isconnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return activeNetwork != null;
    }
}
