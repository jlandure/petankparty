package org.petank.gwt.client.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

public class ObservableArrayList<E> implements Iterable<E>, Collection<E>,
		List<E> {

	private ArrayList<E> mList = new ArrayList<E>();

	private HandlerManager mHandlerManager;

	public final HandlerRegistration addHandler(
			final CollectionItemAddedEvent.CollectionItemAddedHandler<E> handler) {
		return ensureHandlers()
				.addHandler(
						(GwtEvent.Type<CollectionItemAddedEvent.CollectionItemAddedHandler<E>>) CollectionItemAddedEvent
								.getType(), handler);
	}

	public final HandlerRegistration addHandler(
			final CollectionItemRemovedEvent.CollectionItemRemovedHandler<E> handler) {
		return ensureHandlers()
				.addHandler(
						(GwtEvent.Type<CollectionItemRemovedEvent.CollectionItemRemovedHandler<E>>) CollectionItemRemovedEvent
								.getType(), handler);
	}

	private HandlerManager ensureHandlers() {
		return mHandlerManager == null ? mHandlerManager = new HandlerManager(
				this) : mHandlerManager;
	}

	void fireAddedEvent(
			GwtEvent<CollectionItemAddedEvent.CollectionItemAddedHandler<E>> event) {
		ensureHandlers().fireEvent(event);
	}

	void fireRemovedEvent(
			GwtEvent<CollectionItemRemovedEvent.CollectionItemRemovedHandler<E>> event) {
		ensureHandlers().fireEvent(event);
	}

	@Override
	public Iterator<E> iterator() {
		return mList.iterator();
	}

	@Override
	public boolean add(E arg0) {
		boolean val = mList.add(arg0);
		ArrayList<E> addedItems = new ArrayList<E>();
		addedItems.add(arg0);
		fireAddedEvent(new CollectionItemAddedEvent<E>(addedItems));
		return val;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		boolean val = mList.addAll(arg0);

		ArrayList<E> addedItems = new ArrayList<E>();
		addedItems.addAll(arg0);
		fireAddedEvent(new CollectionItemAddedEvent<E>(addedItems));
		return val;
	}

	@Override
	public void clear() {

		ArrayList<E> removedItems = new ArrayList<E>();
		removedItems.addAll(mList);

		mList.clear();

		fireRemovedEvent(new CollectionItemRemovedEvent<E>(removedItems));
	}

	@Override
	public boolean contains(Object arg0) {
		return mList.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return mList.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return mList.isEmpty();
	}

	@Override
	public boolean remove(Object arg0) {

		E item = (E) arg0;

		ArrayList<E> removedItems = new ArrayList<E>();
		removedItems.add(item);

		boolean val = mList.remove(item);

		fireRemovedEvent(new CollectionItemRemovedEvent<E>(removedItems));

		return val;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {

		ArrayList<E> removedItems = new ArrayList<E>();
		removedItems.addAll((Collection<? extends E>) arg0);

		boolean val = mList.removeAll(arg0);

		fireRemovedEvent(new CollectionItemRemovedEvent<E>(removedItems));

		return val;

	}

	@Override
	public boolean retainAll(Collection<?> arg0) {

		ArrayList<E> removedItems = new ArrayList<E>();

		for (E item : mList) {
			if (!arg0.contains(item)) {
				removedItems.add(item);
			}
		}

		return this.removeAll(removedItems);
	}

	@Override
	public int size() {
		return mList.size();
	}

	@Override
	public Object[] toArray() {
		return mList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return mList.toArray(arg0);
	}

	@Override
	public void add(int arg0, E arg1) {

		mList.add(arg0, arg1);

		ArrayList<E> addedItems = new ArrayList<E>();
		addedItems.add(arg1);
		fireAddedEvent(new CollectionItemAddedEvent<E>(addedItems));

	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		boolean val = mList.addAll(arg0, arg1);

		ArrayList<E> addedItems = new ArrayList<E>();
		addedItems.addAll(arg1);
		fireAddedEvent(new CollectionItemAddedEvent<E>(addedItems));
		return val;
	}

	@Override
	public E get(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public int indexOf(Object arg0) {
		return mList.indexOf(arg0);
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return mList.indexOf(arg0);
	}

	@Override
	public ListIterator<E> listIterator() {
		return mList.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		return mList.listIterator(arg0);
	}

	@Override
	public E remove(int arg0) {
		E item = mList.remove(arg0);

		if (item != null) {

			ArrayList<E> removedItems = new ArrayList<E>();
			removedItems.add(item);
			fireRemovedEvent(new CollectionItemRemovedEvent<E>(removedItems));

		}

		return item;
	}

	@Override
	public E set(int arg0, E arg1) {
		E itemRemoved = mList.set(arg0, arg1);

		ArrayList<E> removedItems = new ArrayList<E>();
		removedItems.add(itemRemoved);
		fireRemovedEvent(new CollectionItemRemovedEvent<E>(removedItems));

		return itemRemoved;
	}

	public List<E> subList(int arg0, int arg1) {
		ArrayList<E> result = new ArrayList<E>();
		for (int i = arg0; i <= arg1; i++) {
			result.add(mList.get(i));
		}
		return result;
	}

}
