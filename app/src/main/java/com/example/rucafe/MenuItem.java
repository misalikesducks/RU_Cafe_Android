package com.example.rucafe;

/**
 * MenuItem class is the superclass for the subclasses Donut and Coffee
 * Stored in Order class array.
 * @author Connie Chen, Tiffany Lee
 */
public class MenuItem{
   protected double price;
   protected int quantity;

   public MenuItem(double price, int quantity){
      this.price = price;
      this.quantity = quantity;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public double getPrice(){
      return this.price;
   }

   public double itemPrice(){
      return this.itemPrice();
   }

   /**
    * Overrides default method of toString from Java.Lang.*
    * @return String
    */
   @Override
   public String toString(){
      return "";
   }
}
