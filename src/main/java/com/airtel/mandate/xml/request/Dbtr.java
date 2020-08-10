package com.airtel.mandate.xml.request;

public class Dbtr {
	private String Nm;
    private String AccNo;
    private String Acct_Type;
    private String Cons_Ref_No;
    private String Phone;
    private String Mobile;
    private String Email;
    private String Pan;
    
	public String getNm() {
		return Nm;
	}
	public void setNm(String nm) {
		Nm = nm;
	}
	public String getAccNo() {
		return AccNo;
	}
	public void setAccNo(String accNo) {
		AccNo = accNo;
	}
	public String getAcct_Type() {
		return Acct_Type;
	}
	public void setAcct_Type(String acct_Type) {
		Acct_Type = acct_Type;
	}
	public String getCons_Ref_No() {
		return Cons_Ref_No;
	}
	public void setCons_Ref_No(String cons_Ref_No) {
		Cons_Ref_No = cons_Ref_No;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPan() {
		return Pan;
	}
	public void setPan(String pan) {
		Pan = pan;
	}
	@Override
	public String toString() {
		return "Dbtr [Nm=" + Nm + ", AccNo=" + AccNo + ", Acct_Type=" + Acct_Type + ", Cons_Ref_No=" + Cons_Ref_No
				+ ", Phone=" + Phone + ", Mobile=" + Mobile + ", Email=" + Email + ", Pan=" + Pan + "]";
	}
    
    
}
