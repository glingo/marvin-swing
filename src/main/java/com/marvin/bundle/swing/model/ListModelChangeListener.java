package com.marvin.bundle.swing.model;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

public class ListModelChangeListener implements ListDataListener {

    private final AbstractTableModel model;

    public ListModelChangeListener(AbstractTableModel model) {
        this.model = model;
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        model.fireTableDataChanged();
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        model.fireTableDataChanged();
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        model.fireTableDataChanged();
    }

}
