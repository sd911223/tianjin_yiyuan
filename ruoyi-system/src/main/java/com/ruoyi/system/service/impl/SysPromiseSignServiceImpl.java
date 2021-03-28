package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPromiseSignMapper;
import com.ruoyi.system.domain.SysPromiseSign;
import com.ruoyi.system.service.ISysPromiseSignService;

/**
 * 承诺填写Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-28
 */
@Service
public class SysPromiseSignServiceImpl implements ISysPromiseSignService 
{
    @Autowired
    private SysPromiseSignMapper sysPromiseSignMapper;

    /**
     * 查询承诺填写
     * 
     * @param id 承诺填写ID
     * @return 承诺填写
     */
    @Override
    public SysPromiseSign selectSysPromiseSignById(Long id)
    {
        return sysPromiseSignMapper.selectSysPromiseSignById(id);
    }

    /**
     * 查询承诺填写列表
     * 
     * @param sysPromiseSign 承诺填写
     * @return 承诺填写
     */
    @Override
    public List<SysPromiseSign> selectSysPromiseSignList(SysPromiseSign sysPromiseSign)
    {
        return sysPromiseSignMapper.selectSysPromiseSignList(sysPromiseSign);
    }

    /**
     * 新增承诺填写
     * 
     * @param sysPromiseSign 承诺填写
     * @return 结果
     */
    @Override
    public int insertSysPromiseSign(SysPromiseSign sysPromiseSign)
    {
        sysPromiseSign.setCreateTime(DateUtils.getNowDate());
        return sysPromiseSignMapper.insertSysPromiseSign(sysPromiseSign);
    }

    /**
     * 修改承诺填写
     * 
     * @param sysPromiseSign 承诺填写
     * @return 结果
     */
    @Override
    public int updateSysPromiseSign(SysPromiseSign sysPromiseSign)
    {
        sysPromiseSign.setUpdateTime(DateUtils.getNowDate());
        return sysPromiseSignMapper.updateSysPromiseSign(sysPromiseSign);
    }

    /**
     * 批量删除承诺填写
     * 
     * @param ids 需要删除的承诺填写ID
     * @return 结果
     */
    @Override
    public int deleteSysPromiseSignByIds(Long[] ids)
    {
        return sysPromiseSignMapper.deleteSysPromiseSignByIds(ids);
    }

    /**
     * 删除承诺填写信息
     * 
     * @param id 承诺填写ID
     * @return 结果
     */
    @Override
    public int deleteSysPromiseSignById(Long id)
    {
        return sysPromiseSignMapper.deleteSysPromiseSignById(id);
    }
}
