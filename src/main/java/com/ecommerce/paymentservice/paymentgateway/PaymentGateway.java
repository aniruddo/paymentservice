package com.ecommerce.paymentservice.paymentgateway;


public interface PaymentGateway {
    public String getPaymentLink(Long amount,String orderId,String phoneNumber,String name);
}
