package com.ruoyi.task;

import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.service.SysReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class ReserveTask {
    @Autowired
    SysReserveService sysReserveService;

    @Scheduled(fixedRate = 5000)
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
        System.out.println("任务执行时间：" + LocalDateTime.now());
    }

}
