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
                background(0);
                float wT = (width - 80) / (float) (months.length - 1); // Adjusted width for trend line
                float xT = 40; // Initial x-coordinate for trend line

                stroke(255); // Set stroke color to white for the trend line
                strokeWeight(2); // Set the thickness of the line

                // Draw the trend line
                for (int i = 0; i < months.length - 1; i++) {
                    float x1 = xT + i * wT;
                    float y1 = height - 30 - map(rainfall[i], 0, max(rainfall), height - 30, 40);
                    float x2 = xT + (i + 1) * wT;
                    float y2 = height - 30 - map(rainfall[i + 1], 0, max(rainfall), height - 30, 40);

                    line(x1, y1, x2, y2);

                    textAlign(CENTER, CENTER);
                    fill(255);
                    text(months[i], x1 + wT / 2, height - 20);
                }

                textAlign(RIGHT);

                for (int i = 0; i <= 120; i += 10) {
                    float y = map(i, 0, 120, height - 30, 40);
                    fill(255);
                    text(i, 30, y);

                    // Draw tick marks
                    float tickX = 35; // X position of tick
                    float tickLength = 5; // Length of tick
                    line(tickX, y, tickX + tickLength, y); // Draw tick mark
                }

                textAlign(CENTER);

                text("Rainfall line chart", width / 2, 20);
                stroke(255);
                line(40, 40, 40, height - 30); // Draw y-axis line
                line(40, height - 30, width - 55, height - 30); // Draw x-axis line

                break;

            case 2:
                background(255);
                float[] data = {30, 20, 45, 15}; // Example data
                float diameter = min(width, height) - 40;
                float lastAngle = 0;

                for (int i = 0; i < data.length; i++) {
                    float gray = map(i, 0, data.length, 0, 255);
                    fill(gray, 200, 200);

                    float angle = map(data[i], 0, sum(data), 0, TWO_PI);
                    arc(width / 2, height / 2, diameter, diameter, lastAngle, lastAngle + angle);

                    lastAngle += angle;

                    // Label
                    textAlign(CENTER, CENTER);
                    fill(0);
                    float labelAngle = lastAngle - angle / 2;
                    float labelRadius = diameter / 2 + 20; // Adjust the radius for label positioning
                    text("Slice " + i, width / 2 + labelRadius * cos(labelAngle), height / 2 + labelRadius * sin(labelAngle));
                }

                float sum(float[] arr) {
                    float total = 0;
                    for (float value : arr) {
                        total += value;
                    }
                    return total;
                }

                break;
        }
    }

    public void keyPressed() {
        if (key == '0') {
            graph = 0; // Switch to bar chart mode
        } else if (key == '1') {
            graph = 1; // Switch to trend line mode
        } else if (key == '2') {
            graph = 2; // Switch to pie chart
        }
    }

    public static void main(String[] args) {
        PApplet.main("ie.tudublin.Arrays");
    }
}


        



