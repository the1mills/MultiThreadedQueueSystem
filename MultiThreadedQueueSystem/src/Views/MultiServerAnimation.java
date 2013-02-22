package Views;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import de.MainController;
import de.Server;

public class MultiServerAnimation extends JFrame implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public Vector<JPanel> panelVector;
	public Insets panelInsets;
	private ImageIcon image = null;
	private JPanel queuepanel = null;
	private int server = -1;
	private JLabel[] jlabelArray = null;
	
	public MultiServerAnimation(){
		
		panel = new JPanel();
		panel.setLayout(null);
		panelInsets = panel.getInsets();
		panel.setBackground(Color.white);
		this.add(panel);
		panelVector = new Vector<JPanel>(MainController.numberOfServers);
		//image = new ImageIcon("src/box.jpg");
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/3d-box.jpg"));
		} catch (IOException e) {
		}
		// buffimage = BufferedImage.resize(img, 200);
		Image img2 = (Image) img.getScaledInstance(15, 25,img.getType());
		image = new ImageIcon(img2);
		
		queuepanel = new JPanel();
		queuepanel.setSize(100,30);
		this.panel.add(queuepanel);
		queuepanel.setBounds(100, panel.getHeight()/2 + 250, 100, 60);
		queuepanel.setBackground(Color.magenta);
		Border b = BorderFactory.createBevelBorder(0);
		queuepanel.setBorder(b);	
		
		JPanel serverPanel = null;
		for(int i = 0; i < MainController.numberOfServers; i++ ){
			
			serverPanel = new JPanel();
			serverPanel.setSize(100,30);
			panelVector.add(serverPanel);
			this.panel.add(serverPanel);
			serverPanel.setBounds(450, i*500/MainController.numberOfServers + 50, 100, 30);
		}
		
		jlabelArray = new JLabel[MainController.numberOfServers];
		for(int i = 0; i < jlabelArray.length; i++ ){
			jlabelArray[i] = new JLabel(image);
		}
	}
	
	public void paint(Graphics g){
		
		
	}

	@Override
	public synchronized void update(Observable o, Object arg) {
		
		if(o instanceof Server){
			if(arg.equals(false)){
				server = ((Server)o).getServerId();
				runny();
				return;
			}
			else if(arg.equals(true)){
				int server = ((Server)o).getServerId();	
				JLabel x = jlabelArray[server];
				panelVector.get(server).setBackground(Color.pink);
				if(x != null){	
				x.setVisible(false);
				x.revalidate();
				panel.remove(x);
				x.revalidate();
				panel.revalidate();
				}
			}
		}
		
	}

	private void runny() {
		Runnable x = new Runnable(){

			 
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JLabel jl = jlabelArray[server];
				panel.add(jl);
				jl.setVisible(false);
				jl.setBounds(queuepanel.getX(), queuepanel.getX(), image.getIconWidth(), image.getIconHeight());
				Rectangle b = panelVector.get(server).getBounds();
				
				for(int i = 0; i < 15; i++){
				jl.setVisible(true);
				jl.setLocation(i*(b.getLocation().x-queuepanel.getX())/15 + queuepanel.getX(),i*(b.getLocation().y-queuepanel.getY())/15 + queuepanel.getY());
				try {
					
					Thread.sleep(23);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				panelVector.get(server).setBackground(Color.gray);
				
			}
			
		};
		
		x.run();
	}
}
