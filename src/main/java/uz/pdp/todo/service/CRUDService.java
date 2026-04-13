package uz.pdp.todo.service;

import uz.pdp.todo.criteria.BaseCriteria;
import uz.pdp.todo.model.domain.BaseEntity;
import uz.pdp.todo.model.dto.BaseDto;
import uz.pdp.todo.model.dto.PageDto;

import java.io.Serializable;
import java.util.List;

/**
 * @param <CD> create qilishda keladigan model (create dto)
 * @param <D>
 * @param <UD>
 * @param <K>
 * @param <C>
 */
public interface CRUDService<
        CD extends BaseDto,
        D extends BaseDto,
        UD extends BaseDto,
        K extends Serializable,
        C extends BaseCriteria> {

    /**
     * @param dto
     * @return
     */
    D create(CD dto);

    D update(UD dto, K id);

    D get(K id);

    PageDto<List<D>> getAll(C criteria);

    void delete(K id);
}
