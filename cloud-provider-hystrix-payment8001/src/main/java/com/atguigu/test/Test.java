package com.atguigu.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i-> i*i).distinct().collect(Collectors.toList());

        System.out.println(distinct);

        IntSummaryStatistics statistics = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getAverage());


    }

    public static void filter(List names, Predicate condition){
        for (Object str : names){
            if (condition.test(str)){
                System.out.println(str +" ");
            }
        }
    }

}
