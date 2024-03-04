package edu.project.TouristTicketOrder.Model;

import java.io.Serializable;

public class NhanVienModel implements Serializable {
    int empID;
    String empName;
    String empCCCD;
    String empAddr;
    String empPhone;
    String empMail;
    String empRole;

    public NhanVienModel() {
    }

    public NhanVienModel(String empName, String empCCCD, String empAddr, String empPhone, String empMail, String empRole) {
        this.empName = empName;
        this.empCCCD = empCCCD;
        this.empAddr = empAddr;
        this.empPhone = empPhone;
        this.empMail = empMail;
        this.empRole = empRole;
    }

    public NhanVienModel(int empID, String empName, String empCCCD, String empAddr, String empPhone, String empMail, String empRole) {
        this.empID = empID;
        this.empName = empName;
        this.empCCCD = empCCCD;
        this.empAddr = empAddr;
        this.empPhone = empPhone;
        this.empMail = empMail;
        this.empRole = empRole;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpCCCD() {
        return empCCCD;
    }

    public void setEmpCCCD(String empCCCD) {
        this.empCCCD = empCCCD;
    }

    public String getEmpAddr() {
        return empAddr;
    }

    public void setEmpAddr(String empAddr) {
        this.empAddr = empAddr;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpMail() {
        return empMail;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }
}
