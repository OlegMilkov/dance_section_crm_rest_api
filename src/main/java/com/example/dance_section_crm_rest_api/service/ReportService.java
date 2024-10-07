package com.example.dance_section_crm_rest_api.service;


import com.example.dance_section_crm_rest_api.entity.Child;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReportService {

    public List<Child> getAllChildren();

    public List<Child> getAllDebtors();

    public void saveChild(Child child);

    public Child getChild(int id);

    public void deleteChild(int id);

    public List<Child> getChildrenByGroup(String group_name);

    public  int getNumberOfChildrenInEachGroup (String group_name);

    public List<Child>getChildrenByName(String name);

    public List <String> getAllDistinctGroupNames();

    public List<Child> getAllChildrenSortedByGroupAsc();

    public List<Child> getAllChildrenSortedByGroupDesc();

}

