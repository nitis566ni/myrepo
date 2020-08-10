package com.airtel.mandate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.airtel.mandate.entities.NPCIMandate;
import com.airtel.mandate.repository.NPCIMandateTempRepository;

@RestController
public class ManadateController {

	private static final Logger log = LoggerFactory.getLogger(ManadateController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private NPCIMandateTempRepository repository;

	// Save NPCI mandate info
	@PostMapping("/npci/mandate/xml")
	@ResponseStatus(HttpStatus.CREATED)
	String newMandateXML() {

		// Put your mock server url here
		final String uri = "https://e87ad81d-4b86-4134-af61-d017140833fc.mock.pstmn.io/npci";

		RestTemplate restTemplate = new RestTemplate();
		String mandateInfo = restTemplate.getForObject(uri, String.class);

		log.info(mandateInfo);

		NPCIMandate npciMandateDTO = new NPCIMandate(mandateInfo);
		repository.save(npciMandateDTO);
		return mandateInfo;

	}
	
	// Save NPCI mandate info
		@PostMapping("/npci/mandate/invalid")
		@ResponseStatus(HttpStatus.CREATED)
		String invalidMandateXML() {

			// Put your mock server url here
			final String uri = "https://e87ad81d-4b86-4134-af61-d017140833fc.mock.pstmn.io/npci/invalid";

			RestTemplate restTemplate = new RestTemplate();
			String mandateInfo = restTemplate.getForObject(uri, String.class);

			log.info(mandateInfo);

			NPCIMandate npciMandateDTO = new NPCIMandate(mandateInfo);
			repository.save(npciMandateDTO);
			return mandateInfo;

		}

}
