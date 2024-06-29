package com.example.belajarmengajarreal.models;

public class Materi {

    public String id;
    public String judul;
    public String deskripsi;
    public String kategori;
    public String video;

    public Materi(String id, String judul, String deskripsi, String kategori, String video) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
        this.video = video;
    }

    public static Materi create(String id, String judul, String deskripsi, String kategori, String video) {
        return new Materi(id, judul, deskripsi, kategori, video);
    }

    public String getId() { return id; }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getKategori() {
        return kategori;
    }

    public String getVideoID() {
        return video;
    }

    public String getVideoUrl() {
        return "https://youtube.com/embed/" + video;
    }
}
