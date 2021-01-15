package jtexteditor.filehandler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileWriter {
	private FileOpener fileOpener;
	private Charset charset = StandardCharsets.UTF_8;

	public FileWriter(FileOpener fileOpener) {
		this.fileOpener = fileOpener;
	}

	public FileWriter(FileOpener fileOpener, Charset charset) {
		this(fileOpener);
		this.charset = charset;
	}

	public void setFileContent(String string) {
		byte[] br = string.getBytes(charset);
		try {
			fileOpener.truncate();
			fileOpener.getOutputStream().write(br);
			fileOpener.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
