package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author zmx
 * @create 2022-06-22 10:15
 */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class LoggerTests {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTests.class);

    @Test
    public void testLogger(){
        System.out.println(logger.getName());
        //启用不同级别的日志
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");
    }
}
