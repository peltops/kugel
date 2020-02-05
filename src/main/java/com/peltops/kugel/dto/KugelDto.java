package com.peltops.kugel.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class KugelDto {

    private Integer id;

    private String color;

    @NotNull
    private Integer diameter;
}
