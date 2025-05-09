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

    public <T> T getForAuthenticated(String bitbucketUri, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        String token = "ATCTT3xFfGN0YNVrYPvVvHUvsj7Xo5T3YVXF02HF0qaDo_td4E-aX1-K9cKnRWruefb8dCQ5xe5_50k5LiP2AQURGyfpJJVLGI9ybFz-Jl9hQxqs0VeDjf803Ki05eFQwBkt_oeQpr-ztApDdeCBKKn7G_UqG8VMeCMKGe3Fbi7K67N4T3RqDBA=776D0CE3";
        headers.set("Authorization", token);
        HttpEntity<T> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + bitbucketUri;

        return restTemplate.exchange(uri, HttpMethod.GET, entity, responseType).getBody();
    }
}
