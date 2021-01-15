package jtexteditor.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jtexteditor.gui.dialog.ColorDialog;
import jtexteditor.gui.dialog.EncodingDialog;
import jtexteditor.system.SystemResource;

public class TextEditorFrame extends JFrame {

	private static final long serialVersionUID = 29979719490843488L;
	private static TextEditorFrame textEditorFrame;
	private OpenHandler openHandler;
	private SaveHandler saveHandler;
	private CharsetHandler charsetHandler;
	private ColorHandler colorHandler;
	private TabHandler tabHandler;
	private ItalicHandler italicHandler;
	private BoldHandler boldHandler;
	private PrintHandler printHandler;
	private FontSizeHandler fontSizeHandler;
	private CloseTabHandler closeTabHandler;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private ArrayList<TextEditorPanel> textEditorPanels;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu viewMenu;
	private JMenuItem characterSetMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem colorMenuItem;
	private JMenuItem printMenuItem;
	private JMenuItem quitMenuItem;
	private JComboBox<Integer> fontSizeComboBox;
	private JButton saveButton;
	private JButton openButton;
	private JButton printButton;
	private JButton closeTabButton;
	private JToggleButton italicButton;
	private JToggleButton boldButton;

	public static TextEditorFrame getInstance() {
		if (textEditorFrame == null) {
			textEditorFrame = new TextEditorFrame();
		}
		return textEditorFrame;
	}

