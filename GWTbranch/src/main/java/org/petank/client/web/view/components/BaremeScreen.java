package org.petank.client.web.view.components;

import java.util.LinkedList;
import java.util.List;

import org.petank.client.web.i18n.MessagesConstantsController;
import org.petank.client.web.view.BaremeMediator;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class BaremeScreen extends LayoutContainer  implements IGridScreen{

	

	private static final String BAREMES = "baremes";
	private static final String MIN = "minimum";
	private static final String MAX = "maximum";
	private static final String VICTOIRE_NORMALE = "victoireNormale";
	private static final String VICTOIRE_ANORMALE = "victoireAnormale";
	private static final String DEFAITE_NORMALE = "defaiteNormale";
	private static final String DEFAITE_ANORMALE = "defaiteAnormale";
	
	private BaremeMediator mediator;
	private Grid<BaseModelData> grid;
	public Grid<BaseModelData> getGrid(){return grid;}

	
	
	public BaremeScreen(BaremeMediator mediator) {
		this.mediator = mediator;
		this.setLayout(new FlowLayout());		
	}

	public void addBaremeGrid(Grid<BaseModelData> grid){
		
	}

	public void createBaremeGrid(String xmlText) {
		Document myDoc = XMLParser.parse(xmlText);
		myDoc.getDocumentElement().normalize();
		Element rootElement = myDoc.getDocumentElement();
		XMLParser.removeWhitespace(rootElement);
		
		ListStore<BaseModelData> store = new ListStore<BaseModelData>();
		
		if(BAREMES.equals(rootElement.getNodeName())){
			
			NodeList baremes = rootElement.getChildNodes();
			for (int i = 0; i < baremes.getLength(); i++){
				Node bareme = baremes.item(i);
				BaseModelData baremeData = new BaseModelData();
				
				baremeData.set(MIN, bareme.getAttributes().getNamedItem(MIN).getNodeValue());
				baremeData.set(MAX, bareme.getAttributes().getNamedItem(MAX).getNodeValue());
				baremeData.set(VICTOIRE_NORMALE, bareme.getAttributes().getNamedItem(VICTOIRE_NORMALE).getNodeValue());
				baremeData.set(VICTOIRE_ANORMALE, bareme.getAttributes().getNamedItem(VICTOIRE_ANORMALE).getNodeValue());
				baremeData.set(DEFAITE_NORMALE, bareme.getAttributes().getNamedItem(DEFAITE_NORMALE).getNodeValue());
				baremeData.set(DEFAITE_ANORMALE, bareme.getAttributes().getNamedItem(DEFAITE_ANORMALE).getNodeValue());
				store.add(baremeData);
			}
		}
		
		List<ColumnConfig> columns = new LinkedList<ColumnConfig>();
		ColumnConfig min = new ColumnConfig(MIN, MessagesConstantsController.getConstants().min(), 25);
		columns.add(min);
		ColumnConfig max = new ColumnConfig(MAX, MessagesConstantsController.getConstants().max(), 25);
		columns.add(max);
		ColumnConfig vicNorm = new ColumnConfig(VICTOIRE_NORMALE, MessagesConstantsController.getConstants().vicNorm(), 100);
		columns.add(vicNorm);
		ColumnConfig vicAnorm = new ColumnConfig(VICTOIRE_ANORMALE, MessagesConstantsController.getConstants().vicAnorm(), 100);
		columns.add(vicAnorm);
		ColumnConfig defNorm = new ColumnConfig(DEFAITE_NORMALE, MessagesConstantsController.getConstants().defNorm(), 100);
		columns.add(defNorm);
		ColumnConfig defAnorm = new ColumnConfig(DEFAITE_ANORMALE, MessagesConstantsController.getConstants().defAnorm(), 100);
		columns.add(defAnorm);
		
		ColumnModel cm = new ColumnModel(columns);
		grid = new Grid<BaseModelData>(store, cm);
		
		grid.setSize(500, 300);
		removeAll();
		add(grid, new FlowData(20));
		layout();
		
	}
	
}
