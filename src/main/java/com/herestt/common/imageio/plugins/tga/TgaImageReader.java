package com.herestt.common.imageio.plugins.tga;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

import com.herestt.common.imageio.plugins.tga.TgaMetadata.ImageType;

public class TgaImageReader extends ImageReader {

	private ImageInputStream stream;
	
	private TgaMetadata metadata;
	
	private boolean gotHeader = false;
	
	private boolean gotFooter = false;
	
	protected TgaImageReader(ImageReaderSpi originatingProvider) {
		super(originatingProvider);
		metadata = new TgaMetadata();
	}

	@Override
	public void setInput(Object input) {
		super.setInput(input);
		if(input instanceof ImageInputStream)
			stream = (ImageInputStream) input;
		stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
	}
	
	@Override
	public int getNumImages(boolean allowSearch) throws IOException {
		readHeader();
		if(metadata.imageType() == ImageType.NO_DATA)
			return 0;
		return 1;
	}

	@Override
	public int getWidth(int imageIndex) throws IOException {
		if(imageIndex != 0)
			throw new IndexOutOfBoundsException("imageIndex != 0");
		readHeader();
		return metadata.imageWidth();
	}

	@Override
	public int getHeight(int imageIndex) throws IOException {
		if(imageIndex != 0)
			throw new IndexOutOfBoundsException("imageIndex != 0");
		readHeader();
		return metadata.imageHeight();
	}

	@Override
	public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IIOMetadata getStreamMetadata() throws IOException {
		return null;
	}

