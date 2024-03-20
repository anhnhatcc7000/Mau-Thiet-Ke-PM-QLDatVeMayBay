package edu.project.TouristTicketOrder.BookingPage;

public class VoucherDecorator extends PriceDecorator {
    private int discount;

    public VoucherDecorator(Price decoratedPrice, int discount) {
        super(decoratedPrice);
        this.discount = discount;
    }

    @Override
    public int calculate() {
        return super.calculate() - discount;
    }
}

