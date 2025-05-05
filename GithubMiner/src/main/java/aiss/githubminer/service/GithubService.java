package aiss.githubminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.baseuri}")
    public String baseuri;
    @Value("${github.token}")
    public String token;

    public <T> ResponseEntity<T> getAuthenticated(String githubUri, Class<T> responseType) {
        return getAuthenticatedFullUri(baseuri + githubUri, responseType);
    }

    public <T> ResponseEntity<T> getAuthenticatedFullUri(String uri, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<T> requestEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(uri, org.springframework.http.HttpMethod.GET, requestEntity, responseType);
    }

}
