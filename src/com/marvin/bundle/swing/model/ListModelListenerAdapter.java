package com.marvin.bundle.swing.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class ListModelListenerAdapter implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ListModelHolder<?> listModelHolder = (ListModelHolder<?>) evt.getSource();
        ListModel<?> listModel = listModelHolder.getModel();
        int size = listModel.getSize();

        ListDataEvent contentsChanged = new ListDataEvent(listModel, ListDataEvent.CONTENTS_CHANGED, 0, size);

        List<ListDataListener> listDataListeners = listModelHolder.getListDataListeners();
        listDataListeners.stream().forEach((listDataListener) -> {
            listDataListener.contentsChanged(contentsChanged);
        });

    }
}
