package Filtros;

import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvReleaseImage;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

/**
 *
 * @author Alberto
 */
public class FiltradoEstatico {

	private static double value = 150;

	public static void aumentarValor() {
		value += 10;
	}

	public static void disminuirValor() {
		value -= 10;
	}

	public static void setValorOriginal() {
		value = 150;
	}

	public static List<String> conversor(String ruta, String color) {

		IplImage img1, imghsv, imgbin;
		List<String> parImagenes = new ArrayList<String>(2);

		try {
			System.out.println("entro");

			BufferedImage imagen = ImageIO.read(new File(ruta));
			img1 = IplImage.createFrom(imagen);

			imghsv = cvCreateImage(cvGetSize(img1), 8, 3);
			imgbin = cvCreateImage(cvGetSize(img1), 8, 1);

			cvCvtColor(img1, imghsv, CV_BGR2HSV);
			// Rango de colores por los que se va a filtrar minc maxc
			CvScalar minc, maxc;

			switch (color) {
			default:
				minc = cvScalar(95, value, 75, 0);
				maxc = cvScalar(135, 255, 255, 0);
				break;
			case "Azul":
				minc = cvScalar(95, value, 75, 0);
				maxc = cvScalar(135, 255, 255, 0);
				break;
			case "Rojo":
				minc = cvScalar(0, value, 75, 0);
				maxc = cvScalar(10, 255, 255, 0);
				break;
			case "Verde":
				minc = cvScalar(25, value, 75, 0);
				maxc = cvScalar(70, 255, 255, 0);
				break;
			case "Violeta":
				minc = cvScalar(135, value, 75, 0);
				maxc = cvScalar(150, 255, 255, 0);
				break;
			case "Amarillo/Naranja":
				minc = cvScalar(10, value, 75, 0);
				maxc = cvScalar(25, 255, 255, 0);
				break;
			}

			cvInRangeS(imghsv, minc, maxc, imgbin);

			System.out.println("El valor es " + value);

			cvWaitKey();

			cvSaveImage("src/resources/temp/Original.jpg", img1);
			parImagenes.add("src/resources/temp/Original.jpg");
			cvSaveImage("src/resources/temp/Filtrada.jpg", imgbin);
			parImagenes.add("src/resources/temp/Filtrada.jpg");

			cvReleaseImage(imghsv);
			cvReleaseImage(imgbin);

			return parImagenes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
