package com.example.doancuoiky2.Model;

public class Manage {
    private int id;
    private String tenkhach;
    private String loaixe;
    private String bienso;
    private String makh;

    public Manage(int id, String tenkhach, String loaixe, String bienso, String makh) {
        this.id = id;
        this.tenkhach = tenkhach;
        this.loaixe = loaixe;
        this.bienso = bienso;
        this.makh = makh;
    }

    // Getters
    public int getId() { return id; }
    public String getTenkhach() { return tenkhach; }
    public String getLoaixe() { return loaixe; }
    public String getBienso() { return bienso; }
    public String getMakh() { return makh; }
}
