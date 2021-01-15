package jtexteditor.filehandler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileReader {

	private FileOpener fileOpener;
	private Charset charSet = StandardCharsets.UTF_8;

	public FileReader(FileOpener fileOpener) {
		this.fileOpener = fileOpener;
	}

	public FileReader(FileOpener fileOpener, Charset charSet) {
		this(fileOpener);
		this.charSet = charSet;
	}

	public String getFileContent() {
		byte[] br = new byte[(int) fileOpener.getFileSize()];
		try {
			fileOpener.getInputStream().read(br);
			return new String(br, charSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
