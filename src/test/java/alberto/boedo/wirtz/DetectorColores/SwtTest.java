package alberto.boedo.wirtz.DetectorColores;

import java.awt.datatransfer.StringSelection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Helpers.ColorHelper;

public class SwtTest {

	
	public static void main(String[] args) {
		Display display = new Display();
		//El segundo parametro hace que no se pueda redimensionar la ventana
		Shell shell = new Shell(display, SWT.MIN);
		shell.setText("Mi ejemplo");
		shell.setLayout(new GridLayout());
		shell.setSize(400, 600);
		
		
		
		//Asi se hace un link
		Link link = new Link(shell, SWT.NONE);
	    link.setText("<A>This a very simple link widget.</A>");
	    link.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("Me has pulsado");
				
			}
			
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    link.setSize(140, 40);
	    
	    
	    //Combo
	    Combo combo = new Combo(shell, SWT.NONE);
		
	    
		Button button = new Button(shell, SWT.NONE);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button.setImage(new Image(display, new ImageData("src/resources/return.png")));
		button.setBackground(ColorHelper.COLOR_DARK_GREEN);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("me estan pulsando");
			}
		});
		
		Text text = new Text(shell, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		text.setSize(20, 20);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		text.setText("Campo texto");
		
		
		
		shell.open();
		
		// con esto hacemos que la shell no se desvanezca en tiempo de ejecucion
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				display.dispose();
	}
}
