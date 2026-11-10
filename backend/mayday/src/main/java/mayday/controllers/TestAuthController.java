package mayday.controllers;

import mayday.security.AccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@ResponseBody
public class TestAuthController {

    @GetMapping
    public String test(Authentication authentication) {
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        if (accountDetails == null) return "null";
        return accountDetails.getCustomThing();
    }

}
