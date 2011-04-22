package org.petank.gwt.client.util;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class CollectionItemAddedEvent<E> extends
		GwtEvent<CollectionItemAddedEvent.CollectionItemAddedHandler<E>> {

	Iterable<E> mItemsAdded;

	public CollectionItemAddedEvent(Iterable<E> itemsAdded) {
		mItemsAdded = itemsAdded;
	}

	public Iterable<E> getItemsAdded() {
		return mItemsAdded;
	}

	// marker for handler logic in registration method
	public interface CollectionItemAddedHandler<E> extends EventHandler {
		void itemsAdded(CollectionItemAddedEvent<E> event);
	}

	private static final GwtEvent.Type<?> TYPE = new GwtEvent.Type<CollectionItemAddedHandler<?>>();

	@Override
	protected void dispatch(CollectionItemAddedHandler<E> handler) {
		handler.itemsAdded(this);
	}

	@Override
	public GwtEvent.Type<CollectionItemAddedHandler<E>> getAssociatedType() {
		return (GwtEvent.Type<CollectionItemAddedHandler<E>>) TYPE;
	}

	public static GwtEvent.Type<?> getType() {
		return TYPE;
	}

}
