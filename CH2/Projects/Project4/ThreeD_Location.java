package Project4;

public class ThreeD_Location implements Cloneable {

    // [x,y,z]
    private double[] coords = new double[3];

    public ThreeD_Location() {
        super();
        coords = new double[3];
    }

    public ThreeD_Location(double[] coords) {
        if (coords.length != 3) {
            throw new IllegalArgumentException("double[] coords length must be three to represent a 3D point");
        }
        super();
        this.coords = coords.clone();
    }

       public ThreeD_Location(double x, double y, double z) {
        super();
        setCoords(x, y, z);
    }

    public double getX() {
        return coords[0];
    }

    public double getY() {
        return coords[1];
    }

    public double getZ() {
        return coords[2];
    }

    public double[] getCoords() {
        return this.coords.clone();
    }

    public void setX(double x) {
        coords[0] = x;
    }

    public void setY(double y) {
        coords[1] = y;
    }

    public void setZ(double z) {
        coords[2] = z;
    }

    public void shiftX(double delta) {
        coords[0] += delta;
    }

    public void shiftY(double delta) {
        coords[1] += delta;
    }

    public void shiftZ(double delta) {
        coords[2] += delta;
    }

    public void setCoords(double x, double y, double z) {
        coords[0] = x;
        coords[1] = y;
        coords[2] = z;
    }

    /* After a θ rotation around the x-axis:
    x' = x
    y' = y cos(θ) - z sin(θ)
    z' = y sin(θ) + z cos(θ)*/
    public void rotateAroundX(double theta) {
        rotateAroundXRad(Math.toRadians(theta));
    }

    /* After a θ rotation around the y-axis:
    x' = x cos(θ) + z sin(θ)
    y' = y 
    z' = -x sin(θ) + z cos(θ)*/
    public void rotateAroundY(double theta) {
        rotateAroundYRad(Math.toRadians(theta));
    }

    /*  After a θ rotation around the z-axis:
    x' = x cos(θ) - y sin(θ)
    y' = x sin(θ) + y cos(θ)
    z' = z*/
    public void rotateAroundZ(double theta) {
        rotateAroundZRad(Math.toRadians(theta));
    }



    /* After a θ rotation around the x-axis:
    x' = x
    y' = y cos(θ) - z sin(θ)
    z' = y sin(θ) + z cos(θ)*/
    public void rotateAroundXRad(double radians) {

        double xPrime = this.coords[0];
        double yPrime = ((this.coords[1] * Math.cos(radians)) - (this.coords[2] * Math.sin(radians)));
        double zPrime = ((this.coords[1] * Math.sin(radians)) + (this.coords[2] * Math.cos(radians)));
        setCoords(xPrime, yPrime, zPrime);
    }

    /* After a θ rotation around the y-axis:
    x' = x cos(θ) + z sin(θ)
    y' = y 
    z' = -x sin(θ) + z cos(θ)*/
    public void rotateAroundYRad(double radians) {

        double xPrime = ((this.coords[0] * Math.cos(radians)) + (this.coords[2] * Math.sin(radians)));
        double yPrime = this.coords[1];
        double zPrime = (((-1 * this.coords[0]) * Math.sin(radians)) + (this.coords[2] * Math.cos(radians)));
        setCoords(xPrime, yPrime, zPrime);
    }

    /*  After a θ rotation around the z-axis:
    x' = x cos(θ) - y sin(θ)
    y' = x sin(θ) + y cos(θ)
    z' = z*/
    public void rotateAroundZRad(double radians) {

        double xPrime = ((this.coords[0] * Math.cos(radians)) - (this.coords[1] * Math.sin(radians)));
        double yPrime = ((this.coords[0] * Math.sin(radians)) + (this.coords[1] * Math.cos(radians)));
        double zPrime = this.coords[2];
        setCoords(xPrime, yPrime, zPrime);
    }

    @Override
    public String toString() {
        return java.util.Arrays.toString(this.coords);
    }

    @Override
    public ThreeD_Location clone() {
        ThreeD_Location threeDClone = null;
        try {
            threeDClone = (ThreeD_Location) super.clone();
            threeDClone.coords = this.coords.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
            System.err.println(java.util.Arrays.toString(e.getStackTrace()));
        }
        return threeDClone;
    }

}
