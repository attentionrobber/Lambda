package com.hyunseok.android.lambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    Button btn, btn_call, btn_loop, btn_stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_loop = (Button) findViewById(R.id.btn_loop);
        btn_stream = (Button) findViewById(R.id.btn_stream);


        // 일반적인 사용
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "HIHI", Toast.LENGTH_SHORT).show();
            }
        });

        // Lambda식 사용
        btn.setOnClickListener(
                (v) -> {
                    Toast.makeText(this, "HIHI", Toast.LENGTH_SHORT).show();
                }
        );
        btn.setOnClickListener(
                System.out::println
        );
        // 함수이름 생략
        // (파라미터타입 생략) -> {
        //      실행블럭
        // }
        // (View v) -> { System.out.println(view); }
        // (v) -> { System.out.println(view); }
        // v -> System.out.println(view)
        // System.out::println

        LambdaFunction arg = calc(); // calc()가 호출되면 calc함수에 정의된 람다식이 넘어온다.
        // arg : num -> num * num
        int result = arg.sum(50, 30);
        System.out.println(result);




        String objectArr[] = {"A", "B", "C", "D", "E", "F", "G", "H" };

        btn_loop.setOnClickListener(
                (v) -> {
                    for (String str : objectArr) { // objectArr이 100만개라고 하면 for문을 돌때 100만번을 다 받고나서 loop를 돈다.(완료 후 처리)
                        if(str.length() == 1) {
                            System.out.println("I am "+str);
                        }
                    }
                }
        );
        // Gradle에서 minSdkVersion을 24로 바꿔줘야한다.
        btn_stream.setOnClickListener(
                (v) -> {
                    Stream<String> stream = Arrays.stream(objectArr); // 위의 경우와 반대로 stream은 100만개를 받고 있는 상태이다.
                    stream.filter(a->a.length() == 1); // 그래서 실시간으로 처리 할 수 있다.(진행 중 처리)
                    stream.forEach(a->System.out.println(a));
                }
        );




    }

    public LambdaFunction calc() {
        return (pre, suf) -> { return pre + suf; };
    }

    public LambdaFunction calc2() {
        return new LambdaFunction() {
            @Override
            public int sum(int pre, int suf) {
                return pre + suf;
            }
        };
    }
    interface LambdaFunction {
        public abstract int sum(int pre, int suf);
    }

    public void runLambdaFunction() {

    }
}
