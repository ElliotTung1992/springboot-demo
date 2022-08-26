package com.github.dge1992.fish.util;

import com.github.dge1992.fish.domain.A;
import com.github.dge1992.fish.domain.B;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author dge
 * @version 1.0
 * @date 2021-10-14 21:32
 */
@Mapper
public interface Converter {

    Converter INSTANCE = Mappers.getMapper(Converter.class);

    B aToB(A car);
}
