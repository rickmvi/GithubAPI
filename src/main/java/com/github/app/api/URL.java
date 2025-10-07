package com.github.app.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum URL {
    GITHUB("https://api.github.com/users/");

    @Getter(value = AccessLevel.PUBLIC)
    private final String url;
}
