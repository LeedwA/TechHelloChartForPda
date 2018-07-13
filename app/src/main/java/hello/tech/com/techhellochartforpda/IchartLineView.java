package hello.tech.com.techhellochartforpda;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cwgj.chartlib.hellocharts.gesture.ContainerScrollType;
import com.cwgj.chartlib.hellocharts.listener.LineChartOnValueSelectListener;
import com.cwgj.chartlib.hellocharts.model.Axis;
import com.cwgj.chartlib.hellocharts.model.AxisValue;
import com.cwgj.chartlib.hellocharts.model.Line;
import com.cwgj.chartlib.hellocharts.model.LineChartData;
import com.cwgj.chartlib.hellocharts.model.PointValue;
import com.cwgj.chartlib.hellocharts.model.ValueShape;
import com.cwgj.chartlib.hellocharts.model.Viewport;
import com.cwgj.chartlib.hellocharts.util.ChartUtils;
import com.cwgj.chartlib.hellocharts.view.LineChartView;

import java.util.ArrayList;
import java.util.List;


/**
 * +----------------------------------------------------------------------
 * |  说明     ：日 月 折线图
 * +----------------------------------------------------------------------
 * | 创建者   :  ldw
 * +----------------------------------------------------------------------
 * | 时　　间 ：2018/1/26 15:55
 * +----------------------------------------------------------------------
 * | 版权所有: 北京市车位管家科技有限公司
 * +----------------------------------------------------------------------
 **/
public class IchartLineView extends RelativeLayout {

    public static final int PART = 4;//Y轴四等分

    public static final int TYPE_DAY   = 0;
    public static final int TYPE_MONTH = 1;
    public static final int TYPE_PARKING_FEE_REPORT = 2;
    public static final int TYPE_MONTH_CARD_REPORT_1 = 3;
    public static final int TYPE_MONTH_CARD_REPORT_2 = 4;
    public static final int TYPE_PARK_COUPON_REPORT_1 = 5;
    public static final int TYPE_PARK_COUPON_REPORT_2 = 6;
    public static final int TYPE_PARK_WORKERS_REPORT = 7;


    LineChartView chart_line;

    TextView tv_time, tv_content;

    RelativeLayout rl_content;

    float yMaxValue = 0; //y轴数值

    List<String> xList  = new ArrayList<>(); //x轴数据
    List<String> yList  = new ArrayList<>(); //y轴数据

    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

    private LineChartData data;

    int type;

    Handler mHandler = new Handler();

    public IchartLineView(Context context) {
        super(context);
        initView();
    }

    public IchartLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public IchartLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_line_chart, this);
        chart_line = rootView.findViewById(R.id.chart_line);
        chart_line.setVisibility(View.GONE);
        tv_time = rootView.findViewById(R.id.tv_time);
        tv_content = rootView.findViewById(R.id.tv_content);
        rl_content = rootView.findViewById(R.id.rl_content);
        rl_content.setVisibility(View.GONE);
