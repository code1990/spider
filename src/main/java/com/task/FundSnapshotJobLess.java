package com.task;

import com.SpiderApplication;
import com.dao.FundSnapshotDao;
import com.entity.FundSnapshot;
import com.spider.FundSnapshotSpider;
import com.spider.FundSnapshotSpiderLess;
import com.util.TxtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Component
@EnableScheduling
public class FundSnapshotJobLess {
    private static final String TMP_PATH = SpiderApplication.class.getClassLoader().getResource("tmp.txt").getPath();

    private Logger logger = LoggerFactory.getLogger(FundSnapshotJobLess.class);
    @Autowired
    private FundSnapshotDao fundSnapshotDao;

//    @Scheduled(cron = "${webmagic.job.cron}")
    @PostConstruct//启动项目则开启
    public void job() {

        long startTime, endTime;
        System.out.println("【爬虫开始】");
        startTime = System.currentTimeMillis();
        logger.info("爬取地址：" + FundSnapshotSpider.BASE_URL);
        try {
            List<String> fundCodeList = TxtUtil.readTxt(TMP_PATH);
            logger.error(Arrays.toString(fundCodeList.toArray()));
            List<FundSnapshot> fundSnapshots = FundSnapshotSpiderLess.getLessList(fundCodeList);
            logger.error(Arrays.toString(fundSnapshots.toArray()));
            fundSnapshotDao.saveAll(fundSnapshots);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】");

        System.out.println("基金任务抓取耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库.");

    }


}

