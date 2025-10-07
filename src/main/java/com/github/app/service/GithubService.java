package com.github.app.service;

import static com.github.app.api.URL.GITHUB;

import com.github.rickmvi.jtoolbox.debug.AnsiColor;
import com.github.rickmvi.jtoolbox.debug.Logger;
import com.github.rickmvi.jtoolbox.debug.log.LogLevel;
import com.github.rickmvi.jtoolbox.util.HttpService;
import org.jetbrains.annotations.NotNull;
import com.github.app.domain.GithubUser;

public class GithubService {

    public GithubUser githubUser(@NotNull String username) {
        String url = GITHUB.getUrl() + username;

        var provider = HttpService.create()
                .GET(url)
                .logLevel(LogLevel.INFO)
                .send();

        provider.ifError(code -> {
            String statusCode = AnsiColor.BOLD.getAnsiCode() + AnsiColor.RED.getAnsiCode() + code.toString() + AnsiColor.RESET.getAnsiCode();
            Logger.error("Error when consulting API, code: " + statusCode + ". User Not Found!");
        });

        return provider.asJson(GithubUser.class);
    }
}
