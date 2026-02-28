/**
 * Terrell Richey
 * Lab 1 - Object Serialization
 */

package edu.pfw.richtj03.mod1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Lab1 {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {

        //Creates five Position3D objects with random coordinates.
        Position3D point1 = new Position3D();
        Position3D point2 = new Position3D();
        Position3D point3 = new Position3D();
        Position3D point4 = new Position3D();
        Position3D point5 = new Position3D();

        // Serializes and saves the Position3D objects in a file named points.dat.
        FileOutputStream fileOut = new FileOutputStream("points.dat");
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(point1);
        objOut.writeObject(point2);
        objOut.writeObject(point3);
        objOut.writeObject(point4);
        objOut.writeObject(point5);
        System.out.println("Point Data\n--------------------------------------------------------------");

        System.out.println("Serialized point data is saved in points.dat\n");

        // Deserializes the objects and prints their coordinate values to the console.
        FileInputStream fileIn = new FileInputStream("points.dat");
        ObjectInputStream objIn = new ObjectInputStream(fileIn);

        Position3D dPoint1 = (Position3D) objIn.readObject();
        Position3D dPoint2 = (Position3D) objIn.readObject();
        Position3D dPoint3 = (Position3D) objIn.readObject();
        Position3D dPoint4 = (Position3D) objIn.readObject();
        Position3D dPoint5 = (Position3D) objIn.readObject();

        System.out.println("Deserialized Point 1: " + dPoint1);
        System.out.println("Deserialized Point 2: " + dPoint2);
        System.out.println("Deserialized Point 3: " + dPoint3);
        System.out.println("Deserialized Point 4: " + dPoint4);
        System.out.println("Deserialized Point 5: " + dPoint5);

        objIn.close();
        fileIn.close();
        objOut.close();
        fileOut.close();

        // Creates an ArrayList holding Position3D objects.
        ArrayList<Position3D> points = new ArrayList<>();

        // Adds 5 Position3D objects to the ArrayList.
        for (int i = 0; i < 5; i++) {
            points.add(new Position3D());
        }

        // Serializes the ArrayList to a file named list_points.dat.
        FileOutputStream fileOut2 = new FileOutputStream("list_points.dat");
        ObjectOutputStream objOut2 = new ObjectOutputStream(fileOut2);
        objOut2.writeObject(points);
        objOut2.close();
        fileOut2.close();

        System.out.println("\n\nArrayList Data\n--------------------------------------------------------------");
        System.out.println("Serialized list_point data is saved in list_points.dat\n");

        // Deserializes the ArrayList and prints each element’s 
        // coordinate values to the console.
        FileInputStream fileIn2 = new FileInputStream("list_points.dat");
        ObjectInputStream objIn2 = new ObjectInputStream(fileIn2);

        @SuppressWarnings("unchecked")
        ArrayList<Position3D> dPoints = (ArrayList<Position3D>) objIn2.readObject();
      

        for (int i = 0; i < dPoints.size(); i++) {
            System.out.println("Deserialized List Point " + (i + 1) + ": " + dPoints.get(i));
        }

        objIn2.close();
        fileIn2.close();
        objOut2.close();
        fileOut2.close();

    }
}
