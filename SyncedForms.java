import java.awt.*;
import java.util.*;

public class SyncedForms {

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] Args) {
        // Print header information
        System.out.println("UTSA - Fall 2022 - CS1083 - Section 001 - Project 3 - SyncedForms - written by Soren Watterson");

        drawSyncedForms();
    }

    public static void drawSyncedForms() {
        // Declare variables here
        Scanner scanner = new Scanner(System.in);
        // Initialize arrays here
        int[] xpos = new int[9];
        int[] ypos = new int[9];
        Color[] colorList = new Color[9];
        // Create DrawingPanel object here

        
        
        char shape;
        do {
            System.out.println("What form will be shown (C-ircle, S-quare)?");
            shape = scanner.next().charAt(0);
        } while (!(shape == 'c' || shape == 'C') && !(shape == 's' || shape == 'S'));

        int numForms = 0;
        do {
            System.out.println("How many forms you want to show (max 9)?");
            numForms = scanner.nextInt();
        } while (numForms >= 10 );

        int numMoveForms = 0;
        do {
            System.out.println("How many times you want the forms to move (max 500)?");
            numMoveForms = scanner.nextInt();
        } while (numMoveForms >= 500 );
        // Call initialPosition method here

        initialPosition(xpos, ypos, numForms);
        initialColor(colorList, numForms);

        DrawingPanel panel = new DrawingPanel(400,400);

        moveForms(panel, shape, xpos, ypos, colorList, numForms, numMoveForms);
        
        Graphics g = panel.getGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(20,185,360,40);
        g.setColor(Color.GREEN);
        g.drawRect(20,185,360,40);
        g.drawString("UTSA - Fall 2022 - CS1083 - Section 001 - Project 3", 20, 200);
        g.drawString(" - SyncedForms - written by Soren Watterson", 20, 220);
    }

    public static void initialPosition(int[] xPos, int[] yPos, int numForms) {
        // Implement method to assign initial position for forms
        int panelX = 400;
        int panelY = 400;
        int formSize = 50;
        int[] xPosArray = {(int)(panelX * (1.0/4) - formSize/2), (int)(panelX * (3.0/4) - formSize/2), (int)(panelX * (3.0/4) - formSize/2), 
              (int)(panelX * (1.0/4) - formSize/2), (int)(panelX * (2.0/4) - formSize/2), (int)(panelX * (1.0/4) - formSize/2), 
              (int)(panelX * (3.0/4) - formSize/2), (int)(panelX * (2.0/4) - formSize/2), (int)(panelX * (2.0/4) - formSize/2)};

        int[] yPosArray = {(int)(panelY * (1.0/4) - formSize/2), (int)(panelY * (1.0/4) - formSize/2), (int)(panelY * (3.0/4) - formSize/2), 
              (int)(panelY * (3.0/4) - formSize/2), (int)(panelY * (2.0/4) - formSize/2), (int)(panelY * (2.0/4) - formSize/2), 
              (int)(panelY * (2.0/4) - formSize/2), (int)(panelY * (1.0/4) - formSize/2), (int)(panelY * (3.0/4) - formSize/2)};

        for (int i = 0; i < numForms; i++){
            xPos[i] = xPosArray[i];
            yPos[i] = yPosArray[i];
        }


    }

    public static void initialColor(Color[] colors, int numForms) {
        // Implement method to assign initial color for forms
        Color[] colorsArray = {Color.GREEN, Color.GRAY, Color.YELLOW, Color.RED, Color.ORANGE, Color.PINK, Color.DARK_GRAY, Color.BLUE, Color.BLACK};
        for (int i = 0; i < numForms; i++){
            colors[i] = colorsArray[i];
        
        }
    }

    public static void moveForms(DrawingPanel panel, char shape, int[] xPos, int[] yPos, Color[] colors, int numForms, int numMoves) {
        Scanner scanner = new Scanner(System.in);
        int delay = 50;
        int num = 0;
        int i;
        int orientation;
        Graphics g = panel.getGraphics();

        for(i = 0; i < numForms; i++){
            showForm(g, shape, xPos[i], yPos[i], colors[i], numForms, numMoves);
        }
        orientation = 8;
        while ((orientation > 7) && (num < numMoves)){
            System.out.println("Please, input the different moves");
            orientation = scanner.nextInt();
        }
        

        while (num < numMoves){
            for (i = 0; i < numForms; i++){
                moveForm(xPos, yPos, i, orientation);
                showForm(g, shape, xPos[i], yPos[i], colors[i], numForms, numMoves);
                try{
                    Thread.sleep(50);
                } catch(InterruptedException e){
                    System.out.println("error");
                }
                
            }

            num += 1;
            
            if (num >= numMoves){
                break;
            }
            orientation = scanner.nextInt();
            
        }
        


        
         
    }

    public static void moveForm(int[] xPos, int[] yPos, int i, int orientation) {
        // moves form according to input
        
        switch (orientation){
            case 0:
                xPos[i] += 0;
                yPos[i] -= 25;
                break;
            case 1:
                xPos[i] += 25;
                yPos[i] -= 25;
                break;

            case 2:
                xPos[i] += 25;
                yPos[i] += 0;
                break;
            case 3:
                xPos[i] += 25;
                yPos[i] += 25;
                break;
            case 4:
                xPos[i] += 0;
                yPos[i] += 25;
                break;
            case 5:
                xPos[i] -= 25;
                yPos[i] += 25;
                break;
            case 6:
                xPos[i] -= 25;
                yPos[i] += 0;
                break;
            case 7:
                xPos[i] -= 25;
                yPos[i] -= 25;
                break;
            
            
        }
        
        

    }

    public static void showForm(Graphics g, char shape, int xPos, int yPos, Color color, int numForms, int numMoves) {
        // shows the specific form 
        
        shape = Character.toUpperCase(shape);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,400,400);

        if (shape == 'C'){
            g.setColor(color);
            g.fillOval(xPos, yPos, 50, 50);
            g.setColor(Color.BLACK);
            g.drawOval(xPos, yPos, 50, 50);
        }
        else{
            g.setColor(color);
            g.fillRect(xPos, yPos, 50, 50);
            g.setColor(Color.BLACK);
            g.drawRect(xPos, yPos, 50, 50);
        }


        
    }
}
