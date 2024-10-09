package com.example.dance_section_crm_rest_api.service;

import com.example.dance_section_crm_rest_api.entity.Child;
import com.example.dance_section_crm_rest_api.exeption_handling.NoSuchChildException;
import com.example.dance_section_crm_rest_api.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    @Transactional
    public List<Child> getAllChildren() {
        return reportRepository.findAll();
    }

    @Override
    @Transactional
    public List<Child> getAllDebtors() {
        return reportRepository.findAllByHealthCertificateOrFormOrSafetyRulesOrBirthCertificate
                ("-","-","-","-");
    }

    @Override
    @Transactional
    public Child saveChild(Child child) {
        reportRepository.save(child);
        return child;
    }


    @Override
    @Transactional
    public Child getChild(int id) {
        return reportRepository.findById(id) .orElseThrow(() -> new NoSuchChildException("There is no Child with " + id + " in our Database"));
    }


    @Override
    @Transactional
    public void deleteChild(int id) {
        Child child = reportRepository.findById(id)
                .orElseThrow(() -> new NoSuchChildException("There is no Child with " + id + " in our Database"));
        reportRepository.delete(child);
    }

    @Override
    @Transactional
    public List<Child> getChildrenByGroup(String group_name) {
        List<Child> childrenByGroup = reportRepository.findAllByGroupName(group_name);
        Collections.sort(childrenByGroup, Comparator.comparing(Child::getName));
        return childrenByGroup;
    }

    @Override
    @Transactional
    public int getNumberOfChildrenInEachGroup(String group_name) {
        return reportRepository.countByGroupName(group_name);
    }

    @Override
    @Transactional
    public List<Child> getChildrenByName(String name) {
        return reportRepository.findAllByName(name);
    }

    @Override
    @Transactional
    public List<String> getAllDistinctGroupNames() {
        return reportRepository.findAllDistinctGroupNames();
    }


    @Override
    @Transactional
    public List<Child> getAllChildrenSortedByGroupAsc() {
        return reportRepository.findAllByOrderByGroupNameAsc();
    }

    @Override
    @Transactional
    public List<Child> getAllChildrenSortedByGroupDesc() {
        return reportRepository.findAllByOrderByGroupNameDesc();
    }

}
