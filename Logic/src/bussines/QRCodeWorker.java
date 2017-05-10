package bussines;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import exceptions.NotReadableImageException;
import remotes.QRCodeWorkerRemote;

/**
 * Session Bean implementation class QRCodeWorker
 */
@Stateless
@LocalBean
public class QRCodeWorker implements QRCodeWorkerRemote {

	@Override
	public int getIdFromQRCode(byte[] imageQR) throws NotReadableImageException {
		
		// Get Image
		BufferedImage image;
		
		// Construct image from byte Array
		try {
			image = ImageIO.read(new ByteArrayInputStream(imageQR));
		} catch (IOException e1) {
			throw new NotReadableImageException();
		}
		
		// Create bitmap from buffered image
		BinaryBitmap bitmap = new BinaryBitmap(
				new HybridBinarizer(
						new BufferedImageLuminanceSource(image)));
		
		//Create reader
		QRCodeReader reader = new QRCodeReader();
		
		Result result = null;
		
		// Decode result
		try {
			result = reader.decode(bitmap);
		} catch (Exception e) {

			throw new NotReadableImageException();
		}
		
		System.out.println("id : "+result.getText());
		
		return Integer.parseInt(result.getText());
	}
    

}
