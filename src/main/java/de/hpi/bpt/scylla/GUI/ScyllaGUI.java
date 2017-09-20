package de.hpi.bpt.scylla.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

import de.hpi.bpt.scylla.GUI.GlobalConfigurationPane.GlobalConfigurationPane;
/**
 * @author Leon Bein
 *
 */
@SuppressWarnings("serial")
public class ScyllaGUI extends JFrame {
	
	
	public static String DEFAULTFILEPATH = "samples\\";
	public static final String DESMOJOUTPUTPATH = "desmoj_output\\";

	

	public static final Color ColorField0 = new Color(45,112,145);
	public static final Color ColorField1 = new Color(240,240,240);
	public static final Color ColorField2 = new Color(255,255,255);
	public static final Color ColorBackground = new Color(0,142,185);

	private static GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static java.awt.Rectangle r = env.getMaximumWindowBounds();
	public static final int WIDTH = r.width;//1200
	public static final int HEIGHT = r.height;//900
	public static final double SCALE = ((double)HEIGHT)/900.0;

//	private static double SCALE = 1;
//	private static int WIDTH = 1200;//(int)(1200.0 * SCALE);
//	private static int HEIGHT = 900;//(int)(900 * SCALE);
	
	public static int STD = HEIGHT/24;
	public static int STD1 = WIDTH/32;
	public static int STD2 = WIDTH/48;
	public static int STD3 = HEIGHT/36;
	public static int STDHEI = 3*STD;
	public static int STDHEIH = STDHEI/2;
	
	
	public static final Dimension fileChooserDimension = new Dimension((int)(800.0*SCALE),(int)(500.0*SCALE));
	public static final Font fileChooserFont = new Font("Arial", Font.PLAIN, (int)(14.0*SCALE));

	public static final Font DEFAULTFONT = new Font("Arial", Font.PLAIN, (int)(16.0*SCALE));
	public static final Color DEFAULTFONT_COLOR = new Color(0,0,0);
	public static final Font TITLEFONT = new Font(DEFAULTFONT.getFontName(), Font.PLAIN, (int)(20.0*SCALE));
	public static final Color TITLEFONT_COLOR = new Color(255,255,255);
	public static final Font CONSOLEFONT = new Font("Consolas",Font.PLAIN,(int)(14.0*SCALE));


	
	public static final Insets LEFTMARGIN = new Insets(0, STD2, 0, 0);
	
	public static final ImageIcon ICON_PLUS = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/plus.png")),STD1/2,STDHEIH/2);
	public static final ImageIcon ICON_X = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/remove.png")),STD1/2,STDHEIH/2);
	public static final ImageIcon ICON_MORE = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/more.png")),STD1/2,STD1/2);
	
	public static final ImageIcon ICON_EXPAND = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/expand.png")),DEFAULTFONT.getSize(),DEFAULTFONT.getSize());
	public static final ImageIcon ICON_COLLAPSE = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/collapse.png")),DEFAULTFONT.getSize(),DEFAULTFONT.getSize());
	
	public static final ImageIcon ICON_OPTIONS = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/options.png")),STD1/2,STD1/2);
	
	public static final ImageIcon ICON_NEW = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/newfile.png")),TITLEFONT.getSize(),TITLEFONT.getSize());
	public static final ImageIcon ICON_SAVE = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/save.png")),TITLEFONT.getSize(),TITLEFONT.getSize());
	public static final ImageIcon ICON_SAVEAS = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/saveas.png")),TITLEFONT.getSize(),TITLEFONT.getSize());
	public static final ImageIcon ICON_OPEN = resizeIcon(new ImageIcon(ScyllaGUI.class.getResource("/GUI/open.png")),TITLEFONT.getSize(),TITLEFONT.getSize());
	

	private SimulationPane simulationPane;
	private GlobalConfigurationPane globalconfPane;
	private JTabbedPane contentPane;
	private PrintStream stdErr;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScyllaGUI frame = new ScyllaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public ScyllaGUI() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}

		setDefaultFont(ScyllaGUI.TITLEFONT);
		
		UIManager.put("ToolTip.background", ScyllaGUI.ColorField1);
		int border = (int)(1.5*SCALE);
		UIManager.put("ToolTip.border", BorderFactory.createMatteBorder(border,border,border,border, ScyllaGUI.ColorField1.darker()));
		UIManager.put("ToolTip.font", ScyllaGUI.DEFAULTFONT);


		UIManager.put("Button.background",ScyllaGUI.ColorField1);
		UIManager.put("Button.select",ScyllaGUI.ColorField1.darker());
		UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

		UIManager.put("Button.rollover",false);


		UIManager.put("TextField.background", ScyllaGUI.ColorField2);
		UIManager.put("TextField.foreground", ScyllaGUI.DEFAULTFONT_COLOR);
		
		UIManager.put("List.selectionBackground", ScyllaGUI.ColorField1);
		
		UIManager.put("ScrollBar.width", (int) ((int)UIManager.get("ScrollBar.width") * SCALE));
		
		UIManager.put("TabbedPane.font", ScyllaGUI.TITLEFONT);
		
		UIManager.put("ComboBox.background", ScyllaGUI.ColorField2);
		
		Locale.setDefault(Locale.ENGLISH);
		JComponent.setDefaultLocale(Locale.ENGLISH);

		//UIManager.put("Panel.background", ScyllaGUI.ColorBackground);
		
		
		
		
		setTitle("Scylla GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,WIDTH,HEIGHT);
		
	    if(WIDTH == r.getWidth() && HEIGHT == r.getHeight())setExtendedState(JFrame.MAXIMIZED_BOTH);

		simulationPane = new SimulationPane(this);
		globalconfPane = new GlobalConfigurationPane();
	    contentPane = new JTabbedPane(JTabbedPane.TOP);
		setContentPane(contentPane);
		contentPane.addTab("Simulation", simulationPane);
		contentPane.addTab("Global Configuration Editor", globalconfPane);
		
		globalconfPane.init();
		
		
//		JSeparator separator = new JSeparator();
//		separator.setBounds(600, 0, 1, 812);
//		contentPane.add(separator);
		
		//TODO System.setOut(simulationPane.getConsole().getOut());
    	stdErr = System.err;
		

		
	}
	
	public static ImageIcon resizeIcon(ImageIcon imageIcon,int w, int h) {
		Image img = imageIcon.getImage();
		Image scaled = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return new ImageIcon(scaled);
	}


	public PrintStream getStdErr() {
		return stdErr;
	}
	
	public static void setDefaultFont(Font font){
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		for(Object key : Collections.list(keys)){
	    	if (key.toString().endsWith(".font"))UIManager.put (key, font);
	    }
	}
	
}