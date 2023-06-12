import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana extends JFrame {
	public int x=10,y=10;
	public int ultima_Presionada;
	public int largo=600,ancho=600;
	ArrayList<Rect> rectangulos;
    ArrayList<Pastilla> pastillas;
    ArrayList<Rect> portales;

	
	Rect player ;
	Timer timer ;
	Random r;

	int contadorPastillas;
    int segundos = 0;
    String tiempoFormateado;
    ImageIcon pacmanIcon;
    JLabel lblTiempo= new JLabel("00:00:00");
    JLabel contador;
	int[][] laberinto = {
    {1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	1,	4,	4,	4,	4,	4,	4,	1,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	3,	0,	0,	0,	3,	0,	1,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	3,	0,	0,	3,	0,	0,	3,	0,	0,	3,	0,	0,	3,	0,	0,	3,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	1,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1},
{1,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	1,	0,	0,	0,	3,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	3,	0,	1,	0,	1,	0,	3,	0,	1,	0,	0,	0,	0,	1,	0,	3,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1},
{1,	0,	3,	0,	1,	0,	3,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	3,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	0,	0,	3,	0,	0,	0,	0,	3,	0,	0,	0,	3,	0,	1},
{1,	0,	3,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	3,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	3,	0,	1},
{1,	0,	3,	0,	1,	0,	3,	0,	0,	0,	3,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	3,	0,	0,	0,	3,	0,	0,	3,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	3,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	3,	0,	1},
{1,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	3,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	1,	1,	0,	0,	3,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	3,	0,	0,	0,	0,	3,	0,	0,	0,	3,	0,	1},
{1,	0,	3,	0,	0,	0,	3,	0,	1,	0,	3,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	3,	0,	0,	0,	3,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0},
{1,	0,	0,	0,	0,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1},
{1,	0,	3,	0,	0,	0,	0,	0,	1,	0,	3,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	0,	3,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	3,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	3,	0,	0,	0,	3,	0,	1,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	0,	0,	0,	0,	1,	1,	1,	1,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1},
{1,	0,	3,	0,	1,	0,	3,	0,	0,	0,	3,	0,	0,	1,	0,	1,	0,	0,	3,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	3,	0,	1,	0,	0,	0,	1,	0,	0,	3,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	3,	0,	0,	0,	3,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1},
{1,	0,	3,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	3,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	3,	0,	1,	0,	0,	0,	1,	0,	0,	3,	0,	1},
{1,	0,	0,	0,	1,	1,	1,	1,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	3,	0,	0,	1,	1,	1,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	3,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	1,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	0,	0,	3,	0,	0,	0,	0,	3,	0,	0,	0,	3,	0,	1},
{1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	3,	0,	1,	0,	0,	0,	0,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	1,	1,	1,	0,	0,	0,	0,	1,	1,	1,	1,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	3,	0,	0,	0,	0,	3,	0,	1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	3,	0,	0,	1,	0,	1,	0,	0,	3,	0,	1,	0,	0,	0,	1,	0,	0,	3,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	1,	1,	0,	0,	0,	0,	1,	1,	1,	1,	1,	0,	0,	0,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	3,	0,	0,	0,	3,	0,	0,	3,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	0,	3,	0,	0,	0,	3,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	3,	0,	0,	0,	0,	3,	0,	1},
{1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
{1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	1,	4,	4,	4,	4,	4,	4,	1,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1}

			};
	
	
	
	public Ventana() {
		r = new Random();
	    contadorPastillas=0;
		timer = new Timer();
		segundos = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                segundos++;
                repaint();
            }
        }, 0, 1000);
        try {
             pacmanIcon = new ImageIcon(getClass().getResource("PacMan.gif"));
        } catch (Exception e) {
            // TODO: handle exception
        }
       
		rectangulos= new ArrayList<Rect>();
        portales= new ArrayList<Rect>();
        pastillas= new ArrayList<Pastilla>();
		this.setSize(700,725);
		this.setLocationRelativeTo(null);
		this.setTitle("Laberinto");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setResizable(false);

        // cargar el arreglo de pastillas con las coordenadas 
        
		for (int fila=0;fila<60;fila++) {
					for(int columna=0;columna<60;columna++) {
						if (laberinto[fila][columna]==3) { // si hay un uno en la matriz
							Pastilla pastilla=new Pastilla(columna*10,fila*10);
							pastillas.add(pastilla);
						}
                        if (laberinto[fila][columna]==4) { // cargar el arreglo de portales con las coordenadas
							Rect rect=new Rect(columna*10,fila*10);
							portales.add(rect);
						}
					}
				}
                
		JPanel juego= new JPanel();
		juego.setBackground(Color.decode("#030303"));
		juego.add(new MyGraphics());
		juego.addKeyListener(new KeyListener() {
		
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int xAnterior = x;
		        int yAnterior = y;
				int codigo= e.getKeyCode();
				ultima_Presionada=codigo;
				
				switch(codigo) {
				case 65:
				case 37:
					if(x>0) {
					x-=10;
					}
				break;
				case 87:
				case 38:
					if(y>0) {
					y-=10;
					}
				break;
				case 83:
				case 40:
					if(y<ancho-10) {
						y+=10;
					}
				break;
				case 68:
				case 39:
					if(x<largo-10 ) {
						x+=10;
					}
					break;
				}
				// ejemplo si hay colision con pastilla removerla del arreglo
				for (int i=0;i<pastillas.size();i++) {
					if (pastillas.get(i).colision(new Pastilla(x, y, 30, 30, Color.blue))) {
                        pastillas.remove(pastillas.get(i));
                        contadorPastillas++;
                        contador.setText("Score:"+contadorPastillas);
                        repaint();
			           
			        }
				}

                for (int i=0;i<rectangulos.size();i++) {
					if (rectangulos.get(i).colision(new Rect(x, y, 30, 30, Color.blue))) {
                       
			            // hay una colisión, restaurar las coordenadas del cuadrado
			            x = xAnterior;
			            y = yAnterior;
			        }
				}

                for (int i=0;i<portales.size();i++) {
					if (portales.get(i).colision(new Rect(x, y, 30, 30, Color.blue))) {
                       
			            // hay una colisión, restaurar las coordenadas del cuadrado
			            //x = -x;
                        if(y<100){
                            y +=560;   
                        }
                        else{
                            y -=560;
                        }
                        
			        }
				}
				
				if (contadorPastillas==114) {
					timer.cancel();
					JOptionPane.showMessageDialog(null, "Tu tiempo fue de: " + tiempoFormateado, "Pasaste!", 1, null);	
					timer = new Timer(); // nuevo timer para nuevo mapa
					segundos = 0;
			        timer.scheduleAtFixedRate(new TimerTask() {
			            public void run() {
			                segundos++;
			                repaint();
			            }
			        }, 0, 1000);
			        x=10;
			        y=10;
			        
					contadorPastillas=0;
                    contador.setText("Score:"+contadorPastillas);
                        
                    pastillas.clear();
                    for (int fila=0;fila<60;fila++) {
                            for(int columna=0;columna<60;columna++) {
                                if (laberinto[fila][columna]==3) { // si hay un uno en la matriz
                                    //g.setColor(Color.DARK_GRAY);
                                    
                                    Pastilla pastilla=new Pastilla(columna*10,fila*10);
                                    //g.fillRect(prueba.x, prueba.y, prueba.w,prueba.h);
                                    pastillas.add(pastilla);
                                }
                            }
                        }
					
					repaint();
					
				}
				juego.repaint();	
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.add(juego,BorderLayout.CENTER);
		
		
		JPanel panel= new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new FlowLayout());
		
		JPanel panel2= new JPanel();
		panel2.setBackground(Color.decode("#030303"));
		panel2.setLayout(new FlowLayout(0,200,0));
		lblTiempo.setFont((new Font("Century Gothic", Font.BOLD, 20)));
		lblTiempo.setForeground(Color.white);
		
		JButton btn1= new JButton("Reiniciar");
		btn1.setBackground(Color.decode("#030303"));
		btn1.setFont((new Font("Century Gothic", Font.BOLD, 20)));
		btn1.setForeground(Color.white);
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//rectangulos.clear();
				segundos=0;
				x=10;
				y=10;		
				long tiempoEnSegundos = segundos;
		        long horas = TimeUnit.SECONDS.toHours(tiempoEnSegundos);
		        tiempoEnSegundos -= TimeUnit.HOURS.toSeconds(horas);
		        long minutos = TimeUnit.SECONDS.toMinutes(tiempoEnSegundos);
		        tiempoEnSegundos -= TimeUnit.MINUTES.toSeconds(minutos);
		        long segundosRestantes = tiempoEnSegundos;
				
		        tiempoFormateado = String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
		        lblTiempo.setText(tiempoFormateado);
                
                contadorPastillas=0;
                contador.setText("Score:"+contadorPastillas);
                
		       pastillas.clear();
               for (int fila=0;fila<60;fila++) {
					for(int columna=0;columna<60;columna++) {
						if (laberinto[fila][columna]==3) { // si hay un uno en la matriz
							//g.setColor(Color.DARK_GRAY);
							
							Pastilla pastilla=new Pastilla(columna*10,fila*10);
							//g.fillRect(prueba.x, prueba.y, prueba.w,prueba.h);
							pastillas.add(pastilla);
						}
					}
				}
		        juego.setFocusable(true);
				juego.requestFocus();
				
				
				repaint();
			}
			
		});
		
		panel.add(btn1);

		this.add(panel,BorderLayout.SOUTH);
		lblTiempo.setBackground(Color.decode("#acbfb7"));
		panel2.add(lblTiempo);

        contador= new JLabel("Score:"+contadorPastillas);
        contador.setFont((new Font("Century Gothic", Font.BOLD, 20)));
		contador.setForeground(Color.white);
        panel2.add(contador);
        
		this.add(panel2,BorderLayout.NORTH);
		
		juego.setFocusable(true);
		juego.requestFocus();
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
	
	public class MyGraphics extends JComponent{
		MyGraphics(){
			setPreferredSize(new Dimension(ancho,largo)); 
		}
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			//
			long tiempoEnSegundos = segundos;
	        long horas = TimeUnit.SECONDS.toHours(tiempoEnSegundos);
	        tiempoEnSegundos -= TimeUnit.HOURS.toSeconds(horas);
	        long minutos = TimeUnit.SECONDS.toMinutes(tiempoEnSegundos);
	        tiempoEnSegundos -= TimeUnit.MINUTES.toSeconds(minutos);
	        long segundosRestantes = tiempoEnSegundos;

	        tiempoFormateado = String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
	        lblTiempo.setText(tiempoFormateado);
	        
	        
			g.setColor(Color.decode("#030303"));
			g.fillRect(0,0,ancho,largo);
			
			player= new Rect(x,y,30,30,Color.decode("#a95a52"));
			g.setColor(Color.decode("#ffff57"));
            try {
                g.drawImage(pacmanIcon.getImage(), player.x, player.y, player.w, player.h, null);
                
            } catch (Exception e) {
                g.fillOval(player.x, player.y, player.w, player.h);
            }
			
			 
			  //pintar el arreglo de pastillas ya con coordenadas incluidas
			for (Pastilla past : pastillas) {
                g.setColor(Color.decode("#fffeac"));
                g.fillOval(past.x, past.y, past.w,past.h);
            }
            
				
			for (int fila=0;fila<60;fila++) {
					for(int columna=0;columna<60;columna++) {
						if (laberinto[fila][columna]==1) {
							g.setColor(Color.decode("#5657ff"));
							
							Rect prueba=new Rect(columna*10,fila*10);
							g.fillRect(prueba.x, prueba.y, prueba.w,prueba.h);
							rectangulos.add(prueba);
						}
					}
				}
			
				
		}
	}
	
	public class Rect {
		int x=0;
		int y=0;
		int w=0;
		int h=0;
		Color c= Color.black;
		Rect(int x,int y, int w, int h, Color c){
			this.x=x;
			this.y=y;
			this.w=w;
			this.h=h;
			this.c=c;
		}
		Rect(int x,int y){
			this.x=x;
			this.y=y;
			this.w=10;
			this.h=10;
			this.c=Color.black;
		}
		
		public boolean colision(Rect target) {
			
			if(this.x<target.x + target.w &&
					this.x+this.w>target.x &&
					
					this.y<target.y+target.h &&
					this.y+this.h>target.y) {
				return true;
			}
			
			return false;
			
		}
	}
	
}