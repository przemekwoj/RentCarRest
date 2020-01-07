package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.repositoriesDB.queries.AdministrationQueries;
import com.przemo.rentcar.entities.users.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdministrationRepository extends JpaRepository<Administration,Long> {

    Optional<Administration> findByEmail(String email);

    @Query(AdministrationQueries.getAdministrationByEmail)
    Optional<Administration> getAdministrationByEmail(String email);
}
