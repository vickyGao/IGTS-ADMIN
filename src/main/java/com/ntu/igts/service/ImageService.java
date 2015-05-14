package com.ntu.igts.service;

import com.ntu.igts.model.Image;
import com.ntu.igts.model.container.ImageList;

public interface ImageService {

    public Image createImage(String token, Image image);

    public Image updateImage(String token, Image image);

    public void deleteImage(String token, String imageId);

    public ImageList getImagesByToken(String token);

    public String getStoragePath(String token);

    public Image getImageByFileName(String token, String fileName, String suffix);

    public Image getImageById(String token, String imageId);
}
