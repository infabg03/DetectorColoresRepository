package Ventanas;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import Componentes.BotonImagen;
import Helpers.ColorHelper;
import Helpers.WindowCenterHelper;

public class VentanaSelectora implements Runnable {
	Shell shell;
	Display display;
	String passwd;
	
	
	
	

	public VentanaSelectora(String passwd) {
		super();
		this.passwd = passwd;
	}

	public void ponerVisibles(List<Button> arrayBtn, boolean visible) {
		for (Button boton : arrayBtn) {
			boton.setVisible(visible);
		}
	}
	
	public void ejecutarListerners(Button editar, Button salir,Button estatico, Button dinamico, Button cambioColor, Button moveMouse, Button galeria){
		salir.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent arg0) {
				Thread t = new Thread(new VentanaPrincipal());
				t.start();
				shell.dispose();
				
				
			}
			
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		editar.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent arg0) {
				VentanaRegistroModal secondWindow = new VentanaRegistroModal(VentanaSelectora.this.shell,true,passwd);
				secondWindow.open();
				
			}
			
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public void run() {
		display = new Display();
		
		//Hace que la shell sea de tamano fijo
		shell = new Shell(display, SWT.MIN);
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(425, 300);
		shell.setBackground(ColorHelper.COLOR_BLACK);
		
		//Deshabilita boton cerrado de la shell
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				e.doit = false;
			}
		});

		Composite composite = new Composite(shell, SWT.NONE);
		GridLayout gridLayout = new GridLayout(3, false);
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		composite.setBackground(ColorHelper.COLOR_BLACK);

		final Button btnEditar = new BotonImagen().getBotonImagen(display, composite, "src/resources/boss.png");
		btnEditar.setToolTipText("Editar perfil");
		final Button btnOpciones = new BotonImagen().getBotonImagen(display, composite, "src/resources/opencv.png");
		btnOpciones.setToolTipText("Opciones Opencv");
		final Button btnSalir = new BotonImagen().getBotonImagen(display, composite, "src/resources/exit.png");
		btnSalir.setToolTipText("Salir");

		Composite compositeSup = new Composite(shell, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout(5, false);
		gridLayout2.marginTop = 20;
		compositeSup.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		compositeSup.setLayout(gridLayout2);
		compositeSup.setBackground(ColorHelper.COLOR_BLACK);

		final List<Button> listaBotones = new ArrayList<Button>();

		final Button btnFiltradoEstatico = new BotonImagen().getBotonImagen(display, compositeSup,
				"src/resources/filtradoestatico.png");
		btnFiltradoEstatico.setToolTipText("Filtrado estatico");
		btnFiltradoEstatico.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
				Thread tFiltradoEstatico = new Thread(new VentanaFiltradoEstatico());
				tFiltradoEstatico.start();
				
				
			}
			
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		listaBotones.add(btnFiltradoEstatico);
		final Button btnFiltradoDinamico = new BotonImagen().getBotonImagen(display, compositeSup,
				"src/resources/filtradodinamico.png");
		btnFiltradoDinamico.setToolTipText("Filtrado dinamico");
		listaBotones.add(btnFiltradoDinamico);
		final Button btnCambioColor = new BotonImagen().getBotonImagen(display, compositeSup, "src/resources/cambiocolor.png");
		btnCambioColor.setToolTipText("Cambia de color tu fotografia");
		listaBotones.add(btnCambioColor);
		final Button btnMoverRaton = new BotonImagen().getBotonImagen(display, compositeSup, "src/resources/moverraton.png");
		listaBotones.add(btnMoverRaton);
		btnMoverRaton.setToolTipText("Mueve raton con la camara");
		final Button btnGaleria = new BotonImagen().getBotonImagen(display, compositeSup, "src/resources/galeria.png");
		listaBotones.add(btnGaleria);
		btnGaleria.setToolTipText("Ver mis fotos");
		
		ponerVisibles(listaBotones, false);

		btnOpciones.addListener(SWT.MouseHover, new Listener() {

			public void handleEvent(Event arg0) {
				ponerVisibles(listaBotones, true);
				btnOpciones.forceFocus();
			}
		});
		
		btnEditar.addListener(SWT.MouseHover, new Listener() {

			public void handleEvent(Event arg0) {
				ponerVisibles(listaBotones, false);

			}
		});
		
		btnSalir.addListener(SWT.MouseHover, new Listener() {

			public void handleEvent(Event arg0) {
				ponerVisibles(listaBotones, false);

			}
		});
		
		//Seccion listeners
		ejecutarListerners(btnEditar,btnSalir,btnFiltradoEstatico, btnFiltradoDinamico, btnCambioColor, btnMoverRaton, btnGaleria);

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
