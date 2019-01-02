package com.xcx.xestore.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/**
 * @ClassName : SystemRunner
 * @Description : 系统运行配置
 * @Author : xcx
 * @Date : 2018-10-19 10:38
 * @Version : 1.0
 */
public class SystemRunner implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(SystemRunner.class);

    @Override
    public void run(String... args) throws Exception {
        // TODO: 2018-10-20 生成各种ID
        generateID(1L);
    }

    /**
     * 方案一：生成第二天 存入redis/file中
     * 方案二：使用id池
     * @Description : 生成ID
     * @Date : 2018-10-19 11:31
     * @param timestamp 当日时间戳
     * @return : void
     */
    private void generateID(Long timestamp){

    }
}
