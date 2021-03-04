import java.util.ArrayList;

public class Perceptron {
    private ArrayList<double[][]> trainingSet;
    private int[] outputs;
    private double[] weights, inputs;
    private double theta, n, maxError;
    private int maxEpoch, number;
    private int tot;

    public Perceptron(double learningRate,int maxIterator,double maxDiff,double[] listWeights,ArrayList<double[][]> testSet, int num)
    {
        //Initializing Variables
        n = learningRate;
        maxEpoch = maxIterator;
        maxError = maxDiff;
        weights = listWeights;
        theta = 1;
        trainingSet = testSet;
        number = num;
        outputs = new int[300];
        for(int i = 0; i < 300; i++)
        {
            if(i >= num*30 && i < (num+1)*30)
            {
                outputs[i] = 1;
            }
            else
            {
                outputs[i] = 0;
            }
        }
        inputs = new double[35];
    }

    public void train(int neuron)
    {
        neuron++;
        System.out.println("Perceptron " + neuron + ":");
        int iterator = 1;
        double totError = 1;
        while(iterator < maxEpoch && totError != 0)
        {
            totError = 0;
            for(int images = 0; images < 300; images++)
            {
                for(int i = 0; i < 7; i++)
                {
                    for(int j = 0; j < 5; j++)
                    {
                        inputs[i*5+j] = trainingSet.get(images)[i][j];
                    }
                }
                double target = outputs[images];
                double sampleOutput = activate(inputs);
                double error = target - sampleOutput;
                for(int i = 0; i < 35; i++)
                {
                    double deltaW = n * error * inputs[i];
                    weights[i] += deltaW;
                }
                double deltaTheta = n * error * (-1);
                theta += deltaTheta;
                totError += Math.abs(error);
            }
            System.out.println("Epoch: " +  iterator);
            System.out.println("Error: " + totError);
            iterator++;
        }
        System.out.println();
    }

    public double activate(double[] inputs)
    {
        tot = 0;
        for(int i = 0; i < 35; i++)
        {
            tot += inputs[i]*weights[i];
        }
        if(tot > theta)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int classify(double[][] input)
    {
        double[] currInputs = new double[35];
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                currInputs[i*5+j] = input[i][j];
            }
        }
        if(activate(currInputs) == 1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
