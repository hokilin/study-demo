package cn.hokilin.studydemo.bitmap;

import com.googlecode.javaewah.EWAHCompressedBitmap;

import java.io.*;

/**
 * @author linhuankai
 * @date 2020/11/3 16:38
 */
public class MyEWAHBitMap {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EWAHCompressedBitmap ewahBitmap1 = EWAHCompressedBitmap.bitmapOf(0, 2, 55, 64, 1 << 30);
        EWAHCompressedBitmap ewahBitmap2 = EWAHCompressedBitmap.bitmapOf(1, 3, 64, 1 << 30);
        EWAHCompressedBitmap ewahBitmap3 = EWAHCompressedBitmap.bitmapOf(5, 55, 1 << 30);
        EWAHCompressedBitmap ewahBitmap4 = EWAHCompressedBitmap.bitmapOf(4, 66, 1 << 30);
        EWAHCompressedBitmap orbitmap = ewahBitmap1.or(ewahBitmap2);
        EWAHCompressedBitmap andbitmap = ewahBitmap1.and(ewahBitmap2);
        EWAHCompressedBitmap xorbitmap = ewahBitmap1.xor(ewahBitmap2);
        andbitmap = EWAHCompressedBitmap.and(ewahBitmap1, ewahBitmap2, ewahBitmap3, ewahBitmap4);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bos);
        ewahBitmap1.writeExternal(oo);
        oo.close();
        ewahBitmap1 = null;
        ewahBitmap1 = new EWAHCompressedBitmap();
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ewahBitmap1.readExternal(new ObjectInputStream(bis));
        EWAHCompressedBitmap threshold2 = EWAHCompressedBitmap.threshold(2, ewahBitmap1, ewahBitmap2, ewahBitmap3, ewahBitmap4);
    }
}
