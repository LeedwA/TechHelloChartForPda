package com.cwgj.chartlib.hellocharts.provider;


import com.cwgj.chartlib.hellocharts.model.LineChartData;

public interface LineChartDataProvider {

    public LineChartData getLineChartData();

    public void setLineChartData(LineChartData data);

}
