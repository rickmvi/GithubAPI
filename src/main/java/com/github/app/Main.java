package com.github.app;

import com.github.app.service.GithubService;
import com.github.rickmvi.jtoolbox.console.utils.Scan;
import org.jetbrains.annotations.NotNull;
import com.github.app.domain.GithubUser;

import static com.github.rickmvi.jtoolbox.console.IO.interpolated;

public class Main {
    public static void main(String[] args) {
        GithubService service = new GithubService();

        String username = Scan.readPrompt("Enter with github username:");

        GithubUser user = service.githubUser(username);

        print(user);
    }

    private static void print(@NotNull GithubUser user) {
        interpolated(
                """
                        
                        ===== GitHub User =====
                        Login     : {login}
                        Name      : {name}
                        Bio       : {bio}
                        Repos     : {publicRepos}
                        Followers : {followers}
                        Following : {following}
                        """,
                user
        );
    }
}
