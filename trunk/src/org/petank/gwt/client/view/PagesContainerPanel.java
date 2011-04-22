package org.petank.gwt.client.view;

import org.petank.gwt.client.model.IPage;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.ObservableStack;
import org.petank.gwt.client.util.ObservableStackPopEvent;
import org.petank.gwt.client.util.ObservableStackPushEvent;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public class PagesContainerPanel extends Composite {

	private ObservableStack<IPage> mPageModel;
	private FlowPanel mBasePanel;
	private GenericContainerTag mActiveContainerPanel;
	private GenericContainerTag mInactiveContainerPanel;
	private boolean mIsIphone;

	public PagesContainerPanel() {

		mBasePanel = new FlowPanel();
		mBasePanel.getElement().setId("pages_container");

		mActiveContainerPanel = new GenericContainerTag("div");
		mInactiveContainerPanel = new GenericContainerTag("div");

		mBasePanel.add(mActiveContainerPanel);
		mBasePanel.add(mInactiveContainerPanel);

		mActiveContainerPanel.setStyleName("page");
		mInactiveContainerPanel.setStyleName("page");

		registerDomTransitionEndedEvent(mActiveContainerPanel.getElement());
		registerDomTransitionEndedEvent(mInactiveContainerPanel.getElement());

		mIsIphone = calculateIsIphone();

		initWidget(mBasePanel);

	}

	private native static boolean calculateIsIphone() /*-{
		var ua = navigator.userAgent.toLowerCase();

		if (ua.indexOf("safari") != -1 && 
			ua.indexOf("applewebkit") != -1 && 
			ua.indexOf("mobile") != -1) 
		{    	
			return true;
		}
		else 
		{
			return false;
		}
	}-*/;

	private native void registerDomTransitionEndedEvent(Element element) /*-{
		try
		{
			var instance = this;

			var callBack = function(e){
				instance.@org.petank.gwt.client.view.PagesContainerPanel::onDomTransitionEnded()();
			};

			element.addEventListener('webkitTransitionEnd', callBack, false);	
		}
		catch (err)
		{
		}
	}-*/;

	public void onDomTransitionEnded() {
		mInactiveContainerPanel.clear();
		mActiveContainerPanel.removeStyleName("animate");
		mInactiveContainerPanel.removeStyleName("animate");
		ViewSettings.AnimationRunning = false;
	}

	public void bind(ObservableStack<IPage> pageModel) {
		mPageModel = pageModel;

		mPageModel
				.addHandler(new ObservableStackPopEvent.ObservableStackPopHandler<IPage>() {
					@Override
					public void itemPoped(ObservableStackPopEvent<IPage> e) {
						removePage(e.getItemPoped());
					}
				});

		mPageModel
				.addHandler(new ObservableStackPushEvent.ObservableStackPushHandler<IPage>() {
					@Override
					public void itemPushed(ObservableStackPushEvent<IPage> e) {
						addPage(e.getItemPushed());
					}
				});

		for (IPage p : pageModel) {
			addPage(p);
		}
	}

	private void addPage(IPage p) {

		final GenericContainerTag oldContainer = mActiveContainerPanel;
		final GenericContainerTag newContainer = mInactiveContainerPanel;

		AbstractPagePanel pagePanel = PagePanelFactory.createPagePanel(p);

		newContainer.clear();

		boolean startedAnimation = false;

		if (oldContainer.getWidgetCount() > 0) {

			newContainer.removeStyleName("animate");
			newContainer.removeStyleName("left");
			newContainer.addStyleName("right");

			DeferredCommand.addCommand(new Command() {
				@Override
				public void execute() {
					oldContainer.addStyleName("animate");
					oldContainer.addStyleName("left");
					oldContainer.removeStyleName("right");

					newContainer.addStyleName("animate");
					newContainer.removeStyleName("left");
					newContainer.removeStyleName("right");

				}
			});

			startedAnimation = true;

		} else {
			oldContainer.removeStyleName("animate");
			oldContainer.addStyleName("left");
			oldContainer.removeStyleName("right");

			newContainer.removeStyleName("animate");
			newContainer.removeStyleName("left");
			newContainer.removeStyleName("right");

		}

		newContainer.add(pagePanel);

		mActiveContainerPanel = newContainer;
		mInactiveContainerPanel = oldContainer;

		if (mIsIphone && startedAnimation) {
			ViewSettings.AnimationRunning = true;
		}
	}

	private void removePage(IPage removedPage) {

		final GenericContainerTag oldContainer = mActiveContainerPanel;
		final GenericContainerTag newContainer = mInactiveContainerPanel;

		AbstractPagePanel pagePanel = PagePanelFactory
				.createPagePanel(mPageModel.peek());

		newContainer.clear();

		newContainer.removeStyleName("animate");
		newContainer.addStyleName("left");
		newContainer.removeStyleName("right");

		DeferredCommand.addCommand(new Command() {
			@Override
			public void execute() {
				oldContainer.addStyleName("animate");
				oldContainer.removeStyleName("left");
				oldContainer.addStyleName("right");

				newContainer.addStyleName("animate");
				newContainer.removeStyleName("left");
				newContainer.removeStyleName("right");

			}
		});

		newContainer.add(pagePanel);

		mActiveContainerPanel = newContainer;
		mInactiveContainerPanel = oldContainer;

		if (mIsIphone) {
			ViewSettings.AnimationRunning = true;
		}

	}
}
