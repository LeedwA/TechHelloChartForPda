package com.cwgj.chartlib.hellocharts.formatter;


import com.cwgj.chartlib.hellocharts.model.SliceValue;

public interface PieChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SliceValue value);
}
