package Componentes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.internal.win32.TCHITTESTINFO;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class BotonImagen {
	

	public Button getBotonImagen(Display display,Composite parent, String ruta){
		
		Button button = new Button(parent, SWT.NONE);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button.setImage(new Image(display, new ImageData(ruta)));
		return button;
		
	}
	
	

}
