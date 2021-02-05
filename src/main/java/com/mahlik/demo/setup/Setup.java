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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        //setupDevices -> setupTesters -> setupBugs
        setupDevicesAndTesters();
        setupBugs();

//        testerRepository.findAll().forEach(System.out::println);// REMOVE ----------------------------------------------
//        deviceRepository.findAll().forEach(System.out::println);
//        System.out.println("\n\n-----------------------");
//        System.out.println(testerRepository.findAllTestersByCountry("US"));
//        System.out.println(testerRepository.findAllTestersByCountryIn(new HashSet<String>(){{
//            add("US");
//            add("GB");
//        }}));
//        System.out.println("-----------------------\n\n");
//        System.out.println(testerRepository.findAllTestersByDevices_Description("iPhone 4S"));
//        System.out.println("-----------------------\n\n");
//        System.out.println(testerRepository.findAllTestersByDevices_DescriptionAndCountry("iPhone 5", "US"));
//        System.out.println("-----------------------\n\n");
//        System.out.println(testerRepository.findAllTestersByDevices_DescriptionInAndCountryIn(new HashSet<String>(){{
//            add("iPhone 5");
//            add("iPhone 4S");
//        }}, new HashSet<String>(){{
//            add("US");
//            add("GB");
//        }}));
        //findAllTestersByDevices_DescriptionInAndCountryIn
//        List<Tester> testersUSAndIphone5 = testerRepository.findAllTestersByDevices_DescriptionAndCountry("iPhone 5", "US");
//        testersUSAndIphone5.forEach(t -> {
//            bugRepository.findAllByTesterId(t.getId()).forEach(System.out::println);
//        });

        System.out.println("\n --- SETUP --- \n");
    }

    private void setupBugs() {
        loadObjectList(Bug.class, properties.getBugsPath()).forEach(bugRepository::save);
    }

    private void setupDevicesAndTesters() {
        List<Device> devices = getDevices();
        for (Device device : devices) {
            persistDevice(device);
        }

        List<Tester> testers = getTesters();
//        testers.forEach(this::persistTester);
        for (Tester tester : testers) {
            persistTester(tester);
        }
    }

    public List<Device> getDevices() {
        return loadObjectList(Device.class, properties.getDevicesPath());
    }

    public List<Tester> getTesters() {
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

    public void persistTester(Tester tester) {
        if (!testerRepository.findById(tester.getId()).isPresent()) {
            Set<Device> devices = tester.getDevices();
            Set<Device> persistedDevices = new HashSet<>();

            for (Device device : devices) {
                Optional<Device> persistedDevice = deviceRepository.findById(device.getId());
                persistedDevice.ifPresent(persistedDevices::add);
            }
            tester.setDevices(persistedDevices);

            testerRepository.save(tester);
        }
    }

    public void persistDevice(Device device) {
        if (!deviceRepository.findById(device.getId()).isPresent()) {
            deviceRepository.save(device);
        }
    }

}
