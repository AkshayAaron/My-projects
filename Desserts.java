package com.zensar;

import java.util.Scanner;

abstract class DessertItem{
    abstract int getCost();
    abstract int getStock();
    abstract void addStock(int x);
    abstract void removeStock(int x);
}

class Candy extends DessertItem{
    int price=10;
    int stock=10;
    @Override
    int getStock() {
        return stock;
    }

    @Override
    int getCost() {
        return price;
    }

    @Override
    void addStock(int x) {
        stock+=x;
    }

    @Override
    void removeStock(int x) {
        stock-=x;
    }
}

class Cookie extends DessertItem{
    int price = 20;
    int stock = 10;
    @Override
    int getCost() {
        return price;
    }

    @Override
    int getStock() {
        return stock;
    }

    @Override
    void addStock(int x) {
        stock+=x;
    }

    @Override
    void removeStock(int x) {
        stock-=x;
    }
}

class IceCream extends DessertItem{
    int price = 30;
    int stock = 10;
    @Override
    int getCost() {
       return price;
    }

    @Override
    int getStock() {
        return stock;
    }

    @Override
    void addStock(int x) {
        stock+=x;
    }

    @Override
    void removeStock(int x) {
        stock-=x;
    }
}

public class Desserts {
    public static DessertItem ca=new Candy();
    public static DessertItem co=new Cookie();
    public static DessertItem i=new IceCream();
    public static void main(String[] args) {
        UserInterface();
    }
    public static void order(int candy,int cookie, int ice){
            if(candy>0){
                if(ca.getStock()>candy){
                    int x=ca.getCost();
                    System.out.println(candy+" Candy cost is\n" +
                            "Price in Dollars: "+x*candy+
                            " Price in Rupees: "+x*60*candy+
                            " Price in Euros: "+ x*1.166*candy);
                    ca.removeStock(candy);
                }
                else
                    System.out.println("Candy Stock not available.");
            }
            if(cookie>0){

                if(co.getStock()>cookie){
                    int x=co.getCost();
                    System.out.println(cookie+" Cookie cost is\n" +
                            "Price in Dollars: "+x*cookie+
                            " Price in Rupees: "+x*60*cookie+
                            " Price in Euros: "+ x*1.166*cookie);
                    co.removeStock(cookie);
                }
                else
                    System.out.println("Cookie Stock not available.");
            }
            if(ice>0){

                if(i.getStock()>ice){
                    int x=i.getCost();
                    System.out.println(ice+" IceCream cost is\n" +
                            "Price in Dollars: "+x*ice+
                            " Price in Rupees: "+x*60*ice+
                            " Price in Euros: "+ x*1.166*ice);
                    i.removeStock(ice);
                }
                else
                    System.out.println("IceCream Stock not available.");
            }

    }
    public static void OwnerAdd(int candy,int cookie, int ice){
        ca.addStock(candy);
        co.addStock(cookie);
        i.addStock(ice);
        System.out.println("Current Available Stocks are:");
        System.out.println("Candy: "+ca.getStock());
        System.out.println("Cookie: "+co.getStock());
        System.out.println("IceCream: "+i.getStock());
    }
    public static void UserInterface(){
        Scanner sc=new Scanner(System.in);
        int option;
        System.out.println("Please specify your role(Enter Digit)\n" +
                "1.Customer\n" +
                "2.Owner\n" +
                "3.Quit");
        option=sc.nextInt();
        switch (option){
            case 1:
                System.out.println("Specify the Quantity of the given items:");
                System.out.println("Candy:");
                int candy=sc.nextInt();
                System.out.println("Cookie:");
                int cookie=sc.nextInt();
                System.out.println("IceCream:");
                int ice=sc.nextInt();
                order(candy,cookie,ice);
                UserInterface();
                break;
            case 2:
                System.out.println("Enter quantity of the following items to add: ");
                System.out.println("Candy:");
                int ca=sc.nextInt();
                System.out.println("Cookie:");
                int co=sc.nextInt();
                System.out.println("IceCream:");
                int ic=sc.nextInt();
                OwnerAdd(ca,co,ic);
                UserInterface();
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }
}
