package com.airtel.mandate.xml.request;

public class Mndt {
	private String MndtReqId;
    private String MndtId;
    private String Mndt_Type;
    private String Schm_Nm;
    
    private String ColltnAmt;
    private String MaxAmt;
    
    private Ocrncs Ocrncs;
    private Dbtr dbtr;
    private CrAccDtl crAccDtl;
    
	public String getMndtReqId() {
		return MndtReqId;
	}
	public void setMndtReqId(String mndtReqId) {
		MndtReqId = mndtReqId;
	}
	public String getMndtId() {
		return MndtId;
	}
	public void setMndtId(String mndtId) {
		MndtId = mndtId;
	}
	public String getMndt_Type() {
		return Mndt_Type;
	}
	public void setMndt_Type(String mndt_Type) {
		Mndt_Type = mndt_Type;
	}
	public String getSchm_Nm() {
		return Schm_Nm;
	}
	public void setSchm_Nm(String schm_Nm) {
		Schm_Nm = schm_Nm;
	}
	public String getColltnAmt() {
		return ColltnAmt;
	}
	public void setColltnAmt(String colltnAmt) {
		ColltnAmt = colltnAmt;
	}
	public String getMaxAmt() {
		return MaxAmt;
	}
	public void setMaxAmt(String maxAmt) {
		MaxAmt = maxAmt;
	}
	public Ocrncs getOcrncs() {
		return Ocrncs;
	}
	public void setOcrncs(Ocrncs ocrncs) {
		Ocrncs = ocrncs;
	}
	public Dbtr getDbtr() {
		return dbtr;
	}
	public void setDbtr(Dbtr dbtr) {
		this.dbtr = dbtr;
	}
	public CrAccDtl getCrAccDtl() {
		return crAccDtl;
	}
	public void setCrAccDtl(CrAccDtl crAccDtl) {
		this.crAccDtl = crAccDtl;
	}
	
	@Override
	public String toString() {
		return "Mndt [MndtReqId=" + MndtReqId + ", MndtId=" + MndtId + ", Mndt_Type=" + Mndt_Type + ", Schm_Nm="
				+ Schm_Nm + ", ColltnAmt=" + ColltnAmt + ", MaxAmt=" + MaxAmt + ", Ocrncs=" + Ocrncs + ", dbtr=" + dbtr
				+ ", crAccDtl=" + crAccDtl + "]";
	}
    
    
}
