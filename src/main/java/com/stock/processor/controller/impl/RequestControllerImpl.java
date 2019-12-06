package com.stock.processor.controller.impl;

import com.stock.processor.controller.RequestController;
import com.stock.processor.dto.Request;
import com.stock.processor.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class RequestControllerImpl implements RequestController {
    private final RequestService requestService;

    @Override
    public void startSchedule(Request request) {
        requestService.startSchedule(request);
    }

    @Override
    public void stopSchedule(Request request) {
        requestService.stopSchedule(request);
    }


    public static void main(String[] args) {

        Scheduler scheduler = Schedulers.elastic();
        scheduler.dispose();
        Flux<Long> qqq = Flux.interval(Duration.ofSeconds(10))
                .publishOn(scheduler)
                .doOnEach(po -> {
                            System.out.println(po + " time - "
                                    + LocalDateTime.now());
                    /*      System.out.println(WebClient.create("https://sandbox.iexapis.com/stable/")
                                    .get()
                                    .uri("stock/twtr/price?token=" + "Tsk_a29d14855d4c4ac5b8bc51a86ea05b44")
                                    .retrieve()
                                    .bodyToMono(Double.class)
                                    .block()
                                    + " time - "
                                    + LocalDateTime.now()
                            );*/
                        }
                )
                .doOnError(q -> System.out.println("error"))
                .doFinally(q -> System.out.println("eend"));

        System.out.println("312312312312312");

        qqq
                .blockLast();





/*        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(System.out::println);

        Flux<Long> sequence =
        Flux
                .interval(Duration.ofSeconds(5));
        sequence.subscribe(System.out::println);*/

/*Flux<Long> seq =  Flux.interval(Duration.ofSeconds(5));

seq.subscribe(iii->
        System.out.println(WebClient.create("https://sandbox.iexapis.com/stable/")
                .get()
                .uri("stock/twtr/price?token=" + "Tsk_a29d14855d4c4ac5b8bc51a86ea05b44")
                .retrieve()
                .bodyToMono( Long.class)
                .block(Duration.ofSeconds(5))
        )
);*/

      /*  Flux.interval(Duration.ofSeconds(5))
                .doOnEach(i->{
                    System.out.println(WebClient.create("https://sandbox.iexapis.com/stable/")
                        .get()
                        .uri("stock/twtr/price?token=" + "Tsk_a29d14855d4c4ac5b8bc51a86ea05b44")
                        .retrieve()
                        .bodyToMono( Long.class)
                            .toString()
                    );
                })
                .blockLast();*/

        /*
                .doOnEach(signal -> System.out.println( signal.get()))
                .blockLast();*/

/*        sequence.subscribe(
                System.out::println
                i->{

            System.out.println(WebClient.create("https://sandbox.iexapis.com/stable/")
                    .get()
                    .uri("stock/twtr/price?token=" + "Tsk_a29d14855d4c4ac5b8bc51a86ea05b44")
                    .retrieve()
                    .bodyToMono( Long.class)
                    .block());
        });*/
/*
        System.out.println(WebClient.create("https://sandbox.iexapis.com/stable/")
                .get()
                .uri("stock/twtr/price?token=" + "Tsk_a29d14855d4c4ac5b8bc51a86ea05b44")
                .retrieve()
                .bodyToMono( Long.class)
                .block());
*/


    }

}
