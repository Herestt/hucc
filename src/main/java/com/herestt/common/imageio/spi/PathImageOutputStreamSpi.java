package com.herestt.common.imageio.spi;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.spi.ImageOutputStreamSpi;
import javax.imageio.stream.ImageOutputStream;

public class PathImageOutputStreamSpi extends ImageOutputStreamSpi {

	@Override
	public ImageOutputStream createOutputStreamInstance(Object output,
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