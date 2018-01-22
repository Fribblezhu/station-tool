package com.cie.stationtool.runner;

import com.cie.stationtool.builder.FileBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ToolRunner implements ApplicationRunner{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileBuilder builder;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        if(this.builder.execute()) {
            logger.info("build file succeed .");
        } else {
            logger.error("build file failed .");
        }
        return ;
    }

}
