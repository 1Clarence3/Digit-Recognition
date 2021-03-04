import java.awt.*;

public class Runner
{
    private static Perceptron[] perceptrons = new Perceptron[10];
    public static void main(String [] args)
    {
        StdDraw.setCanvasSize(600,600);
        StdDraw.setXscale(0,10);
        StdDraw.setYscale(0,10);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        int generatePressed = 0;
        int trainPressed = 0;
        int maxEpoch = 500;
        double learningRate = 0.05;
        double maxError = 0;
        GUI gui = new GUI();

        //Draw Title
        Font font = new Font("Sans Serif", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.text(5,9,"Digit Recognition");
        Font font1 = new Font("Sans Serif", Font.BOLD, 20);
        StdDraw.setFont(font1);

        //Draw Grid
        for(int i = 0; i < 8; i++)
        {
            if(i == 0 || i == 7)
            {
                StdDraw.setPenRadius(0.005);
                StdDraw.setPenColor(Color.BLACK);
            }
            else
            {
                StdDraw.setPenRadius();
                StdDraw.setPenColor(Color.LIGHT_GRAY);
            }
            StdDraw.line(0.25,2.55+i*0.7,3.75,2.55+i*0.7);
            StdDraw.line(6.25,2.55+i*0.7,9.75,2.55+i*0.7);
        }
        for(int j = 0; j < 6; j++)
        {
            if(j == 0 || j == 5)
            {
                StdDraw.setPenRadius(0.005);
                StdDraw.setPenColor(Color.BLACK);
            }
            else
            {
                StdDraw.setPenRadius();
                StdDraw.setPenColor(Color.LIGHT_GRAY);
            }
            StdDraw.line(0.25+j*0.7,7.45,0.25+j*0.7,2.55);
            StdDraw.line(6.25+j*0.7,7.45,6.25+j*0.7,2.55);
        }

        //Draw digits (digit menu)
        StdDraw.setPenRadius();
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(5,0.75,5,0.75);
        for(int i = 0; i < 10; i++)
        {
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.filledRectangle(0.125+0.43125+i*(0.8625+0.125),0.75,0.43125,0.625);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.rectangle(0.125+0.43125+i*(0.8625+0.125),0.75,0.43125,0.625);
        }

        StdDraw.setPenColor(Color.BLACK);
        for(int index = 0; index < 10; index++)
        {
            for(int i = 0; i < 7; i++)
            {
                for(int j = 0; j < 5; j++)
                {
                    if(gui.nums.get(index)[i][j] == 1)
                    {
                        StdDraw.filledSquare(0.125+index*(0.8625+0.125)+(0.8625/5)/2+j*(0.8625/5),0.125+0.625*2-(0.8625/5)/2-i*(1.25/7),(0.8625/5)/2);
                    }
                }
            }
        }

        StdDraw.setPenRadius();
        StdDraw.rectangle(5,7.45-(0.88/2),1.125,0.88/2);
        StdDraw.rectangle(5,7.45-(2*(0.88/2)+0.125+0.88/2),1.125,0.88/2);
        StdDraw.rectangle(5,7.45-(4*(0.88/2)+0.125*2+0.88/2),1.125,0.88/2);
        StdDraw.rectangle(5,7.45-(6*(0.88/2)+0.125*3+0.88/2),1.125,0.88/2);
        StdDraw.rectangle(5,7.45-(8*(0.88/2)+0.125*4+0.88/2),1.125,0.88/2);
        StdDraw.text(5,7.45-(0.88/2),"Generate");
        StdDraw.text(5,7.45-(2*(0.88/2)+0.125+0.88/2),"Train");
        StdDraw.text(5,7.45-(4*(0.88/2)+0.125*2+0.88/2),"Classify");
        StdDraw.text(5,7.45-(6*(0.88/2)+0.125*3+0.88/2),"Add Noise");
        StdDraw.text(5,7.45-(8*(0.88/2)+0.125*4+0.88/2),"Clear");
        StdDraw.show();

        javax.swing.JOptionPane.showMessageDialog(null, "Welcome to the Digit Recognition Program! Please note the following:" +
                "\n1. Remember to generate a set of digits before training" +
                "\n2. Remember to train the set of digits before classifying" +
                "\n3. Hold down the \"B\" key and hover your cursor over squares to fill it black. Hold down the \"W\" key and do the same to fill it white" +
                "\n4. Please don't shake the cursor vigorously or click rapidly. This may cause delay, lag, or malfunction in the program");

        while(true)
        {
            if(StdDraw.isMousePressed())
            {
                if(StdDraw.mouseX() < 6.125 && StdDraw.mouseX() > 3.875)
                {
                    if(StdDraw.mouseY() < 7.45-(0.88/2)+0.88/2 && StdDraw.mouseY() > 7.45-(0.88/2)-0.88/2)
                    {
                        generatePressed = 1;
                        gui.generate();
                        StdDraw.pause(500);
                    }
                    else if(StdDraw.mouseY() < 7.45-(2*(0.88/2)+0.125+0.88/2)+0.88/2 && StdDraw.mouseY() > 7.45-(2*(0.88/2)+0.125+0.88/2)-0.88/2)
                    {
                        if(generatePressed == 1)
                        {
                            trainPressed = 1;
                            for(int i = 0; i < 10; i++)
                            {
                                double[] weights = new double[35];
                                for(int j = 0; j < weights.length; j++)
                                {
                                    weights[j] = Math.random() * 0.5 - 0.25;
                                }
                                perceptrons[i] = new Perceptron(learningRate,maxEpoch,maxError,weights,gui.testSet,i);
                            }
                            gui.train(perceptrons);
                            javax.swing.JOptionPane.showMessageDialog(null, "Training Complete!");
                        }
                        else
                        {
                            javax.swing.JOptionPane.showMessageDialog(null, "Please generate the digits first");
                        }
                        StdDraw.pause(500);
                    }
                    else if(StdDraw.mouseY() < 7.45-(4*(0.88/2)+0.125*2+0.88/2)+0.88/2 && StdDraw.mouseY() > 7.45-(4*(0.88/2)+0.125*2+0.88/2)-0.88/2)
                    {
                        if(trainPressed == 1)
                        {
                            gui.classify(perceptrons);
                        }
                        else
                        {
                            javax.swing.JOptionPane.showMessageDialog(null, "Please train the perceptrons first");
                        }
                        StdDraw.pause(500);
                    }
                    else if(StdDraw.mouseY() < 7.45-(6*(0.88/2)+0.125*3+0.88/2)+0.88/2 && StdDraw.mouseY() > 7.45-(6*(0.88/2)+0.125*3+0.88/2)-0.88/2)
                    {
                        gui.addNoise();
                        StdDraw.pause(500);
                    }
                    else if(StdDraw.mouseY() < 7.45-(8*(0.88/2)+0.125*4+0.88/2)+0.88/2 && StdDraw.mouseY() > 7.45-(8*(0.88/2)+0.125*4+0.88/2)-0.88/2)
                    {
                        gui.clear();
                        StdDraw.pause(500);
                    }
                }
                if(StdDraw.mouseY() < 1.5-0.125 && StdDraw.mouseY() > 0.125)
                {
                    for(int index = 0; index < 10; index++)
                    {
                        if(StdDraw.mouseX() < 0.125+0.43125+index*(0.8625+0.125)+0.43125 && StdDraw.mouseX() > 0.125+0.43125+index*(0.8625+0.125)-0.43125)
                        {
                            gui.clear();
                            StdDraw.setPenColor(Color.BLACK);
                            for(int i = 0; i < 7; i++)
                            {
                                for(int j = 0; j < 5; j++)
                                {
                                    if(gui.nums.get(index)[i][j] == 1)
                                    {
                                        StdDraw.filledSquare(0.25+0.35+j*0.7,7.45-0.35-i*0.7,0.7/2-0.05);
                                    }
                                }
                            }
                            gui.copy(gui.nums.get(index));
                        }
                    }
                    StdDraw.show();
                    StdDraw.pause(500);
                }
            }
            if(StdDraw.isKeyPressed(87))
            {
                if(StdDraw.mouseX() < 3.75 && StdDraw.mouseX() > 0.25 && StdDraw.mouseY() > 2.55 && StdDraw.mouseY() < 7.45)
                {
                    for(int i = 0; i < 5; i++)
                    {
                        for(int j = 0; j < 7; j++)
                        {
                            if(0.25+i*0.7 < StdDraw.mouseX() && StdDraw.mouseX() < 0.25+i*0.7+0.7 && 2.55+j*0.7 < StdDraw.mouseY() && StdDraw.mouseY() < 2.55+j*0.7+0.7)
                            {
                                StdDraw.setPenColor(Color.WHITE);
                                StdDraw.filledSquare(0.25+i*0.7+0.7/2,2.55+j*0.7+0.7/2,0.7/2-0.05);
                                StdDraw.show();
                                StdDraw.pause(100);
                                gui.currNum[6-j][i] = 0;
                            }
                        }
                    }
                }
            }
            else if(StdDraw.isKeyPressed(66))
            {
                if(StdDraw.mouseX() < 3.75 && StdDraw.mouseX() > 0.25 && StdDraw.mouseY() > 2.55 && StdDraw.mouseY() < 7.45)
                {
                    for(int i = 0; i < 5; i++)
                    {
                        for(int j = 0; j < 7; j++)
                        {
                            if(0.25+i*0.7 < StdDraw.mouseX() && StdDraw.mouseX() < 0.25+i*0.7+0.7 && 2.55+j*0.7 < StdDraw.mouseY() && StdDraw.mouseY() < 2.55+j*0.7+0.7)
                            {
                                StdDraw.setPenColor(Color.BLACK);
                                StdDraw.filledSquare(0.25+i*0.7+0.7/2,2.55+j*0.7+0.7/2,0.7/2-0.05);
                                StdDraw.show();
                                StdDraw.pause(100);
                                gui.currNum[6-j][i] = 1;
                            }
                        }
                    }
                }
            }
        }
    }
}
