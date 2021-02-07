package com.mahlik.demo.setup;

import com.mahlik.demo.configuration.PropertiesConfiguration;
import com.mahlik.demo.entity.Bug;
import com.mahlik.demo.entity.Device;
import com.mahlik.demo.entity.Tester;
import com.mahlik.demo.entity.TesterToDevice;
import com.mahlik.demo.repository.BugRepository;
import com.mahlik.demo.repository.DeviceRepository;
import com.mahlik.demo.repository.TesterRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

import static com.mahlik.demo.setup.SetupHelper.*;

@Component
public class Setup {

    private final PropertiesConfiguration properties;
    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;
    private final BugRepository bugRepository;

    public Setup(
            PropertiesConfiguration properties,
            TesterRepository testerRepository,
            DeviceRepository deviceRepository,
            BugRepository bugRepository
    ) {
        this.properties = properties;
        this.testerRepository = testerRepository;
        this.deviceRepository = deviceRepository;
        this.bugRepository = bugRepository;
    }

    @PostConstruct
    public void setupData() {
        setupDevices();
        setupTesters();
        setupBugs();
    }

    private void setupDevices() {
        deviceRepository.saveAll(getDevices());
    }

    private List<Device> getDevices() {
        return loadObjectList(Device.class, properties.getDevicesPath());
    }

    private void setupTesters() {
        testerRepository.saveAll(getTesters());
    }

    private List<Tester> getTesters() {
        List<Device> allDevices = getDevices();
        List<Tester> testers = loadObjectList(Tester.class, properties.getTestersPath());
        List<TesterToDevice> testersToDevices = loadObjectList(TesterToDevice.class, properties.getTesterDevicePath());

        for (TesterToDevice testerDevice : testersToDevices) {
            Device device = findDeviceById(allDevices, testerDevice.getDeviceId());
            Tester tester = findTesterById(testers, testerDevice.getTesterId());
            Set<Device> devices = tester.getDevices();
            devices.add(device);
            tester.setDevices(devices);
        }
        return testers;
    }

    private void setupBugs() {
        bugRepository.saveAll(getBugs());
    }

    private List<Bug> getBugs() {
        return loadObjectList(Bug.class, properties.getBugsPath());
    }

}
