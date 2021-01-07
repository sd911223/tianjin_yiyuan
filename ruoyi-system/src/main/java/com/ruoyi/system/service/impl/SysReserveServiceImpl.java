package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.req.ReserveAmContentReq;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysReserveContentMapper;
import com.ruoyi.system.mapper.SysReserveMapper;
import com.ruoyi.system.service.SysReserveService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 预约管理 服务层实现
 *
 * @author shitou
 */
@Service
public class SysReserveServiceImpl implements SysReserveService {
    @Autowired
    SysReserveMapper sysReserveMapper;
    @Autowired
    SysDeptMapper sysDeptMapper;
    @Autowired
    SysReserveContentMapper sysReserveContentMapper;

    @Override
    public List<BusinessReserve> selectReserveList(BusinessReserve businessReserve) {
        return sysReserveMapper.selectReserveList(businessReserve);
    }

    @Override
    public int insertReserve(BusinessReserve businessReserve, List<ReserveAmContentReq> reserveAmContentList) {

        businessReserve.setCreateBy(SecurityUtils.getUsername());
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        businessReserve.setDeptId(deptId.intValue());
        SysDept sysDept = sysDeptMapper.selectDeptById(deptId);
        businessReserve.setDeptName(sysDept.getDeptName());
        int i = sysReserveMapper.insertReserve(businessReserve);
        if (!reserveAmContentList.isEmpty()) {
            reserveAmContentList.forEach(e -> {
                BusinessReserveContent businessReserveContent = new BusinessReserveContent();
                BeanUtils.copyProperties(e, businessReserveContent);
                businessReserveContent.setSurplusNumber(e.getNumberLimit());
                businessReserveContent.setReserveId(businessReserve.getId());
                sysReserveContentMapper.insertReserveContent(businessReserveContent);
            });
        }
        return i;
    }

    @Override
    public int updateReserveStatus(BusinessReserve businessReserve) {
        return sysReserveMapper.updateReserveStatus(businessReserve);
    }

    @Override
    public int deleteReserveById(Integer[] id) {
        return sysReserveMapper.deleteReserveById(id);
    }

    @Override
    public BusinessReserve selectReserveById(Integer id) {
        BusinessReserve businessReserve = sysReserveMapper.selectReserveById(id);
        List<BusinessReserveContent> businessReserveContents = sysReserveContentMapper.selectContentByRId(businessReserve.getId());
        businessReserve.setContentList(businessReserveContents);
        return businessReserve;
    }

    @Override
    @Transactional
    public int updateReserve(BusinessReserve upReserve, List<ReserveAmContentReq> reserveAmContentList) {
        BusinessReserve businessReserve = sysReserveMapper.selectReserveById(upReserve.getId());
        if (businessReserve.getStatus().equals("3")) {
            List<BusinessReserveContent> contentList = sysReserveContentMapper.selectContentByRId(upReserve.getId());
            if (!contentList.isEmpty()) {
                for (BusinessReserveContent content : contentList) {
                    sysReserveContentMapper.delectReserveContent(content.getId());
                }
            }
            for (ReserveAmContentReq reserveAmContentReq : reserveAmContentList) {
                BusinessReserveContent businessReserveContent = new BusinessReserveContent();
                BeanUtils.copyProperties(reserveAmContentReq, businessReserveContent);
                businessReserveContent.setSurplusNumber(reserveAmContentReq.getNumberLimit());
                businessReserveContent.setReserveId(upReserve.getId());
                sysReserveContentMapper.insertReserveContent(businessReserveContent);
            }
        }
        if (!businessReserve.getStatus().equals("3")) {
            reserveAmContentList.forEach(e -> {
                BusinessReserveContent content = sysReserveContentMapper.selectContentByIdForUpdate(e.getId());
                BeanUtils.copyProperties(e, content);
                if (e.getAddNumber() != null) {
                    content.setSurplusNumber(content.getSurplusNumber() + e.getAddNumber());
                    content.setNumberLimit(content.getNumberLimit() + e.getAddNumber());
                    sysReserveContentMapper.updateSurNumber(content);
                    sysReserveContentMapper.updateNumberLimit(content);
                }
            });
        }
        return sysReserveMapper.updateReserve(upReserve);
    }

    @Override
    public int updateRevokeReserve(Integer id) {
        return sysReserveMapper.updateRevokeReserve(id);
    }

    @Override
    public int updateCancelReleaseById(Integer[] id) {
        return sysReserveMapper.updateCancelReleaseById(id);
    }

    @Override
    public int updateReserveNum(BusinessReserve reserve) {
        return sysReserveMapper.updateReserveNum(reserve);
    }
}
