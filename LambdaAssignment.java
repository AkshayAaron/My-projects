package com.zensar;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Order {
    int price;
    String item;
    String status;

    public Order(int price, String item, String status) {
        this.price = price;
        this.item = item;
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", item='" + item + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

class threadNumberPrint implements Runnable{

    List<Integer> numberList;

    public threadNumberPrint(List<Integer> numberList) {
        this.numberList = numberList;
    }

    @Override
    public void run() {
        for(Integer integer:numberList){
            System.out.println(integer);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class LambdaAssignment {
    public static void main(String[] args) {
        Order o1=new Order(3000,"Speaker","Completed");
        Order o2=new Order(5000,"Mouse","Not Accepted");
        Order o3=new Order(10000,"Headset","Accepted");
        Order o4=new Order(15000,"Furniture","Not Accepted");
        Order o5=new Order(20000,"Smartphone","Completed");
        Order o6=new Order(25000,"Refrigerator","Completed");
        Order o7=new Order(30000,"Laptop","Accepted");
        List<Order> orderList=new ArrayList<Order>();
        orderList.add(o1);
        orderList.add(o2);
        orderList.add(o3);
        orderList.add(o4);
        orderList.add(o5);
        orderList.add(o6);
        orderList.add(o7);

        //Write an application using lambda expressions to print Orders having 2 criteria implemented:
        //1) order price more than 10000
        Predicate<Order> priceFilter=(Order o)->o.getPrice()>10000;
        List<Order> filteredPriceOrders=filterOrder(orderList,priceFilter);
        //printList(filteredPriceOrders);

        //2) order status is ACCEPTED or COMPLETED.  Q3)Predicate Test
        Predicate<Order> checkStatus=(Order o)->(o.getStatus().equals("Accepted")||o.getStatus().equals("Completed"));
        List<Order> filteredStatusOrders=filterOrder(orderList,checkStatus);
        //printList(filteredStatusOrders);

        //Q3)Consumer Test
        Consumer<Order> orderConsumer=(Order o)-> System.out.println("Name = "+o.getItem()+" Price = "+o.getPrice());
        //consumerPrintList(orderList,orderConsumer);

        //Q3)Supplier Test
        Supplier<String> stringSupplier=()->"Laptop";
        //supplierPrint(orderList,stringSupplier);

        //Q3)Function Test
        Function<Order,Integer> stringOrderFunction=(Order o)->o.getPrice();
        //System.out.println("Order Price of Furniture = "+stringOrderFunction.apply(o4));

        //Q4)Remove the words that have odd lengths from the list.
        // HINT: Use one of the new methods from JDK 8. Use removeIf() method from Collection interface.
        //printList(deleteOddNames(orderList));

        //Q5)Create a string that consists of the first letter of each word in the list of Strings provided.
        // HINT: Use Consumer interface & a StringBuilder to construct the result.
        StringBuilder appendString=new StringBuilder("Appended First Letters: ");
        Consumer<Order> appendConsumer=(Order o)->{
            appendString.append(o.getItem().charAt(0));
        };
        consumerPrintList(orderList,appendConsumer);
        System.out.println(appendString);

        //Q6)Replace every word in the list with its upper case equivalent.
        //Use replaceAll() method & UnaryOperator interface.
       //System.out.println(replaceUpperNames(orderList));

        //Q7)Convert every key-value pair of the map into a string and append them all into a single string,
        // in iteration order. HINT: Use Map.entrySet() method & a StringBuilder to construct the result String.
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("Akshay","Aaron");
        map.put("Abhishek","Golkonda");
        map.put("Vincy","Sohan");
        map.put("Sahithya","Kezia");
        map.put("Mike","Tyson");
        Set<Map.Entry<String,String>> entries= map.entrySet();
        /*for (Map.Entry<String,String> entry:entries){
            System.out.println("Key = "+entry.getKey()+" Value = "+entry.getValue());
        }*/
        /*Consumer<Map.Entry<String,String>> convertHash=(Map.Entry<String,String> map1)->{
            StringBuilder s=new StringBuilder((String) map1.getKey());
            s.append(map1.getValue());
            System.out.println(s);
        };
        entries.forEach(convertHash);*/
        /*entries.forEach(str->{
            StringBuilder s=new StringBuilder(str.getKey());
            s.append(str.getValue());
            System.out.println(s);
        });*/
        //System.out.println(convertHashMap(map));//Traditional Method

        //Q8)Create a new thread that prints the numbers from the list. Use class Thread & interface Consumer.
        List<Integer> numberList=new ArrayList<Integer>();
        for (int i=1;i<=10;i++)
            numberList.add(i);
        //threadNumberPrint numberPrint=new threadNumberPrint(numberList);
        Runnable runnable= () ->{
            threadNumberPrint threadNumberPrint=new threadNumberPrint(numberList);
            threadNumberPrint.run();
        };
        Thread t=new Thread(runnable);
        //t.start();
    }

    public static void printList(List<Order> orderList){
        for(Order order:orderList)
            System.out.println(order);
    }

    public static List<Order> filterOrder(List<Order> orderList, Predicate<Order> predicate){
        List<Order> newOrderList=new ArrayList<Order>();
        for (Order order:orderList)
            if(predicate.test(order))
                newOrderList.add(order);
        return newOrderList;
    }

    public static void consumerPrintList(List<Order> orderList,Consumer<Order> consumer){
        for(Order order:orderList)
            consumer.accept(order);
    }
    public static void supplierPrint(List<Order> orderList,Supplier<String> stringSupplier){
        for(Order order:orderList)
            if(order.getItem().equals(stringSupplier.get()))
                System.out.println(order);
    }
    public static List<Order> deleteOddNames(List<Order> ol){
        ol.removeIf(o->!(o.getItem().length()%2==0));
        return ol;
    }

    public static List<String> replaceUpperNames(List<Order> ol){
        List<String> namesList=new ArrayList<String>();
        for (Order order:ol)
            namesList.add(order.getItem());
        namesList.replaceAll(name->name.toUpperCase());
        return namesList;
    }
   /* public static List<String> convertHashMap(Map<String,String> map){
        List<String> convertedList=new ArrayList<String>();
        for (Map.Entry<String,String> entry:map.entrySet()){
            StringBuilder s=new StringBuilder(entry.getKey());
            s.append(entry.getValue());
            convertedList.add(String.valueOf(s));
        }
        return convertedList;

    }*/

}
