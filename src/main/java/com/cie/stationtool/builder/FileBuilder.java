package com.cie.stationtool.builder;

import com.alibaba.fastjson.JSONObject;
import com.cie.expands.excel.ExcelIO;
import com.cie.expands.excel.config.Header;
import com.cie.stationtool.config.ToolConfig;
import com.cie.stationtool.model.RuleModel;
import com.cie.stationtool.model.StandardModel;
import com.cie.stationtool.model.TargetModel;
import com.cie.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileBuilder{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ToolConfig toolConfig;

    private List<StandardModel> standards;

        private List<TargetModel> targets;

    private List<RuleModel> rules;

    private  boolean loadFile() {
        int step = 1;
        try {
            rules  = ExcelIO.read2Bean(FileUtils.getResourceAsStream(toolConfig.getRuleFile()), toolConfig.getRuleMapper(), RuleModel.class);
           // this.logger.info("加载rule文件成功:{}.", JSONObject.toJSONString(this.rules));
            ++ step;
            targets = ExcelIO.read2Bean(FileUtils.getResourceAsStream(toolConfig.getTargetFile()), toolConfig.getTargetMapper(), TargetModel.class);
           // this.logger.info("加载target文件成功:{}.", JSONObject.toJSONString(this.targets));
            ++ step;
            standards = ExcelIO.read2Bean(FileUtils.getResourceAsStream(toolConfig.getStandardFile()), toolConfig.getStandardMapper(), StandardModel.class);
           // this.logger.info("加载standard文件成功:{}.", JSONObject.toJSONString(this.standards));
        } catch (Exception e) {
            if(step == 1 )
                this.logger.error("读入rule文件失败...");
            else if(step == 2)
                this.logger.error("读取target文件失败...");
            else
                this.logger.error("读取standard文件失败...");
            this.logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    private void building() {
        for(TargetModel targetModel: targets) {
            for (RuleModel ruleModel : rules) {
                if (targetModel.getPointName().equals(ruleModel.getPointName())) {
                    targetModel.setToSysCateId(ruleModel.getSysCateId());
                }
            }
        }
    }

    private boolean save(){
        List<Header> headerList = new ArrayList<>();
        for(String key: toolConfig.getTargetMapper().keySet()) {
            headerList.add(new Header(key, toolConfig.getTargetMapper().get(key)));
        }
        List<Object> dataList = new ArrayList<>();
        dataList.addAll(this.targets);
        String filePath = "targets"+ System.currentTimeMillis() + ".xlsx";
        File file = new File(filePath);
        if(!file.exists()) {
            try {
                file.createNewFile();
                ExcelIO.write(new FileOutputStream(file), headerList, dataList);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean execute() {
        if(!this.loadFile()) {
            return false;
        }
        building();
        if(!this.save()) {
            return false;
        }
        return true;
    }
}
