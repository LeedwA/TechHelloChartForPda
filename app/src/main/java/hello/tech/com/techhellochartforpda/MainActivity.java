package hello.tech.com.techhellochartforpda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    IchartLineView mIchartLineView;

    float maxValue = 4;
    List<String> xList = new ArrayList<>();
    List<String> yList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIchartLineView  = findViewById(R.id.chart_view);

        for (int i=0; i<10; i++){
            xList.add(String.valueOf(i));
        }

        for (int i=0; i<10; i++){
            yList.add("1");
        }

        mIchartLineView.setData(0, maxValue, xList, yList);


    }
}
