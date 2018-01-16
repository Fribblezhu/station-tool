package com.cie.stationtool.builder;

import com.cie.expands.excel.ExcelIO;
import com.cie.stationtool.config.ToolConfig;
import com.cie.stationtool.model.ConfigModel;
import com.cie.stationtool.model.StandardModel;
import com.cie.stationtool.model.TargetModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToolBuilderImpl implements ToolBuilder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ToolConfig toolConfig;

    private List<ConfigModel> standards;

    private List<TargetModel> targets;

    @Override
    public boolean init() {
        try {
            Map<String, String> wapper = new HashMap<>();
            wapper.put("deviceType", "device_type");
            wapper.put("deviceName", "device_name");
            wapper.put("sysCateId", "sys_cate_id");
            standards  = ExcelIO.read2Bean(new FileInputStream(new File(toolConfig.getConfig())), wapper, ConfigModel.class);
        } catch (Exception e) {
            this.logger.error("读取标准测点失败..");
            return false;
        }
        try {
            Map<String, String> wapper = new HashMap<>();
            wapper.put("deviceType", "device_type");
            wapper.put("deviceName", "device_name");
            wapper.put("sysCateId", "sys_cate_id");
            standards  = ExcelIO.read2Bean(new FileInputStream(new File(toolConfig.getConfig())), wapper, StandardModel.class)
        } catch (Exception e) {
            this.logger.error("读取标准测点失败..");
            return false;
        }

        return false;
    }

    @Override
    public boolean building() {
        return false;
    }
}
