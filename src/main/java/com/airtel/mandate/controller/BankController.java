package com.airtel.mandate.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.mandate.entities.NPCIMandate;
import com.airtel.mandate.entities.NPCIMandate_Approved;
import com.airtel.mandate.repository.NPCIMandateApprovedRepository;
import com.airtel.mandate.repository.NPCIMandateTempRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


@RestController
public class BankController {

	private static final Logger log = LoggerFactory.getLogger(BankController.class);
	
	@Autowired
	private NPCIMandateTempRepository tempMandaterepository;
	
	@Autowired
	private NPCIMandateApprovedRepository approvedMandaterepository;

	@GetMapping("/bank/{mandateId}")
	public String getCustomerMandate(@PathVariable("mandateId") String mandateId) throws IOException {

		Optional<NPCIMandate> orderItem = tempMandaterepository.findById(Long.parseLong(mandateId));

		XmlMapper xmlMapper = new XmlMapper();
		JsonNode node = xmlMapper.readTree(orderItem.get().getMandateInfo().getBytes());

		ObjectMapper jsonMapper = new ObjectMapper();
		String json = jsonMapper.writeValueAsString(node);

		return json;

	}
	
	
	
	@PostMapping("/bank/mandate/{mandateId}/{mandateStatus}")
	public String mandateApproval(@PathVariable("mandateId") String mandateId, @PathVariable("mandateStatus") String mandateStatus)  {
		
		log.info(mandateStatus);
		Optional<NPCIMandate> mandate = tempMandaterepository.findById(Long.parseLong(mandateId));
		NPCIMandate_Approved appovedMandate = new NPCIMandate_Approved(mandate.get().getId(), mandate.get().getMandateInfo());
		String status = "";
		
		if(mandateStatus.equals("1")) {
			approvedMandaterepository.save(appovedMandate);
			
			tempMandaterepository.deleteById(appovedMandate.getId());
			status= "Mandate approved successfully";
		}else if ((mandateStatus=="0")) {
			status=  "Mandate rejected";
		}else {
			status= "Not recognized";
		}
		
		return status;
	
	}
	
	
}
		
		
		
	


	


