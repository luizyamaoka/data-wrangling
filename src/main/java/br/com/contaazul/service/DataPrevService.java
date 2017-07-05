package br.com.contaazul.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import br.com.contaazul.model.Municipality;
import br.com.contaazul.model.UnableToCreateFileException;
import br.com.contaazul.model.UnavailableAPIException;

import com.opencsv.CSVReader;

public class DataPrevService {

	private static final String DATA_PREV_API_URL = "http://api.dataprev.gov.br/previdencia/anuario/%s/acidentes-do-trabalho.csv";
	private static final String OUTPUT_FILE_NAME = "%s_acidentes-do-trabalho.csv";
	
	public void downloadData(int year) {
		
		Client client = ClientBuilder.newClient();

		WebTarget resource = client.target(String.format(DATA_PREV_API_URL, String.valueOf(year)));

		Builder request = resource.request();
		request.accept(MediaType.TEXT_PLAIN);

		Response response = request.get();

		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {

		    response.bufferEntity();
		    String responseBody = response.readEntity(String.class);
		    
		    try {
			    PrintWriter csvOutput = new PrintWriter(String.format(OUTPUT_FILE_NAME, String.valueOf(year)));
			    csvOutput.print(responseBody);
			    csvOutput.close();
		    } catch (FileNotFoundException e) {
		    	throw new UnableToCreateFileException("File " + String.format(OUTPUT_FILE_NAME, String.valueOf(year)) + " could not be created");
		    }
		    
		} else {
		    throw new UnavailableAPIException("API " + String.format(DATA_PREV_API_URL, String.valueOf(year)) + " returned response status " + response.getStatus());
		}
		
	}
	
	public List<Municipality> deserializeCsv(int year) {
		
		List<Municipality> municipalities = new ArrayList<Municipality>();
		
		try {
			CSVReader reader = new CSVReader(new FileReader(String.format(OUTPUT_FILE_NAME, String.valueOf(year))));
			
			// header
			reader.readNext();
			
			String[] line;
			while ((line = reader.readNext()) != null) {
				Municipality municipality = new Municipality();
				
				municipality.setYear(Integer.valueOf(line[0]));
				municipality.setId(Integer.valueOf(line[1]));
				municipality.setName(line[2]);
				municipality.setState(line[3]);
				municipality.setAccidentsWithCategoryTypical(Integer.valueOf(line[4]));
				municipality.setAccidentsWithCategoryPath(Integer.valueOf(line[5]));
				municipality.setAccidentsWithCategoryWorkRelated(Integer.valueOf(line[6]));
				municipality.setDeathQuantity(Integer.valueOf(line[7]));
				municipality.setAccidentsWithoutCategory(Integer.valueOf(line[8]));
				
				municipalities.add(municipality);
			}
			
			reader.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return municipalities;
		
	}
	
}
