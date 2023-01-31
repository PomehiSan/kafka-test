package com.pomehi.task;

import com.pomehi.dto.Cat;
import com.pomehi.dto.Hand;
import com.pomehi.dto.Human;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Component
@RequiredArgsConstructor
@Slf4j
public class CatRunnable implements Runnable {

    private final BlockingDeque<Cat> cats;
    private final Human human;


    @SneakyThrows
    @Override
    public void run() {
        if (!cats.isEmpty()) {
            Cat cat = cats.poll();
            Hand hand = human.getHands().take();
            pet(cat, hand);
        }
    }

    @SneakyThrows
    public void pet(Cat cat, Hand hand) {
        log.info("{} гладит {} рука, в потоке {}", cat.getName(), hand.getName(), Thread.currentThread().getName());
        Thread.sleep(3000);
        log.info("{} погладила {} рука, поток {} свободен", cat.getName(), hand.getName(), Thread.currentThread().getName());
        human.getHands().add(hand);
    }
}
