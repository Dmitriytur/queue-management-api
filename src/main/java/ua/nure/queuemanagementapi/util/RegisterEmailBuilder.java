package ua.nure.queuemanagementapi.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class RegisterEmailBuilder {

    @Value("${server.hostname}")
    private String hostname;

    @Value("${server.register-token-path}")
    private String registerTokenPath;

    public String buildEmailForRegisterToken(String tokenId, String companyName) {
        String registerLink = String.format("http://%s%s/%s", hostname, registerTokenPath, tokenId);

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("You have been registered as manager to company \"%s\".", companyName));
        joiner.add("");
        joiner.add(String.format("To finish registration - follow the link: %s", registerLink));

        return joiner.toString();
    }
}
