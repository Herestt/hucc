package com.herestt.common.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Objects;
import java.util.Set;

/**
 * A file channel that allows data conversion.
 * 
 * @author Herestt
 *
 */
public class FileContentChannel extends FileChannel
		implements NativeConvertibleChannel<FileContentChannel>, SkippableChannel<FileContentChannel> {
	
	private FileChannel channel;
	
	private ByteOrder order = ByteOrder.BIG_ENDIAN;
	
	private FileContentChannel(FileChannel channel) {
		this.channel = channel;
	}
	
	public static FileContentChannel open(Path path, Set<? extends OpenOption> options,
            FileAttribute<?>... attrs) throws IOException {
		FileChannel channel = FileChannel.open(path, options, attrs);
		return new FileContentChannel(channel);
	}
	
	 public static FileContentChannel open(Path path, OpenOption... options)
		        throws IOException {
		FileChannel channel = FileChannel.open(path, options);
		return new FileContentChannel(channel);
	}
	
	@Override
	public int read(ByteBuffer dst) throws IOException {
		Objects.requireNonNull(dst);
		dst.order(order);
		return channel.read(dst);
	}

	@Override
	public long read(ByteBuffer[] dsts, int offset, int length)
			throws IOException {
		for(ByteBuffer bb : dsts) {
			Objects.requireNonNull(bb);
			bb.order(order);
		}
		return channel.read(dsts, offset, length);
	}

	@Override
	public int write(ByteBuffer src) throws IOException {
		return write(src, 0);
	}

	@Override
	public long write(ByteBuffer[] srcs, int offset, int length)
			throws IOException {
		for(ByteBuffer bb : srcs) {
			Objects.requireNonNull(bb);
			bb.order(order);
		}
		return channel.write(srcs, offset, length);
	}

	@Override
	public long position() throws IOException {
		return channel.position();
	}

	@Override
	public FileChannel position(long newPosition) throws IOException {
		return channel.position(newPosition);
	}

	@Override
	public long size() throws IOException {
		return channel.size();
	}

	@Override
	public FileChannel truncate(long size) throws IOException {
		return channel.truncate(size);
	}

	@Override
	public void force(boolean metaData) throws IOException {
		channel.force(metaData);
		
	}

	@Override
	public long transferTo(long position, long count, WritableByteChannel target)
			throws IOException {
		return channel.transferTo(position, count, target);
	}

	@Override
	public long transferFrom(ReadableByteChannel src, long position, long count)
			throws IOException {
		return channel.transferFrom(src, position, count);
	}

	@Override
	public int read(ByteBuffer dst, long position) throws IOException {
		Objects.requireNonNull(dst);
		dst.order(order);
		return channel.read(dst, position);
	}

	@Override
	public int write(ByteBuffer src, long position) throws IOException {
		Objects.requireNonNull(src);
		src.order(order);
		return channel.write(src, position);
	}

	@Override
	public MappedByteBuffer map(MapMode mode, long position, long size)
			throws IOException {
		return channel.map(mode, position, size);
	}

	@Override
	public FileLock lock(long position, long size, boolean shared)
			throws IOException {
		return channel.lock(position, size, shared);
	}

	@Override
	public FileLock tryLock(long position, long size, boolean shared)
			throws IOException {
		return channel.tryLock(position, size, shared);
	}

	@Override
	protected void implCloseChannel() throws IOException {
		if(channel != null)
			channel.close();
	}

	@Override
	public FileContentChannel order(ByteOrder order) {
		this.order = order;
		return this;
	}

	@Override
	public ByteOrder order() {
		return order;
	}
	
	private ByteBuffer read(int length) throws IOException {
		if(length < 0)
			throw new IllegalArgumentException();
		ByteBuffer dst = ByteBuffer.allocateDirect(length);
		while(dst.hasRemaining())
			read(dst);
		dst.flip();
		return dst;
	}
	
	@Override
	public int readUShort() throws IOException {
		int val = readShort();
		return val & 0x0000FFFF;
	}

	@Override
	public long readUInt() throws IOException {
		long val = readInt();
		return val & 0x00000000FFFFFFFFL;
	}

	@Override
	public void writeUShort(int s) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeUInt(long l) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public byte readByte() throws IOException {
		return read(Byte.BYTES).get();
	}

	@Override
	public boolean readBoolean() throws IOException {
		return (read(Byte.BYTES).get() == 0)? false : true;
	}

	@Override
	public short readShort() throws IOException {
		return read(Short.BYTES).getShort();
	}

	@Override
	public int readInt() throws IOException {
		return read(Integer.BYTES).getInt();
	}

	@Override
	public long readLong() throws IOException {
		return read(Long.BYTES).getLong();
	}

	@Override
	public float readFloat() throws IOException {
		return read(Float.BYTES).getFloat();
	}

	@Override
	public double readDouble() throws IOException {
		return read(Double.BYTES).getDouble();
	}

	@Override
	public char readChar() throws IOException {
		return read(Character.BYTES).getChar();
	}

	@Override
	public String readString(int length) throws IOException {
		return readString(length, Charset.forName("UTF-8"));
	}

	@Override
	public String readString(int length, Charset charset) throws IOException {
		ByteBuffer bb = read(length);
		byte[] bytes = new byte[length];
		bb.get(bytes);
		return new String(bytes, charset).trim();
	}

	@Override
	public String readXorString(int length, int key) throws IOException {
		return readXorString(length, key, Charset.forName("UTF-8"));
	}

	@Override
	public String readXorString(int length, int key, Charset charset) throws IOException {
		StringBuilder sb = new StringBuilder();
		String s = readString(length, charset);
		for(byte b : s.getBytes())
			sb.append((char) ((b ^ key) & 0xFF));
		return sb.toString();
	}

	@Override
	public void writeByte(byte b) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeBoolean(boolean b) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeShort(short s) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeInt(int i) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeLong(long l) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeFloat(float f) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeDouble(double d) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeChar(char c) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeString(String s) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeString(String s, Charset charset) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeXorString(String s, int key) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeXorString(String s, int key, Charset charset) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FileContentChannel skip(long count) throws IOException {
		channel.position(channel.position() + count);
		return this;
	}

	@Override
	public FileContentChannel skipByte() throws IOException {
		skip(Byte.BYTES);
		return this;
	}

	@Override
	public FileContentChannel skipBoolean() throws IOException {
		skip(1);
		return this;
	}

	@Override
	public FileContentChannel skipChar() throws IOException {
		skip(Character.BYTES);
		return this;
	}

	@Override
	public FileContentChannel skipInt() throws IOException {
		skip(Integer.BYTES);
		return this;
	}

	@Override
	public FileContentChannel skipLong() throws IOException {
		skip(Long.BYTES);
		return this;
	}

	@Override
	public FileContentChannel skipFloat() throws IOException {
		skip(Float.BYTES);
		return this;
	}
}