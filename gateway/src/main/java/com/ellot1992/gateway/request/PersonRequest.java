package com.ellot1992.gateway.request;

import com.ellot1992.gateway.config.JacksonLocalDateFromLongDeserializer;
import com.ellot1992.gateway.config.JacksonLocalDateTimeFromLongDeserializer;
import com.ellot1992.gateway.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PersonRequest {

    private String name;
    private Integer age;
    @JsonDeserialize(using = JacksonLocalDateFromLongDeserializer.class)
    private LocalDate birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = JacksonLocalDateTimeFromLongDeserializer.class)
    private LocalDateTime entryDate;
    private Date graduationDate;
    private GenderEnum genderEnum;

}
