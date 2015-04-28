package com.herestt.common.imageio.spi;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.spi.ImageInputStreamSpi;
import javax.imageio.stream.ImageInputStream;

public class PathImageInputStreamSpi extends ImageInputStreamSpi {
	
	@Override
	public ImageInputStream createInputStreamInstance(Object input,
			boolean useCache, File cacheDir) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}
}