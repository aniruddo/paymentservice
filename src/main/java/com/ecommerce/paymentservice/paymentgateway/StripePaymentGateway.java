package com.ecommerce.paymentservice.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway{

    @Value("${stripe.apikey}")
    String apiKey;

    @Override
    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String name) {
        Stripe.apiKey = this.apiKey;
        Price price = getPrice(amount);
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder().setUrl("https://example.com/success").build())
                                .build())
                        .build();

        try {
            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl().toString();
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private Price getPrice(Long amount){
        Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(amount)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        try {
            Price price = Price.create(params);
            return price;
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
