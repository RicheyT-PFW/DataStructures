package edu.pfw.richtj03.hw;

import edu.pfw.richtj03.mod3.ArrayBag;

/***
 * DiamondMarket Class (30 Pts) 
 * Terrell Richey
 */

public class DiamondMarket {

        public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("Diamond Market Simulation\n----------------------");

        //1. Create a bag of diamonds named collectionD 
        //   and add 4 different diamonds of varying characteristics 
        //   (no interaction with the user is needed). 
        ArrayBag<Diamond> collectionD = new ArrayBag<>(4);
        collectionD.add(new Diamond("DMN49611", 1.2, "VVS1", 'E', "round"));
        collectionD.add(new Diamond("DMN12344", 0.8, "VS2", 'G', "princess"));
        collectionD.add(new Diamond("DMN31103", 2.2, "I3", 'F', "oval"));
        collectionD.add(new Diamond("DMN80757", 3.0, "I1", 'D', "cushion"));

        //2. Create two other bags, kayD and willD.
        //   Remove two diamonds from collectionD and put one into kayD and one into willD. 
        ArrayBag<Diamond> kayD = new ArrayBag<>(2);
        ArrayBag<Diamond> willD = new ArrayBag<>(2);
        Diamond d = collectionD.grab();
        kayD.add(d);
        collectionD.remove(d);
        d = collectionD.grab();
        collectionD.remove(d);
        willD.add(d);

        //3. Create a new Diamond object that is higher quality than the previous 4.
        Diamond newDiamond = new Diamond("DMN2001", 2.5, "FL", 'D', "round");

        //4. Grab a diamond from collectionD. Compare it with the new diamond, 
        //and put the higher quality one into willD and the lower quality one into kayD. 
        d = collectionD.grab();
        if (d.compareTo(newDiamond) > 0) {
            willD.add(d);
            kayD.add(newDiamond);
        } else {
            willD.add(newDiamond);
            kayD.add(d);
        }

        // Remove it from collectionD. 
        collectionD.remove(d);

        //5. Print the size of collectionD before and after using trimToSize. 
        System.out.println("collectionD size before trim: " + collectionD.getCapacity());
        collectionD.trimToSize();
        System.out.println("collectionD size after trim: " + collectionD.getCapacity());
        System.out.println("----------------------------------------");

        //6. Try to add a diamond to collectionD and catch the expected error using exception handling.
         //  Move on without crashing.
        try {
            collectionD.add(new Diamond("DMN3001", 0.9, "VS1", 'F', "emerald"));
        } catch (Exception e) {
            System.out.println("Caught exception trying to add a diamond: " + e.getMessage());
        }
        System.out.println("----------------------------------------");

        //7. Print collectionD, willD, and kayD. 
        System.out.println("collectionD:\n" + collectionD);
        System.out.println("willD:\n" + willD);
        System.out.println("kayD:\n" + kayD);

        //8. Create a new bag called salesD that is an intersection of kayD and willD.
        ArrayBag<Diamond> salesD = ArrayBag.intersection(kayD, willD);

        //9. Print salesD.
        System.out.println("salesD (intersection):\n" + salesD);

        //salesD has no diamonds in the bag because both neither bag contains the same item 
    }

}