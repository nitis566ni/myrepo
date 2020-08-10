package com.airtel.mandate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airtel.mandate.entities.NPCIMandate;

@Repository
public interface NPCIMandateTempRepository extends JpaRepository<NPCIMandate, Long> {

}
