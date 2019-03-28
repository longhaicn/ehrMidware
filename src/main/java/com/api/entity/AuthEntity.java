package com.api.entity;

import lombok.Data;

import java.util.List;

@Data
public class AuthEntity {

    private List<String> usernames;

    private String appcode;
}
