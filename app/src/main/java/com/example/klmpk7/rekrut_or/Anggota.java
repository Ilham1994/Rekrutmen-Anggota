package com.example.klmpk7.rekrut_or;

import android.os.Parcel;
import android.os.Parcelable;

public class Anggota implements Parcelable
{
    public int id;
    public String nama;
    public String tmpt_lahir;
    public String tgl_lahir;
    public String jurusan;
    public String angkatan;
    public int target_divisi;
    public String motivasi;

    public Anggota(int id, String nama, String tmpt_lahir, String tgl_lahir, String jurusan, String angkatan, int target_divisi, String motivasi) {
        this.id = id;
        this.nama = nama;
        this.tmpt_lahir = tmpt_lahir;
        this.tgl_lahir = tgl_lahir;
        this.jurusan = jurusan;
        this.angkatan = angkatan;
        this.target_divisi = target_divisi;
        this.motivasi = motivasi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags)
    {
        parcel.writeInt(this.id);
        parcel.writeString(this.nama);
        parcel.writeString(this.tmpt_lahir);
        parcel.writeString(this.tgl_lahir);
        parcel.writeString(this.jurusan);
        parcel.writeString(this.angkatan);
        parcel.writeInt(this.target_divisi);
        parcel.writeString(this.motivasi);
    }

    protected Anggota(Parcel in)
    {
        this.id = in.readInt();
        this.nama = in.readString();
        this.tmpt_lahir = in.readString();
        this.tgl_lahir = in.readString();
        this.jurusan = in.readString();
        this.angkatan = in.readString();
        this.target_divisi = in.readInt();
        this.motivasi = in.readString();
    }

    public static final Parcelable.Creator<Anggota> CREATOR = new Parcelable.Creator<Anggota>()
    {
        @Override
        public Anggota createFromParcel(Parcel source)
        {
            return new Anggota(source);
        }

        @Override
        public Anggota[] newArray(int size)
        {
            return new Anggota[size];
        }
    };
}
