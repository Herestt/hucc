package com.herestt.common.nio;

import java.nio.ByteBuffer;

public class Buffers {

	/*** Byte Data Type Processing. ***/
	public static short getUnsignedByte(ByteBuffer src) {
		return getUnsignedByte(src, src.position());
	}
	
	public static void putUnsignedByte(ByteBuffer dst, short value) {
		// TODO - Herestt : implementation.
	}
	
	public static short getUnsignedByte(ByteBuffer src, int index) {
		return (short) (src.get(index) & 0xff);
	}
	
	public static void putUnsignedByte(ByteBuffer dst, int index, short value) {
		// TODO - Herestt : implementation.
	}
	
	public static void getUnsignedByte(ByteBuffer src, short[] dst) {
		// TODO - Herestt : implementation.
	}
	
	public static void putUnsignedByte(ByteBuffer dst, short[] src) {
		// TODO - Herestt : implementation.
	}
	
	public static void getUnsignedByte(ByteBuffer src, ByteBuffer dst) {
		// TODO - Herestt : implementation.
	}
	
	public static void putUnsignedByte(ByteBuffer dst, ByteBuffer src) {
		// TODO - Herestt : implementation.
	}
	
	/*** Short Data Type Processing. ***/
	public static int getUnsignedShort(ByteBuffer src) {
		return getUnsignedShort(src, src.position());
	}
	
	public static void putUnsignedShort(ByteBuffer dst, int value) {
		// TODO - Herestt : implementation.
	}
	
	public static int getUnsignedShort(ByteBuffer src, int index) {
		return (src.getShort(index) & 0xffff);
	}
	
	public static void putUnsignedShort(ByteBuffer dst, int index, int value) {
		// TODO - Herestt : implementation.
	}
	
	/*** Integer Data Type Processing. ***/
	public static long getUnsignedInt(ByteBuffer src) {
		return getUnsignedInt(src, src.position());
	}
	
	public static void putUnsignedInt(ByteBuffer dst, long value) {
		// TODO - Herestt : implementation.
	}
	
	public static long getUnsignedInt(ByteBuffer src, int index) {
		return (src.getInt(index) & 0xffffffffL);
	}
	
	public static void putUnsignedInt(ByteBuffer dst, int index, long value) {
		// TODO - Herestt : implementation.
	}
}