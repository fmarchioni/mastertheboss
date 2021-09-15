package com.mastertheboss.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
@Named
@RequestScoped
public class AutoCompleteBean {

	private String text1;
	private String text2;

	public List<String> completeText(String query) {
		String queryLowerCase = query.toLowerCase();
		List<String> countryList = new ArrayList<>();
		List<Country> countries = getCountries();

		for (Country country : countries) {
			countryList.add(country.getName());
		}

		return countryList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
	}

	private List<Country> getCountries() {
		List<Country> countries = new ArrayList<Country>();

		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			try {
				String iso = locale.getISO3Country();
				String code = locale.getCountry();
				String name = locale.getDisplayCountry();

				if (!"".equals(iso) && !"".equals(code) && !"".equals(name)) {
					countries.add(new Country(iso, code, name));
				}
			} catch (MissingResourceException ex) {
				ex.printStackTrace();
			}

		}

		return countries; 
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}





}






