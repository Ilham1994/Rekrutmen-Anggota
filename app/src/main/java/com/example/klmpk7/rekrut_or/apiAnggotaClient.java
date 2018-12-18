package com.example.klmpk7.rekrut_or;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface apiAnggotaClient
{
    @GET()
    Call<DataAnggota> getAnggotaSekarang(@Url() String url);

}
