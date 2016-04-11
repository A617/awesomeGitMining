package org.Common.po;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.Serializable;

public class SerializableImage implements Serializable{

	protected transient BufferedImage image;
	
	public SerializableImage(BufferedImage image) {
		// TODO Auto-generated constructor stub
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
}
