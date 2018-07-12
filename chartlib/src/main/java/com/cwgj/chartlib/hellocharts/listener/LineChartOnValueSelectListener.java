package com.cwgj.chartlib.hellocharts.listener;


import com.cwgj.chartlib.hellocharts.model.PointValue;

public interface LineChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int lineIndex, int pointIndex, PointValue value);

}
