package edu.project.TouristTicketOrder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import edu.project.TouristTicketOrder.Account_Activity.LoginValidation;
import edu.project.TouristTicketOrder.Model.CTHDModel;
import edu.project.TouristTicketOrder.Model.CustomerModel;
import edu.project.TouristTicketOrder.Model.MayBayModel;
import edu.project.TouristTicketOrder.Model.NhanVienModel;
import edu.project.TouristTicketOrder.Model.ChangBayModel;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;

public class DataBaseHandler extends SQLiteOpenHelper{
    LocalDateTimeConvert localDateTimeConvert = new LocalDateTimeConvert();
    public static final String TABLE_ChangBay = "ChangBay";
    public static final String COLUMN_Chang_MaChangBay = "MaChang";
    public static final String COLUMN_TenChang = "TenChang";
    public static final String COLUMN_SanBayDi = "SanBayDi";
    public static final String COLUMN_SanBayDen = "SanBayDen";
    public static final String CREATE_TABLE_ChangBay = "CREATE TABLE " + TABLE_ChangBay + " ( " +
            " " + COLUMN_Chang_MaChangBay + " integer PRIMARY KEY AUTOINCREMENT, " +
            " " + COLUMN_TenChang + " Nvarchar(50), " +
            " " + COLUMN_SanBayDi + " Nvarchar(50) NOT NULL, " +
            " " + COLUMN_SanBayDen + " Nvarchar(50) NOT NULL " +
            ")";

    // ---------------------------- Table Khach Hang ----------------------------
    public static final String TABLE_KhachHang = "KHACHHANG";
    public static final String COLUMN_MaKH = "MaKH";
    public static final String COLUMN_TenKH = "TenKH";
    public static final String COLUMN_KH_SDT = "SDT";
    public static final String COLUMN_KH_Email = "Mail";
    public static final String COLUMN_KH_Pass = "Password";
    public static final String COLUMN_KH_Passport = "Passport";
    public static final String CREATE_TABLE_KHACHHANG = "CREATE TABLE " + TABLE_KhachHang + " ( " +
            "  " + COLUMN_MaKH + " integer PRIMARY KEY AUTOINCREMENT, " +
            "  " + COLUMN_TenKH + " Nvarchar(30) NOT NULL, " +
            "  " + COLUMN_KH_SDT + " Nvarchar(11) UNIQUE, " +
            "  " + COLUMN_KH_Email + " varchar(20) UNIQUE, " +
            "  " + COLUMN_KH_Pass + " varchar(10), " +
            "  " + COLUMN_KH_Passport + " char(8) UNIQUE " +
            ")";

    // ---------------------------- Table Nhan Vien ----------------------------
    public static final String TABLE_NhanVien = "NHANVIEN";
    public static final String COLUMN_NhanVien_MaNV = "MaNV";
    public static final String COLUMN_TenNV = "TenNV";
    public static final String COLUMN_CCCD = "CCCD";
    public static final String COLUMN_DiaChi = "DiaChi";
    public static final String COLUMN_NhanVien_SDT = "SDT";
    public static final String COLUMN_NhanVien_Mail = "Mail";
    public static final String COLUMN_VaiTro = "VaiTro";
    public static final String CREATE_TABLE_NHANVIEN = "CREATE TABLE " + TABLE_NhanVien + " ( "
            + "  " + COLUMN_NhanVien_MaNV + " integer PRIMARY KEY AUTOINCREMENT, "
            + "  " + COLUMN_TenNV + " Nvarchar(30) NOT NULL, "
            + "  " + COLUMN_CCCD + " Nvarchar(12) UNIQUE, "
            + "  " + COLUMN_DiaChi + " Nvarchar(50), "
            + "  " + COLUMN_NhanVien_SDT + " varchar(11) UNIQUE, "
            + "  " + COLUMN_NhanVien_Mail + " varchar(20) UNIQUE, "
            + "  " + COLUMN_VaiTro + " varchar(10) "
            + ")";

    // ---------------------------- Table May Bay ----------------------------
    public static final String TABLE_MayBay = "MayBay";
    public static final String COLUMN_MayBay_MaMB = "MaMB";
    public static final String COLUMN_TenMayBay = "TenMB";
    public static final String COLUMN_MaSoMB = "MaSoMB";
    public static final String COLUMN_SoGhe = "SoGhe";

    public static final String CREATE_TABLE_MayBay = "CREATE TABLE " + TABLE_MayBay + " ( "
            + "  " + COLUMN_MayBay_MaMB + " integer PRIMARY KEY AUTOINCREMENT, "
            + "  " + COLUMN_TenMayBay + " Nvarchar(30) NOT NULL, "
            + "  " + COLUMN_MaSoMB + " varchar(20) UNIQUE NOT NULL, "
            + "  " + COLUMN_SoGhe + " smallint "
            + ") ";

    // ---------------------------- Table Admin ----------------------------
    public static final String TABLE_Admin = "TAI_KHOAN_ADMIN";
    public static final String COLUMN_Admin_MaNV = "MaNV";
    public static final String COLUMN_Admin_Mail = "Mail";
    public static final String COLUMN_Admin_Pass = "Pass";

    public static final String CREATE_TABLE_ADMIN = "CREATE TABLE " + TABLE_Admin + " ( "
            + "  " + COLUMN_Admin_MaNV + " integer UNIQUE, "
            + "  " + COLUMN_Admin_Mail + " varchar(20) UNIQUE, "
            + "  " + COLUMN_Admin_Pass + " varchar(10), "
            + " FOREIGN KEY ("+COLUMN_Admin_MaNV+") REFERENCES " + TABLE_NhanVien + "("+COLUMN_NhanVien_MaNV+"), "
            + " FOREIGN KEY ("+COLUMN_Admin_Mail+") REFERENCES " + TABLE_NhanVien + "("+COLUMN_NhanVien_Mail+") "
            + ")";

