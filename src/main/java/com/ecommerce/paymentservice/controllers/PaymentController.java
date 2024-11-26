package com.ecommerce.paymentservice.controllers;

import com.ecommerce.paymentservice.dtos.InititatePaymentDto;
import com.ecommerce.paymentservice.paymentgateway.PaymentGateway;
import com.ecommerce.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public String initiatePayment(@RequestBody InititatePaymentDto initiatePaymentDto) {

        return paymentService.getPaymentLink(initiatePaymentDto.getAmount(), initiatePaymentDto.getOrderId(), initiatePaymentDto.getPhoneNumber(), initiatePaymentDto.getName());
    }
}
