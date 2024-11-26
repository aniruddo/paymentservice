package com.ecommerce.paymentservice.paymentgateway;

import com.razorpay.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {
    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;


    public PaymentGateway getPaymentGateway() {
        return razorpayPaymentGateway;
    }
}