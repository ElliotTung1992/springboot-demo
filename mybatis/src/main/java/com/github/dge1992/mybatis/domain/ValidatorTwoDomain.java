package com.github.dge1992.mybatis.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 董感恩
 * @date 2020-02-10 12:54
 * @desc 校验实体
 */
@Data
@NoArgsConstructor
public class ValidatorTwoDomain {
    @Length(min = 5, max = 17, message = "length长度在[5,17]之间")
    private String length;
    @Size(min = 1, max = 3, message = "size在[1,3]之间")
    private String age;
    @Range(min = 150, max = 250, message = "range在[150,250]之间")
    private int high;
    @Size(min = 3,max = 5,message = "list的Size在[3,5]")
    private List<String> list;
}
