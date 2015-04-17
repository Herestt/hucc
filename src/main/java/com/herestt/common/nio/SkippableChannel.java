package com.herestt.common.nio;

import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * A by channel that allows bytes skipping.
 * 
 * <p>Byte skipping is often needed when </p>
 * 
 * @author Herestt
 *
 * @param <E> - the {@link FileChannel} class implementing the interface.
 */
public interface SkippableChannel<E extends FileChannel> {

	/**
	 * Skips the given count of bytes.
	 * 
	 * @param count - the amount of bytes to skip.
	 * @return the current {@link FileChannel} instance.
	 * @throws IOException 
	 */
	public E skip(long count) throws IOException;
	
	/**
	 * Skips a byte.
	 * 
	 * @return the current {@link FileChannel} instance.
	 * @throws IOException 
	 */
	public E skipByte() throws IOException;
	
	/**
	 * Skips a size of a boolean in bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 * @throws IOException 
	 */
	public E skipBoolean() throws IOException;
	
	/**
	 * Skips {@link Short#BYTES} bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 * @throws IOException 
	 */
	public E skipChar() throws IOException;
	
	/**
	 * Skips {@link Integer#BYTES} bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 * @throws IOException 
	 */
	public E skipInt() throws IOException;
	
	/**
	 * Skips {@link Long#BYTES} bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 * @throws IOException 
	 */
	public E skipLong() throws IOException;
	
	/**
	 * Skips {@link Float#BYTES} bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 * @throws IOException 
	 */
	public E skipFloat() throws IOException;
}