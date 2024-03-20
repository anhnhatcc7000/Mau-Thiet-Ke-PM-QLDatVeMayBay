package edu.project.TouristTicketOrder.Admin_Activity.CTHD;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String searchQuery);
}
