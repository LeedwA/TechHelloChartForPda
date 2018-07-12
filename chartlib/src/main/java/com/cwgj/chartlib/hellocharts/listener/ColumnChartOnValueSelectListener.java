package com.cwgj.chartlib.hellocharts.listener;


import com.cwgj.chartlib.hellocharts.model.SubcolumnValue;

public interface ColumnChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

}
