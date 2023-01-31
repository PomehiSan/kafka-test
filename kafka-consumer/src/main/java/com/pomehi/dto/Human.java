package com.pomehi.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class Human {

    BlockingDeque<Hand> hands;

    public Human() {
        hands = new LinkedBlockingDeque<>(List.of(new Hand("Левая"), new Hand("Правая")));
    }

    public BlockingDeque<Hand> getHands() {
        return hands;
    }
}
