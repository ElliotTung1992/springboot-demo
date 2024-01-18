package com.ellot1992.gateway.controller;

import com.ellot1992.gateway.enums.GenderEnum;
import com.ellot1992.gateway.request.PersonRequest;
import com.ellot1992.gateway.response.PersonVO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/person")
public class PersonController {

    @PostMapping
    public void addPerson(@RequestBody PersonRequest personRequest){
        System.out.println(personRequest);
    }

    @GetMapping("/{id}")
    public PersonVO getPersonById(@PathVariable(name = "id") Long id){
        PersonVO personVO = new PersonVO();
        personVO.setName("Elliot");
        personVO.setAge(31);
        personVO.setBirthday(LocalDate.now());
        Date date = new Date();
        System.out.println(date);
        personVO.setGraduationDate(date);
        personVO.setEntryDate(LocalDateTime.now());
        personVO.setGenderEnum(GenderEnum.MALE);
        return personVO;
    }

}
