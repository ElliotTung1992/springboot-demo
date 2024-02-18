package com.github.dge1992.fish.copy;

import com.github.dge1992.fish.constants.enums.GenderEnum;
import com.github.dge1992.fish.domain.dto.PersonDTO;
import com.github.dge1992.fish.domain.po.PersonPo;
import org.mapstruct.factory.Mappers;

/**
 * 对象复制练习
 */
public class ObjectReplicationTest {

    public static void main(String[] args) {
        MapStructMapping mapper = Mappers.getMapper(MapStructMapping.class);
        PersonPo personPo = new PersonPo();
        personPo.setName("Bruce");
        personPo.setAge(31);
        personPo.setGender(1);
        PersonDTO copyPersonDTO = mapper.personPo2PersonDTO(personPo);
        System.out.println(copyPersonDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("Oliver");
        personDTO.setAge(22);
        personDTO.setGender(GenderEnum.MALE);
        PersonPo copyPersonPo = mapper.personDTO2PersonPo(personDTO);
        System.out.println(copyPersonPo);
    }
}
