package com.xhsoft.framework.common.javamail.model;

import java.io.Serializable;
import java.util.Date;

public class Contact implements Serializable {
	
	// Common info
		private String firstName; 
		private String lastName; 
		private String name;
		private String neck;
		private String sex; 
		private String email;
		
		private long groupId;

		// Personnel inof
		private String mobile;
		private String phone;
		private Date birthday;
		private String qq;
		private String tasts;
		private String webSite;

		// Home inof
		// private long nativeId;
		// private long provinceId;
		// priveate long cityId;
		// private long countryId;
		private String fax;
		private String zipCode;
		private String address;

		// Company info
		private String company;
		private String department;
		private String position;
		private String comPhone;
		private String comFax;
		private String comAddress;
		private String comWebSite;
		private String comRemark;

		// Else info
		private String remark;

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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNeck() {
			return neck;
		}

		public void setNeck(String neck) {
			this.neck = neck;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public long getGroupId() {
			return groupId;
		}

		public void setGroupId(long groupId) {
			this.groupId = groupId;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public String getQq() {
			return qq;
		}

		public void setQq(String qq) {
			this.qq = qq;
		}

		public String getTasts() {
			return tasts;
		}

		public void setTasts(String tasts) {
			this.tasts = tasts;
		}

		public String getWebSite() {
			return webSite;
		}

		public void setWebSite(String webSite) {
			this.webSite = webSite;
		}

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getComPhone() {
			return comPhone;
		}

		public void setComPhone(String comPhone) {
			this.comPhone = comPhone;
		}

		public String getComFax() {
			return comFax;
		}

		public void setComFax(String comFax) {
			this.comFax = comFax;
		}

		public String getComAddress() {
			return comAddress;
		}

		public void setComAddress(String comAddress) {
			this.comAddress = comAddress;
		}

		public String getComWebSite() {
			return comWebSite;
		}

		public void setComWebSite(String comWebSite) {
			this.comWebSite = comWebSite;
		}

		public String getComRemark() {
			return comRemark;
		}

		public void setComRemark(String comRemark) {
			this.comRemark = comRemark;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

}
