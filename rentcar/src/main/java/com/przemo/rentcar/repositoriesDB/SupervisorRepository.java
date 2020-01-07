package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.entities.users.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor,Long> {
}
