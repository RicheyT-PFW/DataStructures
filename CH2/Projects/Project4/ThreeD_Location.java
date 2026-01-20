package Project4;

public class ThreeD_Location implements Cloneable {

    private double x;
    private double y;
    private double z;

    //Constructors
    //Uses the default values for x,y and z
    public ThreeD_Location() {
        super();
    }

    public ThreeD_Location(ThreeD_Location threeD_Location) {
        this.x = threeD_Location.x;
        this.y = threeD_Location.y;
        this.z = threeD_Location.z;

    }

    public ThreeD_Location(double[] coords) {
        if (coords == null) {
            throw new NullPointerException(
                    "'public ThreeD_Location(double[] coords)' threw a NullPointerException because coords is null");
        }

        if (coords.length != 3) {
            throw new IllegalArgumentException(
                    "'public ThreeD_Location(double[] coords)' threw an IllegalArgumentException because the coords.length must be 3 to represent a 3D point");
        }
        super();
        this.x = coords[0];
        this.y = coords[1];
        this.z = coords[2];
    }

    public ThreeD_Location(double x, double y, double z) {
        super();
        setCoords(x, y, z);
    }

    // Getters
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double[] getCoords() {
        return new double[]{this.x, this.y, this.z};
    }

    // Setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public final void setCoords(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Shifting methods
    public void shiftX(double delta) {
        this.x += delta;
    }

    public void shiftY(double delta) {
        this.y += delta;
    }

    public void shiftZ(double delta) {
        this.z += delta;
    }

    public void shift(double deltaX, double deltaY, double deltaZ) {
        this.x += deltaX;
        this.y += deltaY;
        this.z += deltaZ;
    }

    //Rotation methods

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
        double xPrime = this.x;
        double yPrime = ((this.y * Math.cos(radians)) - (this.z * Math.sin(radians)));
        double zPrime = ((this.y * Math.sin(radians)) + (this.z * Math.cos(radians)));
        setCoords(xPrime, yPrime, zPrime);
    }

    /* After a θ rotation around the y-axis:
    x' = x cos(θ) + z sin(θ)
    y' = y 
    z' = -x sin(θ) + z cos(θ)*/
    public void rotateAroundYRad(double radians) {
        double xPrime = ((this.x * Math.cos(radians)) + (this.z * Math.sin(radians)));
        double yPrime = this.y;
        double zPrime = (((-1 * this.x) * Math.sin(radians)) + (this.z * Math.cos(radians)));
        setCoords(xPrime, yPrime, zPrime);
    }

    /*  After a θ rotation around the z-axis:
    x' = x cos(θ) - y sin(θ)
    y' = x sin(θ) + y cos(θ)
    z' = z*/
    public void rotateAroundZRad(double radians) {
        double xPrime = ((this.x * Math.cos(radians)) - (this.y * Math.sin(radians)));
        double yPrime = ((this.x * Math.sin(radians)) + (this.y * Math.cos(radians)));
        double zPrime = this.z;
        setCoords(xPrime, yPrime, zPrime);
    }

    //Overrideable methods
    @Override
    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }

    @Override
    public ThreeD_Location clone() throws CloneNotSupportedException {
        ThreeD_Location threeDClone;
        threeDClone = (ThreeD_Location) super.clone();
        return threeDClone;
    }

}
