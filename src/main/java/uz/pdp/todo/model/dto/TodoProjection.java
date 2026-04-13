package uz.pdp.todo.model.dto;

public interface TodoProjection {
    String getId();

    String getTitle();

    String getDescription();

    Boolean getCompleted();
}
