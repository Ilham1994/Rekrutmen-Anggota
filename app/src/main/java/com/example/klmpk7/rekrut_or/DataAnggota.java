package com.example.klmpk7.rekrut_or;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataAnggota
{
    @SerializedName("data")
    @Expose
    public List<Anggota> data = null;
}
