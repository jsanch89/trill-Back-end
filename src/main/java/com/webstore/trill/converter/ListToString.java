package com.webstore.trill.converter;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class ListToString implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ";";
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return strings.stream()
                .collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return Arrays.asList(s.split(SPLIT_CHAR));
    }
}
