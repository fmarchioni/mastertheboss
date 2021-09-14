package com.sample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import model.People;


@ManagedBean
public class TableBean implements Serializable {

    private List<ColumnModel> columns;
    private List<People> people;

    private String columnName;

    public TableBean() {
        createDynamicColumns();
        addPeople();
    }

    private void addPeople() {
        people = new ArrayList<People>();

        People w1 = new People("Homer Simpson","The father",48);
        People w2 = new People("Marge Simpson","The mother",46);
        People w3 = new People("Bart Simpson","Oldest child",11);
        People w4 = new People("Lisa Simpson","Sister of Bart",8);

        people.add(w1);
        people.add(w2);
        people.add(w3);
        people.add(w4);
    }

    private void createDynamicColumns() {

        columns = new ArrayList<ColumnModel>();
        columns.add(new ColumnModel("Name", "name"));
        columns.add(new ColumnModel("Role", "role"));
        columns.add(new ColumnModel("Age", "age"));
    }

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    static public class ColumnModel implements Serializable {

        private String header;
        private String property;

        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }
    }
}