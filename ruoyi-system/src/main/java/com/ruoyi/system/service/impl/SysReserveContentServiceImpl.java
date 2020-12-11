package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import com.ruoyi.system.mapper.SysReserveContentMapper;
import com.ruoyi.system.service.SysReserveContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysReserveContentServiceImpl implements SysReserveContentService {
    @Autowired
    SysReserveContentMapper sysReserveContentMapper;
    @Override
    public List<BusinessReserveContent> selectContentByRId(Integer id) {
        return sysReserveContentMapper.selectContentByRId(id);
    }
}
