package com.airtel.mandate.xml.request;

import java.util.Date;

public class GrpHdr {
	private String NPCI_RefMsgId;
	private String CreDtTm;
	private ReqInitPty reqInitPty;
	
	public String getNPCI_RefMsgId() {
		return NPCI_RefMsgId;
	}
	public void setNPCI_RefMsgId(String nPCI_RefMsgId) {
		NPCI_RefMsgId = nPCI_RefMsgId;
	}
	public String getCreDtTm() {
		return CreDtTm;
	}
	public void setCreDtTm(String creDtTm) {
		CreDtTm = creDtTm;
	}
	public ReqInitPty getReqInitPty() {
		return reqInitPty;
	}
	public void setReqInitPty(ReqInitPty reqInitPty) {
		this.reqInitPty = reqInitPty;
	}
	
	@Override
	public String toString() {
		return "GrpHdr [NPCI_RefMsgId=" + NPCI_RefMsgId + ", CreDtTm=" + CreDtTm + ", reqInitPty=" + reqInitPty + "]";
	}
	
}
