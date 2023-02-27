package com.nttd.billeteradig.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.nttd.billeteradig.api.PhoneApi;
import com.nttd.billeteradig.api.response.PhoneResponse;
import com.nttd.billeteradig.dto.ResponseDto;
import com.nttd.billeteradig.entity.PaymentEntity;
import com.nttd.billeteradig.service.PaymentService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PaymentServiceImpl  implements PaymentService {


    @RestClient
    PhoneApi phoneApi;

    @ConfigProperty(name = "mensaje.general")
    String mensajeGeneral;

    @ConfigProperty(name = "mensaje.monto_mayor")
    String mensajeMontoMayor;

    @ConfigProperty(name = "mensaje.no_existephone")
    String mensajeNoExistePhone;
    
    @ConfigProperty(name = "exception.general")
    String excepcionGeneral;

    @ConfigProperty(name = "valor.activo")
    String valorActivo;
   
    @Override
    public Uni<ResponseDto> getPhoneOperation(String phonenumber){
        Map<String, Object> params = new HashMap<>();
        params.put("state", valorActivo);
        params.put("phonenumberorigin", phonenumber);
        params.put("phonenumberdestination", phonenumber);
        Uni<List<PaymentEntity>> phonepay = PaymentEntity
                .list("state = :state "+
                " and {phonenumberorigin = :phonenumberorigin"+
                    " or phonenumberdestination = :phonenumberdestination}",
                params);
        return phonepay.map(res -> {
            List<PaymentEntity> opelist = new ArrayList<>();
            for(PaymentEntity obj :res){
                if(obj.getPhonedestination().equalsIgnoreCase(phonenumber))
                    obj.setAmountpay(obj.getAmountpay()*-1);

                    opelist.add(obj);                
            }
            return new ResponseDto(Response.Status.OK.getStatusCode(), mensajeGeneral, opelist);
        }).onFailure().recoverWithItem(
                ex -> new ResponseDto(Response.Status.BAD_REQUEST.getStatusCode(), excepcionGeneral, ex.getMessage()));

    }
   
    @Override
    public Uni<ResponseDto> addPayPhone(PaymentEntity paymentEntity){
        
        Uni<PhoneResponse> phoneReactive = phoneApi.findByTelephone(paymentEntity.getPhonedestination());
        return phoneReactive.flatMap(phone -> {
            if(phone != null){
                Map<String, Object> params = new HashMap<>();
                params.put("state", valorActivo);
                params.put("phonenumberorigin", paymentEntity.getPhoneorigin());
                params.put("operationdate",new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                Uni<List<PaymentEntity>> payreactive = PaymentEntity.list("state = :state "+
                            " and phonenumberorigin = :phonenumberorigin"+
                            " and operationdate = :operationdate",
                        params);
                
                return  payreactive.map((lista)->{
                            double cantidad = 0;
                            for(PaymentEntity obj : lista){
                                cantidad += obj.getAmountpay();
                            }
                        cantidad += paymentEntity.getAmountpay();
                        if(cantidad <= -500)
                        return new ResponseDto(Response.Status.BAD_REQUEST.getStatusCode(),
                                                mensajeMontoMayor);
                        else return new ResponseDto(Response.Status.OK.getStatusCode(),
                                            mensajeGeneral);
                    }).flatMap(resultado->{
                        if(resultado.getCode() == Response.Status.OK.getStatusCode()){
                            paymentEntity.setOperationdate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                            paymentEntity.setState(valorActivo);
                            return paymentEntity.persist().map(obj -> {
                                return new ResponseDto(Response.Status.OK.getStatusCode(),
                                        mensajeGeneral, obj);
                                });
                        }
                        return Uni.createFrom().item(resultado);
                    }).onFailure().recoverWithItem(ex -> 
                            new ResponseDto(Response.Status.BAD_REQUEST.getStatusCode(),
                                                excepcionGeneral,
                                                ex.getMessage()));  
            }

            return Uni.createFrom().item(new ResponseDto(Response.Status.BAD_REQUEST.getStatusCode(),
                                                        mensajeNoExistePhone)); 

        });
        
        

    }

}
