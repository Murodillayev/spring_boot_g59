package uz.pdp.todo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TestRestTemplateController {

    private final PostApiService postApiService;

    public TestRestTemplateController(PostApiService postApiService) {
        this.postApiService = postApiService;
    }


    @GetMapping("/getForObject")
    public List<Post> testGetForObject() {
        return postApiService.getAllByGetForObject();
    }

    @GetMapping("/getForEntity")
    public List<Post> testGetForEntity() {
        return postApiService.getAllByGetForEntity().getBody();
    }


    @GetMapping("/getForObject/{id}")
    public Post testGetForObject(@PathVariable String id) {
        return postApiService.getByGetForObject(id);
    }

    @GetMapping("/getForEntity/{id}")
    public Post testGetForEntity(@PathVariable String id) {
        return postApiService.getByGetForEntity(id).getBody();
    }

    @PostMapping("/postForObject")
    public Post testPostForObject(@RequestBody PostCreateDto dto) {

        return postApiService.postForObject(dto);
    }


    // HttpClient - sinxron

    // RestTemplate - sinxron

    // WebClient - sinxron , asinxron -> (reactive)

    // FeignClient - sinxron default, asinxron -> (microservice)


}
