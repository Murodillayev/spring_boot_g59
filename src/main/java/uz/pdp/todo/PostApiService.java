package uz.pdp.todo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "https://jsonplaceholder.typicode.com/posts", name = "jsonplaceholder")
public interface PostApiService {

    @GetMapping
    List<Post> getAllByGetForObject();

    @GetMapping
    ResponseEntity<List<Post>> getAllByGetForEntity();

    @GetMapping("/{id}")
    Post getByGetForObject(@PathVariable String id);

    @GetMapping("/{id}")
    ResponseEntity<Post> getByGetForEntity(@PathVariable String id);

    @PostMapping
    Post postForObject(@RequestBody PostCreateDto dto);

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
