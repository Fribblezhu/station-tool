package com.cie.stationtool.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "tool")
public class ToolConfig {

    private String ruleFile;

    private Map<String, String> ruleMapper;

    private String targetFile;

    private Map<String, String>  targetMapper;

    private String standardFile;

    private Map<String, String> standardMapper;

    public String getRuleFile() {
        return ruleFile;
    }

    public void setRuleFile(String ruleFile) {
        this.ruleFile = ruleFile;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    public String getStandardFile() {
        return standardFile;
    }

    public void setStandardFile(String standardFile) {
        this.standardFile = standardFile;
    }

    public Map<String, String> getRuleMapper() {
        return ruleMapper;
    }

    public void setRuleMapper(Map<String, String> ruleMapper) {
        this.ruleMapper = ruleMapper;
    }

    public Map<String, String> getTargetMapper() {
        return targetMapper;
    }

    public void setTargetMapper(Map<String, String> targetMapper) {
        this.targetMapper = targetMapper;
    }

    public Map<String, String> getStandardMapper() {
        return standardMapper;
    }

    public void setStandardMapper(Map<String, String> standardMapper) {
        this.standardMapper = standardMapper;
    }
}
