package com.cwgj.chartlib.hellocharts.formatter;


import com.cwgj.chartlib.hellocharts.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
