package jtexteditor.system;

import jtexteditor.gui.TextEditorFrame;

public class TextEditorInit implements Runnable {

	private static TextEditorInit textEditorInit;

	public static TextEditorInit getInstance() {
		if (textEditorInit == null) {
			textEditorInit = new TextEditorInit();
		}
		return textEditorInit;
	}

	private TextEditorInit() {
	}

	@Override
	public void run() {
		TextEditorFrame.getInstance().setVisible(true);
	}

}
