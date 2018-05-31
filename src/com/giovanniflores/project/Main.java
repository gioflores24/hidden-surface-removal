package com.giovanniflores.project;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import com.giovanniflores.shapehandling.*;
/**
 * 
 * @author giovanniflores
 *
 */
public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControlPanel controlPanel;
	private JPanel contentPane;
	private GraphicsPanel graphicsPanel;

	// scale workings
	private JLabel scaleLabel;

	private JTextField xSField;
	private JTextField ySField;
	private JTextField zSField;
	private JButton scale;
	// translate workings
	private JLabel translateLabel;

	private JTextField xDimField;
	private JTextField yDimField;
	private JTextField zDimField;
	private JButton translate;
	// rotation workings
	private JLabel rotationLabel;
	private JLabel angleLabel;
	private JTextField angleInput;

	private JTextField arbX;
	private JTextField arbY;
	private JTextField arbZ;

	private JButton arbitrary;
	private JButton openFile;

	private JRadioButton center;
	private JRadioButton origin;

	private JButton reset;

	Shape sh;
	boolean fileOpened = false;
	File curFile = null;

	public Main() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setVisible(true);
		setContentPane(contentPane);

		controlPanel = new ControlPanel();
		controlPanel.setVisible(true);

		graphicsPanel = new GraphicsPanel();
		graphicsPanel.setVisible(true);

		contentPane.add(controlPanel, BorderLayout.EAST);
		contentPane.add(graphicsPanel, BorderLayout.CENTER);
	}

	public class GraphicsPanel extends JPanel {
		/**
		 * 
		 */
		public GraphicsPanel() {
			addMouseListener(new MyMouseListener());
		}

		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {
			super.paint(g);
			if (g instanceof Graphics2D) {
				Graphics2D g2d = (Graphics2D) g;
				int h = this.getHeight();
				int w = this.getWidth();
				g2d.setColor(Color.BLACK);
				g2d.drawRect(0, 0, w - 1, h - 1);
				if (fileOpened) {

					sh.draw(g2d, sh.getMat());
				}

			}
		}
	}

	public class MyMouseListener implements MouseInputListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class ControlPanel extends JPanel {
		/**
		 * Sets up the GUI for handling our shape. 
		 */
		private static final long serialVersionUID = 1L;

		public ControlPanel() {
			ButtonListener buttonListener = new ButtonListener();

			this.setLayout(new GridLayout(21, 1, 5, 5));
			scaleLabel = new JLabel("Scaling (x,y,z)");
			this.add(scaleLabel, 0);
			xSField = new JTextField("2");
			this.add(xSField, 1);
			ySField = new JTextField("2");
			this.add(ySField, 2);
			zSField = new JTextField("2");
			this.add(zSField, 3);
			scale = new JButton("Scale");
			this.add(scale, 4);
			scale.addActionListener(buttonListener);

			translateLabel = new JLabel("Translation (x,y,z)");
			this.add(translateLabel, 5);
			xDimField = new JTextField("100");
			this.add(xDimField, 6);
			yDimField = new JTextField("100");
			this.add(yDimField, 7);
			zDimField = new JTextField("100");
			this.add(zDimField, 8);
			translate = new JButton("Translate");
			this.add(translate, 9);
			translate.addActionListener(buttonListener);

			rotationLabel = new JLabel("Rotation");

			this.add(rotationLabel, 10);
			angleLabel = new JLabel("Angle");
			this.add(angleLabel, 11);
			angleInput = new JTextField("45");
			this.add(angleInput, 12);

			arbX = new JTextField("1");
			this.add(arbX, 13);
			arbY = new JTextField("1");
			this.add(arbY, 14);
			arbZ = new JTextField("1");
			this.add(arbZ, 15);
			arbitrary = new JButton("Arbitrary");
			this.add(arbitrary, 16);
			arbitrary.addActionListener(buttonListener);
			openFile = new JButton("Read File");
			this.add(openFile, 17);
			openFile.addActionListener(buttonListener);

			center = new JRadioButton("Center");
			center.addActionListener(buttonListener);
			this.add(center, 18);

			origin = new JRadioButton("Origin");
			origin.setSelected(true);
			origin.addActionListener(buttonListener);
			this.add(origin, 19);

			ButtonGroup group = new ButtonGroup();

			group.add(center);
			group.add(origin);

			reset = new JButton("Reset");
			this.add(reset, 20);
			reset.addActionListener(buttonListener);

		}
	}

	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Read File")) {

				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("/Users/giovanniflores/Downloads/"));
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

				if (fc.showOpenDialog(openFile) == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					curFile = file;
					try {
						sh = new Shape(file);
						fileOpened = true;
						graphicsPanel.repaint();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
			if (fileOpened) {
				if (e.getActionCommand().equals("Translate")) {

					// get what's in the scaling (x,y,z) text.
					double passableTX = Double.parseDouble(xDimField.getText());
					double passableTY = Double.parseDouble(yDimField.getText());
					double passableTZ = Double.parseDouble(zDimField.getText());
					try {
						sh.setMat(AffineTransformations.translate(passableTX, passableTY, passableTZ, sh.getMat()));

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					graphicsPanel.repaint();

				} else if (e.getActionCommand().equals("Scale")) {
					double passableSX = Double.parseDouble(xSField.getText());
					double passableSY = Double.parseDouble(ySField.getText());
					double passableSZ = Double.parseDouble(zSField.getText());
					try {
						double oldXCenter = sh.getMat()[0][8];
						double oldYCenter = sh.getMat()[1][8];
						double oldZCenter = sh.getMat()[2][8];
						sh.setMat(AffineTransformations.toOrigin(oldXCenter, oldYCenter, oldZCenter, sh.getMat()));
						sh.setMat(AffineTransformations.scale(passableSX, passableSY, passableSZ, sh.getMat()));
						sh.setMat(AffineTransformations.back(oldXCenter, oldYCenter, oldZCenter, sh.getMat()));

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					graphicsPanel.repaint();
				}

				else if (e.getActionCommand().equals("Arbitrary")) {

					try {
						if (origin.isSelected()) {
							sh.setMat(AffineTransformations.rotateAboutArbitrary(Double.parseDouble(arbX.getText()),
									Double.parseDouble(arbY.getText()), Double.parseDouble(arbZ.getText()), sh.getMat(),
									Double.parseDouble(angleInput.getText()), true));

						} else if (center.isSelected()) {
							sh.setMat(AffineTransformations.rotateAboutArbitrary(Double.parseDouble(arbX.getText()),
									Double.parseDouble(arbY.getText()), Double.parseDouble(arbZ.getText()), sh.getMat(),
									Double.parseDouble(angleInput.getText()), false));
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					graphicsPanel.repaint();
				}

				else if (e.getActionCommand().equals("Reset")) {
					try {
						sh = new Shape(curFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					graphicsPanel.repaint();
				}
			}

		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
