package edu.project.TouristTicketOrder.BookingPage;

public abstract class PriceDecorator implements Price {
    protected Price decoratedPrice;

    public PriceDecorator(Price decoratedPrice) {
        this.decoratedPrice = decoratedPrice;
    }

    @Override
    public int calculate() {
        return decoratedPrice.calculate();
    }
}

