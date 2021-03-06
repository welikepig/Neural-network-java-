package cpe593_neuralnet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * This is the class to set a JFrame so that we can just test the file by click a button
 */

public class DrawWindow extends JFrame {  //Create a window for one test button
	private DrawingArea drawArea;
	public DrawWindow(NeuralNet nn) throws IOException {
		super("Drawing");
		setSize(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();	
		
		setVisible(true);
		JButton b=new JButton("Test");
		b.addActionListener(new ActionListener() {//A JButton to read the image and process 
			public void actionPerformed(ActionEvent e) {
				BufferedImage bimage = null;
				try {//Load the image file 
					bimage = ImageIO.read(new File("5.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					drawArea = new DrawingArea();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				drawArea.setBackground(Color.WHITE);
				drawArea.repaint();
				c.add(drawArea, BorderLayout.NORTH);
		        double[] result=new double[28*28];
		        for(int i=0;i<=27;i++){
		        //Do some pre-processing to the image we read and set it same as the training data
					for(int j=0;j<=27;j++){
						if(bimage.getRGB(j, i)==-16777216){
							result[i * 28 + j ]=1;
							System.out.print(1+"");
						}
							
						else{
							System.out.print(0+"");
							result[i * 28 + j ]=0;
						}
					}System.out.println();
				}
		        System.out.println("****************-------******");
		        int numberI=nn.test(result);
		        //Call the test algorithm in NeuranNet to get the output
			
		        		
		        System.out.println("Final result is:"+numberI);
			}
		});
		c.add(BorderLayout.CENTER,b);
	}
}