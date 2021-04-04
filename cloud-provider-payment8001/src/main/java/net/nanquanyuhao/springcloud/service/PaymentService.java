package net.nanquanyuhao.springcloud.service;

import net.nanquanyuhao.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
