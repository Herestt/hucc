package com.herestt.common.imageio.plugins.tga;

import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;

public class TgaImageWriterSpi extends ImageWriterSpi {

	@Override
	public boolean canEncodeImage(ImageTypeSpecifier type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageWriter createWriterInstance(Object extension)
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