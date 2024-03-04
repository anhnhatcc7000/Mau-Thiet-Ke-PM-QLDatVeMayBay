package edu.project.TouristTicketOrder.Model;

import java.io.Serializable;

public class ChangBayModel implements Serializable {
    int maTuyen;
    String tenTuyen, noiXuatPhat, noiDen;
    String  hinh;

    // Constructors
    public ChangBayModel() {}

    public ChangBayModel(int maTuyen, String tenTuyen, String noiXuatPhat, String noiDen) {
        this.maTuyen = maTuyen;
        this.tenTuyen = tenTuyen;
        this.noiXuatPhat = noiXuatPhat;
        this.noiDen = noiDen;
    }
    public ChangBayModel(int maTuyen, String tenTuyen, String noiXuatPhat, String noiDen, String hinh) {
        this.maTuyen = maTuyen;
        this.tenTuyen = tenTuyen;
        this.noiXuatPhat = noiXuatPhat;
        this.noiDen = noiDen;
        this.hinh = hinh;
    }

    public ChangBayModel(String tenTuyen, String noiXuatPhat, String noiDen, String hinh) {
        this.tenTuyen = tenTuyen;
        this.noiXuatPhat = noiXuatPhat;
        this.noiDen = noiDen;
        this.hinh = hinh;
    }
    // Method

    // Getters and Setters
    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }

    public String getTenTuyen() {
        return tenTuyen;
    }

    public void setTenTuyen(String tenTuyen) {
        this.tenTuyen = tenTuyen;
    }

    public String getNoiXuatPhat() {
        return noiXuatPhat;
    }

    public void setNoiXuatPhat(String noiXuatPhat) {
        this.noiXuatPhat = noiXuatPhat;
    }

    public String getNoiDen() {
        return noiDen;
    }

    public void setNoiDen(String noiDen) {
        this.noiDen = noiDen;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }


}
