package com.herestt.common.imageio.plugins.tga;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;

import org.w3c.dom.Node;

import com.herestt.common.nio.Buffers;

public class TgaMetadata extends IIOMetadata {

	public static final String nativeImageMetadataFormatName = "herestt_imageio_tag_1.0";
	
	protected static final String nativeMetadataFormatClassName =
			"com.herestt.common.imageio.plugins.tga.TgaMetadataFormat";
	
	public static final int HEADER_SIZE = 18;
	
	public static final int FOOTER_SIZE = 26;
	
	public static final String FOOTER_SIGNATURE = "TRUEVISION-XFILE.";
	
	private Header header;
	
	private Image image;
	
	private DeveloperArea developerArea;
	
	private ExtensionArea extensionArea;
	
	private Footer footer;
	
	public TgaMetadata() {
		super(true,
				nativeImageMetadataFormatName,
				nativeMetadataFormatClassName,
				null, null);
	}
	
	public void setHeader(ByteBuffer data) {
		if(data.capacity() != HEADER_SIZE)
			throw new IllegalArgumentException("The size of the given chunk is "
					+ "different from the expected TGA header size.");
		header = new Header(data);
	}
	
	public void setImage(ByteBuffer imageId,
			ByteBuffer colorMap) {
		// TODO - Herestt : implementation.
	}
	
	public void setDeveloperArea(ByteBuffer fields,
			ByteBuffer directory) {
		// TODO - Herestt : implementation.
	}
	
	public void setExtensionArea(ByteBuffer data, ByteBuffer scanLineTable,
			ByteBuffer postageStampImage, ByteBuffer colorCorrectionTable) {
		// TODO - Herestt : implementation.
	}
	
	public void setFooter(ByteBuffer data) {
		if(data.capacity() != FOOTER_SIZE)
			throw new IllegalArgumentException("The size of the given chunk is "
					+ "different from the expected TGA header size.");
		footer = new Footer(data);
	}
	
	/* HEADER CHUNK */
	private static class Header {
		
		private ByteBuffer data;
		
		private Header(ByteBuffer data) {
			data.order(ByteOrder.LITTLE_ENDIAN);
			this.data = data;
		}
	}
	
	public int idLength() {
		return Buffers.getUnsignedByte(header.data, 0);
	}
	
	public void idLength(int value) {
		Buffers.putUnsignedByte(header.data, 0, value);
	}
	
	public static enum ColorMapType {
		NO_COLOR_MAP_INCLUDED(0), COLOR_MAP_INCLUDED(1);
		
		private int type;
		
		ColorMapType(final int type) {
			this.type = type;
		}
		
		public int getColorMapType() {
			return type;
		}
		
		public static ColorMapType valueOf(int type) {
			for(ColorMapType cmt : ColorMapType.values()) {
				if(cmt.getColorMapType() == type)
					return cmt;
			}
			throw new IllegalArgumentException("Unknown TGA color map type.");
		}
	}
	
	public ColorMapType colorMapType() {
		int type = Buffers.getUnsignedByte(header.data, 1);
		return ColorMapType.valueOf(type);
	}
	
	public void colorMapType(ColorMapType type) {
		Buffers.putUnsignedByte(header.data, 1, type.getColorMapType());
	}
	
	public static enum ImageType {
		NO_DATA (0), UNCOMPRESSED_COLOR_MAPPED(1),
		UNCOMPRESSED_TRUE_COLOR(2), UNCOMPRESSED_BLACK_AND_WHITE(3),
		RLE_COLOR_MAPPED(9), RLE_TRUE_COLOR(10), RLE_BLACK_AND_WHITE(11);
		
		private int type;
		
		ImageType(final int type) {
			this.type = type;
		}
		
		public int getImageType() {
			return type;
		}
		
		public static ImageType valueOf(int type) {
			for(ImageType it : ImageType.values()) {
				if(it.getImageType() == type)
					return it;
			}
			throw new IllegalArgumentException("Unknown TGA image type.");
		}
	}

	public ImageType imageType() {
		int type = Buffers.getUnsignedByte(header.data, 2);
		return ImageType.valueOf(type);
	}
	
	public void imageType(ImageType type) {
		Buffers.putUnsignedByte(header.data, 2, type.getImageType());
	}
	
	// Color Map Specification.
	public int firstEntryIndex() {
		return Buffers.getUnsignedShort(header.data, 3);
	}
	
	public void firstEntryIndex(int index) {
		Buffers.putUnsignedShort(header.data, 3, index);
	}
	
	public int colorMapLength() {
		return Buffers.getUnsignedShort(header.data, 5);
	}
	
	public void colorMapLength(int length) {
		Buffers.putUnsignedShort(header.data, 5, length);
	}
	
	public int colorMapEntrySize() {
		return Buffers.getUnsignedByte(header.data, 7);
	}
	
	public void colorMapEntrySize(int size) {
		Buffers.putUnsignedByte(header.data, 7, size);
	}
	
