package com.herestt.common.nio;

import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * A byte channel that can convert file data during I/O operations.
 * 
 * <p>Reading/Writing methods allow to handled bytes as one
 * of the primitive data types.</p>
 * 
 * @author Herestt
 *
 */
public interface ConvertibleChannel<E extends FileChannel> extends OrderSensitiveChannel<E> {

	/**
	 * Reads the next byte.
	 * 
	 * @return the byte value.
	 */
	public byte readByte();
	
	/**
	 * Reads the next byte as a boolean.
	 * 
	 * @return the boolean value.
	 */
	public boolean readBoolean();
	
	/**
	 * Reads the next bytes as a short.
	 * 
	 * @return the short value.
	 */
	public short readShort();
	
	/**
	 * Reads the next bytes as an integer.
	 * 
	 * @return the integer value.
	 */
	public int readInt();
		
	/**
	 * Reads the next bytes as a long.
	 * 
	 * @return the long value.
	 */
	public long readLong();
	
	/**
	 * Reads the next bytes as a float.
	 * 
	 * @return the float value.
	 */
	public float readFloat();
	
	/**
	 * Reads the next bytes as double.
	 * 
	 * @return the double value.
	 */
	public double readDouble();
	
	/**
	 * Reads the next byte as a char.
	 * 
	 * @return the decoded character.
	 */
	public char readChar();
	
	/**
	 * Reads the next bytes as an UTF-8 {@link String}.
	 * 
	 * @param length - the length of the string.
	 * 
	 * @return the UTF-8 decoded string.
	 */
	public String readString(int length);
	
	/**
	 * Reads the next bytes as a {@link String} regarding to a specific
	 * {@link Charset}.
	 * 
	 * @param length - the length of the string.
	 * @param charset - the used for decode the read bytes.
	 * 
	 * @return the decoded string.
	 */
	public String readString(int length, Charset charset);
	
	/**
	 * Reads and perform an XOR operation, regarding to a key, on the next bytes before building
	 * an UTF-8 {@link String}.
	 *  
	 * @param length - the length of the string.
	 * @param key - the key to apply on each byte.
	 * @return the decoded string.
	 */
	public String readXorString(int length, int key);
	
	/**
	 * Reads and perform an XOR operation, regarding to a key, on the next bytes before building
	 * a {@link String} regarding to a specific {@link Charset}.
	 *  
	 * @param length - the length of the string.
	 * @param key - the key to apply on each byte.
	 * @return the decoded string.
	 */
	public String readXorString(int length, int key, Charset charset);
	
	/**
	 * Writes the given byte.
	 * 
	 * @param b - the byte value to write.
	 */
	public void writeByte(byte b);
	
	/**
	 * Writes the given boolean.
	 * 
	 * @param b - the boolean value to write.
	 */
	public void writeBoolean(boolean b);
	
	/**
	 * Writes the given short.
	 * 
	 * @param s the short value to write.
	 */
	public void writeShort(short s);
	
	/**
	 * Writes the given integer.
	 * 
	 * @param i - the integer value to write.
	 */
	public void writeInt(int i);
	
	/**
	 * Writes the given long.
	 * 
	 * @param l - the long value to write. 
	 */
	public void writeLong(long l);
	
	/**
	 * Writes the given float.
	 * 
	 * @param f - the float value to write.
	 */
	public void writeFloat(float f);
	
	/**
	 * Writes the given double.
	 * 
	 * @param d - the double value to write.
	 */
	public void writeDouble(double d);
	
	/**
	 * Writes the given character.
	 * 
	 * @param c - the char to write.
	 */
	public void writeChar(char c);
	
	/**
	 * Writes an UTF-8 {@link String}.
	 * 
	 * @param s - the string to write.
	 */
	public void writeString(String s);
	
	/**
	 * Writes a string regarding to a specific {@link Charset}.
	 * 
	 * @param s - the string to write.
	 * @param charset - the {@link Charset} to apply to the string.
	 */
	public void writeString(String s, Charset charset);
	
	/**
	 * Performs and XOR operation on each bytes then writes an UTF-8 {@link String}.
	 *  
	 * @param s - the string to write.
	 * @param key - the key to apply on each byte.
	 */
	public void writeXorString(String s, int key);
	
	/**
	 * Performs and XOR operation on each bytes then writes an {@link String} regarding
	 * to a specific {@link Charset}.
	 * 
	 * @param s - the string to write.
	 * @param key - the key to apply on each byte.
	 * @param charset- the {@link Charset} to apply.
	 */
	public void writeXorString(String s, int key, Charset charset);
}