package com.example.dance_section_crm_rest_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;

@Entity
@Table(name = "report")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Size(min = 3, message = "Ім'я повинно містити не менше 3 символів")
    private String name;

    @Size(min = 3, message = "Фамілія повинна містити не менше 3 символів")
    @Column(name = "surname")
    private String surname;

    @Column(name = "group name")
    @NotEmpty(message = "Поле повинно бути заповнене")
    private String groupName;

    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "health_certificate")
    private String healthCertificate;
    @Column(name = "form")
    private String form;
    @Column(name = "safety rules")
    private String safetyRules;
    @Column(name = "birth certificate")
    private String birthCertificate;
    @Column(name = "payment")
    private int payment;

    public Child() {

    }

    public Child(String name, String surname, String group_name, LocalDate birthday,
                 String health_certificate, String form, String safety_rules, String
                         birth_certificate, int payment) {
        this.name = name;
        this.surname = surname;
        this.groupName = group_name;
        this.birthday = birthday;
        this.healthCertificate = health_certificate;
        this.form = form;
        this.safetyRules = safety_rules;
        this.birthCertificate = birth_certificate;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getHealthCertificate() {
        return healthCertificate;
    }

    public void setHealthCertificate(String health_certificate) {
        this.healthCertificate = health_certificate;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getSafetyRules() {
        return safetyRules;
    }

    public void setSafetyRules(String safety_rules) {
        this.safetyRules = safety_rules;
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birth_certificate) {
        this.birthCertificate = birth_certificate;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        try {
            this.payment = Integer.parseInt(payment.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введене значення не є цілим числом.");
        }
    }

}