	// Image Specification.
	public int xOrigin() {
		return Buffers.getUnsignedShort(header.data, 8);
	}
	
	public void xOrigin(int x) {
		Buffers.putUnsignedShort(header.data, 8, x);
	}
	
	public int yOrigin() {
		return Buffers.getUnsignedShort(header.data, 10);
	}
	
	public void yOrigin(int y) {
		Buffers.putUnsignedShort(header.data, 10, y);
	}
	
	public int imageWidth() {
		return Buffers.getUnsignedShort(header.data, 12);
	}
	
	public void imageWidth(int width) {
		Buffers.putUnsignedShort(header.data, 12, width);
	}
	
	public int imageHeight() {
		return Buffers.getUnsignedShort(header.data, 14);
	}
	
	public void imageHeight(int height) {
		Buffers.putUnsignedShort(header.data, 14, height);
	}
	
	public int pixelDepth() {
		return Buffers.getUnsignedByte(header.data, 16);
	}
	
	public void pixelDepth(int depth) {
		Buffers.putUnsignedByte(header.data, 16, depth);
	}
	
	// Image Descriptor.
	public int alphaChannelBits() {
		return Buffers.getBits(header.data, 17, 0x0f);
	}
	
	public void alphaChannelBits(int count) {
		Buffers.putBits(header.data, 17, count, 0x0f);
	}
	
	public static enum ImageOrigin {
		BOTTOM_LEFT(0), BOTTOM_RIGHT(1), TOP_LEFT(2), TOP_RIGHT(3);
		
		private int origin;
		
		ImageOrigin(final int origin) {
			this.origin = origin;
		}
		
		public int getImageOrigin() {
			return origin;
		}
		
		public static ImageOrigin valueOf(int origin) {
			for(ImageOrigin io : ImageOrigin.values()) {
				if(io.getImageOrigin() == origin)
					return io;
			}
			throw new IllegalArgumentException("Unknown TGA image origin.");
		}
	}
	
	public ImageOrigin imageOrigin() {
		int origin = Buffers.getBits(header.data, 17, 0x30);
		return ImageOrigin.valueOf(origin);
	}
	
	public void imageOrigin(ImageOrigin origin) {
		Buffers.putBits(header.data, 17, origin.getImageOrigin(), 0x30);
	}
	
	/* IMAGE CHUNK */
	private static class Image {
		
		private ByteBuffer id;
		
		private ByteBuffer colorMap;
		
		private Image(ByteBuffer id, ByteBuffer colorMap) {
			id.order(ByteOrder.LITTLE_ENDIAN);
			colorMap.order(ByteOrder.LITTLE_ENDIAN);
			this.id = id;
			this.colorMap = colorMap;
		}
	}
	
	public ByteBuffer imageId() {
		return image.id;
	}
	
	public void imageId(ByteBuffer id) {
		image.id = id;
	}
	
	public ByteBuffer colorMap() {
		return image.colorMap;
	}
	
	public void colorMap(ByteBuffer colorMap) {
		image.colorMap = colorMap;
	}
	
	/* DEVELOPER AREA CHUNK */
	private static class DeveloperArea {
		
		private ByteBuffer fields;
		
		private ByteBuffer directory;
		
		private DeveloperArea(ByteBuffer fields, ByteBuffer directory) {
			// TODO Auto-generated constructor stub
		}
	}
	
	/* EXTENSION AREA CHUNK */
	private static class ExtensionArea {
		
		public static final int DATA_SIZE = 495;
		
		public static final int COLOR_CORRECTION_TABLE_SIZE = 2000;
		
		private ByteBuffer data;
		
		private ByteBuffer scanLineTable;
		
		private ByteBuffer postageStampImage;
		
		private ByteBuffer colorCorrectionTable;
		
		private ExtensionArea(ByteBuffer data, ByteBuffer scanLineTable,
				ByteBuffer postageStampImage, ByteBuffer colorCorrectionTable) {
			// TODO Auto-generated constructor stub
		}
	}
	
	/* FOOTER CHUNK */
	private static class Footer {
		
		private ByteBuffer data;
	
		private Footer(ByteBuffer data) {
			data.order(ByteOrder.LITTLE_ENDIAN);
			this.data = data;
		}
	}
	
	public long extensionAreaOffset() {
		return Buffers.getUnsignedInt(footer.data, 0);
	}
	
	public void extensionAreaOffset(long offset) {
		Buffers.putUnsignedInt(footer.data, 0, offset);
	}
	
	public long developerDirectoryOffset() {
		return Buffers.getUnsignedInt(footer.data, 4);
	}
	
	public void developerDirectoryOffset(long offset) {
		Buffers.putUnsignedInt(footer.data, 4, offset);
	}
	
	public String signature() {
		try {
			return Buffers.getString(footer.data, 8, FOOTER_SIGNATURE.length());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node getAsTree(String formatName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mergeTree(String formatName, Node root)
			throws IIOInvalidTreeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}