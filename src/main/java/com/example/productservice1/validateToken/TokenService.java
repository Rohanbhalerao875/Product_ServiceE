package com.example.productservice1.validateToken;

import com.example.productservice1.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {

    private final RestTemplate restTemplate;

    public TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken(String token) {
        UserDto userDto = restTemplate.getForObject("http://localhost:8080/users/validate/" + token, UserDto.class);

        if (userDto != null && userDto.getName() != null && !userDto.getName().isEmpty()
                && userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
            return true;
        }
        return false;
    }
}
