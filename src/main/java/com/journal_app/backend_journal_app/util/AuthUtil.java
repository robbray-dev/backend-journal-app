package com.journal_app.backend_journal_app.util;

import com.journal_app.backend_journal_app.exception.ForbiddenException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class AuthUtil {

    public String getPersonId(Authentication authentication) {
        Map<String, String> principal =
                (Map<String, String>) authentication.getPrincipal();

        String personId = principal.get("personId");

        if (!StringUtils.hasText(personId)) {
            throw new ForbiddenException("Invalid authentication token");
        }

        return personId;
    }
}
