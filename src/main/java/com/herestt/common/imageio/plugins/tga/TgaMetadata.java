package com.herestt.common.imageio.plugins.tga;

import java.nio.ByteBuffer;

import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;

import org.w3c.dom.Node;

public class TgaMetadata extends IIOMetadata {

	public static final String nativeImageMetadataFormatName = "herestt_imageio_tag_1.0";
	
	protected static final String nativeMetadataFormatClassName =
			"com.herestt.common.imageio.plugins.tga.TgaMetadataFormat";
	
	private Header header;
	
	private Image image;
	
	private DeveloperArea developerArea;
	
	private ExtensionArea extensionArea;
	
	private Footer footer;
	
	public TgaMetadata() {
		// TODO Auto-generated constructor stub
	}
	
	public void setHeader(ByteBuffer data) {
		// TODO - Herestt : implementation.
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
		// TODO - Herestt : implementation.
	}
	
	/* HEADER CHUNK */
	private static class Header {
		
		public static final int DATA_SIZE = 9;
		
		private ByteBuffer data;
		
		private Header(ByteBuffer data) {
			// TODO Auto-generated constructor stub
		}
	}
	
	public int idLength() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void idLength(int value) {
		// TODO - Herestt : implementation.
	}
	
	public static enum ColorMapType {
		NO_COLOR_MAP_INCLUDED(0), COLOR_MAP_INCLUDED(1);
		
		ColorMapType(final int type) {
			// TODO - Herestt : implementation.
		}
		
		public int getColorMapType() {
			// TODO - Herestt : implementation.
			return 0;
		}
	}
	
	public ColorMapType colorMapType() {
		// TODO - Herestt : implementation.
		return null;
	}
	
	public void colorMapType(ColorMapType type) {
		// TODO - Herestt : implementation.
	}
	
	public static enum ImageType {
		NO_DATA (0), UNCOMPRESSED_COLOR_MAPPED(1),
		UNCOMPRESSED_BLACK_AND_WHITE(2), UNCOMPRESSED_TRUE_COLOR(3),
		RLE_COLOR_MAPPED(9), RLE_BLACK_AND_WHITE(10), RLE_TRUE_COLOR(11);
		
		ImageType(final int i) {
			// TODO Auto-generated constructor stub
		}
		
		public int getImageType() {
			// TODO - Herestt : implementation.
			return 0;
		}
	}

	public ImageType imageType() {
		// TODO - Herestt : implementation.
		return null;
	}
	
	public void imageType(ImageType type) {
		// TODO - Herestt : implementation.
	}
	
	// Color Map Specification.
	public int firstEntryIndex() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void firstEntryIndex(int index) {
		// TODO - Herestt : implementation.
	}
	
	public int colorMapLength() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void colorMapLength(int length) {
		// TODO - Herestt : implementation.
	}
	
	public int colorMapEntrySize() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void colorMapEntrySize(int size) {
		// TODO - Herestt : implementation.
	}
	
	// Image Specification.
	public int xOrigin() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void xOrigin(int x) {
		// TODO - Herestt : implementation.
	}
	
	public int yOrigin() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void yOrigin(int y) {
		// TODO - Herestt : implementation.
	}
	
	public int imageWidth() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void imageWidth(int width) {
		// TODO - Herestt : implementation.
	}
	
	public int imageHeight() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void imageHeight(int height) {
		// TODO - Herestt : implementation.
	}
	
	public int pixelDepth() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void pixelDepth(int depth) {
		// TODO - Herestt : implementation.
	}
	
	// Image Descriptor.
	public int alphaChannelBits() {
		// TODO - Herestt : implementation.
		return 0;
	}
	
	public void alphaChannelBits(int count) {
		// TODO - Herestt : implementation.
	}
	
	public static enum ImageOrigin {
		BOTTOM_LEFT(0x00), BOTTOM_RIGHT(0x01), TOP_LEFT(0x10), TOP_RIGHT(0x11);
		
		ImageOrigin(final int origin) {
			// TODO - Herestt : implementation.
		}
		
		public int getImageOrigin() {
			// TODO - Herestt : implementation.
			return 0;
		}
	}
	public ImageOrigin imageOrigin() {
		// TODO - Herestt : implementation.
		return null;
	}
	
	public void imageOrigin(ImageOrigin origin) {
		// TODO - Herestt : implementation.
	}
	
	/* IMAGE CHUNK */
	private static class Image {
		
		private ByteBuffer imageId;
		
		private ByteBuffer colorMap;
		
		private Image(ByteBuffer imageId, ByteBuffer colorMap) {
			// TODO Auto-generated constructor stub
		}
	}
	
	public ByteBuffer imageId() {
		// TODO - Herestt : implementation.
		return null;
	}
	
	public void imageId(ByteBuffer id) {
		// TODO - Herestt : implementation.
	}
	
	public ByteBuffer colorMap() {
		// TODO - Herestt : implementation.
		return null;
	}
	
	public void colorMap(ByteBuffer map) {
		// TODO - Herestt : implementation.
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
		
		public static final int DATA_SIZE = 9;
		
		public static final String SIGNATURE = "TRUEVISION-XFILE";
		
		private ByteBuffer data;
	
		private Footer(ByteBuffer data) {
			// TODO Auto-generated constructor stub
		}
	}
	
	public long extensionAreaOffset() {
		// TODO Auto-generated constructor stub
		return 0;
	}
	
	public void extensionAreaOffset(long offset) {
		// TODO Auto-generated constructor stub
	}
	
	public long developerDirectoryOffset() {
		// TODO Auto-generated constructor stub
		return 0;
	}
	
	public void developerDirectoryOffset(long offset) {
		// TODO Auto-generated constructor stub
	}
	
	public String signature() {
		// TODO Auto-generated constructor stub
		return null;
	}
	
	public void signature(String str) {
		// TODO Auto-generated constructor stub
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