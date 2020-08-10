package com.airtel.mandate.xml.request;

import java.util.Date;

public class Ocrncs {
	private String SeqTp;
    private String Frqcy;
    private String FrstColltnDt;
    private String FnlColltnDt;
    
	public String getSeqTp() {
		return SeqTp;
	}
	public void setSeqTp(String seqTp) {
		SeqTp = seqTp;
	}
	public String getFrqcy() {
		return Frqcy;
	}
	public void setFrqcy(String frqcy) {
		Frqcy = frqcy;
	}
	public String getFrstColltnDt() {
		return FrstColltnDt;
	}
	public void setFrstColltnDt(String frstColltnDt) {
		FrstColltnDt = frstColltnDt;
	}
	public String getFnlColltnDt() {
		return FnlColltnDt;
	}
	public void setFnlColltnDt(String fnlColltnDt) {
		FnlColltnDt = fnlColltnDt;
	}
	@Override
	public String toString() {
		return "Ocrncs [SeqTp=" + SeqTp + ", Frqcy=" + Frqcy + ", FrstColltnDt=" + FrstColltnDt + ", FnlColltnDt="
				+ FnlColltnDt + "]";
	}
    
    
}
