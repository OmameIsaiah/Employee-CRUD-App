package com.employee.crud.app.payload.response;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtAuthResponse {

    @NonNull
    private String accessToken;
    private String tokenType = "Bearer";
}
