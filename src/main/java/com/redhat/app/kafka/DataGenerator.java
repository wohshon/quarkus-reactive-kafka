package com.redhat.app.kafka;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import io.smallrye.reactive.messaging.annotations.Broadcast;

@ApplicationScoped
public class DataGenerator {
    private Random random = new Random();
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Outgoing("generated-data") 
    @Broadcast              
    public Flowable<Integer> generate() {
        return Flowable.interval(2, TimeUnit.SECONDS)
                .map(tick -> random.nextInt(100));
                //.map(tick -> 1);
        /**
        Flowable<Integer> f = Flowable.just(1,2,3,4,5,6,7,8);
        TestSubscriber<Integer> testSubscriber = f.test();
        List<Integer> receivedInts = testSubscriber.getEvents()
        .get(0)
        .stream()
        .mapToInt(object -> (int) object)
        .boxed()
        .collect(Collectors.toList());

        log.info("output");
        
        receivedInts.forEach( item -> log.info("Contents:"+item));
        return f; 
        */
    
    }
}