	@Override
	public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
		if(imageIndex != 0)
			throw new IndexOutOfBoundsException("imageIndex != 0");
		readMetadata();
		return metadata;
	}

	@Override
	public BufferedImage read(int imageIndex, ImageReadParam param)
			throws IOException {
		if(imageIndex != 0)
			throw new IndexOutOfBoundsException("imageIndex != 0");
		if(stream == null)
			throw new IllegalStateException("Input souce not set.");
		readMetadata();
		return decode();
	}
	
	private void readMetadata() throws IOException {
		readHeader();
		readFooter();
	}
	
	private void readHeader() throws IOException {
		if(gotHeader)
			return;
		byte[] h = new byte[TgaMetadata.HEADER_SIZE];
		ByteBuffer header = ByteBuffer.wrap(h);
		stream.seek(0);
		stream.read(h);
		metadata.setHeader(header);
		gotHeader = true;
	}
	
	private void readFooter() throws IOException {
		if(gotFooter)
			return;
		byte[] f = new byte[TgaMetadata.FOOTER_SIZE];
		ByteBuffer footer = ByteBuffer.wrap(f);
		stream.seek(stream.length() - TgaMetadata.FOOTER_SIZE);
		stream.read(f);
		metadata.setFooter(footer);
		gotFooter = true;
	}

	private BufferedImage decode() throws IOException {
		
		LoopedIterator xIter = null, yIter = null;
		
		switch (metadata.imageOrigin()) {
			case BOTTOM_LEFT:
				xIter = new IncreasingIterator(metadata.xOrigin(), metadata.imageWidth());
				yIter = new DecreasingIterator(metadata.yOrigin(), metadata.imageHeight());
				break;

			case BOTTOM_RIGHT:
				xIter = new DecreasingIterator(metadata.xOrigin(), metadata.imageWidth());
				yIter = new DecreasingIterator(metadata.yOrigin(), metadata.imageHeight());
				break;
				
			case TOP_LEFT:
				xIter = new IncreasingIterator(metadata.xOrigin(), metadata.imageWidth());
				yIter = new IncreasingIterator(metadata.yOrigin(), metadata.imageHeight());
				break;
				
			case TOP_RIGHT:
				xIter = new DecreasingIterator(metadata.xOrigin(), metadata.imageWidth());
				yIter = new IncreasingIterator(metadata.yOrigin(), metadata.imageHeight());
				break;
				
			default:
				throw new IOException("Bad image origin.");
		}
		
		switch(metadata.imageType()) {
		
			case UNCOMPRESSED_COLOR_MAPPED :
				throw new UnsupportedOperationException("Unable to decode uncompressed color mapped images.");
				
			case UNCOMPRESSED_TRUE_COLOR :
				throw new UnsupportedOperationException("Unable to decode uncompressed true color images.");
			
			case UNCOMPRESSED_BLACK_AND_WHITE :
				throw new UnsupportedOperationException("Unable to decode uncompressed black and white images.");
			
			case RLE_COLOR_MAPPED :
				throw new UnsupportedOperationException("Unable to decode RLE color mapped images.");
				
			case RLE_TRUE_COLOR :
				return decodeRleTrueColor(xIter, yIter);		// Generic way.
				//return decodeRleTrueColor();					// Fastest way, but needs one method
																// for each origin type.
			case RLE_BLACK_AND_WHITE :
				throw new UnsupportedOperationException("Unable to decode RLE black and white images.");
				
			case NO_DATA :
			default :
				throw new IOException("Bad image type.");
		}
	}
	
	private BufferedImage decodeRleTrueColor(LoopedIterator xIter, LoopedIterator yIter)
			throws IOException {
		
		int width = metadata.imageWidth();
		int height = metadata.imageHeight();
		
		BufferedImage bi = new BufferedImage(width, 
				height, BufferedImage.TYPE_INT_ARGB);
		
		long imageDataOffset = TgaMetadata.HEADER_SIZE + metadata.idLength() + metadata.colorMapLength();
		long length = stream.length();
		if(length == -1)
			throw new IOException("Unable to decode this image - length of stream is missing!");
		stream.seek(imageDataOffset);
		
		int x, y, i;	// x coordinate, y coordinate, packet relative coordinate.
		byte rcf = 0, packetType = 0;
		int pixelCount = 0;
		while(yIter.hasNext()) {
			
			y = yIter.next();
			while(xIter.hasNext()) {
		
				i = 0;
				rcf = stream.readByte();		//Read the packet descriptor.
				packetType = (byte) ((rcf >> 7) & 1);
				pixelCount = ((rcf & 0x7f) + 1);
				
				if(packetType == 0) {			// Raw Packet case.
					while(i < pixelCount) {
						x = xIter.next();
						bi.setRGB(x, y, stream.readInt());
						i++;
					}
				}
				else if(packetType == 1) {		 // RLE Packet case.
					int pixel = stream.readInt();
					while(i < pixelCount) {
						x = xIter.next();
						bi.setRGB(x, y, pixel);
						i++;
					}
				}
			}
			xIter.reset();  // Reset the x iterator so that the next line can be read.
		}
		
		return bi;
	}
	
	@SuppressWarnings("unused")
	private BufferedImage decodeRleTrueColor() throws IOException {
		
		int width = metadata.imageWidth();
		int height = metadata.imageHeight();
		
		BufferedImage bi = new BufferedImage(width, 
				height, BufferedImage.TYPE_INT_ARGB);
		
		long imageDataOffset = TgaMetadata.HEADER_SIZE + metadata.idLength() + metadata.colorMapLength();
		long length = stream.length();
		if(length == -1)
			throw new IOException("Unable to decode this image - length of stream is missing!");
		
		stream.seek(imageDataOffset);
		for(int y = (height - 1); y >= 0; y--) {
			int count = 0;
			while(count < width) {
				byte rcf = stream.readByte();
				byte packetType = (byte) ((rcf >> 7) & 1);
				int pixelCount = ((rcf & 0x7f) + 1);
				if(packetType == 0) { // Raw Packet case.
					for(int x = count; x < count + pixelCount; x++)
						bi.setRGB(x, y, stream.readInt());
				}
				else if(packetType == 1) { // RLE Packet case.
					int pixel = stream.readInt();
					for(int x = count; x < count + pixelCount; x++)
						bi.setRGB(x, y, pixel);
				}
				count += pixelCount;
			}
		}
		
		return bi;
	}
	
	private static interface LoopedIterator extends Iterator<Integer> {
		
		public void reset();
	}
	
	private static class IncreasingIterator implements LoopedIterator {
	
		private int min;
		private int max;
		private int origin;
		
		public IncreasingIterator(int min, int max) {
			this.min = min;
			this.max = max;
			origin = min;
		}

		@Override
		public boolean hasNext() {
			return (min < max);
		}

		@Override
		public Integer next() {
			int v = min;
			min++;
			return v;
		}

		@Override
		public void reset() {
			min = origin;
		}
	}
	
	private static class DecreasingIterator implements LoopedIterator {
		
		private int min;
		private int max;
		private int origin;
		
		public DecreasingIterator(int min, int max) {
			this.min = min;
			this.max = max;
			origin = max;
		}

		@Override
		public boolean hasNext() {
			return (max > min);
		}

		@Override
		public Integer next() {
			return --max;
		}

		@Override
		public void reset() {
			max = origin;
		}
	}
}