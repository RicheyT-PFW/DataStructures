package Project4;


public class Project4_Driver {
    public static void main(String[] args) {
        ThreeD_Location p1 = new ThreeD_Location(1, 1, 1);
         p1.rotateAroundX(90.0);

         
        System.out.println(p1.hashCode() + ": " + p1);
        System.out.println();
        System.out.println();
    
    
    }
}