package org.petank.client.web.view.components;

import java.util.LinkedList;
import java.util.List;

import org.petank.client.web.i18n.MessagesConstantsController;
import org.petank.client.web.view.MatchesMediator;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
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

public class MatchesScreen extends LayoutContainer implements IGridScreen{

	private static final Object MATCHES = "matchs";
	private static final String DATE = "date";
	private static final String ID = "id";
	private static final String BAREME = "bareme";
	private MatchesMediator mediator;
	private Grid<BaseModelData> grid;
	public Grid<BaseModelData> getGrid(){return grid;}

	
	
	public MatchesScreen(MatchesMediator mediator) {
		this.mediator = mediator;
		this.setLayout(new FlowLayout());
		
	}

	public void addMatchGrid(Grid<BaseModelData> grid){
		
	}

	public void createMatchGrid(String xmlText) {
		Document myDoc = XMLParser.parse(xmlText);
		myDoc.getDocumentElement().normalize();
		Element rootElement = myDoc.getDocumentElement();
		XMLParser.removeWhitespace(rootElement);
		
		ListStore<BaseModelData> store = new ListStore<BaseModelData>();
		
		if(MATCHES.equals(rootElement.getNodeName())){
			
			NodeList matches = rootElement.getChildNodes();
			for (int i = 0; i < matches.getLength(); i++){
				Node match = matches.item(i);
				BaseModelData matchData = new BaseModelData();
				
				matchData.set(ID, match.getAttributes().getNamedItem(ID).getNodeValue());
				matchData.set(DATE, match.getAttributes().getNamedItem(DATE).getNodeValue());
				matchData.set(BAREME, match.getAttributes().getNamedItem(BAREME).getNodeValue());
				
				store.add(matchData);
			}
		}
		
		List<ColumnConfig> columns = new LinkedList<ColumnConfig>();
		ColumnConfig id = new ColumnConfig(ID, MessagesConstantsController.getConstants().id(), 25);
		columns.add(id);
		ColumnConfig date = new ColumnConfig(DATE, MessagesConstantsController.getConstants().date(), 100);
		columns.add(date);
		ColumnConfig bareme = new ColumnConfig(BAREME, MessagesConstantsController.getConstants().bareme(), 25);
		columns.add(bareme);

		
		ColumnModel cm = new ColumnModel(columns);
		grid = new Grid<BaseModelData>(store, cm);
		
		grid.setSize(500, 300);
		removeAll();
		add(grid, new FlowData(20));
		layout();
		
	}
	
}
