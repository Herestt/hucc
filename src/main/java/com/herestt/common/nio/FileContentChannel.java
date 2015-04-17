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
		return read(dst, 0);
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
	
	@Override
	public int readUShort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long readUInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeUShort(int s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeUInt(long l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte readByte() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean readBoolean() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public short readShort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long readLong() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float readFloat() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double readDouble() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public char readChar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String readString(int length) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readString(int length, Charset charset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readXorString(int length, int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readXorString(int length, int key, Charset charset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeByte(byte b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeBoolean(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeShort(short s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeInt(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeLong(long l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeFloat(float f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeDouble(double d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeChar(char c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeString(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeString(String s, Charset charset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeXorString(String s, int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeXorString(String s, int key, Charset charset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FileContentChannel skip(long count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileContentChannel skipByte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileContentChannel skipBoolean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileContentChannel skipChar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileContentChannel skipInt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileContentChannel skipLong() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileContentChannel skipFloat() {
		// TODO Auto-generated method stub
		return null;
	}
}