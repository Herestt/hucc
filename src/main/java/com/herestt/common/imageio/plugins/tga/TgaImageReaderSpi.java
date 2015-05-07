package com.herestt.common.imageio.plugins.tga;

import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

public class TgaImageReaderSpi extends ImageReaderSpi {
	
	private static final String vendorName = "Herestt";
	
	private static final String version = "1.0";
	
	private static final String[] names = {"tga", "TGA", "TARGA"};
	
	private static final String[] suffixes = {"tga"};
			
	private static final String[] MIMETypes = {"image/tga", "image/x-tga", "image/targa", "image/x-targa"};

	private static final String readerClassName = "com.herestt.common.imageio.plugins.tga.TgaImageReader";
	
	private static final String[] writerSpiNames = {
		"com.herestt.common.imageio.plugins.tga.TgaImageWriterSpi"
	};
	
	private static final String nativeImageMetadataFormatClassName =
			"com.herestt.common.imageio.plugins.tga.TgaMetadataFormat";
	
	private static final String signature = "TRUEVISION-XFILE";
	
	private ImageInputStream stream;
	
	public TgaImageReaderSpi() {
		super(vendorName,
				version,
				names,
				suffixes,
				MIMETypes,
				readerClassName,
				new Class[] { ImageInputStream.class },
				writerSpiNames,
				false,
				null, null,
				null, null,
				true,
				TgaMetadata.nativeImageMetadataFormatName,
				nativeImageMetadataFormatClassName,
				null, null);
	}
	
	@Override
	public boolean canDecodeInput(Object source) throws IOException {
		if(!(source instanceof ImageInputStream))
			return false;
		ImageInputStream stream = (ImageInputStream) source;
		byte[] signatureBytes = new byte[signature.length()]; 
		long pos = stream.length() - 17 - 1;	// Signature starting offset.
		stream.seek(pos);
		stream.readFully(signatureBytes);
		if(new String(signatureBytes).equals(signature)) {
			this.stream = stream;
			return true;
		}
		return false;
	}

	@Override
	public ImageReader createReaderInstance(Object extension)
			throws IOException {
		ImageReader reader = new TgaImageReader(this);
		reader.setInput(stream);
		return reader;
	}

	@Override
	public String getDescription(Locale locale) {
		return "Standard TGA image reader.";
	}
}