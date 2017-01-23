package com.fesco.bookpay.event;


import com.fesco.bookpay.entity.Image;

public class RefreshImageSelect {
	
	private Image image;

	public RefreshImageSelect(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}
}
