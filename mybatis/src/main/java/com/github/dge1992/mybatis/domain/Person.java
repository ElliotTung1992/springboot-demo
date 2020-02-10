package com.github.dge1992.mybatis.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

/**
 * @author 董感恩
 * @date 2020-02-10 15:25
 * @desc 人
 */
@Data
public class Person {
    @Range(min = 1,max = Integer.MAX_VALUE,message = "必须大于0",groups = {GroupA.class})
    private Integer userId;
    @NotBlank
    @Length(min = 4,max = 20,message = "必须在[4,20]",groups = {GroupB.class})
    private String userName;
    @Range(min = 0,max = 100,message = "年龄必须在[0,100]",groups={Default.class})
    private Integer age;
    @Range(min = 0,max = 2,message = "性别必须在[0,2]",groups = {GroupB.class})
    private Integer sex;
}
