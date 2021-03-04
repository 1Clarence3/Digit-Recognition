import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GUI
{
    public ArrayList<double[][]> testSet = new ArrayList<double[][]>(300);
    public double[][] currNum = new double[7][5];
    public ArrayList<double[][]> nums = new ArrayList<double[][]>();
    private double[][] blank = {{0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0},
                                {0,0,0,0,0}};

    private double[][] error = {{0,1,1,1,1},
                                {1,0,0,0,0},
                                {1,0,0,0,0},
                                {0,1,1,1,0},
                                {1,0,0,0,0},
                                {1,0,0,0,0},
                                {0,1,1,1,1}};

    public GUI()
    {
        double[][] zero = {{0,1,1,1,0},
                           {1,0,0,0,1},
                           {1,0,0,0,1},
                           {1,0,0,0,1},
                           {1,0,0,0,1},
                           {1,0,0,0,1},
                           {0,1,1,1,0}};

        double[][] one = {{0,1,1,0,0},
                          {0,0,1,0,0},
                          {0,0,1,0,0},
                          {0,0,1,0,0},
                          {0,0,1,0,0},
                          {0,0,1,0,0},
                          {1,1,1,1,1}};

        double[][] two = {{0,1,1,1,0},
                          {1,0,0,0,1},
                          {0,0,0,1,0},
                          {0,0,1,0,0},
                          {0,1,0,0,0},
                          {1,0,0,0,0},
                          {1,1,1,1,1}};

        double[][] three = {{0,1,1,1,0},
                            {1,0,0,0,1},
                            {0,0,0,0,1},
                            {0,0,1,1,0},
                            {0,0,0,0,1},
                            {1,0,0,0,1},
                            {0,1,1,1,0}};

        double[][] four = {{0,0,1,1,0},
                           {0,1,0,1,0},
                           {0,1,0,1,0},
                           {1,0,0,1,0},
                           {1,1,1,1,1},
                           {0,0,0,1,0},
                           {0,0,0,1,0}};

        double[][] five = {{1,1,1,1,1},
                          {1,0,0,0,0},
                          {1,1,1,1,0},
                          {0,0,0,0,1},
                          {0,0,0,0,1},
                          {1,0,0,0,1},
                          {0,1,1,1,0}};

        double[][] six = {{0,0,1,1,1},
                          {0,1,0,0,0},
                          {1,1,1,1,0},
                          {1,0,0,0,1},
                          {1,0,0,0,1},
                          {1,0,0,0,1},
                          {0,1,1,1,0}};

        double[][] seven = {{1,1,1,1,1},
                            {0,0,0,0,1},
                            {0,0,0,1,0},
                            {0,0,1,0,0},
                            {0,0,1,0,0},
                            {0,1,0,0,0},
                            {0,1,0,0,0}};

        double[][] eight = {{0,1,1,1,0},
                            {1,0,0,0,1},
                            {1,0,0,0,1},
                            {0,1,1,1,0},
                            {1,0,0,0,1},
                            {1,0,0,0,1},
                            {0,1,1,1,0}};

        double[][] nine = {{0,1,1,1,0},
                           {1,0,0,0,1},
                           {1,0,0,0,1},
                           {0,1,1,1,1},
                           {0,0,0,0,1},
                           {0,0,0,1,0},
                           {0,1,1,0,0}};

        Collections.addAll(nums,zero,one,two,three,four,five,six,seven,eight,nine);
    }

    public void train(Perceptron[] perceptrons)
    {
        for(int i = 0; i < 10; i++)
        {
            perceptrons[i].train(i);
        }
    }

    public void generate()
    {
        for(int index = 0; index < 10; index++)
        {
            clear();
            StdDraw.setPenColor(Color.BLACK);
            testSet.add(nums.get(index));
            for(int i = 0; i < 7; i++)
            {
                for(int j = 0; j < 5; j++)
                {
                    if(nums.get(index)[i][j] == 1)
                    {
                        StdDraw.filledSquare(0.25+0.35+j*0.7,7.45-0.35-i*0.7,0.7/2-0.05);
                    }
                }
            }
            for(int images = 0; images < 29; images++)
            {
                clear();
                copy(nums.get(index));
                StdDraw.setPenColor(Color.BLACK);
                for(int i = 0; i < 7; i++)
                {
                    for(int j = 0; j < 5; j++)
                    {
                        if(nums.get(index)[i][j] == 1)
                        {
                            StdDraw.filledSquare(0.25+0.35+j*0.7,7.45-0.35-i*0.7,0.7/2-0.05);
                        }
                    }
                }
                StdDraw.show();
                int addNoise = (int)(Math.random()*3)+1;
                for(int noiseTimes = 0; noiseTimes < addNoise; noiseTimes++)
                {
                    addNoise();
                }
                double[][] copy1 = new double[7][5];
                for(int i = 0; i < 7; i++)
                {
                    for(int j = 0; j < 5; j++)
                    {
                        copy1[i][j] = currNum[i][j];
                    }
                }
                testSet.add(copy1);
            }
        }
    }

    public void classify(Perceptron[] perceptrons)
    {
        StdDraw.setPenColor(Color.WHITE);
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                StdDraw.filledSquare(6.25+0.35+j*0.7,7.45-0.35-i*0.7,0.7/2-0.05);
            }
        }
        int[] passedPerceptrons = new int[10];
        for(int i = 0; i < 10; i++)
        {
            if(perceptrons[i].classify(currNum) == 1)
            {
                passedPerceptrons[i] = 1;
            }
            else
            {
                passedPerceptrons[i] = 0;
            }
        }
        int passedCnt = 0;
        for(int i = 0; i < passedPerceptrons.length; i++)
        {
            if(passedPerceptrons[i] != 0)
            {
                passedCnt++;
            }
        }
        if(passedCnt > 1 || passedCnt == 0)
        {
            StdDraw.setPenColor(Color.BLACK);
            for(int i = 0; i < 7; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    if(error[i][j] == 1)
                    {
                        StdDraw.filledSquare(6.25+0.35+j*0.7,7.45-0.35-i*0.7,0.7/2-0.05);
                    }
                }
            }
        }
        else if(passedCnt == 1)
        {
            StdDraw.setPenColor(Color.BLACK);
            for(int index = 0; index < passedPerceptrons.length; index++)
            {
                if(passedPerceptrons[index] == 1)
                {
                    for(int i = 0; i < 7; i++)
                    {
                        for (int j = 0; j < 5; j++)
                        {
                            if(nums.get(index)[i][j] == 1)
                            {
                                StdDraw.filledSquare(6.25+0.35+j*0.7,7.45-0.35-i*0.7,0.7/2-0.05);
                            }
                        }
                    }
                }
            }
        }
        StdDraw.show();
    }

    public void addNoise()
    {
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                int rand = (int)(Math.random()*10);
                if(rand == 0)
                {
                    StdDraw.setPenColor(Color.WHITE);
                    StdDraw.filledSquare(0.25 + 0.35 + j * 0.7, 7.45 - 0.35 - i * 0.7, 0.7 / 2 - 0.05);
                    if(currNum[i][j] == 0)
                    {
                        StdDraw.setPenColor(30,30,30);
                        currNum[i][j] = 0.25;
                    }
                    else if(currNum[i][j] == 1)
                    {
                        StdDraw.setPenColor(190,190,190);
                        currNum[i][j] = 0.75;
                    }
                    else
                    {
                        int x = (int)((currNum[i][j]/0.25)*(40)+30);
                        StdDraw.setPenColor(x,x,x);
                        int rand1 = (int)(Math.random()*2);
                        if(rand1 == 1)
                        {
                            currNum[i][j] -= 0.25;
                        }
                        else
                        {
                            currNum[i][j] += 0.25;
                        }
                    }
                    StdDraw.filledSquare(0.25 + 0.35 + j * 0.7, 7.45 - 0.35 - i * 0.7, 0.7 / 2 - 0.05);
                }
            }
        }
        StdDraw.show();
    }

    public void clear()
    {
        StdDraw.setPenColor(Color.WHITE);
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                StdDraw.filledSquare(0.25+0.35+j*0.7,7.45-0.35-i*0.7,0.7/2-0.05);
            }
        }
        copy(blank);
        StdDraw.show();
    }

    public void copy(double[][] original)
    {
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                currNum[i][j] = original[i][j];
            }
        }
    }
}