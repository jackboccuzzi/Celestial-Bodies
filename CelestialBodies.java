public class CelestialBodies {
    String name;
    double mass;
    int initialXCoord;
    int initialYCoord;
    double initialXVelocity;
    double initialYVelocity;
    int pixelSize;

    public CelestialBodies() {
        this.name = "";
        this.mass = 0;
        this.initialXCoord = 0;
        this.initialYCoord = 0;
        this.initialXVelocity = 0;
        this.initialYVelocity = 0;
        this.pixelSize = 0;
    }

    public CelestialBodies(String name, double mass, int initialXCoord, int initialYCoord, double initialXVelocity, double initialYVelocity, int pixelSize) {
        this.name = name;
        this.mass = mass;
        this.initialXCoord = initialXCoord;
        this.initialYCoord = initialYCoord;
        this.initialXVelocity = initialXVelocity;
        this.initialYVelocity = initialYVelocity;
        this.pixelSize = pixelSize;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public void setInitialXCoord(int newCoord) {
        this.initialXCoord = newCoord;
    }

    public int getInitialXCoord() {
        return initialXCoord;
    }
    
    public void setInitialYCoord(int newCoord) {
        this.initialYCoord = newCoord;
    }

    public int getInitialYCoord() {
        return initialYCoord;
    }

    public double getInitialXVelocity() {
        return initialXVelocity;
    }

    public double getInitialYVelocity() {
        return initialYVelocity;
    }

    public int getPixelSize() {
        return pixelSize;
    }

    public String toString() {
        return "Name: " + name + "\nMass: " + mass + "\nInitial X Coord: " + initialXCoord + "\nInitial Y Coord: " + initialYCoord + "\nInitial X Velocity: " + initialXVelocity + "\nInitial Y Velocity: " + initialYVelocity + "\nPixel Size: " + pixelSize + "\n";
    }
}