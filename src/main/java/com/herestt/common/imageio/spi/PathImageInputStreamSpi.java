package com.herestt.common.imageio.spi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

import javax.imageio.spi.ImageInputStreamSpi;
import javax.imageio.stream.ImageInputStream;

import com.herestt.common.imageio.stream.PathImageInputStream;

public class PathImageInputStreamSpi extends ImageInputStreamSpi {
	
	private static final String vendorName = "Herestt";
	
	private static final String version = "1.0";
	
	private static final Class<?> inputClass = Path.class;
	
	public PathImageInputStreamSpi() {
		super(vendorName, version, inputClass);
	}
	
	@Override
	public ImageInputStream createInputStreamInstance(Object input,
			boolean useCache, File cacheDir) throws IOException {
		if(!(input instanceof Path))
			throw new IllegalArgumentException();
		
		try {
			return new PathImageInputStream((Path) input);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		return "Service provider that instantiates a PathImageInputStream from a Path.";
	}
}