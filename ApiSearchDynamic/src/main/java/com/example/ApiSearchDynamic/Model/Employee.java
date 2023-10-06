package com.example.ApiSearchDynamic.Model;

import java.sql.Date;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_employeee")
@Data
public class Employee {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_epl",columnDefinition = "INT(6)", nullable=false)
  private Long id;
  
  @Column(name = "name_epl", nullable=false,length=200)
  private String name;
  
  @Column(name = "address_epl", nullable=false,length=200)
  private String address;
  
  @Column(name = "phone_epl", nullable=false,length=200)
  private String phone;
  
  @Column(name = "email_epl", nullable=false, length=200)
  private String email;
  
  @Column(name = "age_epl", nullable=false)
  private int age;
  
  @Column(name = "department_epl", nullable=false,length=200)
  private String department;
  
  @Column(name = "startDate_epl", nullable=false)
  private Date startDate;
  
  
  
  public Long getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getAddress() {
    return address;
  }
  public String getPhone() {
    return phone;
  }
  public String getEmail() {
    return email;
  }
  public int getAge() {
    return age;
  }
  public String getDepartment() {
    return department;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public void setDepartment(String department) {
    this.department = department;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Employee() {
  }
  public Employee(Long id, String name, String address, String phone, String email, int age, String department,
      Date startDate) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.age = age;
    this.department = department;
    this.startDate = startDate;
  }
  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email
        + ", age=" + age + ", department=" + department + ", startDate=" + startDate + "]";
  }
}

