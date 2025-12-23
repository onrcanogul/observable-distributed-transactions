package com.starter.dtxcollector.presentation.dto;

public final record RegisterModel(
        String username,
        String password,
        String email
) {
}
