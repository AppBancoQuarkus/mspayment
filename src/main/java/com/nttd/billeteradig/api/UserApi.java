package com.nttd.billeteradig.api;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Path;

@RegisterRestClient
@Path("/user")
public interface UserApi {
    
    //@POST
    //public Uni<UserResponse> add( UserRequest auditRequest);
}
