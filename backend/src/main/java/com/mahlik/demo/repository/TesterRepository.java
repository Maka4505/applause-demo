package com.mahlik.demo.repository;

import com.mahlik.demo.entity.Tester;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TesterRepository extends CrudRepository<Tester, Long> {

    @Query("select distinct country from Tester")
    List<String> findDistinctCountries();

    List<Tester> findDistinctTestersByCountryIn(Set<String> countries);

    List<Tester> findDistinctTestersByDevices_IdIn(Set<Long> devicesIds);

    List<Tester> findDistinctTestersByCountryInAndDevices_IdIn(Set<String> countries, Set<Long> devicesIds);

}
