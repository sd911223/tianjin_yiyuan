package com.ruoyi.task;

import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.enums.PromiseStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysPromiseSign;
import com.ruoyi.system.domain.SysStudentPromise;
import com.ruoyi.system.service.ISysPromiseSignService;
import com.ruoyi.system.service.ISysStudentPromiseService;
import com.ruoyi.system.service.SysReservePersonnelService;
import com.ruoyi.system.service.SysReserveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReserveTask {
    private static final Logger log = LoggerFactory.getLogger(ReserveTask.class);
    @Autowired
    SysReserveService sysReserveService;
    @Autowired
    SysReservePersonnelService sysReservePersonnelService;
    @Autowired
    ISysStudentPromiseService sysStudentPromiseService;
    @Autowired
    ISysPromiseSignService sysPromiseSignService;

    /**
     * 项目开始结束定时
     */
    @Scheduled(fixedRate = 12 * 5000)
    public void scheduledTask() {
        BusinessReserve businessReserve = new BusinessReserve();
        businessReserve.setStatus("2");
        List<BusinessReserve> list = sysReserveService.selectReserveList(businessReserve);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Date reserveStartTime = list.get(i).getReserveStartTime();
                Date reserveEndTime = list.get(i).getReserveEndTime();
                Date nowDate = DateUtils.getNowDate();
                //如果当前时间大于开始时间并且结束时间大于当前时间
                if ((nowDate.getTime() - reserveStartTime.getTime()) >= 1 && (reserveEndTime.getTime() - nowDate.getTime()) >= 1) {
                    list.get(i).setStatus("1");
                    sysReserveService.updateReserveStatus(list.get(i));
                }
            }
        }
        businessReserve.setStatus("1");
        List<BusinessReserve> list2 = sysReserveService.selectReserveList(businessReserve);
        if (!list2.isEmpty()) {
            for (int i = 0; i < list2.size(); i++) {
                Date reserveEndTime = list2.get(i).getReserveEndTime();
                Date nowDate = DateUtils.getNowDate();
                //如果当前时间大于结束时间===项目结束
                if ((nowDate.getTime() - reserveEndTime.getTime()) >= 1) {
                    list2.get(i).setStatus("4");
                    sysReserveService.updateReserveStatus(list2.get(i));
                }
            }
        }
    }

    @Scheduled(fixedRate = 5000)
    public void statisticsTask() {
        log.info("执行统计预约总人数定时============================");
        BusinessReserve businessReserve = new BusinessReserve();
        businessReserve.setStatus("1");
        List<BusinessReserve> list = sysReserveService.selectReserveList(businessReserve);
        if (!list.isEmpty()) {
            list.forEach(e -> {
                BusinessReserve reserve = new BusinessReserve();
                reserve.setId(e.getId());
                int numBer = sysReservePersonnelService.selectReservationNumBer(e.getId());
                log.info("时间{}：，统计项目：{}，总人数{}：", DateUtils.getNowDate(), e.getId(), numBer);
                reserve.setReserveNum(numBer);
                sysReserveService.updateReserveNum(reserve);
            });

        }
    }

    /**
     * 承诺人数
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void promiseTask() {
        log.info("执行统计承诺总人数定时============================");
        SysStudentPromise sysStudentPromise = new SysStudentPromise();
        sysStudentPromise.setModuleStatus(PromiseStatus.ISSUE.getCode());
        List<SysStudentPromise> list = sysStudentPromiseService.selectSysStudentPromiseList(sysStudentPromise);
        log.info("查询到发布的承诺条数:{}============================", list.size());
        if (!list.isEmpty()) {
            list.forEach(e -> {
                int signCount = sysPromiseSignService.selectSysPromiseSignCount(e.getId());
                log.info("承诺项目ID:{},承诺项目名称:{},报名人数:{}", e.getId(), e.getExamName(), signCount);
                e.setWriteNumber(Long.valueOf(signCount));
                sysStudentPromiseService.updateSysStudentPromise(e);
            });

        }
    }
}
