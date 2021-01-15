package jtexteditor.system;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class SystemResource {
	private static SystemResource systemResource;

	public static SystemResource getInstance() {
		if (systemResource == null) {
			systemResource = new SystemResource();
		}
		return systemResource;
	}

	private SystemResource() {
	}

	public String getHomeDir() {
		String string = System.getenv("HOME");
		if (string == null) {
		}
		return string;
	}

	public void printFile(File file) {
		if (!Desktop.getDesktop().isSupported(Desktop.Action.PRINT)) {
			JOptionPane.showMessageDialog(null,
					"This platform does not support print action ...",
					"Print Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Desktop.getDesktop().print(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}