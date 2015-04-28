package com.herestt.common.imageio.stream;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Path;

import javax.imageio.stream.ImageOutputStreamImpl;

public class PathImageOutputStream extends ImageOutputStreamImpl {

	public PathImageOutputStream(Path output) {
		// TODO Auto-generated constructor stub
	}

	public PathImageOutputStream(SeekableByteChannel output) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
}