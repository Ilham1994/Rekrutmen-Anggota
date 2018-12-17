package com.example.klmpk7.rekrut_or;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.klmpk7.rekrut_or.Adapter.AnggotaAdapter;
import com.example.klmpk7.rekrut_or.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerview;
    AnggotaAdapter mAdapter;
    AppDatabase databaseAnggota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new AnggotaAdapter();
//        mAdapter.setHandler(this);

        //set recyclerview
        mRecyclerview = (RecyclerView) findViewById(R.id.anggota_recycler_view);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setVisibility(View.VISIBLE);

        //buat database
        databaseAnggota = Room.databaseBuilder(this, AppDatabase.class, "anggota.db")
                .allowMainThreadQueries().build();

//        SharedPreferences
    }

//    @Override
//    public boolean onCreateOptionsMenu

//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//
//    }

//    @Override
//    public void click(Anggota anggota){
//
//    }

    private void getListAnggotaSekarang()
    {
        if(isConnected())
        {
            //Ambil Data
            apiAnggotaClient client = (new Retrofit.Builder()
                    .baseUrl("https://cryptic-ridge-20830.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()).create(apiAnggotaClient.class);

            Call<DataAnggota> call = client.getAnggotaSekarang();

            call.enqueue(new Callback<DataAnggota>() {
                @Override
                public void onResponse(Call<DataAnggota> call, Response<DataAnggota> response) {
                    DataAnggota dataAnggota = response.body();
                    List<Anggota> results = dataAnggota.results;

                    mAdapter.setDataAnggota(new ArrayList<Anggota>(results));

//                    saveAnggotaKeDB(results);
                }

                @Override
                public void onFailure(Call<DataAnggota> call, Throwable t)
                {
                    Toast.makeText(MainActivity.this, "Data gagal diambil", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isConnected()
    {
        ConnectivityManager connectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected();
    }
}
