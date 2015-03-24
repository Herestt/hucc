package com.herestt.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;

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
	private static Surrogate accessor = null;
	
	/** Gives a bunch of function that allows to read data from the accessed file. */
	private static Reader reader = null;
	
	/** Gives a bunch of function that allows to write data into the accessed file. */	
	private static Writer writer = null;
	
	private FileContent() {}
	
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
		// TODO - Herestt.
		return null;
	}
	
	/**
	 * Access the reader utility.
	 *  
	 * @return The class containing reading functions.
	 */
	public static Reader read() {
		// TODO - Herestt.
		return null;
	}
		
	/**
	 * Access the writer utility.
	 *  
	 * @return The class containing writing functions. 
	 */
	public static Writer write() {
		// TODO - Herestt.
		return null;
	}
	
	/**
	 * Update the position of the channel's position.
	 * 
	 * @param newPosition The position to reach.
	 * @return A surrogate class that allows the usage of other file handling functions.
	 * @throws IOException - If I/O exception occurs.
	 */
	public static Surrogate position(long newPosition) throws IOException {
		// TODO - Herestt.
		return null;
	}
	
	/**
	 * Skips an amount of bytes so as for the channel to reach a new position.
	 * 
	 * @param count The amount of bytes to skip.
	 * @return A surrogate class that allows the usage of other file handling functions.
	 * @throws IOException - If I/O exception occurs.
	 */
	public static Surrogate skip(long count) throws IOException {
		// TODO - Herestt.
		return null;
	}
	
	/**
	 * Sets the byte order with which each element will be handled.
	 * 
	 * @param order The byte order.
	 * @return A surrogate class that allows the usage of other file handling functions.
	 */
	public static Surrogate order(ByteOrder order) {
		// TODO - Herestt.
		return null;
	}
	
	/**
	 * This class allows the access of the main methods after a first use.
	 * So the calling of this utility class can be chained.
	 * 
	 * @author Herestt
	 *
	 */
	public static class Surrogate {
		
		/**
		 * Access the reader utility.
		 *  
		 * @return The class containing reading functions.
		 */
		public Reader read() {
			// TODO - Herestt.
			return null;
		}
		
		/**
		 * Access the writer utility.
		 *  
		 * @return The class containing writing functions.
		 */
		public Writer write() {
			// TODO - Herestt.
			return null;
		}
		
		/**
		 * Update the position of the channel's position.
		 * 
		 * @param newPosition The position to reach.
		 * @return A surrogate class that allows the usage of other file handling functions.
		 * @throws IOException - If I/O exception occurs.
		 */
		public Surrogate position(long newPosition) throws IOException {
			// TODO - Herestt.
			return null;
		}
		
		/**
		 * Skips an amount of bytes so as for the channel to reach a new position.
		 * 
		 * @param count The amount of bytes to skip.
		 * @return A surrogate class that allows the usage of other file handling functions.
		 * @throws IOException - If I/O exception occurs.
		 */
		public Surrogate skip(long count) throws IOException {
			// TODO - Herestt.
			return null;
		}
		
		/**
		 * Sets the byte order with which each element will be handled.
		 * 
		 * @param order The byte order.
		 * @return A surrogate class that allows the usage of other file handling functions.
		 */
		public Surrogate order(ByteOrder order) {
			// TODO - Herestt.
			return null;
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
			// TODO - Herestt.
			return null;
		}
		
		/**
		 * Read the next byte as a boolean.
		 * 
		 * @return The read boolean value.
		 * @throws IOException - If an I/O error occurs.
		 */
		public boolean asBoolean() throws IOException {
			// TODO - Herestt.
			return false;
		}
		
		/**
		 * Reads the next byte as a char.
		 * 
		 * @return The read char.
		 * @throws IOException - If an I/O error occurs.
		 */
		public char asChar() throws IOException {
			// TODO - Herestt.
			return '0';
		}
		
		/**
		 * Reads the two next bytes as a short.
		 * 
		 * @return The read value..
		 * @throws IOException - If an I/O error occurs.
		 */
		public short asShort() throws IOException {
			// TODO - Herestt.
			return 0;
		}
		
		/**
		 * Reads the two next bytes as an unsigned short.
		 * 
		 * @return The integer value that holds the unsigned short one.
		 * @throws IOException - If an I/O error occurs.
		 */
		public int asUnsignedShort() throws IOException {
			// TODO - Herestt.
			return 0;
		}
		
		/**
		 * Reads the four next bytes as an integer.
		 * 
		 * @return The read value.
		 * @throws IOException - If an I/O error occurs.
		 */
		public int asInt() throws IOException {
			// TODO - Herestt.
			return 0;
		}
		
		/**
		 * Reads the four next bytes as an unsigned integer.
		 * 
		 * @return The long value that holds the unsigned integer one.
		 * @throws IOException - If an I/O error occurs.
		 */
		public long asUnsignedInt() throws IOException {
			// TODO - Herestt.
			return 0;
		}
		
		/**
		 * Reads the eight next bytes as a long.
		 * 
		 * @return The read value.
		 * @throws IOException - If an I/O error occurs.
		 */
		public long asLong() throws IOException {
			// TODO - Herestt.
			return 0;
		}
		
		/**
		 * Reads the next bytes as an String.
		 * 
		 * @param length The amount of bytes that compose the string.
		 * @return The read string.
		 * @throws IOException - If an I/O error occurs.
		 */
		public String asString(int length) throws IOException {
			// TODO - Herestt.
			return null;
		}
		
		/**
		 * Reads the next bytes as an XOR encoded string.
		 * 
		 * @param length The amount of bytes that compose the XOR string.
		 * @param key The key to decode each byte.
		 * @return The read string.
		 * @throws IOException - If an I/O error occurs.
		 */
		public String asXorString(int length, int key) {
			// TODO - Herestt.
			return null;
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