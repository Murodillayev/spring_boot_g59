package uz.pdp.todo;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostApiService {

    List<Post> getAllByGetForObject();

    ResponseEntity<List<Post>> getAllByGetForEntity();

    Post getByGetForObject(String id);

    ResponseEntity<Post> getByGetForEntity(String id);

    Post postForObject(PostCreateDto dto);

}

// server ga joylash
// oauth
// cache (redis)
// eop
// testing
// spring shell
// graphql
// spring ai
// docker
// websocket
// rabbit mq
// nats
// apache kafka
// microservices
