package edu.project.TouristTicketOrder.Model;

public class VoucherModel {
    private final String maVoucher;
    private final String moTa;
    private final String giamGia;
    private final String ngayBatDau;
    private final String ngayKetThuc;

    private VoucherModel(Builder builder) {
        this.maVoucher = builder.getMaVoucher();
        this.moTa = builder.getMoTa();
        this.giamGia = builder.getGiamGia();
        this.ngayBatDau = builder.getNgayBatDau();
        this.ngayKetThuc = builder.getNgayKetThuc();
    }

    // Interface Builder
    public interface Builder {
        Builder maVoucher(String maVoucher);
        Builder moTa(String moTa);
        Builder giamGia(String giamGia);
        Builder ngayBatDau(String ngayBatDau);
        Builder ngayKetThuc(String ngayKetThuc);
        VoucherModel build();

        String getMaVoucher();
        String getMoTa();
        String getGiamGia();
        String getNgayBatDau();
        String getNgayKetThuc();
    }

    // Concrete Builder implementation
    public static class VoucherBuilder implements Builder {
        private String maVoucher;
        private String moTa;
        private String giamGia;
        private String ngayBatDau;
        private String ngayKetThuc;

        @Override
        public Builder maVoucher(String maVoucher) {
            this.maVoucher = maVoucher;
            return this;
        }

        @Override
        public Builder moTa(String moTa) {
            this.moTa = moTa;
            return this;
        }

        @Override
        public Builder giamGia(String giamGia) {
            this.giamGia = giamGia;
            return this;
        }

        @Override
        public Builder ngayBatDau(String ngayBatDau) {
            this.ngayBatDau = ngayBatDau;
            return this;
        }

        @Override
        public Builder ngayKetThuc(String ngayKetThuc) {
            this.ngayKetThuc = ngayKetThuc;
            return this;
        }

        @Override
        public VoucherModel build() {
            return new VoucherModel(this);
        }

        // Getter methods for the builder fields
        @Override
        public String getMaVoucher() { return maVoucher; }
        @Override
        public String getMoTa() { return moTa; }
        @Override
        public String getGiamGia() { return giamGia; }
        @Override
        public String getNgayBatDau() { return ngayBatDau; }
        @Override
        public String getNgayKetThuc() { return ngayKetThuc; }
    }


    // Getters
    public String getMaVoucher() {
        return maVoucher;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getGiamGia() {
        return giamGia;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }
}
