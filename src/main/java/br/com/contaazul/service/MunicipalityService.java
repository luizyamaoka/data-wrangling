package br.com.contaazul.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.contaazul.model.Municipality;

public class MunicipalityService {

	public void findTop3WorkRelatedAccidentsIncreaseByState(List<Municipality> municipalitiesBefore, List<Municipality> municipalitiesAfter) {
		
		// Use only municipalities that existed before, because if it didn't exist before I can't calculate an increase
		for (Municipality m : municipalitiesBefore) {
			int workRelatedAccidents2007 = municipalitiesAfter
					.stream()
					.filter(p -> p.getId() == m.getId())
					.mapToInt(p -> p.getAccidentsWithCategoryWorkRelated())
					.sum();
			
			m.setWorkRelatedAccidentsIncrease(100.0 * workRelatedAccidents2007 / m.getAccidentsWithCategoryWorkRelated());
		}
		
		// Sort List in descending order
		municipalitiesBefore.sort((m1, m2) -> m2.getWorkRelatedAccidentsIncrease().compareTo(m1.getWorkRelatedAccidentsIncrease()));
		
		// Find all states
		List<String> states = municipalitiesBefore.stream().map(m -> m.getState()).distinct().collect(Collectors.toList());
		
		// Print top 3 cities by state
		for (String state : states) {
			System.out.println("=== " + state + " ===");
			List<Municipality> topMunicipalitiesInState = municipalitiesBefore
					.stream()
					.filter(p -> p.getState().equals(state))
					.limit(3)
					.collect(Collectors.toList());
			
			for (Municipality m : topMunicipalitiesInState) {
				System.out.println(m.getName() + "\t" + m.getWorkRelatedAccidentsIncrease());
			}
			
			System.out.println();
		}
	}
	
}
