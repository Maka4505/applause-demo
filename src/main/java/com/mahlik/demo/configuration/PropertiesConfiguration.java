package com.mahlik.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "file")
public class PropertiesConfiguration {

    private String testersPath;
    private String devicesPath;
    private String bugsPath;
    private String testerDevicePath;

    public String getTestersPath() {
        return testersPath;
    }

    public PropertiesConfiguration setTestersPath(String testersPath) {
        this.testersPath = testersPath;
        return this;
    }

    public String getDevicesPath() {
        return devicesPath;
    }

    public PropertiesConfiguration setDevicesPath(String devicesPath) {
        this.devicesPath = devicesPath;
        return this;
    }

    public String getBugsPath() {
        return bugsPath;
    }

    public PropertiesConfiguration setBugsPath(String bugsPath) {
        this.bugsPath = bugsPath;
        return this;
    }

    public String getTesterDevicePath() {
        return testerDevicePath;
    }

    public PropertiesConfiguration setTesterDevicePath(String testerDevicePath) {
        this.testerDevicePath = testerDevicePath;
        return this;
    }
}
