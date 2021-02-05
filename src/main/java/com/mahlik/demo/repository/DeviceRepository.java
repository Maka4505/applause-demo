package com.mahlik.demo.repository;

import com.mahlik.demo.entity.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    List<Device> findAllByDescription(String description);

    List<Device> findAllByIdIn(Set<Long> description);

}
