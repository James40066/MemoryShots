package com.james.memoryshots.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "member")
public class Member {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "member_id")
   int memberId;

   @Column(name = "email")
   @NotNull
   @Email
   @ApiModelProperty(value = "電子信箱")
   String email;

   @Column(name = "pwd")
   @NotNull
   @ApiModelProperty(value = "密碼", required = true)
   String pwd;

   @Column(name = "name")
   @NotNull
   @ApiModelProperty(value = "會員名稱")
   String name;

   public int getMemberId() {
      return memberId;
   }

   public void setMemberId(int memberId) {
      this.memberId = memberId;
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
