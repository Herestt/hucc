package com.herestt.common.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileSystem;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for handling a sequence of data within a file.
 * 
 * @author Herestt
 *
 */
public class FileContent {
	
	/** The created channel that accesses the current file. */
	private static SeekableByteChannel sbc = null;
		
	/** The order with which the each piece of data will be read. */
	private static ByteOrder order = null;
	
	/** Allows a direct access to the basic function of this utility class.  */
	private static Surrogate surrogate = null;
	
	/** Gives a bunch of function that allows to read data from the accessed file. */
	private static Reader reader = null;
	
	/** Gives a bunch of function that allows to write data into the accessed file. */	
	private static Writer writer = null;
	
	private FileContent() {}
	
	private static <T extends OpenOption> SeekableByteChannel loadByteChannel(Path path, OpenOption... options) {
		try {
			FileSystem fs = path.getFileSystem();
			Set<OpenOption> set = new HashSet<>(options.length);
			Collections.addAll(set, options);
			if(!set.contains(StandardOpenOption.WRITE)
					|| !set.contains(StandardOpenOption.APPEND)
					&& !set.contains(StandardOpenOption.READ))
				set.add(StandardOpenOption.READ);
			sbc = fs.provider().newByteChannel(path, set);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sbc;
	}
	
	/**
	 * The first function to call so as to access a file.
	 * 
	 * @param path	The path to the file.
	 * @param position The initial offset position into the file.
	 * @param options The options that will be passes to the 
	 * 		@see {@link java.nio.file.spi.FileSystemProvider#newByteChannel(Path, java.util.Set, java.nio.file.attribute.FileAttribute...)}
	 * @return The {@link SeekableByteChannel} used for accessing the file.
	 */
	public static SeekableByteChannel access(Path path, long position, OpenOption... options) {
		loadByteChannel(path, options);
		order = ByteOrder.BIG_ENDIAN;
		surrogate = new Surrogate();
		reader = new Reader();
		writer = new Writer();
		return sbc;
	}
	
	/**
	 * Access the reader utility.
	 *  
	 * @return The class containing reading functions.
	 */
	public static Reader read() {
		return surrogate.read();
	}
		
	/**
	 * Access the writer utility.
	 *  
	 * @return The class containing writing functions. 
	 */
	public static Writer write() {
		return surrogate.write();
	}
	
	/**
	 * Update the position of the channel's position.
	 * 
	 * @param newPosition The position to reach.
	 * @return A surrogate class that allows the usage of other file handling functions.
	 * @throws IOException - If I/O exception occurs.
	 */
	public static Surrogate position(long newPosition) throws IOException {
		surrogate.position(newPosition);
		return surrogate;
	}
	
	/**
	 * Skips an amount of bytes so as for the channel to reach a new position.
	 * 
	 * @param count The amount of bytes to skip.
	 * @return A surrogate class that allows the usage of other file handling functions.
	 * @throws IOException - If I/O exception occurs.
	 */
	public static Surrogate skip(long count) throws IOException {
		surrogate.skip(count);
		return surrogate;
	}
	
	/**
	 * Sets the byte order with which each element will be handled.
	 * 
	 * @param order The byte order.
	 * @return A surrogate class that allows the usage of other file handling functions.
	 */
	public static Surrogate order(ByteOrder order) {
		surrogate.order(order);
		return surrogate;
	}
	
	/**
	 * This class allows the access of the main methods after a first use.
	 * So the calling of this utility class can be chained.
	 * 
	 * @author Herestt
	 *
	 */
	public static class Surrogate {
		
		private Surrogate() {}
		
		/**
		 * Access the reader utility.
		 *  
		 * @return The class containing reading functions.
		 */
		public Reader read() {
			if(reader == null)
				throw new NullPointerException("The file content helper must be initialized by calling the access method first.");
			return reader;
		}
		
		/**
		 * Access the writer utility.
		 *  
		 * @return The class containing writing functions.
		 */
		public Writer write() {
			if(writer == null)
				throw new NullPointerException("The file content helper must be initialized by calling the access method first.");
			return writer;
		}
		
		/**
		 * Update the position of the channel's position.
		 * 
		 * @param newPosition The position to reach.
		 * @return A surrogate class that allows the usage of other file handling functions.
		 * @throws IOException - If I/O exception occurs.
		 */
		public Surrogate position(long newPosition) throws IOException {
			if(newPosition < 0 || newPosition >= sbc.size())
				throw new IllegalArgumentException();
			sbc.position(newPosition);
			return this;
		}
		
		/**
		 * Skips an amount of bytes so as for the channel to reach a new position.
		 * 
		 * @param count The amount of bytes to skip.
		 * @return A surrogate class that allows the usage of other file handling functions.
		 * @throws IOException - If I/O exception occurs.
		 */
		public Surrogate skip(long count) throws IOException {
			long newPosition = sbc.position() + count;
			position(newPosition);
			return this;
		}
		
		/**
		 * Sets the byte order with which each element will be handled.
		 * 
		 * @param order The byte order.
		 * @return A surrogate class that allows the usage of other file handling functions.
		 */
		public Surrogate order(ByteOrder order) {
			FileContent.order = order;
			return this;
		}
	}
	
	/**
	 * The reader utility class.
	 * 
	 * @author Herestt
	 *
	 */
	public static class Reader {
		
		private Reader() {}
		
		/**
		 * Reads the amount of bytes given by the capacity of the buffer that will contain them.
		 * 
		 * @param capacity The capacity of the buffer that will contain the data.
		 * @return The buffer.
		 * @throws IOException - If an I/O error occurs.
		 */
		public ByteBuffer asByteBuffer(int capacity) throws IOException {
			if(capacity < 0)
				throw new IllegalArgumentException();
			ByteBuffer buffer = ByteBuffer.allocateDirect(capacity).order(order);
			sbc.read(buffer);
			buffer.rewind();
			return buffer;
		}
		
		/**
		 * Read the next byte as a boolean.
		 * 
		 * @return The read boolean value.
		 * @throws IOException - If an I/O error occurs.
		 */
		public boolean asBoolean() throws IOException {
			ByteBuffer bb = asByteBuffer(1);
			return (bb.get() == 0)? false : true;
		}
		
		/**
		 * Reads the next byte as a char.
		 * 
		 * @return The read char.
		 * @throws IOException - If an I/O error occurs.
		 */
		public char asChar() throws IOException {
			ByteBuffer bb = asByteBuffer(Character.SIZE / 8);
			return bb.getChar();
		}
		
		/**
		 * Reads the two next bytes as a short.
		 * 
		 * @return The read value..
		 * @throws IOException - If an I/O error occurs.
		 */
		public short asShort() throws IOException {
			ByteBuffer bb = asByteBuffer(Short.SIZE / 8);
			return bb.getShort();
		}
		
		/**
		 * Reads the two next bytes as an unsigned short.
		 * 
		 * @return The integer value that holds the unsigned short one.
		 * @throws IOException - If an I/O error occurs.
		 */
		public int asUnsignedShort() throws IOException {
			int val = asShort();
			return val & 0x0000FFFF;
		}
		
		/**
		 * Reads the four next bytes as an integer.
		 * 
		 * @return The read value.
		 * @throws IOException - If an I/O error occurs.
		 */
		public int asInt() throws IOException {
			ByteBuffer bb = asByteBuffer(Integer.SIZE / 8);
			return bb.getInt();
		}
		
		/**
		 * Reads the four next bytes as an unsigned integer.
		 * 
		 * @return The long value that holds the unsigned integer one.
		 * @throws IOException - If an I/O error occurs.
		 */
		public long asUnsignedInt() throws IOException {
			long val = asInt();
			return val & 0x00000000FFFFFFFFL;
		}
		
		/**
		 * Reads the eight next bytes as a long.
		 * 
		 * @return The read value.
		 * @throws IOException - If an I/O error occurs.
		 */
		public long asLong() throws IOException {
			ByteBuffer bb = asByteBuffer(Long.SIZE / 8);
			return bb.getLong();
		}
		
		/**
		 * Reads the next bytes as an String.
		 * 
		 * @param length The amount of bytes that compose the string.
		 * @return The read string.
		 * @throws IOException - If an I/O error occurs.
		 */
		public String asString(int length) throws IOException {
			ByteBuffer bb = asByteBuffer(length);
			byte[] s = new byte[length];
			bb.get(s);
			return new String(s, "UTF-8").trim();
		}
		
		/**
		 * Reads the next bytes as an XOR encoded string.
		 * 
		 * @param length The amount of bytes that compose the XOR string.
		 * @param key The key to decode each byte.
		 * @return The read string.
		 * @throws IOException - If an I/O error occurs.
		 */
		public String asXorString(int length, int key) throws IOException {
			StringBuilder sb = new StringBuilder();
			String s = asString(length);
			for(byte b : s.getBytes())
				sb.append((char) ((b ^ key) & 0xFF));
			return sb.toString();
		}
	}
	
	/**
	 * The writer utility class.
	 * 
	 * @author Herestt
	 *
	 */
	public static class Writer {
	
		private Writer() {};
		
		/**
		 * Writes the given bytes.
		 *  
		 * @param bb The bytes to write.
		 * @throws IOException
		 */
		public void asByteBuffer(ByteBuffer bb) throws IOException {
			// TODO - Herestt.
		}
		
		/**
		 * Writes the given boolean.
		 * 
		 * @param b The boolean to write.
		 * @throws IOException - If an I/O error occurs.
		 */
		public void asBoolean(boolean b) throws IOException {
			// TODO - Herestt.
		}
		
		/**
		 * Writes the given char.
		 *  
		 * @param c The char to write.
		 * @throws IOException - If an I/O error occurs.
		 */
		public void asChar(char c) throws IOException {
			// TODO - Herestt.
		}
		
		/**
		 * Writes the given short value.
		 * 
		 * @param s The value to write.
		 * @throws IOException - If an I/O error occurs.
		 */
		public void asShort(short s) throws IOException {
			// TODO - Herestt.
		}
		
		/**
		 * Writes the given unsigned short representation.
		 * 
		 * @param us The unsigned short value to write.
		 * @throws IOException - If an I/O error occurs.
		 */
		public void asUnsignedShort(int uShort) throws IOException {
			// TODO - Herestt.
		}
		
		/**
		 * Writes the given integer value.
		 * 
		 * @param i The value to write.
		 * @throws IOException- If an I/O error occurs.
		 */
		public void asInt(int i) throws IOException {
			// TODO - Herestt.
		}
		
		/**
		 * Writes the given unsigned integer representation.
		 * 
		 * @param ui The unsigned integer value representation.
		 * @throws IOException- If an I/O error occurs.
		 */
		public void asUnsignedInt(long uInt) throws IOException {
			// TODO - Herestt.
		}
		
		/**
		 * Writes the given long value.
		 * 
		 * @param l The value to write.
		 * @throws IOException- If an I/O error occurs.
		 */
		public void asLong(long l) throws IOException {
			// TODO - Herestt.
		}
		
		/**
		 * Writes the given string.
		 * 
		 * @param str The string to write.
		 * @throws IOException- If an I/O error occurs.
		 */
		public void asString(String str) throws IOException {
			// TODO - Herestt.
		}
		
		/**
		 * Encodes the given string with the XOR operator then writes it.
		 * 
		 * @param str The string to encode and write.
		 * @param key The encoding key.
		 * @throws IOException- If an I/O error occurs.
		 */
		public void asXorString(String str, int key) throws IOException {
			// TODO - Herestt.
		}
	}
}