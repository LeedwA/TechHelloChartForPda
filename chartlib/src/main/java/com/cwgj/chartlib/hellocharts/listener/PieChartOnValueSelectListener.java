package com.cwgj.chartlib.hellocharts.listener;


import com.cwgj.chartlib.hellocharts.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int arcIndex, SliceValue value);

}
