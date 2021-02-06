package com.mahlik.demo.repository;

import com.mahlik.demo.entity.Bug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BugRepository extends CrudRepository<Bug, Long> {

    int countAllByTesterId(long testerId);

    int countAllByTesterIdAndDeviceIdIn(long testerId, Set<Long> deviceId);

}
