package Componentes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.omg.CORBA.PRIVATE_MEMBER;

public class LabeledEditText {

	private Composite parent;
	private String textoInterior;
	private String textoLabel;
	private boolean passwd;
	private int filtros;
	private int ancho;
	Text text;

	private boolean escribo = false;

	public LabeledEditText(Composite parent, int estilo, String textoInterior, String textoLabel, boolean passwd,
			int ancho) {
		this.parent = parent;
		this.textoInterior = textoInterior;
		this.textoLabel = textoLabel;
		this.passwd = passwd;
		this.ancho = ancho;
		getLabelEditText(parent, textoInterior, textoLabel, passwd, ancho);

	}

	public void getLabelEditText(Composite parent, String textoInterior, String textoLabel, boolean passwd, int ancho) {

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);

		Label label = new Label(composite, SWT.LEFT);
		label.setText(textoLabel);
		GridData gd = new GridData();
		gd.widthHint = ancho;
		label.setLayoutData(gd);

		text = new Text(composite, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		text.setToolTipText(textoInterior);
		text.setText(textoInterior);
		GridData gridData2 = new GridData(SWT.BEGINNING, SWT.FILL, true, true);
		gridData2.widthHint = 180;
		gridData2.heightHint = SWT.DEFAULT;
		text.setLayoutData(gridData2);
		if (passwd)
			text.setEchoChar('*');

		// Hacemos esto para el borrado de los textos de los combos
		text.addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			// KeyEvent.getKeyText(e.getKeyCode()))
			public void keyPressed(KeyEvent arg0) {
				if (!escribo) {
					text.setText("");
					escribo = true;
				}
			}
		});

	}

	public String getText() {
		return text.getText();
	}

	public void setText(String texto) {
		text.setText(texto);
	}
	
	public void setDisable(){
		text.setEditable(false);
	}
}
