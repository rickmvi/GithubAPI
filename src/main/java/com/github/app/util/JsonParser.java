package com.github.app.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@lombok.experimental.UtilityClass
public class JsonParser {

    private final Gson gson = build();

    public <T> T fromJson(@NotNull String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    @ApiStatus.Internal
    @Contract(" -> new")
    private static @NotNull Gson build() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .serializeNulls()
                .create();
    }
}
