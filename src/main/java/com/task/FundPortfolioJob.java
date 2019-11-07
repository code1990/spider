package com.task;

import com.dao.FundPortfolioDao;
import com.entity.FundPortfolio;
import com.spider.FundPortfolioSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Component
@EnableScheduling
public class FundPortfolioJob {

    private Logger logger = LoggerFactory.getLogger(FundPortfolioJob.class);
    @Autowired
    private FundPortfolioDao fundPortfolioDao;

    @Scheduled(cron = "${webmagic.job.cron}")
//    @PostConstruct//启动项目则开启
    public void job() {

        long startTime, endTime;
        System.out.println("【爬虫开始】");
        startTime = System.currentTimeMillis();
        logger.info("爬取地址：" + FundPortfolioSpider.BASE_URL);
        try {
            int allNumber = 361;
            int beginSize = (int)fundPortfolioDao.count()/25+1;
            System.out.println(beginSize);
//            int beginSize = 361;//
            for(int i =beginSize;i<=allNumber;i++){
                List<FundPortfolio> fundPortfolios = FundPortfolioSpider.getPageList(i);
                logger.error("第"+i+"页数据保存>>>>>>>>>>>begin");
                if(fundPortfolios.get(0).getFundName().equals("")){
                    break;
                }
                fundPortfolioDao.saveAll(fundPortfolios);
                logger.error("第"+i+"页数据保存>>>>>>>>>>>end\t,\t数量"+fundPortfolios.size());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】");

        System.out.println("基金任务抓取耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库.");

    }


}

