package com.amazon.ata.immutabilityandfinal.classroom.primephoto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class representing a PrimePhoto - contains dimensions, and a list of Pixels that make up the image.
 */
// make the class immutable
//    add final to the class
//    made instance variables final
//    defensive copy any references passed to ctor
//    defensive return in getter
public class PrimePhoto {
    // make the instance variables final so they are immutable (can't be changed)
    private final List<Pixel> pixels;
    private final int height;
    private final int width;
    // used when saving to a buffered image
    private final int type;

    // ctor is receiving a reference - List<List> - defensive copy of parameter
    public PrimePhoto(List<Pixel> pixelList, int height, int width, int type) {
        if (pixelList.size() != (height * width)) {
            throw new IllegalArgumentException("Not enough pixels for the dimensions of the image.");
        }
        // this.pixels = pixelList;  // replaced by defensive copy of the parameter
        this.pixels = new ArrayList<>(pixelList); // Copy parameter to new List object
        this.height = height;
        this.width = width;
        this.type = type;
    }

    // getter is returning reference - List<Pixel> - defensive return
    public List<Pixel> getPixels() {

        // return pixels;  // replaced with defensive return
        return new ArrayList<>(pixels); // return a copy of the instance variable
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pixels, height, width, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        PrimePhoto photo = (PrimePhoto) obj;

        return photo.height == this.height && photo.width == this.width &&
            photo.type == photo.type && Objects.equals(photo.pixels, this.pixels);
    }

}
