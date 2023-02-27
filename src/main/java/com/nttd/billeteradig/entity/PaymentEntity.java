package com.nttd.billeteradig.entity;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection="DWTOPERATION")
public class PaymentEntity extends ReactivePanacheMongoEntity {

    @BsonProperty("phonenumberorigin")
    private String phoneorigin;
    private String operationdate;
    @BsonProperty("phonenumberdestination")
    private String phonedestination;
    private double amountpay;
    @BsonProperty("paymentdescription")
    private String paydescription;
    private String state;

    public PaymentEntity() {
    }


}
