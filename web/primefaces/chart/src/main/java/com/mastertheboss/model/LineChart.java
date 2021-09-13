package com.mastertheboss.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

import org.primefaces.model.charts.*;
import org.primefaces.model.charts.line.*;
import org.primefaces.model.charts.optionconfig.title.Title;

@Model
public class LineChart {
    private LineChartModel lineModel;

    @PostConstruct
    public void init() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();
        
        LineChartDataSet dataSet = new LineChartDataSet();
        List values = new ArrayList<>();
        values.add(15);
        values.add(2);
        values.add(10);
        values.add(25);
        values.add(19);
        values.add(23);
 
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Acme Gross Incomes");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);
        
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
 
        data.setLabels(labels);
        
        //Options
        LineChartOptions options = new LineChartOptions();        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Line Chart");
        options.setTitle(title);
        
        lineModel.setOptions(options);
        lineModel.setData(data);
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }
}
