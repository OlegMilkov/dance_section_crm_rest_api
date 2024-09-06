package com.example.dance_section_crm_rest_api.repository;

import com.example.dance_section_crm_rest_api.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Child, Integer> {
    List<Child> findAllByHealthCertificateOrFormOrSafetyRulesOrBirthCertificate(String healthCertificate, String form, String safetyRules, String birthCertificate);

    List<Child> findAllByGroupName(String groupName);

    int countByGroupName(String groupName);

    List<Child> findAllByName(String name);

    @Query("SELECT DISTINCT c.groupName FROM Child c")
    List<String> findAllDistinctGroupNames();

    List<Child> findAllByOrderByGroupNameAsc();

    List<Child> findAllByOrderByGroupNameDesc();


}

