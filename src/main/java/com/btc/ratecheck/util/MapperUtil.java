package com.btc.ratecheck.util;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class MapperUtil encapsulates all the functionalities for mapping entity to DTO, collection of entities to
 * collection of DTO's, paginated entities to paginated DTO's and vice versa.
 *
 * @author Bosko Mijin
 */
@Component
public class MapperUtil {

    /** The model mapper. */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Maps the {@code entity} of <code>T</code> as input to {@code outClass} of <code>D</code> class as output.
     *
     * @param <D> type of result object.
     * @param <T> type of source object to map from.
     * @param entity entity that needs to be mapped.
     * @param outClass class of result object.
     * @return new object of <code>outClass</code> type.
     * @NB <code>outClass</code> object must have NoArgsConstructor!
     */
    public <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /**
     * Maps the Collection {@code entityCollection} of <code>T</code> type which have to be mapped as input to
     * {@code outClass} collection of mapped object with <code>D</code> type.
     *
     * @param <D> type of objects in result list
     * @param <T> type of entity in <code>entityList</code>
     * @param entityCollection list of entities that needs to be mapped
     * @param outCLass class of result list element
     * @return collection of mapped object with <code>D</code> type.
     * @NB <code>outClass</code> object must have NoArgsConstructor!
     */
    public <D, T> Collection<D> mapAll(final Collection<T> entityCollection, Class<D> outCLass) {
        return entityCollection.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
    }
}
