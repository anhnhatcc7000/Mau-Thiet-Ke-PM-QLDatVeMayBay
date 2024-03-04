package edu.project.TouristTicketOrder.Model;

import android.database.Cursor;

import java.io.Serializable;

public class TuyenBayModel implements Serializable {
    int maTuyenXe, maTuyen, maXe, soGhe, gia;
    String ngayKhoihanh, ngayKetThuc, tenTuyen, tenMB, loaiVe, hangGhe, sanBayDi, sanBayDen;

    Cursor tuyenXeModelCursor;
    int position;

    public Cursor getTuyenXeModelCursor() {
        return tuyenXeModelCursor;
    }

    public void setTuyenXeModelCursor(Cursor tuyenXeModelCursor, int position) {
        this.tuyenXeModelCursor = tuyenXeModelCursor;
        this.position = position;
    }

    public TuyenBayModel() {
    }

    public TuyenBayModel(String ngayKhoihanh, String ngayKetThuc, Cursor tuyenXeModelCursor) {
        this.ngayKhoihanh = ngayKhoihanh;
        this.ngayKetThuc = ngayKetThuc;
        this.tuyenXeModelCursor = tuyenXeModelCursor;
    }

    public TuyenBayModel(String ngayKhoihanh, String ngayKetThuc) {
        this.ngayKhoihanh = ngayKhoihanh;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getLoaiVe() {
        return loaiVe;
    }

    public String getSanBayDi() {
        return sanBayDi;
    }

    public void setSanBayDi(String sanBayDi) {
        this.sanBayDi = sanBayDi;
    }

    public String getSanBayDen() {
        return sanBayDen;
    }

    public void setSanBayDen(String sanBayDen) {
        this.sanBayDen = sanBayDen;
    }

    public void setLoaiVe(String loaiVe) {
        this.loaiVe = loaiVe;
    }

    public String getHangGhe() {
        return hangGhe;
    }

    public void setHangGhe(String hangGhe) {
        this.hangGhe = hangGhe;
    }

    public String getTenMB() {
        return tenMB;
    }

    public void setTenMB(String tenMB) {
        this.tenMB = tenMB;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public TuyenBayModel(int maTuyenXe, String ngayKhoihanh, String ngayKetThuc) {
        this.maTuyenXe = maTuyenXe;
        this.ngayKhoihanh = ngayKhoihanh;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public String getTenTuyen() {
        return tenTuyen;
    }

    public void setTenTuyen(String tenTuyen) {
        this.tenTuyen = tenTuyen;
    }

    public int getPosition() {
        return position;
    }

    public int getMaTuyenXe() {
        return maTuyenXe;
    }

    public void setMaTuyenXe(int maTuyenXe) {
        this.maTuyenXe = maTuyenXe;
    }

    public String getNgayKhoihanh() {
        return ngayKhoihanh;
    }

    public void setNgayKhoihanh(String ngayKhoihanh) {
        this.ngayKhoihanh = ngayKhoihanh;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
