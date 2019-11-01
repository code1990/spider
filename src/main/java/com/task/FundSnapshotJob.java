package com.task;

import com.SpiderApplication;
import com.spider.MorningStarSpider;
import com.dao.FundSnapshotDao;
import com.entity.FundSnapshot;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Component
@EnableScheduling
public class FundSnapshotJob {

    private Logger logger = LoggerFactory.getLogger(FundSnapshotJob.class);
    @Autowired
    private FundSnapshotDao fundSnapshotDao;

//    @Scheduled(cron = "${webmagic.job.cron}")
    @PostConstruct//启动项目则开启
    public void job() {

        long startTime, endTime;
        System.out.println("【爬虫开始】");
        startTime = System.currentTimeMillis();
        logger.info("爬取地址：" + MorningStarSpider.BASE_URL);
        try {
            int allNumber = 361;
//            int beginSize = (int)fundSnapshotDao.count()/25+1;
            int beginSize = 112;//
            for(int i =beginSize;i<allNumber;i++){
                List<FundSnapshot> fundSnapshots = MorningStarSpider.getPageList(i);
                logger.error("第"+i+"页数据保存>>>>>>>>>>>begin");
                fundSnapshotDao.saveAll(fundSnapshots);
                logger.error("第"+i+"页数据保存>>>>>>>>>>>end\t,\t数量"+fundSnapshots.size());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】");

        System.out.println("基金任务抓取耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库.");

    }


}

