package uz.pdp.todo;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class PostApiServiceImpl implements PostApiService {
    private final RestTemplate restTemplate;
    private final static String postServerUrl = "https://jsonplaceholder.typicode.com/posts";

    public PostApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Post> getAllByGetForObject() {
        Post[] postArr = restTemplate.getForObject(postServerUrl, Post[].class);
        return postArr == null ? Collections.emptyList() : Arrays.stream(postArr).toList();
    }

    @Override
    public Post getByGetForObject(String id) {
        return restTemplate.getForObject(postServerUrl + "/{id}", Post.class, id);
    }

    @Override
    public List<Post> getAllByGetForEntity() {
        ResponseEntity<Post[]> forEntity = restTemplate.getForEntity(postServerUrl, Post[].class);
        System.out.println(forEntity.getStatusCode());
        HttpHeaders headers = forEntity.getHeaders();
        headers.forEach((k, v) -> {
            System.out.println("key: " + k + " value: " + v);
        });
        return forEntity.getBody() == null ? Collections.emptyList() : Arrays.stream(forEntity.getBody()).toList();
    }


    @Override
    public Post getByGetForEntity(String id) {
        ResponseEntity<Post> forEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/{id}", Post.class, id);
        return forEntity.getBody() == null ? null : forEntity.getBody();
    }

    @Override
    public Post postForObject(PostCreateDto dto) {
        return restTemplate.postForObject("https://jsonplaceholder.typicode.com/posts", dto, Post.class);

    }

    @Override
    public Post postForEntity(PostCreateDto dto) {
        ResponseEntity<Post> postResponseEntity = restTemplate.postForEntity("https://jsonplaceholder.typicode.com/posts", dto, Post.class);
        return postResponseEntity.getBody();
    }

    @Override
    public Post putObject(PostUpdateDto dto, String id) {
        restTemplate.put("https://jsonplaceholder.typicode.com/posts/{id}", dto, id);

        return null;
    }

    @Override
    public void deleteObject(String id) {
        restTemplate.delete("https://jsonplaceholder.typicode.com/posts/{id}", id);
    }

    @Override
    public void testExchange() {

        ResponseEntity<Post[]> exchange = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, null, Post[].class);


        // --- //
        ResponseEntity<Post> post = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/1", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        // --- //
        PostCreateDto createDto = new PostCreateDto();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//        headers.add("Accept", "application/json");
//        headers.setBasicAuth("username", "password");
//        headers.add("Authorization", "Basic lkjhashjksadhjksadkhj==");

        HttpEntity<PostCreateDto> reqEntity = new HttpEntity<>(createDto);

        ResponseEntity<Post> crePost = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.POST, reqEntity, Post.class);

        // --- //

        PostUpdateDto updateDto = new PostUpdateDto();
        HttpHeaders headersForUpdate = new HttpHeaders();
        headersForUpdate.add("Content-Type", "application/json");
        headersForUpdate.add("Accept", "application/json");
        headersForUpdate.setBasicAuth("username", "password");
        headersForUpdate.add("Authorization", "Basic lkjhashjksadhjksadkhj==");
        HttpEntity<PostUpdateDto> en = new HttpEntity<>(updateDto, headersForUpdate);
        ResponseEntity<Post> up = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/1", HttpMethod.PUT, reqEntity, Post.class);

    }

}
