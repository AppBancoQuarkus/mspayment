package com.nttd.billeteradig.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import com.nttd.billeteradig.dto.ResponseDto;
import com.nttd.billeteradig.entity.PaymentEntity;
import com.nttd.billeteradig.service.PaymentService;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;



@Path("/api/payment")
public class PaymentResource {

   @Inject
   PaymentService paymentService;

  
   @Inject
   Logger logger;


   /* Buscar las operaciones del telefono */
   @GET
   @Path("/{id}")
   @Operation(summary = "Obtener las operaciones de la billetera digital por número telefono.",description = "Permite obtener las operaciones de la billetera digital por número telefono.")
   public Uni<ResponseDto> getPhoneOperation(@PathParam("id") String phonenumber){
        logger.info("Iniciando el metodo getPhoneOperation - Resource.");
        return paymentService.getPhoneOperation(phonenumber);
   }


     /* Crear el pago de la billetera digital */
     @POST
     @Operation(summary = "Registrar el pago de la billetera digital",description = "Permite registrar el pago de la billetera digital")
     public Uni<ResponseDto> addPayPhone(PaymentEntity paymentEntity){
     logger.info("Iniciando el metodo addPayPhone - Resource.");
     return paymentService.addPayPhone(paymentEntity);
     } 


}
