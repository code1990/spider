package com.task;

import com.dao.FundPerformanceDao;
import com.entity.FundPerformance;
import com.spider.FundPerformanceSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Component
@EnableScheduling
public class FundPerformanceJob {

    private Logger logger = LoggerFactory.getLogger(FundPerformanceJob.class);
    @Autowired
    private FundPerformanceDao fundPerformanceDao;

//    @Scheduled(cron = "${webmagic.job.cron}")
//    @PostConstruct//启动项目则开启
    public void job() {
        System.out.println("===============");
        System.out.println("===============");
        System.out.println("===============");
        System.out.println("===============");
        System.out.println("===============");
        System.out.println("===============");
        long startTime, endTime;
        System.out.println("【爬虫开始】");
        startTime = System.currentTimeMillis();
        logger.info("爬取地址：" + FundPerformanceSpider.BASE_URL);
        try {
            int allNumber = 361;
            int beginSize = (int)fundPerformanceDao.count()/25+1;
            System.out.println(beginSize);
//            int beginSize = 361;//
            for(int i =beginSize;i<=allNumber;i++){
                List<FundPerformance> list = FundPerformanceSpider.getPageList(i);
                logger.error("第"+i+"页数据保存>>>>>>>>>>>begin");
                if(list.get(0).getFundName().equals("")){
                    break;
                }
                fundPerformanceDao.saveAll(list);
                logger.error("第"+i+"页数据保存>>>>>>>>>>>end\t,\t数量"+list.size());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】");

        System.out.println("基金任务抓取耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库.");

    }


}

