package com.example.rucafe;

/**
 * Donut Class represents instances of menuItem that are specific to donuts
 * Each donut has a type, price, flavour, and quantity
 * @author Connie Chen, Tiffany Lee
 */
public class Donut extends MenuItem{
   protected String type;
   protected String flavour;
   protected int quantity;
   public static final double YEAST_PRICE = 1.39;
   public static final double CAKE_PRICE = 1.59;
   public static final double DONUT_HOLE_PRICE = 0.33;

   /**
    * Creates a Donut object
    * @param type of Donut
    * @param flavour of Donut
    * @param quantity of Donut(s)
    */
   public Donut(String type, String flavour, int quantity) {
      super(0, quantity);
      this.type = type;
      this.flavour = flavour;
      this.price = this.itemPrice();
      this.quantity = quantity;
   }

   /**
    * Calculates and returns the price of the Donut order
    * @return int representing price of the Donut(s)
    */
   @Override
   public double itemPrice(){
      if(type.equals("Yeast"))
         return this.quantity * YEAST_PRICE;
      else if (type.equals("Cake"))
         return this.quantity * CAKE_PRICE;
      else
         return this.quantity * DONUT_HOLE_PRICE;
   }

   /**
    * Overrides default method of toString from Java.Lang.*
    * @return String
    */
   @Override
   public String toString(){
      return flavour + " " + type + " Donut (" + quantity + ")";
   }
}
