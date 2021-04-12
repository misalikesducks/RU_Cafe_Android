package com.example.rucafe;

import java.util.ArrayList;

/**
 * Order class representing a list of all MenuItem objects
 * @author Connie Chen, Tiffany Lee
 */
public class Order implements Customizable{
   //keeps the list of menuItems
   protected int orderID;
   protected double subTotal;
   protected double salesTax;
   protected double total;
   protected ArrayList<MenuItem> items;
   protected static int ID_NUMBER = 1;
   public static final double taxRate = 0.06625;

   /**
    * Default Order constructor to create an empty MenuItem ArrayList
    */
   public Order(){
      this.orderID = ID_NUMBER;
      this.subTotal = 0.00;
      this.salesTax = 0;
      this.total = 0.00;
      items = new ArrayList<>();
   }

   /**
    * Accesses the orderID data member of an Order
    * @return int representing orderID of an Order
    */
   public int getOrderID(){
      return this.orderID;
   }

   /**
    * Increments the ID_NUMBER data member of an Order
    */
   public static void incrementIDNumber(){
      ID_NUMBER++;
   }

   /**
    * Accesses the subTotal data member of an Order
    * @return double representing sub total of an Order
    */
   public double getSubTotal(){
      return subTotal;
   }

   /**
    * Accesses the salesTax data member of an Order
    * @return double representing sales tax of an Order
    */
   public double getSalesTax(){
      return salesTax;
   }

   /**
    * Accesses the total data member of an Order
    * @return double representing the total of an Order
    */
   public double getTotal(){
      return total;
   }

   /**
    * Accesses the items data member of an Order
    * @return ArrayList of MenuItem objects in an Order
    */
   public ArrayList<MenuItem> getItems(){
      return items;
   }

   /**
    * Sets the subTotal data member of an Order
    */
   public void setSubTotal(){
      this.subTotal = 0;
      for(MenuItem currentItem: items){
         this.subTotal += currentItem.getPrice();
      }
   }

   /**
    * Sets the salesTax data member of an Order
    */
   public void setSalesTax(){
      this.salesTax = subTotal * taxRate;
   }

   /**
    * Sets the total data member of an Order
    */
   public void setTotal(){
      this.total = subTotal + salesTax;
   }

   /**
    * Inserts a MenuItem Object in the items ArrayList
    * @param obj to be added to the items Arraylist of Order
    * @return true if the obj was added, false otherwise
    */
   public boolean add(Object obj){
      if(obj instanceof MenuItem){
         if(this.items.add((MenuItem) obj)){
            return true;
         }
      }
      return false;
   }

   /**
    * Removes the given MenuItem object from the items ArrayList
    * @param obj to be removed
    * @return true if the obj was removed, false otherwise
    */
   public boolean remove(Object obj){
      if(obj instanceof MenuItem){
         return this.items.remove(obj);
      }
      return false;
   }

   /**
    * Returns a String of all MenuItem Objects in the items ArrayList
    * @return String
    */
   public String print(){
      String allOrders = "" + orderID + ": ";

      for(int i = 0; i < items.size(); i++){
         allOrders += items.get(i).toString();
         if(i != items.size()-1)
            allOrders += ", ";
      }

      return allOrders;
   }
}
