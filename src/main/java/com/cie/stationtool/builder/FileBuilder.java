package com.cie.stationtool.builder;

import com.alibaba.fastjson.JSONObject;
import com.cie.expands.excel.ExcelIO;
import com.cie.expands.excel.config.Header;
import com.cie.stationtool.config.ToolConfig;
import com.cie.stationtool.model.RuleModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FileBuilder{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ToolConfig toolConfig;

    private List<TargetModel> targets;

    private List<RuleModel> rules;

    private  boolean loadFile() {
        int step = 1;
        try {
            rules  = ExcelIO.read2Bean(FileUtils.getResourceAsStream(toolConfig.getRuleFile()), toolConfig.getRuleMapper(), RuleModel.class);
            ++ step;
            targets = ExcelIO.read2Bean(FileUtils.getResourceAsStream(toolConfig.getTargetFile()), toolConfig.getTargetMapper(), TargetModel.class);
        } catch (Exception e) {
            if(step == 1 )
                this.logger.error("读入rule文件失败...");
            else
                this.logger.error("读取target文件失败...");
            this.logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    private void building() {
        Map<String, List<RuleModel>> ruleMap = new HashMap<>();
        for(RuleModel r: this.rules) {
            if(ruleMap.get(r.getDeviceType()) != null) {
               ruleMap.get(r.getDeviceType()).add(r);
            } else {
                List<RuleModel> newList = new ArrayList<>();
                newList.add(r);
                ruleMap.put(r.getDeviceType(), newList);
            }
        }
        this.logger.info("ruleMap.size = {}",ruleMap.size());
        this.logger.info("ruleMap.keys = {}", JSONObject.toJSONString(ruleMap.keySet()));
        logger.info("开始匹配, 总数为:{}", targets.size());
        int count = 0;
        for(TargetModel targetModel: targets) {
            if(ruleMap.get(targetModel.getDeviceType()) != null) {
                for (RuleModel ruleModel : ruleMap.get(targetModel.getDeviceType())) {
                    if (this.compareTo(targetModel.getPointName(), ruleModel.getPointName())) {
                        targetModel.setToSysCateId(ruleModel.getSysCateId());
                        this.logger.info("匹配成功 {}->{} ->{}", targetModel.getPointName(), ruleModel.getPointName() , ruleModel.getSysCateId());
                        count++;
                        break;
                    }
                }
            }else  {
                this.logger.debug("Device Type 不存在: {}", targetModel.getDeviceType());
            }
        }
        logger.info("匹配结束, 成功个数为:{}", count);
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

    private boolean compareTo(String target, String rule) {
        if(target.contains(rule))
            return true;
        if(rule.contains(target))
            return  true;
        return false;
    }

    public boolean execute() {
        if(!this.loadFile()) {
            return false;
        }
        this.building();
        if(!this.save()) {
            return false;
        }
        return true;
    }
}
