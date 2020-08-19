package com.ystartor.jdk8;

import com.sun.deploy.util.StringUtils;
import com.ystartor.jdk8.entity.Trader;
import com.ystartor.jdk8.entity.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        Trader one = new Trader("one", "one");
        Trader two = new Trader("two", "two");
        Trader three = new Trader("three", "three");
        Trader four = new Trader("four", "four");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(one, 2011, 300),
                new Transaction(one, 2012, 400),
                new Transaction(two, 2011, 200),
                new Transaction(three, 2011, 500),
                new Transaction(one, 2013, 200),
                new Transaction(four, 2011, 400),
                new Transaction(one, 2012, 100),
                new Transaction(three, 2012, 200)
        );

        List<Object> list = transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

        print(list);
        System.out.println("-----------------------");
        List<Object> distinct = transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct().collect(Collectors.toList());

        print(distinct);
        System.out.println("-----------------------");
        List<Object> collect = transactions.stream().map(Transaction::getTrader).filter(trader -> "one".equals(trader.getCity())).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());

        print(collect);
        System.out.println("-----------------------");
        List<Object> collect1 = transactions.stream().map(Transaction::getTrader).map(Trader::getName).sorted().collect(Collectors.toList());

        print(collect1);
        System.out.println("-----------------------");

        boolean b = transactions.stream().map(Transaction::getTrader).anyMatch(trader -> "on2e".equals(trader.getCity()));
        System.out.println(b);
        System.out.println("-----------------------");


        List<Object> collect3 = transactions.stream().filter(transaction ->
            "one".equals(transaction.getTrader().getCity())
        ).map(Transaction::getValue).collect(Collectors.toList());

        print(collect3);


        Optional<Transaction> max = transactions.stream().max(Comparator.comparing(Transaction::getValue));

        Optional<Transaction> min = transactions.stream().min(Comparator.comparing(Transaction::getValue));


    }


    private static void print(List<Object> list){
        for (Object obj : list) {
            System.out.println(obj);
        }
    }


}
