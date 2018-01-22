package com.cie.stationtool.config;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolConfigTest {

    @Autowired
    private ToolConfig toolConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() {
        Assert.assertNotNull(toolConfig);
        Assert.assertNotNull(toolConfig.getRuleFile());
        Assert.assertNotNull(toolConfig.getTargetFile());
        Assert.assertNotNull(toolConfig.getStandardFile());
        Assert.assertTrue(toolConfig.getRuleMapper().size() > 0);
        Assert.assertTrue(toolConfig.getTargetMapper().size() > 0);
        Assert.assertTrue(toolConfig.getStandardMapper().size() > 0);

        logger.info(JSONObject.toJSONString(toolConfig));
    }


}