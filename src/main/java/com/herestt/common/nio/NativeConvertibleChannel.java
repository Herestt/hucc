package com.herestt.common.nio;

import java.nio.channels.FileChannel;

/**
 * A byte channel that can convert file data during I/O operations.
 * 
 * <p>Reading/Writing methods allow to translate native signed data into one
 * of the primitive data types.</p>
 * 
 * @author Herestt
 *
 */
public interface NativeConvertibleChannel<E extends FileChannel> 
		extends ConvertibleChannel<E> {

	/**
	 * Reads the next bytes as an unsigned short and wrap them into integer;
	 * 
	 * @return the wrapped short value. 
	 */
	public int readUShort();
	
	/**
	 * Reads the next bytes as an unsigned integer and wrap them into a long.
	 *  
	 * @return the wrapped integer value.
	 */
	public long readUInt();
	
	/**
	 * Writes the given value as it was an unsigned short wrapped into an integer.
	 * 
	 * @param s - the integer wrapping the short value.
	 */
	public void writeUShort(int s);
	
	/**
	 * Writes the given value as it was an unsigned integer wrapped into a long.
	 * 
	 * @param l - the long wrapping the integer value. 
	 */
	public void writeUInt(long l);
}