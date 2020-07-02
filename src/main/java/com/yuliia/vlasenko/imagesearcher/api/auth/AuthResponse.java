package com.yuliia.vlasenko.imagesearcher.api.auth;

import lombok.Data;

@Data
public class AuthResponse {
    private boolean auth;
    private String token;
}
