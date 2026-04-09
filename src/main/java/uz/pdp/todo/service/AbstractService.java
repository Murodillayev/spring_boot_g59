package uz.pdp.todo.service;

/**
 * @param <R> repo
 * @param <M> mapper
 * @param <V> validaor
 */
public abstract class AbstractService<R, M, V> {
    protected final R repository;
    protected final M mapper;
    protected final V validator;

    public AbstractService(R repository, M mapper, V validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }
}
