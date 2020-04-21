package cz.muni.fi.pa165.library.services;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 * <p>
 * A service for mapping objects from one class to another.
 * In our case from DTOs to entities and vice-versa.
 */
@Service
public class MappingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MappingService.class);

    private final Mapper dozer;

    public MappingService(Mapper dozer) {
        this.dozer = dozer;
    }

    /**
     * Maps a collection of objects of one class to other class
     *
     * @param objects    objects to be mapped
     * @param mapToClass class to which the objects will be mapped
     * @param <T>
     * @return a collection of converted objects
     */
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            LOGGER.info("Mapping {} to {}.", object, mapToClass);
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    /**
     * Maps an object of one class to other class
     *
     * @param object     object to be mapped
     * @param mapToClass class to which the object will be mapped
     * @param <T>
     * @return converted object
     */
    public <T> T mapTo(Object object, Class<T> mapToClass) {
        LOGGER.info("Mapping {} to {}.", object, mapToClass);
        return dozer.map(object, mapToClass);
    }
}

