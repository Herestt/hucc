package com.herestt.common.imageio.plugins.tga;

import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;

public class TgaImageReaderSpi extends ImageReaderSpi {
	
	@Override
	public boolean canDecodeInput(Object source) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageReader createReaderInstance(Object extension)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}
}