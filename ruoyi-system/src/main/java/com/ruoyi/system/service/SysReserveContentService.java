package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.BusinessReserveContent;

import java.util.List;

/**
 * 预约内容管理业务层
 *
 * @author shitou
 */
public interface SysReserveContentService {

    List<BusinessReserveContent> selectContentByRId(Integer id);
}
