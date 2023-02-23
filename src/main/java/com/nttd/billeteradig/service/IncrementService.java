package com.nttd.billeteradig.service;

import com.nttd.billeteradig.redis.Increment;
import com.nttd.billeteradig.service.IncrementService;
import io.smallrye.mutiny.Uni;



public interface IncrementService {

    public Uni<Increment> add(Increment increment);

    public Uni<String> get(String key);
}
