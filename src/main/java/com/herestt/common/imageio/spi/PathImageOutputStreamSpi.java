package com.herestt.common.imageio.spi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

import javax.imageio.spi.ImageOutputStreamSpi;
import javax.imageio.stream.ImageOutputStream;

import com.herestt.common.imageio.stream.PathImageOutputStream;

public class PathImageOutputStreamSpi extends ImageOutputStreamSpi {

	private static final String vendorName = "Herestt";
	
	private static final String version = "1.0";
	
	private static final Class<?> inputClass = Path.class;
	
	public PathImageOutputStreamSpi() {
		super(vendorName, version, inputClass);
	}
	
	@Override
	public ImageOutputStream createOutputStreamInstance(Object output,
			boolean useCache, File cacheDir) throws IOException {
		if(!(output instanceof Path))
			throw new IllegalArgumentException();
		
		try {
			return new PathImageOutputStream((Path) output);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		return "Service provider that instantiates a PathImageOutputStream from a Path.";
	}
}