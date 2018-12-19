package com.example.klmpk7.rekrut_or;

import android.os.Parcel;
import android.os.Parcelable;

public class Anggota implements Parcelable
{
    public int id;
    public String nama;
    public String tmpt_lahir;
    public String tgl_lahir;
    public String alamat;
    public String nim;
    public String motivasi;
    public String foto;
    public boolean favorit;

    public Anggota(int id, String nama, String tmpt_lahir, String tgl_lahir, String alamat, String nim, String motivasi, String foto, boolean favorit) {
        this.id = id;
        this.nama = nama;
        this.tmpt_lahir = tmpt_lahir;
        this.tgl_lahir = tgl_lahir;
        this.alamat = alamat;
        this.nim = nim;
        this.motivasi = motivasi;
        this.foto = foto;
        this.favorit = favorit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags)
    {
        boolean myBoolean = true;
        parcel.writeInt(this.id);
        parcel.writeString(this.nama);
        parcel.writeString(this.tmpt_lahir);
        parcel.writeString(this.tgl_lahir);
        parcel.writeString(this.alamat);
        parcel.writeString(this.nim);
        parcel.writeString(this.motivasi);
        parcel.writeString(this.foto);
        parcel.writeByte((byte)(myBoolean ? 1 : 0));
    }

    protected Anggota(Parcel in)
    {
        this.id = in.readInt();
        this.nama = in.readString();
        this.tmpt_lahir = in.readString();
        this.tgl_lahir = in.readString();
        this.alamat = in.readString();
        this.nim = in.readString();
        this.motivasi = in.readString();
        this.foto = in.readString();
        this.favorit = in.readByte() != 0;
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

    @Override
    public String toString() {
        return "Anggota{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", tmpt_lahir='" + tmpt_lahir + '\'' +
                ", tgl_lahir='" + tgl_lahir + '\'' +
                ", alamat='" + alamat + '\'' +
                ", nim='" + nim + '\'' +
                ", motivasi='" + motivasi + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
