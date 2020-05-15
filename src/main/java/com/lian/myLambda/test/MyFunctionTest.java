package com.lian.myLambda.test;

import com.lian.myLambda.customizeInterface.MyFunction;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/14 21:42
 */
public class MyFunctionTest {

    public static Integer operation(Integer num, MyFunction myFunction){
        return myFunction.getValue(num);
    }

    public static void main(String[] args) {
        Integer operation = operation(200, y -> y*100);
        System.out.println(operation);
    }

}
