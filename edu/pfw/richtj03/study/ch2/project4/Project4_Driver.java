package project4;


public class Project4_Driver {
    public static void main(String[] args) throws CloneNotSupportedException {
        ThreeD_Location p1 = new ThreeD_Location(1, 1, 1);
        p1.rotateAroundX(90.0);
        ThreeD_Location p2 = p1.clone();
         
        
        System.out.println(p1.hashCode() + ": " + p1);
        System.out.println(p2.hashCode() + ": " + p2);
        System.out.println();
        System.out.println();
    
    
    }
}