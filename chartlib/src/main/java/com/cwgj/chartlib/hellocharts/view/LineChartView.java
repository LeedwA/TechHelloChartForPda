package com.cwgj.chartlib.hellocharts.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.cwgj.chartlib.BuildConfig;
import com.cwgj.chartlib.hellocharts.listener.DummyLineChartOnValueSelectListener;
import com.cwgj.chartlib.hellocharts.listener.LineChartOnValueSelectListener;
import com.cwgj.chartlib.hellocharts.model.ChartData;
import com.cwgj.chartlib.hellocharts.model.LineChartData;
import com.cwgj.chartlib.hellocharts.model.PointValue;
import com.cwgj.chartlib.hellocharts.model.SelectedValue;
import com.cwgj.chartlib.hellocharts.provider.LineChartDataProvider;
import com.cwgj.chartlib.hellocharts.renderer.LineChartRenderer;



/**
 * LineChart, supports cubic lines, filled lines, circle and square points. Point radius and stroke width can be
 * adjusted using LineChartData attributes.
 *
 * @author Leszek Wach
 */
public class LineChartView extends AbstractChartView implements LineChartDataProvider {
    private static final String TAG = "LineChartView";
    protected LineChartData data;
    protected LineChartOnValueSelectListener onValueTouchListener = new DummyLineChartOnValueSelectListener();

    private int mTouchSlop;

    public LineChartView(Context context) {
        this(context, null, 0);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setChartRenderer(new LineChartRenderer(context, this, this));
        setLineChartData(LineChartData.generateDummyData());
    }

    @Override
    public LineChartData getLineChartData() {
        return data;
    }

    @Override
    public void setLineChartData(LineChartData data) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Setting data for LineChartView");
        }

        if (null == data) {
            this.data = LineChartData.generateDummyData();
        } else {
            this.data = data;
        }

        super.onChartDataChange();
    }

    @Override
    public ChartData getChartData() {
        return data;
    }

    @Override
    public void callTouchListener() {
        SelectedValue selectedValue = chartRenderer.getSelectedValue();

        if (selectedValue.isSet()) {
            PointValue point = data.getLines().get(selectedValue.getFirstIndex()).getValues()
                    .get(selectedValue.getSecondIndex());
            onValueTouchListener.onValueSelected(selectedValue.getFirstIndex(), selectedValue.getSecondIndex(), point);
        } else {
            onValueTouchListener.onValueDeselected();
        }
    }

    public LineChartOnValueSelectListener getOnValueTouchListener() {
        return onValueTouchListener;
    }

    public void setOnValueTouchListener(LineChartOnValueSelectListener touchListener) {
        if (null != touchListener) {
            this.onValueTouchListener = touchListener;
        }
    }


    //    private float startY;
//    private float startX;
//    // 记录viewPager是否拖拽的标记
//    private boolean mIsVpDragger;
//    private int mTouchSlop;
//
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                // 记录手指按下的位置
//                startY = ev.getY();
//                startX = ev.getX();
//                // 初始化标记
//                mIsVpDragger = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                // 如果viewpager正在拖拽中，那么不拦截它的事件，直接return false；
//                if(mIsVpDragger) {
//                    return false;
//                }
//
//                // 获取当前手指位置
//                float endY = ev.getY();
//                float endX = ev.getX();
//                float distanceX = Math.abs(endX - startX);
//                float distanceY = Math.abs(endY - startY);
//                // 如果X轴位移大于Y轴位移，那么将事件交给viewPager处理。
//                if(distanceY > mTouchSlop && distanceX < distanceY) {
//                    mIsVpDragger = true;
//                    return false;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                // 初始化标记
//                mIsVpDragger = false;
//                break;
//        }
//        // 如果是Y轴位移大于X轴，事件交给swipeRefreshLayout处理。
//        return super.onInterceptTouchEvent(ev);
//    }


    private float startY;
    private float startX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("xxx", "canScrollHorizontally1: ");
//        int action = event.getAction();

//        if(action == MotionEvent.ACTION_DOWN){
//            startX = event.getX();
//            startY = event.getY();
//        }else if(action == MotionEvent.ACTION_MOVE){
//            // 获取当前手指位置
//            float endY = event.getY();
//            float endX = event.getX();
//            float distanceX = Math.abs(endX - startX);
//            float distanceY = Math.abs(endY - startY);
//
//            if( distanceY > mTouchSlop && distanceX < distanceY) {
//                Log.d("11111111111111", "isInteractive: false");
//                return  false;
//            }
//
//        }else if(action == MotionEvent.ACTION_UP){
//
//        }

        return super.onTouchEvent(event);
    }
}
