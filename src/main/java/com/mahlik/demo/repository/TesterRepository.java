package com.mahlik.demo.repository;

import com.mahlik.demo.entity.Tester;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TesterRepository extends CrudRepository<Tester, Long> {

    List<Tester> findAllTestersByCountry(String country);

    List<Tester> findAllTestersByCountryIn(Set<String> countries);

    List<Tester> findAllTestersByDevices_Description(String description);

    List<Tester> findAllTestersByDevices_DescriptionAndCountry(String description, String country);

    List<Tester> findAllTestersByDevices_IdInAndCountryIn(Set<Long> devicesIds, Set<String> countries);

}
