package com.herestt.common.nio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

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

	/* Bits Processing. */
	
	/**
	 * Gets the byte value after applying a mask. The goal of the method
	 * is to allow the reading of specific bits.
	 * 
	 * <pre><code>
	 * ByteBuffer dst = ByteBuffer.allocate(1);
	 * dst.put((byte) 0xAB); 
	 * int v = Buffers.getBits(dst, 0, 0xF0);	// v == 0x0A. /!\ Notice that v != 0xA0
	 * v = Buffers.getBits(dst, 0, 0x0F);		// v == 0x0B
	 * </code></pre>
	 * 
	 * @param src - The {@link ByteBuffer} from which bits will be read.
	 * @param index - The index from which the value will be read.
	 * @param mask - The mask to apply on the read byte.
	 * @return the masked value.
	 */
	public static int getBits(ByteBuffer src, int index, int mask) {
		if(mask < 0 || mask > 255)
			throw new IllegalArgumentException("Tha mask must belongs to [0, 255].");
		
		int value = getUnsignedByte(src, index);
		if((mask & 0xff) == 0)	// IF the eight first bits are equal to 0.
			return value;
		boolean hasStarted = false;
		int startMask = 0, length = 0;
		for(int i = 0; i < Byte.SIZE; i++) {	// Determine where mask starts, and its length.
			int b = (mask >> i) & 1;
			if(b == 0) {
				if(hasStarted)
					break;
				startMask++;
			}
			else {
				hasStarted = true;
				length++;
			}
		}
		
		mask = 0;
		for(int i = 0; i < length; i++)		// Create a mask that starts at the LSB and measures n = length bits long.
			mask += Math.pow(2, i);
		
		value = value >>> startMask;		// Shift the value so that the wanted chunk starts at the LSB.
		return (value & mask);
	}
	
	/**
	 * Gets the byte at the given index and update bits values where the mask bits
	 * are set.
	 * 
	 * <pre><code>
	 * ByteBuffer dst = ByteBuffer.allocate(1);
	 * dst.put((byte) 0x11);
	 * Buffers.putBits(dst, 0, 0x02, 0x03);
	 * byte b = dst.get(0);		// b == 0x12
	 * </code></pre>
	 * 
	 * @param dst - The {@link ByteBuffer} into which the bits will be written.
	 * @param index - The index of the byte of which bits will be written.
	 * @param value - The value to mask before the putting operation.
	 * @param mask - The mask to apply on the value held within the {@code dst} buffer
	 * at the given index.
	 */
	public static void putBits(ByteBuffer dst, int index, int value, int mask) {
		throw new UnsupportedOperationException("Not implemented yet.");
//		if(value < 0 || value > 255)
//			throw new IllegalArgumentException("Tha value must belongs to [0, 255].");
//		if(mask == 0)
//			putUnsignedByte(dst, index, value);
//		
//		int result = getUnsignedByte(dst, index);
//		
//		for(int i = 0; i < Byte.SIZE; i++) {
//			
//			int maskBit = (mask >> i) & 1;
//			if(maskBit == 1) {
//			
//				int valueBit = (value >> i) & 1;
//				byte oneBitMask = (byte) Math.pow(2, i);
//				if(valueBit == 1)
//					result = (byte) (result | oneBitMask); 
//				else {
//					oneBitMask = (byte) ~oneBitMask;
//					result = (byte) (result & oneBitMask);
//				}
//			}
//		}
//		putUnsignedByte(dst, index, result);
	}
	
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
	 * @param index - The index where the value will be read.
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
	 * @param index - The index where the value will be written.
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
	 * @param index - The index where the value will be read.
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
	 * @param index - The index where the value will be written.
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
	 * @param index - The index where the value will be read.
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
	 * @param index - The index where the value will be written.
	 * @param value - The value to convert. 
	 */
	public static void putUnsignedInt(ByteBuffer dst, int index, long value) {
		if(value < 0 || value > 4294967295L)
			throw new IllegalArgumentException("Tha value must belongs to [0, 4294967295].");
		dst.putInt(index, (int) (value & 0xffffffffL));
	}
	
	/* String Processing. */
	
	/**
	 * Gets the next bytes at the current position then convert them into an
	 * {@code UTF-8} {@link String}.
	 *
	 * @param src - The {@link ByteBuffer} from which the value will be read.
	 * @param length - The length of the {@code String} to read.
	 *
	 * @return the read {@code String}.
	 * 
	 * @throws UnsupportedEncodingException if the {@code String} is not {@code UTF-8}.
	 */
	public static String getString(ByteBuffer src, int length) throws UnsupportedEncodingException {
		return getString(src, src.position(), length);
	}
	
	/**
	 * Puts an {@code UTF-8} {@link String} at the current position. 
	 * 
	 * @param dst - The {@link ByteBuffer} into which the value will be written.
	 * @param str - The {@code String} to put into.
	 */
	public static void putString(ByteBuffer dst, String str) {
		putString(dst, dst.position(), str);
	}
	
	/**
	 * Gets the next bytes at the given position then convert them into an
	 * {@code UTF-8} {@link String}.
	 *
	 * @param src - The {@link ByteBuffer} from which the value will be read.
	 * @param index - The index where the {@code String} will be read.
	 * @param length - The length of the {@code String} to read.
	 *
	 * @return the gotten {@code String}.
	 * 
	 * @throws UnsupportedEncodingException if the {@code String} is not {@code UTF-8}.
	 */
	public static String getString(ByteBuffer src, int index, int length) throws UnsupportedEncodingException {
		byte[] dst = new byte[length];
		src.position(index);
		src.get(dst);
		return new String(dst).trim();
	}
	
	/**
	 * Puts the next bytes at the given position into an {@code UTF-8}
	 * {@link String}. 
	 * 
	 * @param dst - The {@link ByteBuffer} into which the value will be written.
	 * @param index - The index where the {@code String} will be written.
	 * @param str - The {@code String} to put into.
	 */
	public static void putString(ByteBuffer dst, int index, String str) {
		dst.position(index);
		ByteOrder oldOrder = dst.order();
		dst.order(ByteOrder.BIG_ENDIAN);
		dst.put(str.getBytes());
		dst.order(oldOrder);
	}
}