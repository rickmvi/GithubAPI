package com.github.app;

import com.github.app.service.GithubService;
import org.jetbrains.annotations.NotNull;
import com.github.app.domain.GithubUser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GithubService service = new GithubService();

        System.out.println("Enter with github username:");
        String username = scanner.nextLine();

        GithubUser user = service.githubUser(username);

        print(user);
    }

    private static void print(@NotNull GithubUser user) {
        System.out.printf(
                "%n===== GitHub User =====%n" +
                        "Login     : %s%n" +
                        "Name      : %s%n" +
                        "Bio       : %s%n" +
                        "Repos     : %d%n" +
                        "Followers : %d%n" +
                        "Following : %d%n",
                user.getLogin(),
                user.getName(),
                user.getBio(),
                user.getPublicRepos(),
                user.getFollowers(),
                user.getFollowing()
        );
    }
}