    // ---------------------------- Table TuyenBay ----------------------------
    public static final String TABLE_TuyenBay = "TuyenBay";
    public static final String COLUMN_TuyenBay_MaTuyenBay = "MaTuyenBay";
    public static final String COLUMN_TuyenBay_MaChang = "MaChang";
    public static final String COLUMN_TuyenBay_MaMB = "MaMB";
    public static final String COLUMN_TuyenBay_MaNV = "MaNV";
    public static final String COLUMN_TUYENBAY_NgayBay = "NgayBay";
    public static final String COLUMN_HangGhe = "HangGhe";
    public static final String COLUMN_LoaiVe = "LoaiVe";
    public static final String COLUMN_GiaVe = "GiaVe";
    public static final String CREATE_TABLE_TuyenBay = "CREATE TABLE " + TABLE_TuyenBay + " ( "
            + " " + COLUMN_TuyenBay_MaTuyenBay + " integer PRIMARY KEY AUTOINCREMENT, "
            + " " + COLUMN_TuyenBay_MaChang + " integer, "
            + " " + COLUMN_TuyenBay_MaMB + " integer, "
            + " " + COLUMN_TuyenBay_MaNV + " integer, "
            + " " + COLUMN_TUYENBAY_NgayBay + " varchar(16), "
            + " " + COLUMN_HangGhe + " varchar(20), "
            + " " + COLUMN_LoaiVe + " varchar(20), "
            + " " + COLUMN_GiaVe + " integer, "
            + " FOREIGN KEY ("+COLUMN_TuyenBay_MaChang+") REFERENCES " + TABLE_ChangBay + "("+COLUMN_Chang_MaChangBay+"), "
            + " FOREIGN KEY ("+COLUMN_TuyenBay_MaNV+") REFERENCES " + TABLE_NhanVien + "("+COLUMN_NhanVien_MaNV+"), "
            + " FOREIGN KEY ("+COLUMN_TuyenBay_MaMB+") REFERENCES " + TABLE_MayBay + "("+COLUMN_MayBay_MaMB+") "
            + ")";

    // ---------------------------- Table CTHD ----------------------------
    public static final String TABLE_CTHD = "CTHD";
    public static final String COLUMN_MaDon = "MaDon";
    public static final String COLUMN_CTHD_MaKH = "MaKH";
    public static final String COLUMN_CTHD_MaTuyenBay = "MaTuyenBay";
    public static final String COLUMN_CTHD_Ghe = "Ghe";
    public static final String COLUMN_HanhLy = "HanhLy";
    public static final String COLUMN_TrangThai = "TrangThai";
    public static final String COLUMN_ThanhTien = "ThanhTien";
    public static final String COLUMN_NgayDat = "NgayDat";
    public static final String CREATE_TABLE_CTHD = "CREATE TABLE " + TABLE_CTHD + " ( "
            + "  " + COLUMN_MaDon + " integer PRIMARY KEY AUTOINCREMENT, "
            + "  " + COLUMN_CTHD_MaKH + " integer, "
            + "  " + COLUMN_CTHD_MaTuyenBay + " integer, "
            + "  " + COLUMN_CTHD_Ghe + " char(2), "
            + "  " + COLUMN_TrangThai + " Nvarchar(30), "
            + "  " + COLUMN_ThanhTien + " integer, "
            + "  " + COLUMN_NgayDat + " varchar(16), "
            + "  " + COLUMN_HanhLy + " integer, "
            + " FOREIGN KEY ("+COLUMN_CTHD_MaKH+") REFERENCES " + TABLE_KhachHang + "("+COLUMN_MaKH+"), "
            + " FOREIGN KEY ("+COLUMN_CTHD_MaTuyenBay+") REFERENCES " + TABLE_TuyenBay + "("+COLUMN_TuyenBay_MaTuyenBay+") "
            + ")";


    /* -------------------------------------------------------------------------------------------------------------------- */
    public DataBaseHandler(@Nullable Context context) {
        super(context, "QLVeMaybay.db", null, 17);
    }

    // This is called the first time a db is access
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_TABLE_KHACHHANG);
        db.execSQL(CREATE_TABLE_ChangBay);
        db.execSQL(CREATE_TABLE_NHANVIEN);
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_MayBay);
        db.execSQL(CREATE_TABLE_CTHD);
        db.execSQL(CREATE_TABLE_TuyenBay);

        onCreateInsert(db);
