package com.mahlik.demo.repository;

import com.mahlik.demo.entity.Bug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BugRepository extends CrudRepository<Bug, Long> {

    List<Bug> findAllByTesterId(long testerId);
    List<Bug> findAllByDeviceId(long deviceId);
    List<Bug> findAllByTesterIdAndDeviceId(long testerId, long deviceId);
    List<Bug> findAllByTesterIdAndDeviceIdIn(long testerId, Set<Long> deviceId);
    int countAllByTesterIdAndDeviceIdIn(long testerId, Set<Long> deviceId);
    List<Bug> findAllByTesterIdInAndDeviceIdIn(long testerId, long deviceId);

}
