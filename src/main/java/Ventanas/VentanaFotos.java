package Ventanas;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import Helpers.ColorHelper;
import Helpers.WindowCenterHelper;

public class VentanaFotos implements Runnable {
	private final String rutaImagen = "src/resources/abrir.png";
	private final String rutaImagenSave = "src/resources/save.png";
	static List<String> listaFotos;

	private Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
		image.dispose(); // don't forget about me!
		return scaled;
	}

	public void run() {
		final Shell shell;
		final Display display = new Display();
		shell = new Shell(display, SWT.MIN);
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(800, 600);

		final List<Image> listaImagenes = new ArrayList<Image>();

		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridData.widthHint = SWT.DEFAULT;
		gridData.heightHint = SWT.DEFAULT;
		composite.setLayoutData(gridData);
		composite.setBackground(ColorHelper.COLOR_BLACK);

		// Creamos la toolbar
		ToolBar toolBar = new ToolBar(composite, SWT.WRAP);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		toolBar.setBackground(ColorHelper.COLOR_BLACK);

		// Anhadimos al toolbar el iconito para ejecutar un filechooser
		ToolItem itemPush = new ToolItem(toolBar, SWT.PUSH);
		Image imagen = new Image(display, rutaImagen);
		itemPush.setToolTipText("Abrir");
		itemPush.setImage(imagen);

		// Anhadimos al toolbar el iconito para guardar
		ToolItem itemPush2 = new ToolItem(toolBar, SWT.PUSH);
		Image imagen2 = new Image(display, rutaImagenSave);
		itemPush2.setToolTipText("Guardar");
		itemPush2.setImage(imagen2);

		// Label donde se insertaran las fotos
		final Label label = new Label(composite, SWT.BORDER);
		Image im2 = new Image(display, "src/resources/blanco.jpg");
		im2 = resize(im2, 770, 500);
		label.setImage(im2);
		

		final Slider slider = new Slider(composite, SWT.HORIZONTAL);
		slider.setVisible(false);
		slider.setLayoutData(new GridData(SWT.FILL, SWT.END, true, false));
		slider.setBackground(ColorHelper.COLOR_LIGHT_PINK);

		// Accion de pulsar el boton seleccion de ficheros
		itemPush.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Open");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.jpg", "*.png" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();

				Image im2 = new Image(display, selected);
				im2 = resize(im2, 770, 500);
				listaImagenes.add(im2);
				label.setImage(im2);

				// Configuramos el slider
				if (listaImagenes.size() > 1)
					slider.setVisible(true);
				slider.setMinimum(0);
				slider.setMaximum(listaImagenes.size());
				slider.setIncrement(1);
				slider.setSelection(listaImagenes.size() - 1);

				slider.addSelectionListener(new SelectionListener() {

					public void widgetSelected(SelectionEvent arg0) {
						label.setImage(listaImagenes.get(slider.getSelection()));

					}

					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub

					}
				});
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Accion de pulsar el boton guardar
		itemPush2.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("tamanho lista: " + listaImagenes.size());
				System.out.println("posicion slider " + slider.getSelection());

			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Centrar la ventana
		WindowCenterHelper.centrarVentana(display, shell);

		shell.open();

		// Con esto hacemos que la shell no se desvanezca en tiempo de ejecucion
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}

}
