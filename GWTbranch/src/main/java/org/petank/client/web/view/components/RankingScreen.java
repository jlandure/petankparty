package org.petank.client.web.view.components;

import java.util.LinkedList;
import java.util.List;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.i18n.MessagesConstantsController;
import org.petank.client.web.util.GWTStringUtils;
import org.petank.client.web.view.RankingMediator;

import com.extjs.gxt.ui.client.Events;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.History;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class RankingScreen extends LayoutContainer implements IGridScreen{

	private static final String PLAYERS = "players";
	private static final String PLAYER = "player";
	private static final String DATE = "date";
	private static final String PLACE = "place";
	private static final String NAME = "nom";
	private static final String RANK = "rank";
	private static final String SCORE = "score";
	private static final String NB_PLAYED = "nbPlayed";
	private static final String WON = "won";
	private static final String LOST = "lost";
	private static final String POINTS_PER_MATCH = "ppm";
	protected static final String ICON_NAME = "iconName";
	protected static final String ID = "id";
	
	private RankingMediator mediator;
	private Grid<BaseModelData> grid;
	public Grid<BaseModelData> getGrid(){return grid;}

	
	private final GridCellRenderer<BaseModelData> linkRendererWithIcon = new GridCellRenderer<BaseModelData>() {  
		public String render(BaseModelData model, String property, ColumnData config, int rowIndex,  
				int colIndex, ListStore<BaseModelData> store) {
			String idObject = (String) model.get(property);

			String firstColInfo = "<span style=\"text-decoration:underline; color:blue; padding:0px; margin:0px;\">";
			String iconObjPath = model.get(ICON_NAME);
			if( GWTStringUtils.isNotEmpty(iconObjPath) ) {
				firstColInfo += "<img src=\"" + iconObjPath + "\" class=\"objIconTable\">&nbsp;";
			}
			firstColInfo += idObject + "</span>";
			return  firstColInfo;
		}  
	};
	
	
	public RankingScreen(RankingMediator mediator) {
		this.mediator = mediator;
		this.setLayout(new FlowLayout());
	}

	public void createRankingGrid(String xmlText){
		Document myDoc = XMLParser.parse(xmlText);
		myDoc.getDocumentElement().normalize();
		Element rootElement = myDoc.getDocumentElement();
		XMLParser.removeWhitespace(rootElement);
		
		ListStore<BaseModelData> store = new ListStore<BaseModelData>();
		String date = GWTStringUtils.EMPTY;
		
		if(PLAYERS.equals(rootElement.getNodeName())){
			date = rootElement.getAttributeNode(DATE).getNodeValue();
			NodeList players = rootElement.getChildNodes();
			for (int i = 0; i < players.getLength(); i++){
				Node player = players.item(i);
				BaseModelData playerData = new BaseModelData();
				
				playerData.set(PLACE, player.getAttributes().getNamedItem(PLACE).getNodeValue());
				playerData.set(NAME, player.getAttributes().getNamedItem(NAME).getNodeValue());
				playerData.set(SCORE, player.getAttributes().getNamedItem(SCORE).getNodeValue());
				
				store.add(playerData);
			}
		}
		
		List<ColumnConfig> columns = new LinkedList<ColumnConfig>();
		ColumnConfig rank = new ColumnConfig(PLACE, MessagesConstantsController.getConstants().rank(), 25);
		columns.add(rank);
		ColumnConfig name = new ColumnConfig(NAME, MessagesConstantsController.getConstants().name(), 100);
		name.setRenderer(linkRendererWithIcon);
		columns.add(name);
		ColumnConfig score = new ColumnConfig(SCORE, MessagesConstantsController.getConstants().score(), 25);
		columns.add(score);
		ColumnConfig nbPlayed = new ColumnConfig(NB_PLAYED, MessagesConstantsController.getConstants().nbPlayed(), 25);
		columns.add(nbPlayed);
		ColumnConfig won = new ColumnConfig(WON, MessagesConstantsController.getConstants().won(), 25);
		columns.add(won);
		ColumnConfig lost = new ColumnConfig(LOST, MessagesConstantsController.getConstants().lost(), 25);
		columns.add(lost);
		ColumnConfig ppm = new ColumnConfig(POINTS_PER_MATCH, MessagesConstantsController.getConstants().ppm(), 25);
		columns.add(ppm);
		
		ColumnModel cm = new ColumnModel(columns);
		grid = new Grid<BaseModelData>(store, cm);
		
		grid.addListener(Events.CellClick, new Listener<GridEvent>() {

			public void handleEvent(GridEvent ge) {
				ColumnConfig myCC = ge.grid.getColumnModel().getColumn(ge.colIndex);
				if(NAME.equals(myCC.getId())){
					String userId = ge.model.get(ID);
					History.newItem(PetankFacade.DISPLAY_USER_PAGE + "?" + userId);
				}
			}
		});
		grid.setSize(500, 300);
		removeAll();
		add(grid, new FlowData(20));
		layout();
	}
	
}
