package com.cwgj.chartlib.hellocharts.listener;


import com.cwgj.chartlib.hellocharts.model.BubbleValue;


public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int bubbleIndex, BubbleValue value);

}
