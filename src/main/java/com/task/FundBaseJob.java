package com.task;

import com.SpiderApplication;
import com.dao.FundBaseDao;
import com.dao.FundSnapshotDao;
import com.entity.FundBase;
import com.entity.FundSnapshot;
import com.spider.FundBaseSpider;
import com.spider.FundSnapshotSpider;
import com.util.TxtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Component
@EnableScheduling
public class FundBaseJob {
    String PATH= SpiderApplication.class.getClassLoader().getResource("fund.txt").getPath();
    private Logger logger = LoggerFactory.getLogger(FundBaseJob.class);
    @Autowired
    private FundBaseDao fundBaseDao;

    @Scheduled(cron = "${webmagic.job.cron}")
//    @PostConstruct//启动项目则开启
    public void job() {

        long startTime, endTime;
        System.out.println("【爬虫开始】");
        startTime = System.currentTimeMillis();
        try {
            List<String> list= TxtUtil.readTxt(PATH);
            int start =  (int)fundBaseDao.count();
            System.out.println(start);
            for (int i = start; i <list.size() ; i++) {
                String fundUrl = list.get(i);
                System.out.println(fundUrl);
                String num = (i+1)+"";
                FundBase fundBase = FundBaseSpider.getPageList(num,fundUrl);
                System.out.println(fundBase.toString());
                fundBaseDao.save(fundBase);
                System.out.println(fundBaseDao.count());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】");

        System.out.println("基金任务抓取耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库.");

    }


}

