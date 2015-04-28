package com.herestt.common.imageio.spi;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.Locale;

import javax.imageio.spi.ImageInputStreamSpi;
import javax.imageio.stream.ImageInputStream;

import com.herestt.common.imageio.stream.PathImageInputStream;

public class SbcImageInputStreamSpi extends ImageInputStreamSpi {

	private static final String vendorName = "Herestt";
	
	private static final String version = "1.0";
	
	private static final Class<?> inputClass = SeekableByteChannel.class;
	
	public SbcImageInputStreamSpi() {
		super(vendorName, version, inputClass);
	}
	
	@Override
	public ImageInputStream createInputStreamInstance(Object input,
			boolean useCache, File cacheDir) throws IOException {
		if(!(input instanceof SeekableByteChannel))
			throw new IllegalArgumentException();
		
		try {
			return new PathImageInputStream((SeekableByteChannel) input);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		return "Service provider that instantiates a PathImageInputStream from a SeekableByteChannel.";
	}
}