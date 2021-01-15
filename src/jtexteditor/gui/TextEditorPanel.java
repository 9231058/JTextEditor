package jtexteditor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import jtexteditor.filehandler.FileOpener;
import jtexteditor.filehandler.FileReader;
import jtexteditor.filehandler.FileWriter;
import jtexteditor.system.SystemResource;

public class TextEditorPanel extends JPanel {

	private static final long serialVersionUID = -6399390563043431244L;
	private JTextPane textPane;
	private JScrollPane scrollPane;

	private Charset charSet = StandardCharsets.UTF_8;
	private String fileLocation = SystemResource.getInstance().getHomeDir();
	private boolean isItalic;
	private boolean isBold;
	private Integer fontSize;
	private Font font;
	private FileOpener fileOpener;

	public TextEditorPanel(String fileLocation) {
		this.fileLocation = fileLocation;
		setPreferredSize(new Dimension(450, 400));
		setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		textPane = new JTextPane();
		textPane.setForeground(Color.ORANGE);
		textPane.setBackground(Color.BLACK);
		scrollPane.setViewportView(textPane);

		font = textPane.getFont();

		fileConf();
	}

	public void reConfigCharacterSet(Charset charSet) {
		this.charSet = charSet;
		fileConf();
	}

	public void setFgColor(Color fg) {
		textPane.setForeground(fg);
	}

	public void setBgColor(Color bg) {
		textPane.setBackground(bg);
	}

	public boolean isItalic() {
		return isItalic;
	}

	public void setItalic(boolean isItalic) {
		this.isItalic = isItalic;
		if (isItalic == true) {
			font = new Font(font.getFontName(), font.getStyle() + Font.ITALIC,
					font.getSize());
		} else {
			font = new Font(font.getName(), font.getStyle() - Font.ITALIC,
					font.getSize());
		}
		textPane.setFont(font);
	}

	public boolean isBold() {
		return isBold;
	}

	public void setBold(boolean isBold) {
		this.isBold = isBold;
		if (isBold == true) {
			font = new Font(font.getFontName(), font.getStyle() + Font.BOLD,
					font.getSize());
		} else {
			font = new Font(font.getFontName(), font.getStyle() - Font.BOLD,
					font.getSize());
		}
		textPane.setFont(font);
	}

	public Integer getFontSize() {
		return fontSize;
	}

	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
		font = new Font(font.getFontName(), font.getStyle(), fontSize);
		textPane.setFont(font);
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void print() {
		SystemResource.getInstance().printFile(fileOpener.getFile());
	}

	public void save(String fileLocation) {
		try {
			FileOpener fileOpener = new FileOpener(fileLocation, "WR+");
			FileWriter fileWriter = new FileWriter(fileOpener, charSet);
			fileWriter.setFileContent(textPane.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fileConf() {
		try {
			fileOpener = new FileOpener(fileLocation, "WR+");
			FileReader fileReader = new FileReader(fileOpener, charSet);
			textPane.setText(fileReader.getFileContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
