package com.mastertheboss.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.mastertheboss.model.Person;

@Named(value = "manager")
@ViewScoped
public class PropertyManager implements Serializable {

	private String name;
	private String surname;
	private int age;
	private String city;
	// Getters/Setters omitted for brevity

	public void save() {
		Person p = new Person(name, surname, age, city);
		cacheList.add(p);
	}

	public void clear() {
		cacheList.clear();
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

	public ArrayList<Person> getCacheList() {
		return cacheList;
	}

	public void setCacheList(ArrayList<Person> cacheList) {
		this.cacheList = cacheList;
	}

	ArrayList<Person> cacheList = new ArrayList();

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