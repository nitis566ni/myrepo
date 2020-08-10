package com.airtel.mandate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airtel.mandate.entities.NPCIMandate_Approved;


@Repository
public interface NPCIMandateApprovedRepository extends JpaRepository<NPCIMandate_Approved, Long> {

}