	private TextEditorFrame() {
		super("Java Text Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		openHandler = new OpenHandler();
		saveHandler = new SaveHandler();
		charsetHandler = new CharsetHandler();
		colorHandler = new ColorHandler();
		tabHandler = new TabHandler();
		italicHandler = new ItalicHandler();
		boldHandler = new BoldHandler();
		printHandler = new PrintHandler();
		fontSizeHandler = new FontSizeHandler();
		closeTabHandler = new CloseTabHandler();

		tabbedPane = new JTabbedPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tabbedPane, 50,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tabbedPane, 0,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tabbedPane, 0,
				SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tabbedPane, 0,
				SpringLayout.SOUTH, contentPane);
		add(tabbedPane);
		tabbedPane.addChangeListener(tabHandler);

		textEditorPanels = new ArrayList<>();

		menuBar = new JMenuBar();
		add(menuBar);

		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		menuBar.add(fileMenu);

		viewMenu = new JMenu("View");
		viewMenu.setMnemonic('V');
		menuBar.add(viewMenu);

		characterSetMenuItem = new JMenuItem("Set Encodding");
		fileMenu.add(characterSetMenuItem);
		characterSetMenuItem.addActionListener(charsetHandler);

		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setMnemonic('S');
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_DOWN_MASK));
		fileMenu.add(saveMenuItem);
		saveMenuItem.addActionListener(saveHandler);

		openMenuItem = new JMenuItem("Open");
		openMenuItem.setMnemonic('O');
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				KeyEvent.CTRL_DOWN_MASK));
		fileMenu.add(openMenuItem);
		openMenuItem.addActionListener(openHandler);

		printMenuItem = new JMenuItem("Print");
		printMenuItem.setMnemonic('P');
		fileMenu.add(printMenuItem);
		printMenuItem.addActionListener(printHandler);

		quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.setMnemonic('Q');
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				KeyEvent.CTRL_DOWN_MASK));
		fileMenu.add(quitMenuItem);

		colorMenuItem = new JMenuItem("Color");
		colorMenuItem.setMnemonic('C');
		viewMenu.add(colorMenuItem);
		colorMenuItem.addActionListener(colorHandler);

		fontSizeComboBox = new JComboBox<>();
		sl_contentPane.putConstraint(SpringLayout.EAST, fontSizeComboBox, 0,
				SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, fontSizeComboBox, 0,
				SpringLayout.NORTH, tabbedPane);
		for (int i = 0; i < 10; i++) {
			fontSizeComboBox.addItem(new Integer((i + 1) * 10));
		}
		fontSizeComboBox.addItemListener(fontSizeHandler);
		contentPane.add(fontSizeComboBox);

		saveButton = new JButton();
		saveButton.setIcon(new ImageIcon("./IconFolder/saveIcon.png"));
		sl_contentPane.putConstraint(SpringLayout.WEST, saveButton, 0,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, saveButton, 20,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, saveButton, 0,
				SpringLayout.NORTH, tabbedPane);
		saveButton.setPreferredSize(new Dimension(30, 30));
		saveButton.setToolTipText("Save");
		saveButton.addActionListener(saveHandler);
		contentPane.add(saveButton);

		openButton = new JButton();
		openButton.setIcon(new ImageIcon("./IconFolder/openIcon.png"));
		sl_contentPane.putConstraint(SpringLayout.WEST, openButton, 0,
				SpringLayout.EAST, saveButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, openButton, 20,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, openButton, 0,
				SpringLayout.NORTH, tabbedPane);
		openButton.setPreferredSize(new Dimension(30, 30));
		openButton.addActionListener(openHandler);
		openButton.setToolTipText("Open");
		contentPane.add(openButton);

		printButton = new JButton();
		printButton.setIcon(new ImageIcon("./IconFolder/printIcon.png"));
		sl_contentPane.putConstraint(SpringLayout.WEST, printButton, 0,
				SpringLayout.EAST, openButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, printButton, 0,
				SpringLayout.NORTH, openButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, printButton, 0,
				SpringLayout.SOUTH, openButton);
		printButton.setPreferredSize(new Dimension(30, 30));
		printButton.addActionListener(printHandler);
		printButton.setToolTipText("Print");
		contentPane.add(printButton);

		closeTabButton = new JButton();
		closeTabButton.setIcon(new ImageIcon("./IconFolder/closeTabIcon.png"));
		sl_contentPane.putConstraint(SpringLayout.WEST, closeTabButton, 0,
				SpringLayout.EAST, printButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, closeTabButton, 0,
				SpringLayout.NORTH, printButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, closeTabButton, 0,
				SpringLayout.SOUTH, printButton);
		closeTabButton.setPreferredSize(new Dimension(30, 30));
		closeTabButton.setToolTipText("Close Tab");
		closeTabButton.addActionListener(closeTabHandler);
		contentPane.add(closeTabButton);

		italicButton = new JToggleButton();
		italicButton.setIcon(new ImageIcon("./IconFolder/italicIcon.png"));
		sl_contentPane.putConstraint(SpringLayout.WEST, italicButton, 50,
				SpringLayout.EAST, closeTabButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, italicButton, 0,
				SpringLayout.NORTH, closeTabButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, italicButton, 0,
				SpringLayout.SOUTH, closeTabButton);
		italicButton.setPreferredSize(new Dimension(30, 30));
		italicButton.addActionListener(italicHandler);
		italicButton.setToolTipText("Italic");
		contentPane.add(italicButton);

		boldButton = new JToggleButton();
		boldButton.setIcon(new ImageIcon("./IconFolder/boldIcon.png"));
		sl_contentPane.putConstraint(SpringLayout.WEST, boldButton, 0,
				SpringLayout.EAST, italicButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, boldButton, 0,
				SpringLayout.NORTH, italicButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, boldButton, 0,
				SpringLayout.SOUTH, italicButton);
		boldButton.setPreferredSize(new Dimension(30, 30));
		boldButton.addActionListener(boldHandler);
		boldButton.setToolTipText("Bold");
		contentPane.add(boldButton);

	}

	private class OpenHandler implements ActionListener {

		private String fileLocation = SystemResource.getInstance().getHomeDir();

		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser fileChooser = new JFileChooser(fileLocation);
			fileChooser.showOpenDialog(TextEditorFrame.this);
			if (fileChooser.getSelectedFile() == null) {
				return;
			}
			fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
			textEditorPanels.add(new TextEditorPanel(fileLocation));
			tabbedPane.addTab(fileChooser.getSelectedFile().getName(),
					textEditorPanels.get(textEditorPanels.size() - 1));
		}
	}

	private class SaveHandler implements ActionListener {

		private String fileLocation = ".";

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				fileLocation = textEditorPanels.get(
						tabbedPane.getSelectedIndex()).getFileLocation();

			} catch (IndexOutOfBoundsException exception) {
				JOptionPane.showMessageDialog(TextEditorFrame.this,
						"There is no opened tab", "No Tab Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			JFileChooser fileChooser = new JFileChooser(fileLocation);
			fileChooser.showSaveDialog(TextEditorFrame.this);
			if (fileChooser.getSelectedFile() == null) {
				return;
			}
			fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
			textEditorPanels.get(tabbedPane.getSelectedIndex()).save(
					fileLocation);
		}
	}

	private class CharsetHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (tabbedPane.getTabCount() == 0) {
				return;
			}
			EncodingDialog encodingDialog = new EncodingDialog();
			encodingDialog.setVisible(true);
			System.err.println(encodingDialog.getSelectedCharset());
			try {
				textEditorPanels.get(tabbedPane.getSelectedIndex())
						.reConfigCharacterSet(
								encodingDialog.getSelectedCharset());
			} catch (IndexOutOfBoundsException exception) {
				JOptionPane.showMessageDialog(TextEditorFrame.this,
						"There is no opened tab", "No Tab Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class ColorHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (tabbedPane.getTabCount() == 0) {
				return;
			}
			ColorDialog colorDialog = new ColorDialog();
			colorDialog.setVisible(true);
			try {
				textEditorPanels.get(tabbedPane.getSelectedIndex()).setFgColor(
						colorDialog.getFgColor());
				textEditorPanels.get(tabbedPane.getSelectedIndex()).setBgColor(
						colorDialog.getBgColor());
			} catch (IndexOutOfBoundsException exception) {
				JOptionPane.showMessageDialog(TextEditorFrame.this,
						"There is no opened tab", "No Tab Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class PrintHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				textEditorPanels.get(tabbedPane.getSelectedIndex()).print();
			} catch (IndexOutOfBoundsException exception) {
				JOptionPane.showMessageDialog(TextEditorFrame.this,
						"There is no opened tab", "No Tab Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class ItalicHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				textEditorPanels.get(tabbedPane.getSelectedIndex()).setItalic(
						italicButton.isSelected());
			} catch (IndexOutOfBoundsException exception) {
				JOptionPane.showMessageDialog(TextEditorFrame.this,
						"There is no opened tab", "No Tab Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class BoldHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				textEditorPanels.get(tabbedPane.getSelectedIndex()).setBold(
						boldButton.isSelected());
			} catch (IndexOutOfBoundsException exception) {
				JOptionPane.showMessageDialog(TextEditorFrame.this,
						"There is no opened tab", "No Tab Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class TabHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent event) {
			int index = tabbedPane.getSelectedIndex();
			try {
				italicButton
						.setSelected(textEditorPanels.get(index).isItalic());
				boldButton.setSelected(textEditorPanels.get(index).isBold());
				fontSizeComboBox.setSelectedItem(textEditorPanels.get(index)
						.getFontSize());
			} catch (IndexOutOfBoundsException exception) {

			}
		}
	}

	private class FontSizeHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					textEditorPanels.get(tabbedPane.getSelectedIndex())
							.setFontSize((Integer) event.getItem());
				} catch (IndexOutOfBoundsException exception) {
					JOptionPane.showMessageDialog(TextEditorFrame.this,
							"There is no opened tab", "No Tab Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private class CloseTabHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				tabbedPane.remove(tabbedPane.getSelectedIndex());
			} catch (IndexOutOfBoundsException exception) {
				JOptionPane.showMessageDialog(TextEditorFrame.this,
						"There is no opened tab", "No Tab Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
