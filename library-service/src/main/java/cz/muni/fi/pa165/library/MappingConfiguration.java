package cz.muni.fi.pa165.library;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 * <p>
 * Creates a Mapper bean, which is used for converting DTO to database entities and vice-versa.
 */
@Configuration
public class MappingConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        beanMapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        return beanMapper;
    }
}
