import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;

public class Main extends JPanel implements ActionListener {

    Timer t = new Timer(1, this);
    List<CelestialBodies> celestialBodies;

    // Tutorial 1 Function
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        for (int i = 0; i < celestialBodies.size(); i++) {
            int size = celestialBodies.get(i).getPixelSize();
            g.fillOval(celestialBodies.get(i).getInitialXCoord(), celestialBodies.get(i).getInitialYCoord(), size, size);
        }
        // starts the timer, which starts the actionPerformend()
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < celestialBodies.size(); i++) {
            CelestialBodies body = celestialBodies.get(i);
            body.setInitialXCoord((int)(body.getInitialXCoord() + body.getInitialXVelocity()));
            body.setInitialYCoord((int)(body.getInitialYCoord() + body.getInitialYVelocity()));
        }
        repaint();

        // APPLYING FORCE APPROACH (work-in-progress)
        // for (int i = 0; i < celestialBodies.size(); i++) {
        //     double[] forceArray = calcTotalForce(i);
        //     celestialBodies.get(i).initialXVelocity += (forceArray[0] * 100);
        //     celestialBodies.get(i).initialYVelocity += (forceArray[1] * 100);
        //     double x = celestialBodies.get(i).initialXVelocity;
        //     double y = celestialBodies.get(i).initialYVelocity;
        //     celestialBodies.get(i).initialXCoord += x;
        //     celestialBodies.get(i).initialYCoord += y;
        // }
        // repaint();
    }

    public double[] calcTotalForce(int bodyIndex) {
        double G = 6.67408 * Math.pow(10, -11);
        double totalForce = 0;
        double accelerationX = 0;   
        double accelerationY = 0;
        // mass of the target body
        double mass = celestialBodies.get(bodyIndex).getMass();
        // for each body in the list
        for (int i = 0; i < celestialBodies.size(); i++) {
            // if the body at current index is not the same as target body
            if (i != bodyIndex) {
                // difference between the x and y components
                double diffX = celestialBodies.get(bodyIndex).getInitialXCoord() - celestialBodies.get(i).getInitialXCoord();
                double diffY = celestialBodies.get(bodyIndex).getInitialYCoord() - celestialBodies.get(i).getInitialYCoord();
                // distance between the two bodies
                double dist = Math.hypot(diffX, diffY);
                // mass of the second body
                double mass2 = celestialBodies.get(i).getMass();
                if (dist != 0) {
                    totalForce += (G * (mass * mass2)) / (dist * dist);
                    accelerationX = -(totalForce * diffX / dist / mass);
                    accelerationY = -(totalForce * diffY / dist / mass);
                }
                else {
                    accelerationX = 0;
                    accelerationY = 0;
                }
            }
        }
        return new double[] {accelerationX, accelerationY};
    }

    //helper function to get the force between two bodies
    public double getForce(CelestialBodies body1, CelestialBodies body2) {
        double G = 6.67408 * Math.pow(10, -11);
        int body1Mass = (int)body1.getMass();
        int body2Mass = (int)body2.getMass();
        int body1_x1 = body1.getInitialXCoord();
        int body1_y1 = body1.getInitialYCoord();
        int body2_x2 = body2.getInitialXCoord();
        int body2_y2 = body2.getInitialYCoord();
        double distance = Math.sqrt(Math.pow(body2_x2 - body1_x1, 2) + Math.pow(body2_y2 - body1_y1, 2));
        double force = G * (body1Mass * body2Mass) / Math.pow(distance, 2);
        return force;
    }

    //helper function to print the data
    public static void printData(List<CelestialBodies> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
    
    public static void main(String[] args) throws Exception {
        Main cbodyDemo = new Main();
        try {
            if (args.length > 0) {
                // Requirement 1: Read the File
                File file = new File(args[0]);
                Scanner myReader = new Scanner(file);
                String storageType = myReader.nextLine()/*.toLowerCase()*/;
                double scale = Double.parseDouble(myReader.nextLine());

                // Requirement 2: Create/Instantiate/Maintain List of Celestial Bodies
                cbodyDemo.celestialBodies = (List<CelestialBodies>) Class.forName(storageType).getDeclaredConstructor().newInstance();

                // name[0], mass[1], initialXCoord[2], initialYCoord[3], initialXVelocity[4], initialYVelocity[5], pixelSize[6]
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] dataArray = data.split(",");
                    CelestialBodies celestialBody = new CelestialBodies(dataArray[0], Double.parseDouble(dataArray[1]), Integer.parseInt(dataArray[2]), Integer.parseInt(dataArray[3]), Double.parseDouble(dataArray[4]), Double.parseDouble(dataArray[5]), Integer.parseInt(dataArray[6]));
                    cbodyDemo.celestialBodies.add(celestialBody);
                }
                myReader.close();

                // print the data
                //printData(cbodyDemo.celestialBodies);
            }
            else {
                System.out.println("No file passed.");
                return;
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }

        // Requirement 3: Create/Instantiate/Maintain List of Celestial Bodies
        //Tutorial t = new Tutorial();
        JFrame jf = new JFrame();
        jf.setTitle("Celestial Bodies");
        jf.setSize(768, 768); 
        jf.add(cbodyDemo);
        jf.setVisible(true); 
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Requirement 4: Change the position of the bodies
        // int body1_mass = (int)cbodyDemo.celestialBodies.get(0).getMass();
        // int body2_mass = (int)cbodyDemo.celestialBodies.get(1).getMass();
        // int body1_x1 = (int)cbodyDemo.celestialBodies.get(0).getInitialXCoord();
        // int body1_y1 = (int)cbodyDemo.celestialBodies.get(0).getInitialYCoord();
        // int body2_x1 = (int)cbodyDemo.celestialBodies.get(1).getInitialXCoord();
        // int body2_y1 = (int)cbodyDemo.celestialBodies.get(1).getInitialYCoord();
        // double distance = Point2D.distance(body1_x1, body1_y1, body2_x1, body2_y1);
        // double force = (6.67408 * (Math.pow(10, -11) * body1_mass * body2_mass) / (Math.pow(distance, 2)));
    }

}