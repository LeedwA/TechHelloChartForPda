package com.cwgj.chartlib.hellocharts.listener;


import com.cwgj.chartlib.hellocharts.model.PointValue;
import com.cwgj.chartlib.hellocharts.model.SubcolumnValue;

public interface ComboLineColumnChartOnValueSelectListener extends OnValueDeselectListener {

    public void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

    public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value);

}
