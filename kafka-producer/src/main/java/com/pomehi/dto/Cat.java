package com.pomehi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cat {

    private String name;

    private int age;

    private Toy favoriteToy;
}
