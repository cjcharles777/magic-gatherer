/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightningboltu.magic.gatherer.util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Cedric
 */
public class ImageUtil 
{
    public static byte[] convertInternetImageToByteArray (String url) throws IOException
    {
    
        byte[] imageInByte;
        BufferedImage originalImage = ImageIO.read(new URL(url));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos);
        baos.flush();
        return baos.toByteArray();
    }
}
