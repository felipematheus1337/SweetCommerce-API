package com.sweetcommerceapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Problem {

    private Integer status;
    private OffsetDateTime hourDate;
    private String title;
    private List<Field> fields;

    @AllArgsConstructor
    @Getter
    public static class Field  {
        private String name;
        private String message;
    }
}
