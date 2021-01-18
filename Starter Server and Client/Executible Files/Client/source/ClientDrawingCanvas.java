import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.net.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class ClientDrawingCanvas extends PApplet {

// 2B: Shared drawing canvas (Client)



Client c; 
String input;
int data[]; 

public void setup() { 
   
  background(204);
  stroke(0);
  frameRate(5); // Slow it down a little
  // Connect to the server’s IP address and port­
  c = new Client(this, "127.0.0.1", 12345); // Replace with your server’s IP and port
} 

public void draw() {         
  if (mousePressed == true) {
    // Draw our line
    stroke(255);
    line(pmouseX, pmouseY, mouseX, mouseY); 
    // Send mouse coords to other person
    c.write(pmouseX + " " + pmouseY + " " + mouseX + " " + mouseY + "\n");
  }

  // Receive data from server
  if (c.available() > 0) { 
    input = c.readString(); 
    input = input.substring(0,input.indexOf("\n"));  // Only up to the newline
    data = PApplet.parseInt(split(input, ' '));  // Split values into an array
    // Draw line using received coords
    stroke(0);
     line(data[0], data[1], data[2], data[3]); 
  } 
}
  public void settings() {  size(450, 255); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ClientDrawingCanvas" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
