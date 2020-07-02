package com.yuliia.vlasenko.imagesearcher.download;

import lombok.Data;

@Data
public class AuthResponse {
    private boolean auth;
    private String token;
}