//        chart_line.setScrollEnabled(false);
//        chart_line.setOnTouchListener(new MyOntouch());
        chart_line.setOnValueTouchListener(new LineChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                rl_content.setVisibility(View.VISIBLE);
                String name ="";
                String content ="";
                if(type == 0 || type == 1){
                    name = xList.get(pointIndex);
                    content = "总收入: " +yList.get(pointIndex) +"元";
                }else if(type == TYPE_PARKING_FEE_REPORT || type == TYPE_PARK_WORKERS_REPORT){
                    name = "应收金额";
                    content = yList.get(pointIndex) +"元";
                }else if(type == TYPE_MONTH_CARD_REPORT_1){
                    name = "金额";
                    content = yList.get(pointIndex) +"元";
                }else if(type == TYPE_MONTH_CARD_REPORT_2){
                    name = "笔数";
                    content = yList.get(pointIndex);
                }else if(type == TYPE_PARK_COUPON_REPORT_1){
                    name = "结算金额";
                    content = yList.get(pointIndex) +"元";
                }else if(type == TYPE_PARK_COUPON_REPORT_2){
                    name = "数量";
                    content = yList.get(pointIndex);
                }
                tv_time.setText(name);
                tv_content.setText(content);
                mHandler.removeCallbacksAndMessages(null);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rl_content.setVisibility(View.GONE);
                    }
                }, 2000);
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

    class MyOntouch implements OnTouchListener {

        float downX;
        float downY;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            int action = motionEvent.getAction();

            if(action == MotionEvent.ACTION_DOWN){
                downX =  motionEvent.getX();
                downY =  motionEvent.getY();
            }else if(action == MotionEvent.ACTION_MOVE){

                float distanceX = Math.abs(motionEvent.getX() - downX);
                float distanceY = Math.abs(motionEvent.getY() - downY);
                if(distanceX < distanceY) {
                    Log.d("44444", "onTouch: 可以上下滑yyyyyy" );
                    chart_line.setScrollEnabled(false);
                    return false;
                }

            }else if(action == MotionEvent.ACTION_UP){

            }
            Log.d("44444", "onTouch: 可以上下滑xxxx" );
            chart_line.setScrollEnabled(true);
            return false;
        }
    }

    public void removeHandler(){
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }


    /**
     * 数据赋值，绘画折线图
     * @param type 0 日报表 1 月报表
     * @param yMaxValue
     * @param xList
     * @param yList
     */
    public void setData(int type, float yMaxValue, List<String> xList, List<String> yList){
        chart_line.setVisibility(View.VISIBLE);
        this.type = type;
//        this.yMaxValue = yMaxValue;
        this.yMaxValue = (float) Math.ceil(yMaxValue); //四舍五入取整
        this.xList = xList;
        this.yList = yList;
        mAxisXValues.clear();

        resetViewport();
        generateData();
        resetViewport();

        if(xList!=null && xList.size() >6 && yList!=null && yList.size() >6){
            //默认滑动到最后
            chart_line.moveTo(xList.size() - 4, 0);
        }
    }

    /**
     * 设置图片的x，y 轴显示
     */
    private void resetViewport() {

        chart_line.setInteractive(true);
        chart_line.setZoomEnabled(false);
        chart_line.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

        chart_line.setViewportCalculationEnabled(false);
        Viewport v = new Viewport(chart_line.getMaximumViewport());
        v.bottom = 0;
        v.top = yMaxValue;
        v.left = 0;
        //默认x轴一屏显示6的坐标
        v.right = 5;
        chart_line.setCurrentViewport(v);

        Viewport maxV = new Viewport(chart_line.getMaximumViewport());
        maxV.bottom = 0;
        maxV.top = yMaxValue;
        maxV.left = 0;
        if(xList!=null && xList.size() > 5){
            maxV.right = (float) (xList.size() - 0.5);
        }else {
            //小于6个坐标值显示6个
            maxV.right = 5;
        }

        chart_line.setMaximumViewport(maxV);
    }

    //        初始化配置 和 xy轴数据
    private void generateData() {
        chart_line.setYAxisRange((int) (yMaxValue/PART));

        List<Line> lines = new ArrayList<Line>();
        List<PointValue> pointValues = new ArrayList<PointValue>();
        Line line = null;
        for (int j = 0; j < xList.size(); ++j) {
            float yValue = Float.parseFloat(yList.get(j));
            if(yValue > yMaxValue){
                //带有小数点的值，如果数值大于最大值整数，重置到最大值
                yValue = yMaxValue;
            }else if( yValue < yMaxValue/100){
                //如果最小值很小，同一个数值横线会很小，做个限制
                yValue =  yMaxValue/100;
            }
            pointValues.add(new PointValue(j, yValue));
            mAxisXValues.add(new AxisValue(j).setLabel(xList.get(j)));
        }
        line = new Line(pointValues);
        line.setColor(ChartUtils.COLORS[0]); //line的颜色值
        line.setShape(ValueShape.CIRCLE); //line数值点的样式
        line.setPointRadius(3); //line 数值点的圆圈半径
        line.setStrokeWidth(2);//line的宽度dp
        line.setCubic(false);
        line.setFilled(true); //line下有蒙层
        line.setHasLabels(false);
        line.setHasLabelsOnlyForSelected(false);
        line.setHasLines(true); //点之间是否有连线
        line.setHasPoints(true); //数值点是否圆圈点
        line.setHasLabelsOnlyForSelected(false);
        lines.add(line);
        data = new LineChartData(lines);
//            设置X，Y轴
        Axis axisX = new Axis();
        axisX.setTextSize(10); // x 轴字体大小
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        axisX.setMaxLabelChars(5); //字符个数
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        axisX.setHasLines(false); //x 轴分割线
        axisX.setHasSeparationLine(false); //x轴不显示

        Axis axisY = new Axis();
        axisY.setTextColor(Color.GRAY);  //设置字体颜色
        axisY.setMaxLabelChars(String.valueOf(yMaxValue).length() + 1); //字符个数
        axisY.setHasLines(true);
        axisY.setHasSeparationLine(false); //y轴不显示
        axisY.setTextSize(10);
        axisY.setHasTiltedLabels(false);  //y坐标轴字体是斜的显示还是直的，true是斜的显示(y轴斜的显示有问题)
        List<AxisValue> Yvalues = new ArrayList<>();
        //y 轴坐标值
        for (float i = 0; i <= yMaxValue; i += yMaxValue/PART) {
            AxisValue value = new AxisValue(i);
            String label = i + "";
            value.setLabel(label);
            Yvalues.add(value);
        }
        axisY.setValues(Yvalues);

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart_line.setLineChartData(data);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            return false;
        } else {
            return true;
        }
    }



}
