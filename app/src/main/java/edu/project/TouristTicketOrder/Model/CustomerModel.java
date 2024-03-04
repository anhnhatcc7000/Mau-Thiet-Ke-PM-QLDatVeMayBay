package edu.project.TouristTicketOrder.Model;

import java.io.Serializable;

public class CustomerModel implements Serializable {

    private int id;
    private String tenKH;
    private String SDT;
    private String mail;
    private String pass;
    private String Passport;

    private String phone;


    //Constructors
    public CustomerModel() {
    }

    public CustomerModel(int id, String tenKH, String SDT, String mail, String pass) {
        this.id = id;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.mail = mail;
        this.pass = pass;
    }

    public CustomerModel(String tenKH, String SDT, String mail, String pass) {
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.mail = mail;
        this.pass = pass;
    }

    public CustomerModel(String tenKH, String SDT, String mail, String pass, String Passport) {
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.mail = mail;
        this.pass = pass;
        this.Passport = Passport;
    }

    public CustomerModel(int id, String tenKH, String SDT, String mail, String pass, String passport) {
        this.id = id;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.mail = mail;
        this.pass = pass;
        this.Passport = passport;
    }

    public CustomerModel(String tenKH) {
        this.tenKH = tenKH;
    }


    // ToString method




    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassport() {
        return Passport;
    }

    public void setPassport(String passport) {
        this.Passport = passport;
    }
}
