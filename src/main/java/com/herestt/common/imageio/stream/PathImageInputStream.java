package com.herestt.common.imageio.stream;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Path;

import javax.imageio.stream.ImageInputStreamImpl;

public class PathImageInputStream extends ImageInputStreamImpl {

	public PathImageInputStream(Path input) {
		// TODO Auto-generated constructor stub
	}

	public PathImageInputStream(SeekableByteChannel input) {
		// TODO Auto-generated constructor stub
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