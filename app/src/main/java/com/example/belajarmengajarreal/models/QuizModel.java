package com.example.belajarmengajarreal.models;

public class QuizModel {
    public String soal;
    public String jawaban1;
    public String jawaban2;
    public String jawaban3;
    public int jawabanBenar = 0;

    public  QuizModel(String soal,String jawaban1,String jawaban2,String jawaban3, int jawabanBenar){
        this.soal = soal;
        this.jawaban1 = jawaban1;
        this.jawaban2 = jawaban2;
        this.jawaban3 = jawaban3;
        this.jawabanBenar = jawabanBenar;
    }

    public String getSoal() {
        return soal;
    }

    public String getJawaban1() {
        return jawaban1;
    }

    public String getJawaban2() {
        return jawaban2;
    }

    public String getJawaban3() {
        return jawaban3;
    }

    public int getJawabanBenar() {
        return jawabanBenar;
    }

}
