package uz.pdp.todo;

import java.util.List;

public interface PostApiService {

    List<Post> getAllByGetForObject();

    List<Post> getAllByGetForEntity();

    Post getByGetForObject(String id);

    Post getByGetForEntity(String id);

    Post postForObject(PostCreateDto dto);

    Post postForEntity(PostCreateDto dto);

    Post putObject(PostUpdateDto dto, String id);

    void deleteObject(String id);



    void testExchange();
}
