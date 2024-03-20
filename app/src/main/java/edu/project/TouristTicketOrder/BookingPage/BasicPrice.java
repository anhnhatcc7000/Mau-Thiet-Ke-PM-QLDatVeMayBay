package edu.project.TouristTicketOrder.BookingPage;

public class BasicPrice implements Price {
    private int basePrice;

    public BasicPrice(int basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public int calculate() {
        return basePrice;
    }
}

