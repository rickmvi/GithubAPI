package com.github.app.domain;

@lombok.Data
public class GithubUser {
    private String login;
    private String name;
    private String bio;
    private int publicRepos;
    private int followers;
    private int following;
}
