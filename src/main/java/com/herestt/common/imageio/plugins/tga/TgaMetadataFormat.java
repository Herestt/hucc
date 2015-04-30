package com.herestt.common.imageio.plugins.tga;

import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadataFormat;
import javax.imageio.metadata.IIOMetadataFormatImpl;

public class TgaMetadataFormat extends IIOMetadataFormatImpl {

	private static IIOMetadataFormat instance = null;
	
	private TgaMetadataFormat() {
		super(TgaMetadata.nativeImageMetadataFormatName,
				CHILD_POLICY_SOME);
	}

	public static synchronized IIOMetadataFormat getInstance() {
		if(instance != null)
			instance = new TgaMetadataFormat();
		return instance;
	}
	
	@Override
	public boolean canNodeAppear(String elementName,
			ImageTypeSpecifier imageType) {
		// TODO Auto-generated method stub
		return false;
	}
}