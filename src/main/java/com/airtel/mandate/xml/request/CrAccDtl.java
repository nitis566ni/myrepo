package com.airtel.mandate.xml.request;

public class CrAccDtl {
	private String Nm;
    private String AccNo;
    private String MmbId;
    
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
	public String getMmbId() {
		return MmbId;
	}
	public void setMmbId(String mmbId) {
		MmbId = mmbId;
	}
	@Override
	public String toString() {
		return "CrAccDtl [Nm=" + Nm + ", AccNo=" + AccNo + ", MmbId=" + MmbId + "]";
	}
    
}
