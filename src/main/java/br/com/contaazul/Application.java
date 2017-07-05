package br.com.contaazul;

import java.util.List;

import br.com.contaazul.model.Municipality;
import br.com.contaazul.service.DataPrevService;
import br.com.contaazul.service.MunicipalityService;

public class Application {

	public static void main(String[] args) {
		
		DataPrevService dataPrevService = new DataPrevService();
		
		// Download CSV files from Dataprev API
		for (int year = 2002; year <= 2009; year++)
			dataPrevService.downloadData(year);
		
		// Deserialize CSV to list of Municipalities
		List<Municipality> municipalities2003 = dataPrevService.deserializeCsv(2003);
		List<Municipality> municipalities2007 = dataPrevService.deserializeCsv(2007);
		
		// Find top 3 cities, which grew work related accidents by state
		MunicipalityService municipalityService = new MunicipalityService();
		municipalityService.findTop3WorkRelatedAccidentsIncreaseByState(municipalities2003, municipalities2007);
		
	}
	
}
