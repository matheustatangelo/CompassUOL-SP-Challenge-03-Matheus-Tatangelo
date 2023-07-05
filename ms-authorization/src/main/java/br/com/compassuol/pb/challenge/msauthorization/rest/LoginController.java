package br.com.compassuol.pb.challenge.msauthorization.rest;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/oauth")
@RestController
public class LoginController {

    @PostMapping("/token")
    public String token() {
        return "token";
    }

}
