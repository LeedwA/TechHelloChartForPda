package com.cwgj.chartlib.hellocharts.formatter;


import com.cwgj.chartlib.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    public int formatChartValue(char[] formattedValue, PointValue value);
}
