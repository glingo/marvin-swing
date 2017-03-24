package com.marvin.bundle.swing.model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ListModelHolder<E> {

    private static final String PROPERTY_LIST_MODEL = "listModel";

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private final ListModelListenerAdapter listModelListenerAdapter = new ListModelListenerAdapter();

    private ListModel<E> listModel = new DefaultListModel<>();
    private final List<ListDataListener> listDataListeners = new ArrayList<>();

    public ListModelHolder() {
        propertyChangeSupport.addPropertyChangeListener(listModelListenerAdapter);
    }

    public List<ListDataListener> getListDataListeners() {
        return Collections.unmodifiableList(listDataListeners);
    }

    public ListModel<E> getModel() {
        return listModel;
    }

    public final void setModel(ListModel<E> listModel) {
        if (this.listModel != null) {
            removeListDataListeners(listModel, listDataListeners);
        }

        ListModel<E> oldModel = this.listModel;
        this.listModel = listModel;

        if (listModel != null) {
            addListDataListeners(listModel, listDataListeners);
        }

        propertyChangeSupport.firePropertyChange(PROPERTY_LIST_MODEL, oldModel, this.listModel);
    }

    public void addListDataListeners(ListDataListener listDataListener) {
        if (listDataListener != null) {
            this.listDataListeners.add(listDataListener);
            this.listModel.addListDataListener(listDataListener);
        }
    }

    public void removeListDataListener(ListDataListener listDataListener) {
        this.listDataListeners.remove(listDataListener);
        this.listModel.removeListDataListener(listDataListener);
    }

    private void removeListDataListeners(ListModel<E> listModel, List<ListDataListener> listDataListeners) {
        listDataListeners.stream().forEach((listDataListener) -> {
            listModel.removeListDataListener(listDataListener);
        });
    }

    private void addListDataListeners(ListModel<E> listModel, List<ListDataListener> listDataListeners) {
        listDataListeners.stream().forEach((listDataListener) -> {
            listModel.addListDataListener(listDataListener);
        });
    }

}
