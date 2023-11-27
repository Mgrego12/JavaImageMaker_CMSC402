// M GREGOREK PROJECT ONE
// JFRame , main to make final Panel to show images.
// MAIN CLASS -- Image Maker sets image panel to frame and starts timer
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ImageMaker{
	public static void main(String[] args){
		
		final ImagePanel panel = new ImagePanel();
		JFrame frame = new JFrame("Matt's Graphics!");
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(350, 300));
		
		Dimension windowDimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setLocation((windowDimension.width - frame.getWidth()) / 2, (windowDimension.height - frame.getHeight()) / 2);
		frame.pack();
		Timer animationTimer;
		
		animationTimer = new Timer(2000, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent ae){
				
				if(panel.getFrameNumber() > 3){
					panel.setFrameNumber(0);
					}
				else{
					panel.setFrameNumber(panel.getFrameNumber() + 1);
					}
				panel.repaint();
				}
			}
		);
		frame.setVisible(true);
		animationTimer.start();
	}
} 