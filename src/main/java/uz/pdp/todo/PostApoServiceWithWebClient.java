package uz.pdp.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class PostApoServiceWithWebClient implements PostApiService {

    private final AppConstants appConstants;
    private final WebClient webClient; // asinxron and sinxron

    public PostApoServiceWithWebClient(AppConstants appConstants, WebClient webClient) {
        this.appConstants = appConstants;
        this.webClient = webClient;
    }

    // Reactive -> Mono -> one element, Flux -> many element

    @Override
    public List<Post> getAllByGetForObject() {
        return webClient.get()
                .uri(appConstants.getJsonPlaceHolderApi())
                .retrieve()
                .bodyToFlux(Post.class)
                .collectList().block();

    }

    @Override
    public ResponseEntity<List<Post>> getAllByGetForEntity() {

        return null;
    }

    @Override
    public Post getByGetForObject(String id) {
        return webClient.get()
                .uri(appConstants.getJsonPlaceHolderApi() + "/{id}", id)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }

    @Override
    public ResponseEntity<Post> getByGetForEntity(String id) {
        return null;
    }

    @Override
    public Post postForObject(PostCreateDto dto) {
        return webClient
                .post()
                .uri(appConstants.getJsonPlaceHolderApi())
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }
}
