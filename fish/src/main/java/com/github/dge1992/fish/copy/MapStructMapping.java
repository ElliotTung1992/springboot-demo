package com.github.dge1992.fish.copy;

import com.github.dge1992.fish.constants.enums.GenderEnum;
import com.github.dge1992.fish.domain.dto.PersonDTO;
import com.github.dge1992.fish.domain.po.PersonPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Objects;

@Mapper
public interface MapStructMapping {

    @Mappings({
            @Mapping(source = "gender.code", target = "gender"),
    })
    PersonPo personDTO2PersonPo(PersonDTO personDTO);

    @Mappings({
            @Mapping(source = "gender", target = "gender"),
    })
    PersonDTO personPo2PersonDTO(PersonPo personPo);

    default GenderEnum getEnum(Integer code) {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if(Objects.nonNull(code) && genderEnum.getCode() == code){
                return genderEnum;
            }
        }
        return null;
    }
}
