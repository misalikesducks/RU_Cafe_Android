package com.example.rucafe;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * StoreOrder representing a list of all Order objects
 * @author Connie Chen, Tiffany Lee
 */
public class StoreOrders implements Customizable{
   protected ArrayList<Order> orders;

   /**
    * Default Order constructor to create an empty Order ArrayList
    */
   public StoreOrders(){
      this.orders = new ArrayList<Order>();
   }

   /**
    * Accesses the orders data member of a StoreOrder
    * @return ArrayList of Order objects
    */
   public ArrayList<Order> getOrders(){
      return orders;
   }

   /**
    * Inserts an Order Object in the orders ArrayList
    * @param obj to be added to the orders Arraylist of StoreOrder
    * @return true if the obj was added, false otherwise
    */
   public boolean add(Object obj){
      if(obj instanceof Order){
         return orders.add((Order) obj);
      }
      return false;
   }

   /**
    * Removes the given Order object from the orders ArrayList
    * @param obj to be removed
    * @return true if the obj was removed, false otherwise
    */
   public boolean remove(Object obj){
      if(obj instanceof Order){
         return orders.remove((Order) obj);
      }
      return false;
   }

   /**
    * Returns a String of all the Order objects in orders ArrayList
    * @return String
    */
   public String print(){
      String allOrders = "";

      for(Order order : orders){
         allOrders += order.print() + "\n";
      }

      return allOrders;
   }

   /**
    * Returns an Order object given an orderID
    * @param ID of Order to be found
    * @return Order
    */
   public Order findOrder(int ID){
      for(int i = 0; i < orders.size(); i++){
         if(orders.get(i).orderID == ID)
            return orders.get(i);
      }
      return null;
   }

   /**
    * Converts a double into a formatted Object that represents currency
    * @param number to be converted to money
    * @return String representing the amount in currency format
    */
   public static String convertToMoney(double number){
      String pattern = "$#,##0.00";
      DecimalFormat formatMoney = new DecimalFormat(pattern);
      return formatMoney.format(number);
   }
}
