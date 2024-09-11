package com.example.dance_section_crm_rest_api.controller;

import com.example.dance_section_crm_rest_api.entity.Child;
import com.example.dance_section_crm_rest_api.exeption_handling.NoSuchChildException;
import com.example.dance_section_crm_rest_api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/data")
public class MyRestController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/children")
    public List<Child> getAllChildren() {
        List<Child> children = reportService.getAllChildren();
        return children;
    }

    @GetMapping("/children/{id}")
    public Child getChild(@PathVariable int id) {
        Child child = reportService.getChild(id);
        if (child == null) {
            throw new NoSuchChildException("There is no Child with " + id + " in our Database");
        }
        return child;
    }

    @PostMapping("/children")
    public Child addNewChild(@RequestBody Child child) {
        reportService.saveChild(child);
        return child;
    }

    @PutMapping("/children")
    @PreAuthorize("hasRole('HR')")
    public Child updateChild(@RequestBody Child child) {
        reportService.saveChild(child);
        return child;
    }

    @DeleteMapping("/children/{id}")
    public String deleteChild(@PathVariable int id) {
        Child child = reportService.getChild(id);
        if (child == null) {
            throw new NoSuchChildException("There is no Child with" + id + " in our Database");
        }
        reportService.deleteChild(id);
        return "Child with ID =" + id + " was deleted";
    }

    @GetMapping("/childrenByGroup/{group_name}")
    public List<Child> getChildrenByGroup(@PathVariable String group_name) {
        List<Child> allChildByGroup = reportService.getChildrenByGroup(group_name);
        return allChildByGroup;
    }

    @GetMapping("/countByGroup/{group_name}")
    public ResponseEntity<String> getNumberOfChildrenInEachGroup(@PathVariable String group_name) {
        int count = reportService.getNumberOfChildrenInEachGroup(group_name);
        String message = "Кількість дітей  у группі " + group_name + ": " + count;

        return ResponseEntity.ok(message);
    }

    @GetMapping("/children/debtors")
    public List<Child> getAllDebtors() {
        List<Child> allDebtors = reportService.getAllDebtors();
        return allDebtors;
    }

    @GetMapping("/children/name/{name}")
    public List<Child> getChildrenByName(@PathVariable String name) {
        List<Child> getChildrenName = reportService.getChildrenByName(name);
        return getChildrenName;

    }

    @GetMapping("/groups/distinct")
    public List<String> getAllDistinctGroupNames() {
        List<String> allDistinctGroupNames = reportService.getAllDistinctGroupNames();
        return allDistinctGroupNames;
    }

    @GetMapping("/children/sort/asc")
    public List<Child> getAllChildrenSortedByAsc() {
        List<Child> allChildren = reportService.getAllChildrenSortedByGroupAsc();
        if (allChildren == null) {
            throw new NullPointerException("Children list is null");
        }
        return allChildren;
    }

    @GetMapping("/children/sort/desc")
    public List<Child> getAllChildrenSortedByDesc() {
        List<Child> allChildren = reportService.getAllChildrenSortedByGroupDesc();
        if (allChildren == null) {
            throw new NullPointerException("Children list is null");
        }
        return allChildren;
    }
}
