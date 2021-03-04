# Digit-Recognition
In this Digit Recognition Project, I programmed in Java 
and used the StdDraw class to draw the graphics. I generated 
30 images with noise added per digit. Noise was added 1-4 times 
for each digit. As for the training method in my Perceptron class, 
I used the 35 pixels as my inputs and assigned 35 weights 
to each individual pixel. Then, I looped through the 300 (30 images from digits 0-9)
images and updated the weights as well as the theta for 
each individual pixel. 

Overall, the hardest part of this project was actually to 
figure out a method to update what was being entered on the 
left grid so I could classify accurately (get each pixel to
be assigned as a 1 or 0). At first I just used a 2D array to 
represent the left cell but that created reference problems 
since whenever I changed that 2D array, all the other values
referenced to that array would be altered. Thus, I had to 
copy each value in that 2D array to a new 2D array. 

The most time-consuming part of this project was actually 
drawing out the GUI since everything had to aligned perfectly 
(especially when filling in the cells to represent the digit).


