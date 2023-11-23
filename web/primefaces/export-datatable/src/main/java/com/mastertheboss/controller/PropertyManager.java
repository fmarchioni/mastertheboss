package com.mastertheboss.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.mastertheboss.model.Person;
import java.util.List;
import java.util.Random;

@Named(value = "manager")
@ViewScoped
public class PropertyManager implements Serializable {

	private String name;
	private String surname;
	private int age;
	private String city;
	// Getters/Setters omitted for brevity

	@PostConstruct
	public void init() {
		cacheList = generateRandomPeople();
	}

	public void clear() {
		cacheList.clear();
	}

	public List<Person> generateRandomPeople() {
		List<Person> people = new ArrayList<>();
		String[] names = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Isabel", "Jack"};
		String[] surnames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Martinez", "Lee"};
		String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};

		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			String name = names[random.nextInt(names.length)];
			String surname = surnames[random.nextInt(surnames.length)];
			int age = 18 + random.nextInt(50); // Random age between 18 and 67
			String city = cities[random.nextInt(cities.length)];

			Person person = new Person(name, surname, age, city);
			people.add(person);
		}
		return people;
	}
	public void postProcessXLS(Object document) {
		DataFormatter formatter = new DataFormatter(Locale.US);
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		CellStyle style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

		for (Row row : sheet) {
			for (Cell cell : row) {

				switch (cell.getCellType()) {

				case STRING:
					cell.setCellValue(cell.getStringCellValue().toUpperCase());
					cell.setCellStyle(style);
					break;

				}

			}
		}
	}

	public List<Person> getCacheList() {
		return cacheList;
	}

	public void setCacheList(List<Person> cacheList) {
		this.cacheList = cacheList;
	}

	List<Person> cacheList = new ArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}