//        db.close();
    }

    public void onCreateInsert(SQLiteDatabase db) {


        Date dateTime = new Date();
        // TUYENBAY
        String noiXuatPhat = "Tân Sơn Nhất";
        String noiDen = "Liên Khương";
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TenChang, "Hồ Chí Minh - Đà Lạt");
        cv.put(COLUMN_SanBayDi, noiXuatPhat);
        cv.put(COLUMN_SanBayDen, noiDen);

        db.insert(TABLE_ChangBay, null, cv);

        noiDen = "Nha Trang";
        cv = new ContentValues();
        cv.put(COLUMN_TenChang, "Hồ Chí Minh - Nha Trang");
        cv.put(COLUMN_SanBayDi, noiXuatPhat);
        cv.put(COLUMN_SanBayDen, noiDen);

        db.insert(TABLE_ChangBay, null, cv);

        noiDen = "Nội Bài";
        cv = new ContentValues();
        cv.put(COLUMN_TenChang, "Hồ Chí Minh - Hà Nội");
        cv.put(COLUMN_SanBayDi, noiXuatPhat);
        cv.put(COLUMN_SanBayDen, noiDen);

        db.insert(TABLE_ChangBay, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_MaKH, 10);
        cv.put(COLUMN_TenKH, "Test 2");
        cv.put(COLUMN_KH_SDT, "12345678910");
        cv.put(COLUMN_KH_Email, "test2@gmail.com");
        cv.put(COLUMN_KH_Pass, "12345678");
        cv.put(COLUMN_KH_Passport, "passport 200");

        db.insert(TABLE_KhachHang, null, cv);

        // KHACHHANG
        cv = new ContentValues();

        cv.put(COLUMN_TenKH, "Test 1");
        cv.put(COLUMN_KH_SDT, "01234567891");
        cv.put(COLUMN_KH_Email, "test1@gmail.com");
        cv.put(COLUMN_KH_Pass, "12345678");
        cv.put(COLUMN_KH_Passport, "passport 1");

        db.insert(TABLE_KhachHang, null, cv);

        cv = new ContentValues();

        cv.put(COLUMN_TenKH, "Test 3");
        cv.put(COLUMN_KH_SDT, "12345678910");
        cv.put(COLUMN_KH_Email, "test3@gmail.com");
        cv.put(COLUMN_KH_Pass, "12345678");
        cv.put(COLUMN_KH_Passport, "passport 20");

        db.insert(TABLE_KhachHang, null, cv);

        // NHANVIEN
        cv = new ContentValues();
        cv.put(COLUMN_TenNV, "admin 1");
        cv.put(COLUMN_CCCD, "079203036000");
        cv.put(COLUMN_DiaChi, "Binh Chanh");
        cv.put(COLUMN_NhanVien_SDT, "12345678910");
        cv.put(COLUMN_NhanVien_Mail, "admin1@gmail.com");
        cv.put(COLUMN_VaiTro, "admin");

        db.insert(TABLE_NhanVien, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_TenNV, "nhanvien 1");
        cv.put(COLUMN_CCCD, "079203036222");
        cv.put(COLUMN_DiaChi, "Binh Chanh");
        cv.put(COLUMN_NhanVien_SDT, "09182756222");
        cv.put(COLUMN_NhanVien_Mail, "nhanvien1@gmail.com");
        cv.put(COLUMN_VaiTro, "taixe");

        db.insert(TABLE_NhanVien, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_TenNV, "nhanvien 2");
        cv.put(COLUMN_CCCD, "079203036111");
        cv.put(COLUMN_DiaChi, "Binh Chanh");
        cv.put(COLUMN_NhanVien_SDT, "09152456111");
        cv.put(COLUMN_NhanVien_Mail, "nhanvien2@gmail.com");
        cv.put(COLUMN_VaiTro, "taixe");

        db.insert(TABLE_NhanVien, null, cv);

        // MAYBAY
        // 32
        for (int i = 1; i <= 3; i++)
        {
            Random ran = new Random();
            int num = ran.nextInt(9999);
            if(num < 1000)
                num += 1000;
            cv = new ContentValues();
            cv.put(COLUMN_TenMayBay, "Airbus A320");
            cv.put(COLUMN_MaSoMB, "Airbus-A320-01" + num);
            cv.put(COLUMN_SoGhe, 146);
            db.insert(TABLE_MayBay, null, cv);
        }

        // TUYENBAY
        cv = new ContentValues();
        cv.put(COLUMN_TuyenBay_MaTuyenBay, 10);
        cv.put(COLUMN_TuyenBay_MaChang, 1);
        cv.put(COLUMN_TuyenBay_MaMB, 1);
        try {
            cv.put(COLUMN_TUYENBAY_NgayBay, localDateTimeConvert.getOtherWeekFromDate(new Date().toString(), 1));
        } catch (ParseException e) {}
        cv.put(COLUMN_HangGhe, "Phổ thông");
        cv.put(COLUMN_LoaiVe, "1 chiều");
        cv.put(COLUMN_GiaVe, 900000);
        db.insert(TABLE_TuyenBay, null, cv);

        cv = new ContentValues();
        cv.put(COLUMN_TuyenBay_MaChang, 2);
        cv.put(COLUMN_TuyenBay_MaMB, 2);
        try {
            cv.put(COLUMN_TUYENBAY_NgayBay, localDateTimeConvert.getOtherWeekFromDate(new Date().toString(), 1));
        } catch (ParseException e) {}
        cv.put(COLUMN_HangGhe, "Phổ thông");
        cv.put(COLUMN_LoaiVe, "1 chiều");
        cv.put(COLUMN_GiaVe, 1313000);
        db.insert(TABLE_TuyenBay, null, cv);

    }

    // This is called if the db version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CTHD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MayBay);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Admin);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KhachHang);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NhanVien);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ChangBay);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TuyenBay);

        onCreate(db);
    }

    /*---------------------------------------------- TABLE_KHACHHANG METHODS START ---------------------------------------------- */
    // Add new customer to database
    public boolean addOne(CustomerModel newCustomer) {
        String tenKH = newCustomer.getTenKH();
        String sdt = newCustomer.getSDT();
        String mail = newCustomer.getMail();
        String pass = newCustomer.getPass();
        String passport = newCustomer.getPassport();


        if(tenKH.length() > 0 && sdt.length() > 0 && mail.length() > 0 && pass.length() > 0 && passport.length() > 0)  {
            boolean check = CheckCondition(newCustomer);

            if(!check) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues cv = new ContentValues();

                cv.put(COLUMN_TenKH, newCustomer.getTenKH());
                cv.put(COLUMN_KH_SDT, newCustomer.getSDT());
                cv.put(COLUMN_KH_Email, newCustomer.getMail());
                cv.put(COLUMN_KH_Pass, newCustomer.getPass());
                cv.put(COLUMN_KH_Passport, newCustomer.getPassport());

                db.insert(TABLE_KhachHang, null, cv);

                db.close();
                return true;
            }
        }
            return false;
    }

    // Check if the information is duplicated
    public boolean CheckCondition(CustomerModel newCustomer) {
        SQLiteDatabase db = this.getReadableDatabase();
        String mail_convert = "'" + newCustomer.getMail() + "'";
        String sdt_convert = "'" + newCustomer.getSDT() + "'";

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_KhachHang
                                        + " WHERE " + COLUMN_KH_SDT + " = " + sdt_convert
                                        + " OR " + COLUMN_KH_Email + " = " + mail_convert, null);

        if(cursor.moveToFirst()) {
            cursor.close();
            db.close();
                return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    // Check login information
    public LoginValidation checkCustomer(String mail, String pass) {
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_KhachHang + " WHERE " + COLUMN_KH_Email + " = ?", new String[]{mail})) {
            if (cursor.moveToFirst()) {
                String cusPass = cursor.getString(4);

                if (pass != null && pass.equals(cusPass)) {
                    int cusID = cursor.getInt(0);
                    String cusName = cursor.getString(1);
                    String cusPhone = cursor.getString(2);
                    String cusMail = cursor.getString(3);
                    CustomerModel newCustomer = new CustomerModel(cusID, cusName, cusPhone, cusMail, cusPass);
                    db.close();
                    return new LoginValidation(newCustomer, true);
                }
            }
        }
        db.close();
        return new LoginValidation(false);
    }


    public boolean CustomerMailValidate(String mail) {
        SQLiteDatabase db = this.getReadableDatabase();
        String mail_convert = "'" + mail + "'";

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_KhachHang
                + " WHERE " + COLUMN_KH_Email + " = " + mail_convert, null);
        if(cursor.moveToFirst())
        {
            cursor.close();
            db.close();
            return true;
        }
        return false;
    }

    // Get customer by the name is being searched
    public ArrayList<CustomerModel> get_Customer(String name) {
        final ArrayList<CustomerModel> arrayList = new ArrayList<CustomerModel>();

        String name_convert = "'%" + name + "%'";
        // get data from database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_KhachHang +
                " WHERE " + COLUMN_TenKH + " LIKE " + name_convert, null);

        if(cursor.moveToFirst()) {
            do {
                int cusID = cursor.getInt(0);
                String cusName = cursor.getString(1);
                String cusPhone = cursor.getString(2);
                String cusMail = cursor.getString(3);
                String cusPass = cursor.getString(4);
                String cusPassport = cursor.getString(5);


                CustomerModel newCustomer = new CustomerModel(cusID, cusName, cusPhone, cusMail, cusPass, cusPassport);

                arrayList.add(newCustomer);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arrayList;
    }


    // Delete a customer
    public Boolean deleteData (CustomerModel clickedCustomer)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_KhachHang +
                " WHERE " + COLUMN_MaKH + " = " + clickedCustomer.getId(), null);

        if (cursor.moveToFirst())
        {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    // Update a customer data
    public Boolean updateData(CustomerModel clickedCustomer, String name, String phone, String mail,String pass, String passport) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            if(name.length() > 0)
                cv.put(COLUMN_TenKH, name);
            if(mail.length() > 0)
                cv.put(COLUMN_KH_Email, mail);
            if(phone.length() > 0)
                cv.put(COLUMN_KH_SDT, phone);
            if(pass.length() > 0)
                cv.put(COLUMN_KH_Pass, pass);
            if(passport.length() > 0)
                cv.put(COLUMN_KH_Passport, passport);
            try {
                db.update(TABLE_KhachHang, cv, "" + COLUMN_MaKH + " = " + clickedCustomer.getId(), null);
                db.close();
                return true;
            }  catch (Exception e) {}
//            else
//            {
//                String name_convert = "'" + name + "'";
//                String phone_convert = "'" + phone + "'";
//                String mail_convert = "'" + mail + "'";
//
//                db.execSQL("UPDATE " + TABLE_KhachHang
//                        + " SET " + COLUMN_TenKH + " = " + name_convert
//                        + "," + COLUMN_KH_SDT + " = " + phone_convert
//                        + "," + COLUMN_KH_Email + " = " + mail_convert
//                        + " WHERE " + COLUMN_MaKH + " = " + clickedCustomer.getId());
//                db.close();
//                return true;
//            }
        }catch (Exception e) {}

        db.close();
        return false;
    }

    public boolean updateDataFromKH(int maKH, String name, String mail, String phone)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(name.length() > 0)
            cv.put(COLUMN_TenKH, name);
        else if(mail.length() > 0)
            cv.put(COLUMN_KH_Email, mail);
        else
            cv.put(COLUMN_KH_SDT, phone);
        try {
            db.update(TABLE_KhachHang, cv, "" + COLUMN_MaKH + " = " + maKH, null);
            db.close();
            return true;
        } catch (Exception e) {}
        db.close();
        return false;
    }

    /*---------------------------------------------- TABLE_KHACHHANG METHODS END ---------------------------------------------- */



    /*---------------------------------------------- TABLE_TUYENXE METHODS START ---------------------------------------------- */

    // Add CHANGBAY MAYBAY
    public boolean addTuyenXe(ChangBayModel changBayModel) {
        String startDes = changBayModel.getNoiXuatPhat();
        String endDes = changBayModel.getNoiDen();

        String tenTuyen = changBayModel.getTenTuyen();
        boolean check = CheckConditionTuyenXe(changBayModel);

        if(!check) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_TenChang, tenTuyen);
            cv.put(COLUMN_SanBayDi, startDes);
            cv.put(COLUMN_SanBayDen, endDes);

            db.insert(TABLE_ChangBay, null, cv);

            db.close();
            return true;
        }
        return false;
    }

    // Check login information
    public ChangBayModel CheckTuyenXe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ChangBay
                + " WHERE " + COLUMN_TuyenBay_MaChang + " = " + id, null);

        ChangBayModel changBayModel = new ChangBayModel();

        if(cursor.moveToFirst()) {
            int tuyenID = cursor.getInt(0);
            String tenTuyen = cursor.getString(1);
            String noiXuatPhat = cursor.getString(2);
            String noiDen = cursor.getString(3);
//            String diemDon = cursor.getString(4);
//            String diemDung = cursor.getString(5);
//            int gia = Integer.parseInt(cursor.getString(6));
            String ngayKhoiHanh = cursor.getString(4);
//            String ngayKetThuc = cursor.getString(8);
            String hinh = cursor.getString(5);

            changBayModel = new ChangBayModel(tuyenID, tenTuyen, noiXuatPhat, noiDen, hinh);
        }
        cursor.close();
        db.close();
        return changBayModel;
    }

    // Check CHANGBAY MAYBAY
    public boolean CheckConditionTuyenXe(ChangBayModel changBayModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        String tenTuyen_convert = "'" + changBayModel.getTenTuyen() + "'";

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ChangBay
                + " WHERE " + COLUMN_TenChang + " = " + tenTuyen_convert, null);

        if(cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    // Get data by the name is being searched
    public ArrayList<ChangBayModel> getTuyenOption(String search_tenTuyen) {
        final ArrayList<ChangBayModel> arrayList = new ArrayList<ChangBayModel>();

        String tenTuyen_convert = "'%" + search_tenTuyen + "%'";
        // get data from database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ChangBay +
                " WHERE " + COLUMN_TenChang + " LIKE " + tenTuyen_convert, null);

        if(cursor.moveToFirst()) {
            do {
                int tuyenID = cursor.getInt(0);
                String tenTuyen = cursor.getString(1);
                String noiXuatPhat = cursor.getString(2);
                String noiDen = cursor.getString(3);
//                String diemDon = cursor.getString(4);
//                String diemDung = cursor.getString(5);
//                int gia = Integer.parseInt(cursor.getString(6));
//                String hinh = cursor.getString(4);

                ChangBayModel changBayModel = new ChangBayModel(tuyenID, tenTuyen, noiXuatPhat, noiDen);
                arrayList.add(changBayModel);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arrayList;
    }

    // Update a tuyen xe data
    public Boolean updateTuyen(ChangBayModel changBayModel, String startDes, String endDes) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TenChang, startDes + "-" + endDes);
        cv.put(COLUMN_SanBayDi, startDes);
        cv.put(COLUMN_SanBayDen, endDes);

        try {
            db.update(TABLE_ChangBay, cv, "" + COLUMN_TuyenBay_MaChang + " = " + changBayModel.getMaTuyen(), null);
            db.close();
            return true;
        }
        catch (Exception e) {}
        db.close();
        return false;
    }

    // Delete a customer
    public Boolean deleteTuyen(ChangBayModel changBayModel)
    {
        if(deleteTuyenRequestValidator(changBayModel))
        {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_ChangBay +
                    " WHERE " + COLUMN_TuyenBay_MaChang + " = " + changBayModel.getMaTuyen(), null);

            if (cursor.moveToFirst())
            {
                cursor.close();
                db.close();
            }
            return true;
        }
        return false;
    }

    public Boolean deleteTuyenRequestValidator(ChangBayModel changBayModel)
    {
        ArrayList<TuyenBayModel> arrayList = getTuyenXeOption(String.valueOf(changBayModel.getMaTuyen()), "", false);
        if(arrayList.size() > 0)
            return false;
        return true;
    }
    /*---------------------------------------------- TABLE_TUYENXE METHODS EMD ---------------------------------------------- */


    /*---------------------------------------------- TABLE_NHANVIEN METHODS START ---------------------------------------------- */
    // Add CHANGBAY MAYBAY
    public boolean addNhanVien(NhanVienModel nhanVienModel) {
        String empName = nhanVienModel.getEmpName();
        String empAddr = nhanVienModel.getEmpAddr();
        String empPhone = nhanVienModel.getEmpPhone();
        String empMail = nhanVienModel.getEmpMail();
        String empCCCD = nhanVienModel.getEmpCCCD();
        String empRole = nhanVienModel.getEmpRole();


        if(empName.length() == 0 || empPhone.length() == 0 || empMail.length() == 0 || empCCCD.length() == 0)
            return false;

        boolean check = CheckConditionNhanVien(nhanVienModel);

        if(!check) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_TenNV, empName);
            cv.put(COLUMN_DiaChi, empAddr);
            cv.put(COLUMN_NhanVien_SDT, empPhone);
            cv.put(COLUMN_NhanVien_Mail, empMail);
            cv.put(COLUMN_CCCD, empCCCD);
            cv.put(COLUMN_VaiTro, empRole);

            db.insert(TABLE_NhanVien, null, cv);

            db.close();
            return true;
        }
        return false;
    }

    // Check CHANGBAY MAYBAY
    public boolean CheckConditionNhanVien(NhanVienModel nhanVienModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = new String[] {
                nhanVienModel.getEmpMail(),
                nhanVienModel.getEmpPhone(),
                nhanVienModel.getEmpCCCD()
        };

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NhanVien
                + " WHERE " + COLUMN_NhanVien_Mail + " = ?"
                + " OR " + COLUMN_NhanVien_SDT + " = ?"
                + " OR " + COLUMN_CCCD + " = ?", selectionArgs);

        if(cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }


    // Get customer by the name is being searched
    public ArrayList<NhanVienModel> get_NV(String search_NV) {
        final ArrayList<NhanVienModel> arrayList = new ArrayList<NhanVienModel>();

        String tenNV_convert = "'%" + search_NV + "%'";
        // get data from database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NhanVien +
                " WHERE " + COLUMN_TenNV + " LIKE " + tenNV_convert, null);

        if(cursor.moveToFirst()) {
            do {
                int maNV = cursor.getInt(0);
                String empName = cursor.getString(1);
                String empCCCD = cursor.getString(2);
                String empAddr = cursor.getString(3);
                String empPhone = cursor.getString(4);
                String empMail = cursor.getString(5);
                String empRole = cursor.getString(6);

                NhanVienModel nhanVienModel = new NhanVienModel(maNV, empName, empCCCD, empAddr, empPhone, empMail, empRole);

                arrayList.add(nhanVienModel);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arrayList;
    }

    // Update a CHANGBAY MAYBAY data
    public Boolean updateNV(NhanVienModel nhanVienModel, String empCCCD, String empAddr, String empPhone, String empMail) {
        SQLiteDatabase db = getWritableDatabase();

        String empCCCD_convert = "'" + empCCCD + "'";
        String empAddr_convert = "'" + empAddr + "'";
        String empPhone_convert = "'" + empPhone + "'";
        String empMail_convert = "'" + empMail + "'";

        try {

            db.execSQL("UPDATE " + TABLE_NhanVien
                    + " SET "+ COLUMN_CCCD + " = " + empCCCD_convert
                    + "," + COLUMN_DiaChi + " = " + empAddr_convert
                    + "," + COLUMN_NhanVien_SDT + " = " + empPhone_convert
                    + "," + COLUMN_NhanVien_Mail + " = " + empMail_convert
                    + " WHERE " + COLUMN_NhanVien_MaNV + " = " + nhanVienModel.getEmpID());

            db.close();
            return true;
        }
        catch (Exception e) {}
        db.close();
        return false;
    }

    // Delete a customer
    public Boolean deleteNV (NhanVienModel nhanVienModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_NhanVien +
                " WHERE " + COLUMN_NhanVien_MaNV + " = " + nhanVienModel.getEmpID(), null);

        if (cursor.moveToFirst())
        {
            cursor.close();
            db.close();
            return true;
        }
        return false;
    }
    /*---------------------------------------------- TABLE_NHANVIEN METHODS END ---------------------------------------------- */

    /*---------------------------------------------- TABLE_MayBay METHODS START ---------------------------------------------- */
    public boolean addMayBay(MayBayModel mayBayModel) {
        boolean check = CheckConditionXe(mayBayModel);

        if(!check) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_TenMayBay, mayBayModel.getTenXe());
            cv.put(COLUMN_MaSoMB, mayBayModel.getBienSo());
            cv.put(COLUMN_SoGhe, mayBayModel.getSoGhe());

            db.insert(TABLE_MayBay, null, cv);

            db.close();
            return true;
        }
        return false;
    }

    // Update a xe data
    public Boolean updateMayBay(MayBayModel mayBayModel, String tenMB, String plate, String seats) {
//        SQLiteDatabase db = getWritableDatabase();
//
//        String[] selectionArgs = new String[] {
//            mayBayModel.getTenXe(),
//            mayBayModel.getbienSo()
//        };
//
//        try {
//            Cursor cursor = db.rawQuery("UPDATE " + TABLE_MayBay
//                    + " SET "+ COLUMN_TenMayBay + " = ?"
//                    + "," + COLUMN_MaSoMB + " = ?"
//                    + "," + COLUMN_SoGhe + " = " + seats
//                    + " WHERE " + COLUMN_MayBay_MaMB + " = " + mayBayModel.getMãXe(), selectionArgs);
//
//            cursor.close();
//            db.close();
//            return true;
//        }
//        catch (Exception e) {}
//        db.close();
//        return false;
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TenMayBay, tenMB);
        cv.put(COLUMN_MaSoMB, plate);
        cv.put(COLUMN_SoGhe, seats);


        try {
            db.update(TABLE_MayBay, cv, "" + COLUMN_MayBay_MaMB + " = " + mayBayModel.getMãXe(), null);
            db.close();
            return true;
        }
        catch (Exception e) {}
        db.close();
        return false;
    }

    // Delete a customer
    public Boolean deleteMayBay(MayBayModel mayBayModel)
    {
        if(deleteXeRequestValidator(mayBayModel))
        {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_MayBay +
                    " WHERE " + COLUMN_MayBay_MaMB + " = " + mayBayModel.getMãXe(), null);

            if (cursor.moveToFirst())
            {
                cursor.close();
                db.close();

            }
            return true;

        }
        return false;
    }

    public Boolean deleteXeRequestValidator(MayBayModel mayBayModel)
    {
        ArrayList<TuyenBayModel> arrayList = getTuyenXeOption( "", mayBayModel.getBienSo(), false);
        if(arrayList.size() > 0)
        {
            return false;
        }
        return true;
    }

    public Boolean CheckConditionXe(MayBayModel mayBayModel)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = new String[] { mayBayModel.getBienSo() };
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MayBay
                + " WHERE " + COLUMN_TenMayBay + " = ?", selectionArgs);
        return cursor.moveToFirst();
    }

    // Get customer by the name is being searched
    public ArrayList<MayBayModel> getXe(String tenMayBay) {
        final ArrayList<MayBayModel> arrayList = new ArrayList<>();

        String tenXe_convert = "'%" + tenMayBay + "%'";
        // get data from database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MayBay +
                " WHERE " + COLUMN_TenMayBay + " LIKE " + tenXe_convert, null);

        if(cursor.moveToFirst()) {
            do {
                MayBayModel mayBayModel = new MayBayModel();
                mayBayModel.setMãXe(cursor.getInt(0));
                mayBayModel.setTenXe(cursor.getString(1));
                mayBayModel.setBienSo(cursor.getString(2));
                mayBayModel.setSoGhe(cursor.getInt(3));


                arrayList.add(mayBayModel);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arrayList;
    }
    /*---------------------------------------------- TABLE_XE METHODS END ---------------------------------------------- */

    /*---------------------------------------------- TABLE_CTHD METHODS START ---------------------------------------------- */
    public Cursor getBookedSeats(int maTuyenBay)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Ghe FROM " + TABLE_CTHD
                + " WHERE " + COLUMN_CTHD_MaTuyenBay + " = " + maTuyenBay, null);
        if(cursor.moveToFirst())
        {
            db.close();
            return cursor;
        }
        db.close();
        cursor.close();
        return null;
    }

    public ArrayList<CTHDModel> getAllCTHD(int maKH, String tenTuyen, String bienSoXe)
    {
        final ArrayList<CTHDModel> arrayList = new ArrayList<>();
        String tenTuyen_convert = "'%" + tenTuyen + "%'";
        String bienSoXe_convert = "'%" + bienSoXe + "%'";
        String maKhString = "'%" + maKH + "%'";

        SQLiteDatabase db = this.getReadableDatabase();
        if(maKH <= 0)
        {
            maKhString = "'%%'";
        }

        Cursor cursor = db.rawQuery("SELECT CTHD.MaDon, CTHD.MaKH, KHACHHANG.TenKH, TUYENBAY.MaTuyenBay, CHANGBAY.TenChang, MAYBAY.TenMB, TUYENBAY.NgayBay, CTHD.Ghe, CTHD.NgayDat, CTHD.ThanhTien, CTHD.TrangThai " +
                "FROM CTHD " +
                "INNER JOIN TUYENBAY ON TUYENBAY.MaTuyenBay = CTHD.MaTuyenBay " +
                "INNER JOIN CHANGBAY ON TUYENBAY.MaChang = CHANGBAY.MaChang " +
                "INNER JOIN KHACHHANG ON KHACHHANG.MaKH = CTHD.MaKH " +
                "INNER JOIN MAYBAY ON TUYENBAY.MaMB = MAYBAY.MaMB " +
                "WHERE CTHD.MaKH LIKE " + maKhString +
                " AND CHANGBAY.TenChang LIKE " + tenTuyen_convert +
                " AND MAYBAY.MaSoMB LIKE " + bienSoXe_convert, null);

        while (cursor.moveToNext()) {
            CTHDModel cthdModel = new CTHDModel();
            cthdModel.setMaDon(cursor.getInt(0));
            cthdModel.setMaKH(cursor.getInt(1));
            cthdModel.setTenKH(cursor.getString(2));
            cthdModel.setMaTuyenXe(cursor.getInt(3));
            cthdModel.setTenTuyen(cursor.getString(4));
            cthdModel.setTenXe(cursor.getString(5));
            cthdModel.setNgayKhoiHanh(cursor.getString(6));
            cthdModel.setGhe(cursor.getString(7));
            cthdModel.setNgayDat(cursor.getString(8));
            cthdModel.setThanhTien(cursor.getInt(9));
            cthdModel.setTrangThai(cursor.getString(10));

            arrayList.add(cthdModel);
        }
        cursor.close();
        db.close();
        return arrayList;
    }


    public void addCTHD(TuyenBayModel tuyenBayModel, String seats, int maKH, int total, int hanhLy)
    {
        Date date = new Date();
        String currentDateTime = localDateTimeConvert.getLocalDateTimeString(date.toString());

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CTHD_MaKH, maKH);
        cv.put(COLUMN_CTHD_MaTuyenBay , tuyenBayModel.getMaTuyenXe());
        cv.put(COLUMN_CTHD_Ghe, seats);
        cv.put(COLUMN_TrangThai, "Chua thanh toan");
        cv.put(COLUMN_ThanhTien, total);
        cv.put(COLUMN_NgayDat, currentDateTime);
        cv.put(COLUMN_HanhLy, hanhLy);
        db.insert(TABLE_CTHD, null, cv);
    }

    public Boolean deleteCTHD(CTHDModel cthdModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_CTHD +
                " WHERE " + COLUMN_MaDon + " = " + cthdModel.getMaDon(), null);

        if (cursor.moveToFirst())
        {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }
    /*---------------------------------------------- TABLE_CTHD METHODS END ---------------------------------------------- */

    /*---------------------------------------------- TABLE_TUYENXE METHODS START ---------------------------------------------- */
    // Get data by the name is being searched
    public ArrayList<TuyenBayModel> getTuyenXeOption(String search_tenTuyen, String bienSoXe, boolean isKH) {
        final ArrayList<TuyenBayModel> arrayList = new ArrayList<>();

        String tenTuyen_convert = "'%" + search_tenTuyen + "%'";
        String bienSoXe_convert = "'%" + bienSoXe + "%'";
        // get data from database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT TUYENBAY.MaTuyenBay, TUYENBAY.NgayBay, CHANGBAY.MaChang, CHANGBAY.TenChang, MAYBAY.MaMB, MAYBAY.TenMB, TUYENBAY.GiaVe, TUYENBAY.LoaiVe, TUYENBAY.HangGhe, CHANGBAY.SanBayDi, CHANGBAY.SanBayDen, MAYBAY.SoGhe " +
                "FROM TUYENBAY " +
                "INNER JOIN CHANGBAY ON TUYENBAY.MaChang = CHANGBAY.MaChang " +
                "INNER JOIN MAYBAY ON TUYENBAY.MaMB = MAYBAY.MaMB " +
                "WHERE CHANGBAY.TenChang LIKE + " + tenTuyen_convert +
                " AND MAYBAY.MaSoMB LIKE + " + bienSoXe_convert, null);

        Date date = new Date();
        String currentDateTime = localDateTimeConvert.getLocalDateTimeString(date.toString());
        if(cursor.moveToFirst()) {
            do {
                TuyenBayModel tuyenBayModel = new TuyenBayModel();
                tuyenBayModel.setMaTuyenXe(cursor.getInt(0));
                tuyenBayModel.setNgayKhoihanh(cursor.getString(1));

                if(isKH && tuyenBayModel.getNgayKhoihanh() != null && !localDateTimeConvert.DateTimeCompare(currentDateTime, tuyenBayModel.getNgayKhoihanh()))
                {
//                    if(!localDateTimeConvert.DateTimeCompare(currentDateTime, tuyenBayModel.getNgayKhoihanh()))
                        getDataTuyenXe(tuyenBayModel, arrayList, cursor);
                } else if(!isKH) {
                    getDataTuyenXe(tuyenBayModel, arrayList, cursor);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arrayList;
    }
    public void getDataTuyenXe(TuyenBayModel tuyenBayModel, ArrayList<TuyenBayModel> arrayList, Cursor cursor)
    {
        tuyenBayModel.setMaTuyen(cursor.getInt(2));
        tuyenBayModel.setTenTuyen(cursor.getString(3));
        tuyenBayModel.setMaXe(cursor.getInt(4));
        tuyenBayModel.setTenMB(cursor.getString(5));
        tuyenBayModel.setGia(cursor.getInt(6));
        tuyenBayModel.setLoaiVe(cursor.getString(7));
        tuyenBayModel.setHangGhe(cursor.getString(8));
        tuyenBayModel.setSanBayDi(cursor.getString(9));
        tuyenBayModel.setSanBayDen(cursor.getString(10));
        tuyenBayModel.setSoGhe(cursor.getInt(11));

        arrayList.add(tuyenBayModel);
    }

    public Boolean addTuyenXe(ChangBayModel changBayModel, MayBayModel mayBayModel)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TUYENBAY " +
                "WHERE MaChang = " + changBayModel.getMaTuyen() +
                " AND MaMB = " + mayBayModel.getMãXe(), null);
        if(!cursor.moveToFirst())
        {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_TuyenBay_MaChang, changBayModel.getMaTuyen());
            cv.put(COLUMN_TuyenBay_MaMB, mayBayModel.getMãXe());

            if(db.insert(TABLE_TuyenBay, null, cv) != -1)
            {
                cursor.close();
                db.close();
                return true;
            }
        }
        db.close();
        return false;
    }
    public Boolean updateTuyenXe(TuyenBayModel tuyenBayModel, String ngayBay, String hangGhe, String loaiVe, Integer giaVe, String nhanVien) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TUYENBAY_NgayBay, ngayBay);
        cv.put(COLUMN_HangGhe, hangGhe);
        cv.put(COLUMN_LoaiVe, loaiVe);
        cv.put(COLUMN_GiaVe, giaVe);
        cv.put(COLUMN_TuyenBay_MaNV, nhanVien);

        try {
            db.update(TABLE_TuyenBay, cv, "" + COLUMN_TuyenBay_MaTuyenBay + " = " + tuyenBayModel.getMaTuyenXe(), null);
            db.close();
            return true;
        }
        catch (Exception e) {}
        db.close();
        return false;
    }

    public Boolean deleteTuyenXe(TuyenBayModel tuyenBayModel)
    {
        if(deleteTuyenXeRequestValidator(tuyenBayModel))
        {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_TuyenBay +
                    " WHERE " + COLUMN_TuyenBay_MaTuyenBay + " = " + tuyenBayModel.getMaTuyenXe(), null);

            if (cursor.moveToFirst())
            {
                cursor.close();
                db.close();
            }

            return true;
        }
        return false;
    }

    public Boolean deleteTuyenXeRequestValidator(TuyenBayModel tuyenBayModel)
    {
        ArrayList<CTHDModel> cthdList = getAllCTHD(-1, String.valueOf(tuyenBayModel.getMaTuyen()), "e");
        if(cthdList.size() > 0)
        {
            return false;
        }
        return true;
    }
    /*---------------------------------------------- TABLE_TUYENXE METHODS END ---------------------------------------------- */
    /*---------------------------------------------- Tong Doanh Thu ---------------------------------------------- */
    public int getTotalRevenue() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(" + COLUMN_ThanhTien + ") AS TotalRevenue FROM " + TABLE_CTHD;
        Cursor cursor = db.rawQuery(query, null);

        int totalRevenue = 0;

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("TotalRevenue");
            if (columnIndex >= 0) {
                totalRevenue = cursor.getInt(columnIndex);
            }
        }

        cursor.close();
        return totalRevenue;
    }

    /*---------------------------------------------- Tong Hoa Don ---------------------------------------------- */
    public int getNumberOfOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) AS NumberOfOrders FROM " + TABLE_CTHD;
        Cursor cursor = db.rawQuery(query, null);

        int numberOfOrders = 0;

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("NumberOfOrders");
            if (columnIndex >= 0) {
                numberOfOrders = cursor.getInt(columnIndex);
            }
        }

        cursor.close();
        return numberOfOrders;
    }


}
