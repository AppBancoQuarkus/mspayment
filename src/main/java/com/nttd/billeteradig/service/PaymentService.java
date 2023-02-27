package com.nttd.billeteradig.service;

import com.nttd.billeteradig.dto.ResponseDto;
import com.nttd.billeteradig.entity.PaymentEntity;

import io.smallrye.mutiny.Uni;

public interface PaymentService {

    public Uni<ResponseDto> getPhoneOperation(String phonenumber);
   
    public Uni<ResponseDto> addPayPhone(PaymentEntity paymentEntity);

}
