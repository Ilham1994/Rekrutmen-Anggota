package com.example.klmpk7.rekrut_or;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface apiAnggotaClient
{
    @GET()
    Call<DataAnggota> getAnggotaSekarang(@Url() String url);

    @FormUrlEncoded
    @POST("anggota/favorit/update")
    Call<ResponseBody> addFavorite(@Field("id") Integer id);

    @GET("anggota/favorite")
    Call<DataAnggota> getFavoriteAnggota();
}
