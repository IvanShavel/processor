package com.stock.processor.controller.impl;

import com.stock.processor.controller.RequestController;
import com.stock.processor.dto.Request;
import com.stock.processor.schedule.RequestSchedule;
import com.stock.processor.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class RequestControllerImpl implements RequestController {
    private final RequestService requestService;
    @Override
    public long startSchedule(Request request) {

        Flux.interval(Duration.ofSeconds(3)).publishOn(Schedulers.elastic())
                .doOnEach(po-> {
                    System.out.println(WebClient.create("https://sandbox.iexapis.com/stable/")
                            .get()
                            .uri("stock/twtr/price?token=" + "Tsk_a29d14855d4c4ac5b8bc51a86ea05b44")
                            .retrieve()
                            .bodyToMono(Double.class)
                            .block()
                    );
                        }
                ).blockLast();
        return 0;
        //   return requestService.startSchedule(request);
    }

    @Override
    public boolean stopSchedule(long scheduleNumber) {
        return false;
    }


    public static void main(String[] args) {


        System.out.println(WebClient.create("https://sandbox.iexapis.com/stable/")
                .get()
                .uri("stock/twtr/price?token=" + "Tsk_a29d14855d4c4ac5b8bc51a86ea05b44")
                .retrieve()
                .bodyToMono( Double.class)
                .block()
        );


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
