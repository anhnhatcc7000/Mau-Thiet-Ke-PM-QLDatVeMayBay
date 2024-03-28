package edu.project.TouristTicketOrder.Model;

public class VoucherDirector {
    private VoucherModel.Builder builder;

    public VoucherDirector(VoucherModel.Builder builder) {
        this.builder = builder;
    }

    public VoucherModel constructDiscountVoucher(String maVoucher, String moTa, String giamGia, String ngayBatDau, String ngayKetThuc) {
        return builder.maVoucher(maVoucher)
                .moTa(moTa)
                .giamGia(giamGia)
                .ngayBatDau(ngayBatDau)
                .ngayKetThuc(ngayKetThuc)
                .build();
    }
}
