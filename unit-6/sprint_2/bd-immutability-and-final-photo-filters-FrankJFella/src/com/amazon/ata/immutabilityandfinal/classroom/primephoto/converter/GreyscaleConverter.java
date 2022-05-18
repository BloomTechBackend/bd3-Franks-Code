package com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.ConversionType;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.Pixel;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.PrimePhoto;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.RGB;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.util.PrimePhotoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts an image to a grey scale version.
 */
public class GreyscaleConverter implements PrimePhotoConverter {

    public String convert(final PrimePhoto image, final String imageName) {
        List<Pixel> pixels = new ArrayList<>();

        for (Pixel pixel : image.getPixels()) {
            RGB rgb = pixel.getRGB();
            // rgb.toGreyScale();     // original code - making changes to the rgb single shared object
                                      // now each conversion type has their own new RGB object returned
                                      //     we have to stored the object returned from converted in our object
                                      //
                                      // like doing:       aString.upperCase(); - forgot to store the result from method()
                                      //     rather than:  aString = aString.upperCase();
            rgb = rgb.toGreyScale();  // Convert to grey scale and store it back into original object
            pixels.add(new Pixel(pixel.getX(), pixel.getY(), rgb));
        }

        PrimePhoto convertedImage = new PrimePhoto(pixels, image.getHeight(), image.getWidth(), image.getType());

        return PrimePhotoUtil.savePrimePhoto(convertedImage, imageName, ConversionType.GREYSCALE);
    }
}
