package org.petank.gwt.client.util;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class CollectionItemRemovedEvent<E> extends
		GwtEvent<CollectionItemRemovedEvent.CollectionItemRemovedHandler<E>> {

	Iterable<E> mItemsAdded;

	public CollectionItemRemovedEvent(Iterable<E> itemsAdded) {
		mItemsAdded = itemsAdded;
	}

	public Iterable<E> getItemsRemoved() {
		return mItemsAdded;
	}

	// marker for handler logic in registration method
	public interface CollectionItemRemovedHandler<E> extends EventHandler {
		void itemsRemoved(CollectionItemRemovedEvent<E> event);
	}

	private static final GwtEvent.Type<?> TYPE = new GwtEvent.Type<CollectionItemRemovedEvent<?>>();

	@Override
	protected void dispatch(CollectionItemRemovedHandler<E> handler) {
		handler.itemsRemoved(this);
	}

	@Override
	public GwtEvent.Type<CollectionItemRemovedHandler<E>> getAssociatedType() {
		return (GwtEvent.Type<CollectionItemRemovedHandler<E>>) TYPE;
	}

	public static GwtEvent.Type<?> getType() {
		return TYPE;
	}

}
