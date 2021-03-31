package com.changyi.fegin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
public class User implements Serializable {

    private String name;

    private String password;
}
