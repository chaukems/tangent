package za.co.tangent.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service("UserDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    String inMemoryUsername = "admin1";
    @Autowired
    private Gson gson;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Map<String, String> users = new HashMap();

        users.put("admin1", "admin1");

        String result = "";

        String token = "";

        if (users.get(username) != null) {

            String url = "http://userservice.staging.tangentmicroservices.com:80/api-token-auth/";

            RestTemplate restTemplate = new RestTemplate();

// Create a multimap to hold the named parameters
            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
            parameters.add("username", username);
            parameters.add("password", username);

// Create the http entity for the request
            HttpEntity<MultiValueMap<String, String>> entity
                    = new HttpEntity<MultiValueMap<String, String>>(parameters, createHeaders(username, username));
            // Get the response as a string
            result = restTemplate.postForObject(url, entity, String.class);

            TokenClass tokenObj = gson.fromJson(result, new TypeToken<TokenClass>() {
            }.getType());

            token = tokenObj.getToken();

        } else {
            throw new UsernameNotFoundException("Username not found");
        }

        MyUserDetailsModel loggedUserModel = new MyUserDetailsModel(username, username,
                true, true, true, true, null, token);

        return loggedUserModel;
    }

    HttpHeaders createHeaders(final String username, final String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }

}
