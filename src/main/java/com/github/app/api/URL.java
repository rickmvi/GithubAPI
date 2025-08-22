package com.github.app.api;

@lombok.RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public enum URL {
    GITHUB("https://api.github.com/users/");

    @lombok.Getter(value = lombok.AccessLevel.PUBLIC)
    private final String url;
}
