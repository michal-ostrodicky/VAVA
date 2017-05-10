package remotes;

import java.awt.image.BufferedImage;

import javax.ejb.Remote;
import javax.imageio.ImageIO;

import exceptions.NotReadableImageException;


@Remote
public interface QRCodeWorkerRemote {

	int getIdFromQRCode(byte[] imageQR) throws NotReadableImageException;
		
}
