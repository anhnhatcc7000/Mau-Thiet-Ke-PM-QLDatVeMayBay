package edu.project.TouristTicketOrder.Model;

import java.io.Serializable;

public class MayBayModel implements Serializable {
    int mãXe;
    String tenXe;
    int soGhe;
    String bienSo;
    int maTuyen;
    int maTaiXe;
    String hinh;

    public MayBayModel() {
    }

    public MayBayModel(int mãXe, String tenXe, int soGhe, String bienSo) {
        this.mãXe = mãXe;
        this.tenXe = tenXe;
        this.soGhe = soGhe;
        this.bienSo = bienSo;
    }

    public MayBayModel(String tenXe, int soGhe, String bienSo) {
        this.tenXe = tenXe;
        this.soGhe = soGhe;
        this.bienSo = bienSo;
    }

    public int getMãXe() {
        return mãXe;
    }

    public void setMãXe(int mãXe) {
        this.mãXe = mãXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    @Override
    public String toString() {
        return "MayBayModel{" +
                "mãXe=" + mãXe +
                ", tenXe='" + tenXe + '\'' +
                ", soGhe=" + soGhe +
                ", bienSo='" + bienSo + '\'' +
                '}';
    }
}
