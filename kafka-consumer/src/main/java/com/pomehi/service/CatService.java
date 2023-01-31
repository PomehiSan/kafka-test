package com.pomehi.service;

import com.pomehi.dto.Cat;
import com.pomehi.task.CatRunnable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class CatService  {

    private final Deque<Cat> cats;
    private final ExecutorService executorService;
    private final CatRunnable catRunnable;

    public void strokingCat(Cat cat) {
        cats.add(cat);
        executorService.submit(catRunnable);
    }
}
