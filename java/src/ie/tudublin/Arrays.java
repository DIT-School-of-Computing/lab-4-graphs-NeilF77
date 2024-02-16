package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet
{
    int graph = 0;
    String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};

    public float map1(float a, float b, float c, float d, float e)
    {
        float r1 = c - b;
        float r2 = e - d;

        float howFar = a - b;
        return d + (howFar / r1) * r2;
    }

    void randomize()
    {
        for (int i = 0; i < rainfall.length; i++) {
            rainfall[i] = random(500);
        }
    }

    public void settings()
    {
        size(500, 500);

        String[] m1 = months;
        print(m1[0]);
        for(int i = 0; i < months.length; i ++)
        {
            println("Month: " + months[i] + "\t" + rainfall[i]);
        }
        for (String s : m1) {
            println(s);
        }

        int minIndex = 0;
        for(int i= 0 ; i < rainfall.length ; i ++)
        {
            if (rainfall[i] < rainfall[minIndex])
            {
                minIndex = i;
            }
        }

        int maxIndex = 0;
        for(int i= 0 ; i < rainfall.length ; i ++)
        {
            if (rainfall[i] > rainfall[maxIndex])
            {
                maxIndex = i;
            }
        }

        println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
        println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");


        float tot = 0;
        for(float f:rainfall)
        {
            tot += f;
        }

        float avg = tot / (float) rainfall.length;

        // a b-c d-e;
        println(map1(5, 0, 10, 0, 100));
        // 50

        println(map1(25, 20, 30, 200, 300));
        // 250

        println(map1(26, 25, 35, 0, 100));
        // 10


    }

    public void setup() {
        colorMode(HSB);
        background(0);
        randomize();


    }

	public void draw() {
		// Clear the canvas at the beginning of each case
		background(0);
	
		switch (graph) {
			case 0:
				float w = width / (float)months.length - 4;
	
				stroke(255);
				line(40, 40, 40, height - 60); // Adjust the starting point of the y-axis
	
				for(int i = 0; i < months.length; i++) {
					float x = i * w + 40;
					fill(map1(i, 0, months.length, 0, 255), 255, 255);
					rect(x, height - 30, w, -rainfall[i]);
	
					textAlign(CENTER, CENTER);
					fill(255);
					text(months[i], x + w/2, height - 20);
				}
	
				line(30, height - 30, width, height - 30);
	
				for (int i = 0; i <= 120; i += 10) {
					float y = map1(i, 0, 120, height - 30, 40);
					fill(255);
					text((int) i, 30, y);
				}
				break;
	
			case 1:
				float d = (width - 80) / (float)months.length; // Adjusted bar width
				float spacing = 10; // Adjust the spacing between bars
	
				stroke(255);
				line(40, 40, 40, height - 60); // Adjust the starting point of the y-axis
	
				for (int i = 1; i < months.length; i++) {
					float x1 = 40 + (i - 1) * (d + spacing); // Start x-coordinate
					float x2 = 40 + i * (d + spacing); // End x-coordinate
					float y1 = height - 30 - map(rainfall[i - 1], 0, 120, height - 30, 40); // Start y-coordinate
					float y2 = height - 30 - map(rainfall[i], 0, 120, height - 30, 40); // End y-coordinate
	
					line(x1 + d, y1, x2, y2); // Draw line between points
				}
	
				for (int i = 0; i < months.length; i++) {
					float x = 40 + i * (d + spacing); // Adjusted x-coordinate with spacing between bars
					fill(map1(i, 0, months.length, 0, 255), 255, 255);
					ellipse(x, height - 30 - map(rainfall[i], 0, 120, height - 30, 40), 10, 10); // Draw a point for each data point
	
					textAlign(CENTER, CENTER);
					fill(255);
					text(months[i], x + d / 2, height - 20); // Use the calculated barWidth
				}
	
				line(30, height - 30, width, height - 30);
				break;
		}
	}
	



    public void keyPressed() {
        if (key == '0') {
            graph = 0; // Switch to bar chart mode
        } 
        else if (key == '1') 
        {
            graph = 1; // Switch to trend line mode
        }
        else if (key == '2')
        {
            graph = 2; // Switch to pie chart
        }
    }

public static void main(String[] args) {
	PApplet.main("ie.tudublin.Arrays");
}
}

        



