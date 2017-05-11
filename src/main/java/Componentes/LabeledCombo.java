package Componentes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LabeledCombo {

	private Composite parent;
	private String textoLabel;
	private int estilo;
	Combo combo;

	public LabeledCombo(Composite parent, String textoLabel, int estilo) {
		super();
		this.parent = parent;
		this.textoLabel = textoLabel;
		this.estilo = estilo;
		generarLabeledCombo(parent, textoLabel, estilo);
	}
	
	public void generarLabeledCombo (Composite parent, String textoLabel, int estilo){
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
//		gridLayout.marginWidth = 5;
//		gridLayout.marginHeight = 5;
//		gridLayout.verticalSpacing = 0;
//		gridLayout.horizontalSpacing = 0;
		composite.setLayout(gridLayout);		
		
		Label label = new Label(composite, SWT.NONE);
		label.setText(textoLabel);
		
		combo = new Combo(composite, estilo);
		combo.setTouchEnabled(false);
		
	}
	
	public void add(String item){
		combo.add(item);
	}

}
