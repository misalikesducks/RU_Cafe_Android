package com.example.rucafe;

import java.util.ArrayList;

/**
 * Coffee class creates instances of menuItems that are specific to Coffee
 * Coffee is customizable with add-ins and have different sizes/prices
 * @author Connie Chen, Tiffany Lee
 */
public class Coffee extends MenuItem implements Customizable{
   protected String size;
   protected ArrayList<String> addIns;
   public static final double SHORT = 1.99;
   public static final double TALL = 2.49;
   public static final double GRANDE = 2.99;
   public static final double VENTI = 3.49;
   public static final double ADD_IN = 0.20;
   public static final double EMPTY = 0.0;

   /**
    * Creates a Coffee object
    * @param price of Coffee
    * @param size of Coffee
    * @param quantity of Coffee
    * @param addIns of Coffee
    */
   public Coffee(double price, String size, int quantity, ArrayList<String> addIns) {
      super(price, quantity);
      this.size = size;
      this.addIns = addIns;
   }

   /**
    * Sets the size data member of a Coffee object
    * @param size of a Coffee
    */
   public void setSize(String size) {
      this.size = size;
   }

   /**
    * Overrides super class's itemPrice() method
    * Calculates the price of the Coffee object
    * @return double representing the price
    */
   @Override
   public double itemPrice(){
      double itemPrice = EMPTY;
      switch(this.size){
         case "Short":
            itemPrice += SHORT;
            break;
         case "Tall":
            itemPrice += TALL;
            break;
         case "Grande":
            itemPrice += GRANDE;
            break;
         case "Venti":
            itemPrice += VENTI;
      }
      itemPrice += addIns.size() * ADD_IN;
      itemPrice *= quantity;
      return itemPrice;
   }

   /**
    * Inserts a String object representing an addIn to the addIns ArrayList
    * @param obj to be added to the addIns ArrayList of Coffee
    * @return true if the obj was added, false otherwise
    */
   @Override
   public boolean add(Object obj){
      if(obj instanceof String){
         String adding = (String) obj;
         this.addIns.add(adding);
         return true;
      }
      return false;
   }

   /**
    * Removes a String object representing an addIn from the addIns ArrayList
    * @param obj to be removed
    * @return true if the obj was removed, false otherwise
    */
   @Override
   public boolean remove(Object obj){
      if(obj instanceof String){
         String removes = (String) obj;
         this.addIns.remove(removes);
         return true;
      }
      return false;
   }

   /**
    * Overrides default method of toString from Java.Lang.*
    * @return String
    */
   @Override
   public String toString(){
      if(addIns.size() != 0) {
         String addInsList = "";
         for (String addIn : addIns)
            addInsList += ", " + addIn;
         return size + " Coffee" + addInsList + " (" + quantity + ")";
      }else
         return size + " Coffee" + " (" + quantity + ")";
   }
}
