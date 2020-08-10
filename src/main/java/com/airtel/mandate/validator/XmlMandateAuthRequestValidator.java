package com.airtel.mandate.validator;

import java.io.IOException;
import java.io.StringReader;

import com.airtel.mandate.xml.request.CrAccDtl;
import com.airtel.mandate.xml.request.Dbtr;
import com.airtel.mandate.xml.request.GrpHdr;
import com.airtel.mandate.xml.request.Info;
import com.airtel.mandate.xml.request.Mndt;
import com.airtel.mandate.xml.request.MndtAuthReq;
import com.airtel.mandate.xml.request.Ocrncs;
import com.airtel.mandate.xml.request.ReqInitPty;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;

@Service
public class XmlMandateAuthRequestValidator {
	
	public static final String SCHEMA_FILE = "mndt_auth_req.xsd";

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		XmlMandateAuthRequestValidator XMLValidator = new XmlMandateAuthRequestValidator();

		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<Document xmlns=\"http://npci.org/onmags/schema\">\r\n" + "	<MndtAuthReq>\r\n"
				+ "		<GrpHdr>\r\n"
				+ "			<NPCI_RefMsgId></NPCI_RefMsgId>\r\n"
				+ "			<CreDtTm>2017-02-09T15:12:39</CreDtTm>\r\n" + "			<ReqInitPty>\r\n"
				+ "				<Info>\r\n" + "					<Id>HDFC00001123456789</Id>\r\n"
				+ "					<CatCode>COO1</CatCode>\r\n" + "					<UtilCode>6543</UtilCode>\r\n"
				+ "					<CatDesc>Insurance Company</CatDesc>\r\n"
				+ "					<Name>LIC</Name>\r\n"
				+ "					<Spn_Bnk_Nm>State Bank Of India</Spn_Bnk_Nm>\r\n" + "				</Info>\r\n"
				+ "			</ReqInitPty>\r\n" + "		</GrpHdr>\r\n" + "		<Mndt>\r\n"
				+ "			<MndtReqId>000f0f29dc27f00000101b09c52b8e50037</MndtReqId>\r\n"
				+ "			<MndtId>UMRN</MndtId>\r\n" + "			<Mndt_Type>DEBIT</Mndt_Type>\r\n"
				+ "			<Schm_Nm>ABC123</Schm_Nm>\r\n" + "			<Ocrncs>\r\n"
				+ "				<SeqTp>RCUR</SeqTp>\r\n" + "				<Frqcy>MNTH</Frqcy>\r\n"
				+ "				<FrstColltnDt>2017-04-05</FrstColltnDt>\r\n"
				+ "				<FnlColltnDt>2018-03-05</FnlColltnDt>\r\n" + "			</Ocrncs>\r\n"
				+ "			<ColltnAmt Ccy=\"INR\">1200</ColltnAmt>\r\n"
				+ "			<MaxAmt Ccy=\"INR\">1200</MaxAmt>\r\n" + "			<Dbtr>\r\n"
				+ "				<Nm>FAIROSE</Nm>\r\n" + "				<AccNo>ACNo123456</AccNo>\r\n"
				+ "				<Acct_Type>SAVINGS</Acct_Type>\r\n"
				+ "				<Cons_Ref_No>XXX123</Cons_Ref_No>\r\n"
				+ "				<Phone>+91-XXX-XXXXXXXX</Phone>\r\n"
				+ "				<Mobile>+91-XXXXXXXXXX</Mobile>\r\n" + "				<Email>XX@XX.COM</Email>\r\n"
				+ "				<Pan>XXXXX9999X</Pan>\r\n" + "			</Dbtr>\r\n" + "			<CrAccDtl>\r\n"
				+ "				<Nm>ABC India Limited</Nm>\r\n" + "				<AccNo>ACNo78912340</AccNo>\r\n"
				+ "				<MmbId>HDFC0000001</MmbId>\r\n" + "			</CrAccDtl>\r\n" + "		</Mndt>\r\n"
				+ "	</MndtAuthReq>\r\n" + "</Document>";
		
		boolean valid = XMLValidator.validateXML(content, SCHEMA_FILE);

		System.out.printf("validation = %b.\n", valid);

		MndtAuthReq req = XMLValidator.parseXmlMndtAuthReq(content);
		
		boolean validReq = XMLValidator.validateMandateReq(req);
		
