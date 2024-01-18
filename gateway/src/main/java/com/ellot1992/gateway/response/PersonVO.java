package com.ellot1992.gateway.response;

import com.ellot1992.gateway.config.JacksonLocalDate2NumberSerializer;
import com.ellot1992.gateway.config.JacksonLocalDateTime2NumberSerializer;
import com.ellot1992.gateway.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PersonVO {

    private String name;
    private Integer age;
    @JsonSerialize(using = JacksonLocalDate2NumberSerializer.class)
    private LocalDate birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = JacksonLocalDateTime2NumberSerializer.class)
    private LocalDateTime entryDate;
    private Date graduationDate;
    private GenderEnum genderEnum;
}
