package com.herestt.common.imageio.stream;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.stream.ImageInputStreamImpl;

public class PathImageInputStream extends ImageInputStreamImpl {

	private SeekableByteChannel sbc;
	
	public PathImageInputStream(Path input) throws IOException {
		this(input == null ? null : Files.newByteChannel(input));
	}

	public PathImageInputStream(SeekableByteChannel input) {
		if(input == null)
			throw new IllegalArgumentException();
		sbc = input;
	}

	@Override
	public int read() throws IOException {
		checkClosed();
		bitOffset = 0;
		ByteBuffer dst = ByteBuffer.allocate(Byte.SIZE / 8);
		if(sbc.read(dst) == -1)
			return -1;
		streamPos++;
		dst.flip();
		return (dst.get() & 0xff);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		checkClosed();
		bitOffset = 0;
		ByteBuffer wrapper = ByteBuffer.wrap(b, off, len);
		wrapper.order(getByteOrder());
		int count = 0;
		while(wrapper.hasRemaining())
			count = sbc.read(wrapper);
		streamPos += count;
		return count;
	}
	
	@Override
	public void seek(long pos) throws IOException {
		super.seek(pos);
		sbc.position(pos);
	}
	
	@Override
	public int skipBytes(int n) throws IOException {
		int count = super.skipBytes(n);
		sbc.position(getStreamPosition());
		return count;
	}
	
	@Override
	public long skipBytes(long n) throws IOException {
		long count = super.skipBytes(n);
		sbc.position(getStreamPosition());
		return count;
	}
	
	@Override
	public long length() {
		try {
			return sbc.size();
		} catch (IOException e) {
			return super.length();
		}
	}
	
	@Override
	public void reset() throws IOException {
		super.reset();
		sbc.position(getStreamPosition());
	}
	
	@Override
	public void close() throws IOException {
		super.close();
		if(sbc != null && sbc.isOpen())			
			sbc.close();
	}
}