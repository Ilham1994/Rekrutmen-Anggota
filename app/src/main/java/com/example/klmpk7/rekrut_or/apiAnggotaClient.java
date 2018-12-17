package com.example.klmpk7.rekrut_or;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiAnggotaClient
{
    @GET("api/anggota")
    Call<DataAnggota> getAnggotaSekarang();

}
