package com.github.dge1992.mybatis.domain;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author 董感恩
 * @date 2020-02-10 16:15
 * @desc 校验组顺序
 */
@GroupSequence({GroupA.class, GroupB.class, Default.class})
public interface GroupOrder {

}
