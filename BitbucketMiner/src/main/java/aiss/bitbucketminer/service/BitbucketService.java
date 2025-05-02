package aiss.bitbucketminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class BitbucketService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${bitbucket.baseUri}")
    public String baseUri;
    @Value("${bitbucket.username}")
    public String username;
    @Value("${bitbucket.password}")
    public String password;

    public <T> T getForAuthenticated(String bitbucketUri, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        headers.add("Authorization", "Basic " + new String(encodedAuth));
        HttpEntity<T> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + bitbucketUri;

        return restTemplate.exchange(uri, HttpMethod.GET, entity, responseType).getBody();
    }
}
