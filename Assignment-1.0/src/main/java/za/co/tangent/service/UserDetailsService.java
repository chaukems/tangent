package za.co.tangent.service;

import org.springframework.http.HttpEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service("UserDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        RestTemplate rt = new RestTemplate();

// Create a multimap to hold the named parameters
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("username", username);
        parameters.add("password", username);

// Create the http entity for the request
        HttpEntity<MultiValueMap<String, String>> entity
                = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);

// Get the response as a string
        String response = rt.postForObject(endpoint, entity, String.class);

        System.out.println(response);

        return null;
    }

}
