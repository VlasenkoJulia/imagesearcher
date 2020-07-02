package com.yuliia.vlasenko.imagesearcher.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String apiKey;
}
