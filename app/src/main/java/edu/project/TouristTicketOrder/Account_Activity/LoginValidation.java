package edu.project.TouristTicketOrder.Account_Activity;

import edu.project.TouristTicketOrder.Model.CustomerModel;

public class LoginValidation {
    public CustomerModel curUser;
    public boolean correct;

    public LoginValidation(CustomerModel curUser, boolean correct) {
        this.curUser = curUser;
        this.correct = correct;
    }

    public LoginValidation(boolean correct) {
        this.correct = correct;
    }

    public CustomerModel getCurUser() {
        return curUser;
    }

    public void setCurUser(CustomerModel curUser) {
        this.curUser = curUser;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
