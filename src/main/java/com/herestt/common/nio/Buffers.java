package com.herestt.common.nio;

import java.nio.ByteBuffer;

/**
 * An utility class for interacting with {@link ByteBuffer} containers.
 * 
 * <p>The main purpose of this class is to convert data before putting operations,
 * and after getting ones.</p>
 * 
 * @author Herestt
 *
 */
public class Buffers {

	/* Byte Data Type Processing. */
	
	/**
	 * Gets the byte at the current position then converts it into an unsigned
	 * byte wrapped in an integer.
	 *
	 * @param src - The {@link ByteBuffer} from which the value will be read.
	 * @return the converted value.
	 */
	public static int getUnsignedByte(ByteBuffer src) {
		return getUnsignedByte(src, src.position());
	}
	
	/**
	 * Converts a positive integer value between {@code 0} and {@code 255}
	 * into a byte then put it into the {@link ByteBuffer} at the current
	 * position.
	 *
	 * @param dst - The {@link ByteBuffer} into which the converted
	 * value will be written.
	 * @param value - The value to convert. 
	 */
	public static void putUnsignedByte(ByteBuffer dst, int value) {
		putUnsignedByte(dst, dst.position(), value);
	}
	
	/**
	 * Gets the byte at the given position then converts it into an unsigned
	 * byte wrapped in an integer.
	 *
	 * @param src - The {@link ByteBuffer} from which the value will be read.
	 * @param index - The index from which the value will be read.
	 * @return the converted value.
	 */
	public static int getUnsignedByte(ByteBuffer src, int index) {
		return (src.get(index) & 0xff);
	}
	
	/**
	 * Converts a positive integer value between {@code 0} and {@code 255}
	 * into a byte then put it into the {@link ByteBuffer} at the given
	 * position.
	 *
	 * @param dst - The {@link ByteBuffer} into which the converted
	 * value will be written.
	 * @param index - The index from which the value will be read.
	 * @param value - The value to convert. 
	 */
	public static void putUnsignedByte(ByteBuffer dst, int index, int value) {
		if(value < 0 || value > 255)
			throw new IllegalArgumentException("Tha value must belongs to [0, 255].");
		dst.put(index, (byte) (value & 0xff));
	}
	
	/* Short Data Type Processing. */
	
	/**
	 * Gets the 2 bytes at the current position then converts it into an unsigned
	 * short wrapped in an integer.
	 *
	 * @param src - The {@link ByteBuffer} from which the value will be read.
	 * @return the converted value.
	 */
	public static int getUnsignedShort(ByteBuffer src) {
		return getUnsignedShort(src, src.position());
	}
	
	/**
	 * Converts a positive integer value between {@code 0} and {@code 65535}
	 * into a short then put it into the {@link ByteBuffer} at the current
	 * position.
	 *
	 * @param dst - The {@link ByteBuffer} into which the converted
	 * value will be written.
	 * @param value - The value to convert. 
	 */
	public static void putUnsignedShort(ByteBuffer dst, int value) {
		putUnsignedShort(dst, dst.position(), value);
	}
	
	/**
	 * Gets the 2 bytes at the given position then converts it into an unsigned
	 * short wrapped in an integer.
	 *
	 * @param src - The {@link ByteBuffer} from which the value will be read.
	 * @param index - The index from which the value will be read.
	 * @return the converted value.
	 */
	public static int getUnsignedShort(ByteBuffer src, int index) {
		return (src.getShort(index) & 0xffff);
	}
	
	/**
	 * Converts a positive integer value between {@code 0} and {@code 65535}
	 * into a short then put it into the {@link ByteBuffer} at the given
	 * position.
	 *
	 * @param dst - The {@link ByteBuffer} into which the converted
	 * value will be written.
	 * @param index - The index from which the value will be read.
	 * @param value - The value to convert. 
	 */
	public static void putUnsignedShort(ByteBuffer dst, int index, int value) {
		if(value < 0 || value > 65535)
			throw new IllegalArgumentException("Tha value must belongs to [0, 65535].");
		dst.putShort(index, (short) (value & 0xffff));
	}
	
	/* Integer Data Type Processing. */
	
	/**
	 * Gets the 4 bytes at the current position then converts it into an unsigned
	 * integer wrapped in an long.
	 *
	 * @param src - The {@link ByteBuffer} from which the value will be read.
	 * @return the converted value.
	 */
	public static long getUnsignedInt(ByteBuffer src) {
		return getUnsignedInt(src, src.position());
	}
	
	/**
	 * Converts a positive long value between {@code 0} and {@code (2^32)-1}
	 * into an integer then put it into the {@link ByteBuffer} at the current
	 * position.
	 *
	 * @param dst - The {@link ByteBuffer} into which the converted
	 * value will be written.
	 * @param value - The value to convert. 
	 */
	public static void putUnsignedInt(ByteBuffer dst, long value) {
		putUnsignedInt(dst, dst.position(), value);
	}
	
	/**
	 * Gets the 4 bytes at the given position then converts it into an unsigned
	 * integer wrapped in an long.
	 *
	 * @param src - The {@link ByteBuffer} from which the value will be read.
	 * @param index - The index from which the value will be read.
	 * @return the converted value.
	 */
	public static long getUnsignedInt(ByteBuffer src, int index) {
		return (src.getInt(index) & 0xffffffffL);
	}
	
	/**
	 * Converts a positive long value between {@code 0} and {@code (2^32)-1}
	 * into an integer then put it into the {@link ByteBuffer} at the given
	 * position.
	 *
	 * @param dst - The {@link ByteBuffer} into which the converted
	 * value will be written.
	 * @param index - The index from which the value will be read.
	 * @param value - The value to convert. 
	 */
	public static void putUnsignedInt(ByteBuffer dst, int index, long value) {
		if(value < 0 || value > 4294967295L)
			throw new IllegalArgumentException("Tha value must belongs to [0, 4294967295].");
		dst.putInt(index, (int) (value & 0xffffffffL));
	}
}