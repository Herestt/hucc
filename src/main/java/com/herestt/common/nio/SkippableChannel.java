package com.herestt.common.nio;

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
	 */
	public E skip(long count);
	
	/**
	 * Skips a byte.
	 * 
	 * @return the current {@link FileChannel} instance.
	 */
	public E skipByte();
	
	/**
	 * Skips a size of a boolean in bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 */
	public E skipBoolean();
	
	/**
	 * Skips {@link Short#BYTES} bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 */
	public E skipChar();
	
	/**
	 * Skips {@link Integer#BYTES} bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 */
	public E skipInt();
	
	/**
	 * Skips {@link Long#BYTES} bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 */
	public E skipLong();
	
	/**
	 * Skips {@link Float#BYTES} bytes.
	 * 
	 * @return the current {@link FileChannel} instance.
	 */
	public E skipFloat();
}