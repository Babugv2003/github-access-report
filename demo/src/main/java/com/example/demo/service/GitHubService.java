package com.example.demo.service;

import com.example.demo.model.AccessReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GitHubService {

    @Value("${github.token}")
    private String token;

    @Value("${github.org}")
    private String org;

    private RestTemplate restTemplate = new RestTemplate();

    public List<AccessReport> getAccessReport() {

        Map<String, List<String>> accessMap = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String repoUrl = "https://api.github.com/orgs/" + org + "/repos";

        ResponseEntity<List> repoResponse =
                restTemplate.exchange(repoUrl, HttpMethod.GET, entity, List.class);

        // ✅ Improved type safety
        List<Map<String, Object>> repos = repoResponse.getBody();

        for (Map<String, Object> repo : repos) {

            String repoName = (String) repo.get("name");

            String collaboratorUrl =
                    "https://api.github.com/repos/" + org + "/" + repoName + "/collaborators";

            ResponseEntity<List> collaboratorResponse =
                    restTemplate.exchange(collaboratorUrl, HttpMethod.GET, entity, List.class);


            List<Map<String, Object>> collaborators = collaboratorResponse.getBody();

            for (Map<String, Object> user : collaborators) {

                String username = (String) user.get("login");

                accessMap
                        .computeIfAbsent(username, k -> new ArrayList<>())
                        .add(repoName);
            }
        }

        List<AccessReport> result = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : accessMap.entrySet()) {

            result.add(
                    new AccessReport(entry.getKey(), entry.getValue())
            );
        }

        return result;
    }
}