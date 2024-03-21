package edu.project.TouristTicketOrder.BookingPage;

import android.content.Context;

public class PaymentStrategyFactory  {
    public static   PaymentStrategy getPaymentMethod(String method, Context context) {
        if ("cash".equals(method)) {
            return new CashPayment(context);
        } else if ("zalo".equals(method)) {
            return new ZaloPayment(context);
        } else {
            throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
    }
}
