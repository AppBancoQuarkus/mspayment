package com.nttd.billeteradig.service;

import io.smallrye.mutiny.Uni;

public interface PaymentService {

    public Uni<String> getAllPayment();

}
