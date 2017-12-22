package com.marvin.bundle.swing.model;

import java.util.Observable;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListModelSelection<E> extends Observable {

    public ListAdapterListModel<E> listAdapterListModel;

    private ListSelectionModel listSelectionModel;

    private final ListSelectionAdapter listSelectionAdapter;

    public ListModelSelection() {
        listAdapterListModel = new ListAdapterListModel<>();
        listSelectionModel = new DefaultListSelectionModel();
        listSelectionAdapter = new ListSelectionAdapter();
        listSelectionModel.addListSelectionListener(listSelectionAdapter);
    }

    public void setListModels(ListAdapterListModel<E> listAdapterListModel,
            ListSelectionModel listSelectionModel) {

        this.listAdapterListModel = listAdapterListModel;

        if (this.listSelectionModel != null) {
            this.listSelectionModel
                    .removeListSelectionListener(listSelectionAdapter);
        }

        this.listSelectionModel = listSelectionModel;

        if (listSelectionModel != null) {
            listSelectionModel.addListSelectionListener(listSelectionAdapter);
        }
    }

    public E getSelection() {
        int minSelectionIndex = listSelectionModel.getMinSelectionIndex();
        E elementAt = null;
        if (minSelectionIndex > -1) {
            elementAt = listAdapterListModel.getElementAt(minSelectionIndex);
        }
        return elementAt;
    }

    private class ListSelectionAdapter implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            setChanged();
            notifyObservers();
        }

    }

    public void setSelection(E selection) {
        int indexOf = listAdapterListModel.indexOf(selection);
        listSelectionModel.setSelectionInterval(indexOf, indexOf);
    }
}
