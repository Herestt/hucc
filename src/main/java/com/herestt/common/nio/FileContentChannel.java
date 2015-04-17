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
import java.util.Set;

/**
 * A file channel that allows data conversion.
 * 
 * @author Herestt
 *
 */
public class FileContentChannel extends FileChannel
		implements NativeConvertibleChannel<FileContentChannel>, SkippableChannel<FileContentChannel> {

	public static FileContentChannel open(Path path, Set<? extends OpenOption> options,
            FileAttribute<?>... attrs) throws IOException {
		// TODO - Herestt : implementation.
		return null;
	}
	
	 public static FileContentChannel open(Path path, OpenOption... options)
		        throws IOException {
		 // TODO - Herestt : implementation.
		 return null;
	}
	 
	@Override
	public int read(ByteBuffer dst) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long read(ByteBuffer[] dsts, int offset, int length)
			throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int write(ByteBuffer src) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long write(ByteBuffer[] srcs, int offset, int length)
			throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long position() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FileChannel position(long newPosition) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long size() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FileChannel truncate(long size) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void force(boolean metaData) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long transferTo(long position, long count, WritableByteChannel target)
			throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long transferFrom(ReadableByteChannel src, long position, long count)
			throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int read(ByteBuffer dst, long position) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int write(ByteBuffer src, long position) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MappedByteBuffer map(MapMode mode, long position, long size)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileLock lock(long position, long size, boolean shared)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileLock tryLock(long position, long size, boolean shared)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void implCloseChannel() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FileContentChannel order(ByteOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteOrder order() {
		// TODO Auto-generated method stub
		return null;
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