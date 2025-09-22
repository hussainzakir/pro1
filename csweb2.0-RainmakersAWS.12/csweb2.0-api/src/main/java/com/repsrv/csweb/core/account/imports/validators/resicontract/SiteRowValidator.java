package com.repsrv.csweb.core.account.imports.validators.resicontract;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.repsrv.csweb.core.model.account.imports.ContainerInformation;
import com.repsrv.csweb.core.model.account.imports.SiteInformation;

public class SiteRowValidator {

	public static List<String> validateRow(SiteInformation row) {
		String csaNum = row.getCsaNumber();
		List<String> errors = new ArrayList<>();
		if(StringUtils.isBlank(csaNum))
			return errors;

		String firstChar = csaNum.substring(0, 1);
		if("A".equalsIgnoreCase(firstChar) || "B".equalsIgnoreCase(firstChar)
				|| "S".equalsIgnoreCase(firstChar)) {
			errors.add("CSA Number cannot begin with A, B or S");
		}

		containerNumberValidator(row, errors);

		return errors;
	}

	private static void containerNumberValidator(SiteInformation row, List<String> errors) {
		List <ContainerInformation> containers = row.getContainers();
		Map<String, List<ContainerInformation>> containerGroups = containers.stream().collect(Collectors.groupingBy(container -> {
			return container.getAccountNumber()+'.'+container.getSiteNumber();
		}));
		containerGroups.entrySet().stream().map(entrySet -> entrySet.getValue()).forEach(containerGroup -> {
			int size = containerGroup.size();
			if (size == 1){
				if (!containerGroup.get(0).getContainerGroupNumber().equals("1")){
					errors.add("Associated Container must have an id of 1");
				}
			} else {
				int max = containerGroup.stream().map(ContainerInformation::getContainerGroupNumber).map(Integer::valueOf).max(Integer::compare).get();
				for (int i = 1; i < max; i++){
					Integer index = Integer.valueOf(i);
					boolean validateSiteNumber = containerGroup.stream().map(ContainerInformation::getContainerGroupNumber).map(Integer::valueOf).anyMatch(siteNumber -> siteNumber.equals(index));
					if (!validateSiteNumber) {
						errors.add("Associated Containers must be sequential starting at 1");
					break;
					}

				}
			}
		});
	}
	
	/**
	 * We want to format the values while we are validating - most efficient
	 * @param row
	 */
	private static void formatValues(SiteInformation row) {
		row.setCompanyNumber(leftPad(row.getCompanyNumber(), 5, " "));
		row.setAccountNumber(leftPad(row.getAccountNumber(), 7, " "));
		row.setSiteNumber(leftPad(row.getSiteNumber(), 5, "0"));
		
	}
}
