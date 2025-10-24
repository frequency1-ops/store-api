package com.tech2tech.store.services;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import com.tech2tech.store.entities.Order;
import com.tech2tech.store.entities.OrderItem;
import com.tech2tech.store.entities.PaymentStatus;
import com.tech2tech.store.exceptions.PaymentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class StripePaymentGateway implements PaymentGateway {
    @Value("${websiteUrl}")
    private String websiteUrl;
    @Value("${stripe.webhookSecretKey}")
    private String webhookSecretKey;
    @Override
    public CheckoutSession createCheckoutSession(Order order) {
        try{
            var builder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(websiteUrl+"/checkout-success?orderId="+order.getId())
                    .setCancelUrl(websiteUrl+"/checkout-cancel")
                    .putMetadata("order_id", order.getId().toString());
            order.getItems().forEach(item -> {
                var lineItem = createLineItem(item);
                builder.addLineItem(lineItem);
            });
            var session = Session.create(builder.build());
            return new CheckoutSession(session.getUrl());
        }catch (StripeException e){
            System.out.println(e.getMessage());
            throw new PaymentException();
        }
    }

    @Override
    public Optional<PaymentResults> parseWebhookRequest(WebhookRequest request) {
        try {
            var signature = request.getHeaders().get("stripe-signature");
            var payload = request.getPayload();
            var event = Webhook.constructEvent(payload, signature, webhookSecretKey);


            return switch (event.getType()) {
                case "payment_intent.succeeded" ->
                    //update order status (PAID)
                    Optional.of(new PaymentResults(extractOrderId(event), PaymentStatus.PAID));

                case "payment_intent.PAYMENT_failed" ->
                    //update order status (FAILED)
                    Optional.of(new PaymentResults(extractOrderId(event), PaymentStatus.FAILED));

                default -> Optional.empty();

            };

        } catch (SignatureVerificationException e) {
        throw new PaymentException();
        }
}

private Long extractOrderId(Event event){
    var stripeObject = event.getDataObjectDeserializer().getObject().orElseThrow(
            () -> new PaymentException("Could not extract order id")
        );
    var paymentIntent = (PaymentIntent) stripeObject;
    return Long.valueOf(paymentIntent.getMetadata().get("order_id"));
}

private SessionCreateParams.LineItem createLineItem(OrderItem item) {
    return SessionCreateParams.LineItem.builder()
            .setQuantity(Long.valueOf(item.getQuantity()))
            .setPriceData(createPriceData(item))
            .build();
}

private SessionCreateParams.LineItem.PriceData createPriceData(OrderItem item) {
    return SessionCreateParams.LineItem.PriceData.builder()
            .setCurrency("usd")
            .setUnitAmountDecimal(item.getUnitPrice().multiply(BigDecimal.valueOf(100)))
            .setProductData(
                    createProductData(item)
            )
            .build();
}

private SessionCreateParams.LineItem.PriceData.ProductData createProductData(OrderItem item) {
    return SessionCreateParams.LineItem.PriceData.ProductData.builder()
            .setName(item.getProduct().getName())
            .build();
}
}