		System.out.printf("validation = %b.\n", validReq);
	}

	public boolean validateXML(String xmlContent, String schemaFile) {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {

			Schema schema = schemaFactory.newSchema(new File(getResource(schemaFile)));

			Validator validator = schema.newValidator();
			StringReader reader = new StringReader(xmlContent);

			validator.validate(new StreamSource(reader));

			return true;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public MndtAuthReq parseXmlMndtAuthReq(String xmlContent) throws ParserConfigurationException, SAXException, IOException {
		MndtAuthReq authReq = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringReader reader = new StringReader(xmlContent);
		Document doc = builder.parse(new InputSource(reader));

		// normalize XML response
		doc.getDocumentElement().normalize();

		// read MandateReq details
		authReq = new MndtAuthReq();

		GrpHdr grpHdr = new GrpHdr();

		String NPCI_RefMsgId = doc.getElementsByTagName("NPCI_RefMsgId").item(0).getTextContent();
		String CreDtTm = doc.getElementsByTagName("CreDtTm").item(0).getTextContent();

		grpHdr.setNPCI_RefMsgId(NPCI_RefMsgId);
		grpHdr.setCreDtTm(CreDtTm);

		Info info = new Info();

		String Id = doc.getElementsByTagName("Id").item(0).getTextContent();
		String CatCode = doc.getElementsByTagName("CatCode").item(0).getTextContent();
		String UtilCode = doc.getElementsByTagName("UtilCode").item(0).getTextContent();
		String CatDesc = doc.getElementsByTagName("CatDesc").item(0).getTextContent();
		String Name = doc.getElementsByTagName("Name").item(0).getTextContent();
		String Spn_Bnk_Nm = doc.getElementsByTagName("Spn_Bnk_Nm").item(0).getTextContent();

		info.setId(Id);
		info.setCatCode(CatCode);
		info.setUtilCode(UtilCode);
		info.setCatDesc(CatDesc);
		info.setName(Name);
		info.setSpn_Bnk_Nm(Spn_Bnk_Nm);

		ReqInitPty initPty = new ReqInitPty();
		initPty.setInfo(info);

		grpHdr.setReqInitPty(initPty);

		////////////////////////////////////////////

		Mndt mndt = new Mndt();

		String MndtReqId = doc.getElementsByTagName("MndtReqId").item(0).getTextContent();
		String MndtId = doc.getElementsByTagName("MndtId").item(0).getTextContent();
		String Mndt_Type = doc.getElementsByTagName("Mndt_Type").item(0).getTextContent();
		String Schm_Nm = doc.getElementsByTagName("Schm_Nm").item(0).getTextContent();

		mndt.setMndtReqId(MndtReqId);
		mndt.setMndtId(MndtId);
		mndt.setMndt_Type(Mndt_Type);
		mndt.setSchm_Nm(Schm_Nm);

		Ocrncs ocrncs = new Ocrncs();

		String SeqTp = doc.getElementsByTagName("SeqTp").item(0).getTextContent();
		String Frqcy = doc.getElementsByTagName("Frqcy").item(0).getTextContent();
		String FrstColltnDt = doc.getElementsByTagName("FrstColltnDt").item(0).getTextContent();
		String FnlColltnDt = doc.getElementsByTagName("FnlColltnDt").item(0).getTextContent();

		ocrncs.setSeqTp(SeqTp);
		ocrncs.setFrqcy(Frqcy);
		ocrncs.setFrstColltnDt(FrstColltnDt);
		ocrncs.setFnlColltnDt(FnlColltnDt);

		mndt.setOcrncs(ocrncs);

		String ColltnAmt = doc.getElementsByTagName("ColltnAmt").item(0).getTextContent();
		String MaxAmt = doc.getElementsByTagName("MaxAmt").item(0).getTextContent();

		mndt.setColltnAmt(ColltnAmt);
		mndt.setMaxAmt(MaxAmt);

		Dbtr dbtr = new Dbtr();

		String Nm_dbtr = doc.getElementsByTagName("Nm").item(0).getTextContent();
		String AccNo_dbtr = doc.getElementsByTagName("AccNo").item(0).getTextContent();
		String Acct_Type = doc.getElementsByTagName("Acct_Type").item(0).getTextContent();
		String Cons_Ref_No = doc.getElementsByTagName("Cons_Ref_No").item(0).getTextContent();
		String Phone = doc.getElementsByTagName("Phone").item(0).getTextContent();
		String Mobile = doc.getElementsByTagName("Mobile").item(0).getTextContent();
		String Email = doc.getElementsByTagName("Email").item(0).getTextContent();
		String Pan = doc.getElementsByTagName("Pan").item(0).getTextContent();

		dbtr.setNm(Nm_dbtr);
		dbtr.setAccNo(AccNo_dbtr);
		dbtr.setAcct_Type(Acct_Type);
		dbtr.setCons_Ref_No(Cons_Ref_No);
		dbtr.setPhone(Phone);
		dbtr.setMobile(Mobile);
		dbtr.setEmail(Email);
		dbtr.setPan(Pan);

		CrAccDtl crAccDtl = new CrAccDtl();

		String Nm_crAcDet = doc.getElementsByTagName("Nm").item(1).getTextContent();
		String AccNo_crAcDet = doc.getElementsByTagName("AccNo").item(1).getTextContent();
		String MmbId = doc.getElementsByTagName("MmbId").item(0).getTextContent();

		crAccDtl.setNm(Nm_crAcDet);
		crAccDtl.setAccNo(AccNo_crAcDet);
		crAccDtl.setMmbId(MmbId);

		mndt.setDbtr(dbtr);
		mndt.setCrAccDtl(crAccDtl);

		////////////////////////////////////////////////

		authReq.setGrpHdr(grpHdr);
		authReq.setMndt(mndt);

		return authReq;
	}

	public boolean validateMandateReq(MndtAuthReq req) {
		boolean isReqValid = true;

		if (req != null) {
			if (req.getGrpHdr() != null) {
				GrpHdr grpHdr = req.getGrpHdr();
				if (grpHdr.getNPCI_RefMsgId().isEmpty() || grpHdr.getNPCI_RefMsgId() == null) {
					isReqValid = false;
				} else {
					if(grpHdr.getNPCI_RefMsgId().length()>35) {
						isReqValid = false;
					}
				}
				
				if (grpHdr.getCreDtTm() == null) {
					isReqValid = false;
				} else {
					if(grpHdr.getCreDtTm().length()>25) {
						isReqValid = false;
					}
				}
				
				if (grpHdr.getReqInitPty() != null) {
					if (grpHdr.getReqInitPty().getInfo() != null) {
						Info info = grpHdr.getReqInitPty().getInfo();
						if (info.getCatCode() == null || info.getCatCode().isEmpty()) {
							isReqValid = false;
						}
						
						if (info.getCatDesc() == null || info.getCatDesc().isEmpty()) {
							isReqValid = false;
						}
						
						if (info.getId() == null || info.getId().isEmpty()) {
							isReqValid = false;
						} else {
							if(info.getId().length()>18) {
								isReqValid = false;
							}
						}
						
						if (info.getName() == null || info.getName().isEmpty()) {
							isReqValid = false;
						}
						
						if (info.getSpn_Bnk_Nm() == null || info.getSpn_Bnk_Nm().isEmpty()) {
							isReqValid = false;
						}
						
						if (info.getUtilCode() == null) {
							isReqValid = false;
						}
					} else {
						isReqValid = false;
					}
				} else {
					isReqValid = false;
				}
			} else {
				isReqValid = false;
			}

			if (req.getMndt() != null) {
				Mndt mndt = req.getMndt();
				if (mndt.getMndtReqId() == null || mndt.getMndtReqId().isEmpty()) {
					isReqValid = false;
				}
				if (mndt.getMndtId() == null || mndt.getMndtId().isEmpty()) {
					isReqValid = false;
				}
				if (mndt.getMndt_Type() == null || mndt.getMndt_Type().isEmpty()) {
					isReqValid = false;
				}

				if (mndt.getOcrncs() != null) {
					Ocrncs ocrncs = mndt.getOcrncs();
					if (ocrncs.getSeqTp() == null || ocrncs.getSeqTp().isEmpty()) {
						isReqValid = false;
					}
				} else {
					isReqValid = false;
				}

				if (mndt.getDbtr() != null) {
					Dbtr dbtr = mndt.getDbtr();
					if (dbtr.getNm() == null || dbtr.getNm().isEmpty()) {
						isReqValid = false;
					}
					if (dbtr.getAccNo() == null || dbtr.getAccNo().isEmpty()) {
						isReqValid = false;
					}
					if (dbtr.getAcct_Type() == null || dbtr.getAcct_Type().isEmpty()) {
						isReqValid = false;
					}
				} else {
					isReqValid = false;
				}

				if (mndt.getCrAccDtl() != null) {
					CrAccDtl crAccDtl = mndt.getCrAccDtl();
					if (crAccDtl.getNm() == null || crAccDtl.getNm().isEmpty()) {
						isReqValid = false;
					}
					if (crAccDtl.getAccNo() == null || crAccDtl.getAccNo().isEmpty()) {
						isReqValid = false;
					}
					if (crAccDtl.getMmbId() == null || crAccDtl.getMmbId().isEmpty()) {
						isReqValid = false;
					}

				} else {
					isReqValid = false;
				}
			} else {
				isReqValid = false;
			}
		} else {
			isReqValid = false;
		}

		return isReqValid;
	}

	private String getResource(String filename) throws FileNotFoundException {
		URL resource = getClass().getClassLoader().getResource(filename);
		Objects.requireNonNull(resource);

		return resource.getFile();
	}
}
