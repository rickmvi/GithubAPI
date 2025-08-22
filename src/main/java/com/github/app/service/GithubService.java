package com.github.app.service;

import com.github.app.exception.ErrorGithubConsultationException;
import com.github.app.infra.HttpClientProvider;
import static com.github.app.api.URL.GITHUB;
import org.jetbrains.annotations.NotNull;
import com.github.app.domain.GithubUser;
import com.github.app.util.JsonParser;

public class GithubService {

    private final HttpClientProvider provider = HttpClientProvider.create();

    public GithubUser githubUser(@NotNull String username) {
        String url = GITHUB.getUrl() + username;

        if (provider.statusCode(url) != 200)
            throw new ErrorGithubConsultationException("Error when consulting API, user does not exist!");

        var json = provider.body(url);

        provider.close();
        return JsonParser.fromJson(json, GithubUser.class);
    }
}
