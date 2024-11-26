package com.ecommerce.paymentservice.paymentgateway;

import com.razorpay.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {
    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;

    @Autowired
    private StripePaymentGateway stripePaymentGateway;
    public PaymentGateway getPaymentGateway() {

        // can generate a random number and based on that number can return the payment gateway
        // or can have some other logic to choose the payment gateway
        return stripePaymentGateway;
    }
}