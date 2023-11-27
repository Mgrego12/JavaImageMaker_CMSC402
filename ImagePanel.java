import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
/**
 * CMSC405 Computer Graphics
 * Matt gregorek August 29, 2022
 * PROJECT 1 
 * 
 * @author mattg
 *In this project you will create 3 simple, images or your choice and use Java 2D graphic methods to
rotate, scale and translate each of the images.

Requirements:
1. Using Netbeans or Eclipse, develop a Java 2D graphics application that creates 3 images. The
images should have the following specifications:

	a. Size: minimum 25x25 pixels, larger images are Okay
	b. Type: Color (consists of two or more colors)
	c. Simple form or shape (Hint: consider a letter or number, or even simple shapes such as
		crossing lines, rectangles, or circles
	d. You should generate the image inside of separate methods and store them as 2D arrays.
	
2. Use Java 2D graphics to display your original images.

3. For each image use the existing Java 2D graphics transformation methods to translate, rotate
	and scale each object. You should perform the following transformations on each image:
	a. Translate -5 in x direction, Translate +7 in the y direction.
	b. Rotate 45 counter clockwise.
	c. Rotate 90 clockwise
	d. Scale 2 times for the x component, scale 0.5 times for the y component
	e. Each of these transformations should be displayed in sequence with the images always
	
 */// ImagePanel class, extends JPanel, 

public class ImagePanel extends JPanel{
	
	private static int transitionX;
	private static int transitionY;
	private static double rotation;
	private static double scaleX;
	private static double scaleY;
	private int framesCounter;
	private ImageInfo myImage;
	private BufferedImage mImage, aImage, tImage;
	
	
	// Make ImagePanel Constructor, set all variables accordingly
	public ImagePanel(){
		transitionX = 0;
		transitionY = 0;
		rotation = 0.0;
		scaleX = 1.0;
		scaleY = 1.0;
		myImage = new ImageInfo();
		mImage = myImage.makeImage(ImageInfo.mLetter);
		aImage = myImage.makeImage(ImageInfo.aLetter);
		tImage = myImage.makeImage(ImageInfo.tLetter);
		}
	
	@Override
	protected void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.DARK_GRAY);
		g2.fillRect(0, 0, getWidth(), getHeight());
		makeAnimation(g2, -65, 65, -65, 65, true);
		
		AffineTransform afnTrans = g2.getTransform();
		System.out.println("Frame Number: " + framesCounter);
		
		
		//use switch case to go through transitions
		switch(framesCounter){
		
		case 0:
			transitionX = 0;
			transitionY = 0;
			scaleX = 1.0;
			scaleY = 1.0;
			rotation = 0;
			break;
			
		case 1:
			transitionX = -5;
			transitionY = 7;
			break;
			
		case 2:
			transitionX = -5;
			transitionY = 7;
			rotation = 45 * Math.PI / 180.0;
			break;
			
		case 3:
			transitionX = -5;
			transitionY = 7;
			rotation = (360 - 90) * Math.PI / 180.0;
			break;
			
		case 4:
			scaleX = 2 * scaleX;
			scaleY = 0.5 * scaleY;
			rotation = 0;
			break;
			
		default:break;
		}
		// graphics 2d, x , y , image, transformed image
		printImage(g2, -50, -50, mImage, afnTrans);
		printImage(g2, -10, -10, aImage, afnTrans);
		printImage(g2, 30, 30, tImage, afnTrans);
		}
	
	public int getFrameNumber(){
		return framesCounter;
		}
	public void setFrameNumber(int number){
		framesCounter = number;
		} 
	private void printImage(Graphics2D g2d, int x, int y,
			BufferedImage image, AffineTransform afnTrans){
		
		g2d.translate(transitionX, transitionY);
		g2d.translate(x, y);
		g2d.rotate(rotation);
		g2d.scale(scaleX, scaleY);
		g2d.drawImage(image, 0, 0, this);
		g2d.setTransform(afnTrans);
		}
	
	// animation starter class, 
	private void makeAnimation(Graphics2D g2, double left,
								double right, double bottom,
								double top, boolean isPreserve){
		int drawingAreaWidth = getWidth();
		int drawingAreaHeight = getHeight();
		double displayArea;
		double requiredArea;
		double expandedViewport;
		
		if(isPreserve){
			displayArea = Math.abs((double) drawingAreaHeight / drawingAreaWidth);
			requiredArea = Math.abs((bottom - top) / (right - left));
			if(displayArea > requiredArea)
			{
				expandedViewport = (bottom - top) * (displayArea / requiredArea - 1);
				bottom += expandedViewport / 2;
				top -= expandedViewport / 2;
				}
			else if(displayArea < requiredArea){
				expandedViewport = (right - left) * (requiredArea / displayArea - 1);
				right += expandedViewport / 2;
				left -= expandedViewport / 2;
				}
			}
		g2.scale(drawingAreaWidth / (right - left), drawingAreaHeight / (bottom - top));
		g2.translate(-left, -top);
		}
}