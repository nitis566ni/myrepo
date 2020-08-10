package com.airtel.mandate.xml.request;

public class MndtAuthReq {
	private GrpHdr grpHdr;
	private Mndt mndt;
	
	public GrpHdr getGrpHdr() {
		return grpHdr;
	}
	public void setGrpHdr(GrpHdr grpHdr) {
		this.grpHdr = grpHdr;
	}
	public Mndt getMndt() {
		return mndt;
	}
	public void setMndt(Mndt mndt) {
		this.mndt = mndt;
	}
	@Override
	public String toString() {
		return "MndtAuthReq [grpHdr=" + grpHdr + ", mndt=" + mndt + "]";
	}
}
