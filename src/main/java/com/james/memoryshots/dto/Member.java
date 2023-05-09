package com.james.memoryshots.dto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "member")
public class Member {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "member_id")
   int member_id;

   @Column(name = "email")
   @NotNull
   @Email
   String email;

   @Column(name = "pwd")
   @NotNull
   String pwd;

   @Column(name = "name")
   @NotNull
   String name;

   public int getMember_id() {
      return member_id;
   }

   public void setMember_id(int member_id) {
      this.member_id = member_id;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPwd() {
      return pwd;
   }

   public void setPwd(String pwd) {
      this.pwd = pwd;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
