package com.mastertheboss.model;

import jakarta.persistence.*;

import org.hibernate.annotations.SoftDelete;

import java.io.Serializable;
import org.hibernate.annotations.SoftDeleteType;

@Entity
@SoftDelete(strategy= SoftDeleteType.ACTIVE)
//@SoftDelete(columnName = "removed")
public class Customer implements Serializable {

  public Customer() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String email;

  @Column
  private String firstName;

  @Column
  private String lastName;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
  }

}
