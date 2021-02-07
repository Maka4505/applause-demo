package com.mahlik.demo.service;

import com.mahlik.demo.dto.DropdownListItem;
import com.mahlik.demo.entity.Device;
import com.mahlik.demo.repository.DeviceRepository;
import com.mahlik.demo.repository.TesterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Service
public class TesterSearchCriteriaService {

    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;

    public TesterSearchCriteriaService(
            TesterRepository testerRepository,
            DeviceRepository deviceRepository
    ) {
        this.testerRepository = testerRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<DropdownListItem> getAvailableDeviceOptions() {
        ArrayList<Device> allDevices = (ArrayList<Device>) deviceRepository.findAll();
        return allDevices.stream().map(
                device -> new DropdownListItem(device.getId(), device.getDescription())
        ).collect(toList());
    }

    public List<DropdownListItem> getAvailableCountryOptions() {
        List<String> allCountries = testerRepository.findDistinctCountries();
        return IntStream.range(0, allCountries.size()).mapToObj(
                index -> new DropdownListItem((long) (index + 1), allCountries.get(index))
        ).collect(toList());
    }
}
