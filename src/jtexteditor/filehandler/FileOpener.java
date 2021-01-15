package jtexteditor.filehandler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class FileOpener {

	private String fileLocation;
	private File file;
	private String mode;
	private BufferedOutputStream bufferedOutputStream;
	private BufferedInputStream bufferedInputStream;
	private boolean isW = false;
	private boolean isR = false;
	private boolean isPlus = false;

	public FileOpener(String fileLocation, String mode) throws IOException {
		this.fileLocation = fileLocation;
		this.mode = mode;
		createFile();
		createMode();
		bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(
				file, true));
		bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
	}

	private void createFile() throws IOException {
		file = new File(fileLocation);
		if (file.exists()) {
			if (isR) {
				if (!file.canRead()) {
					throw new IOException("can not read the file");
				}
				if (isPlus) {
					if (!file.canWrite()) {
						throw new IOException("can not write the file");
					}
				}
			}
			if (isW) {
				if (!file.canWrite()) {
					throw new IOException("can not write the file");
				}
				if (isPlus) {
					if (!file.canRead()) {
						throw new IOException("can not read the file");
					}
				}
			}
		} else {
			file.createNewFile();
		}
	}

	private void createMode() {
		for (int i = 0; i < mode.length(); i++) {
			switch (mode.charAt(i)) {
			case 'w':
				isW = true;
			case 'W':
				isW = true;
			case 'r':
				isR = true;
			case 'R':
				isR = true;
			case '+':
				isPlus = true;
			}
		}
	}

	public InputStream getInputStream() {
		if (isR || isPlus) {
			return bufferedInputStream;
		}
		return null;
	}

	public OutputStream getOutputStream() {
		if (isW || isPlus) {
			try {
				bufferedOutputStream.flush();
				return bufferedOutputStream;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public BufferedWriter getPrintWriter() {
		if (isW) {
			try {
				return new BufferedWriter(new FileWriter(file, isPlus));
			} catch (IOException e) {
			}
		}
		return null;
	}

	public BufferedReader getReader() {
		if (isR) {
			try {
				return new BufferedReader(new FileReader(file));
			} catch (IOException e) {
			}
		}
		return null;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public long getFileSize() {
		return file.length();
	}

	public File getFile() {
		return file;
	}

	public void truncate() {
		try {
			new RandomAccessFile(file.getAbsolutePath(), "rw").setLength(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
