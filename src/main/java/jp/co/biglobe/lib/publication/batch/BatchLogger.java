package jp.co.biglobe.lib.publication.batch;

import jp.co.biglobe.lib.pipes_and_filters.publication.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchLogger {

    public static void outputLog(Counter counter, Class<?> clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);

        logger.info("総件数: {}", counter.getTotalCount());
        logger.info("成功: {}", counter.getSuccessCount());
        logger.info("失敗: {}", counter.getFailedCount());
        logger.info("スキップ: {}", counter.getSkipCount());
    }

}
