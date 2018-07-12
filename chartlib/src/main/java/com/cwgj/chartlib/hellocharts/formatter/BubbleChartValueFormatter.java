package com.cwgj.chartlib.hellocharts.formatter;


import com.cwgj.chartlib.hellocharts.model.BubbleValue;

public interface BubbleChartValueFormatter {

    public int formatChartValue(char[] formattedValue, BubbleValue value);
}
