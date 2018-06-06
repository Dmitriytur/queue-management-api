package ua.nure.queuemanagementapi.converter.service;


import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtendedConversionServiceImpl extends DefaultConversionService implements ExtendedConversionService {

    @Override
    public <T> List<T> convertAll(Collection<?> source, Class<T> targetClass) {
        Stream<?> stream = stream(source);
        return stream
                .filter(Objects::nonNull)
                .map(item -> convert(item, targetClass))
                .collect(Collectors.toList());
    }

    @Override
    public <T> Set<T> convertSet(Collection<?> source, Class<T> targetClass) {
        Stream<?> stream = stream(source);
        return stream
                .filter(Objects::nonNull)
                .map(item -> convert(item, targetClass))
                .collect(Collectors.toSet());
    }


    private static <T> Stream<T> stream(Collection<T> collection){
        if (Objects.isNull(collection)){
            return Stream.empty();
        }
        return collection.stream();
    }

}
