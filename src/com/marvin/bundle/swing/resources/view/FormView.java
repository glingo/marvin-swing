package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.bundle.swing.action.ApplicationAction;
import com.marvin.component.form.FormTypeInterface;
import com.marvin.component.form.support.ButtonType;
import com.marvin.component.form.support.CheckboxType;
import com.marvin.component.form.support.PasswordType;
import com.marvin.component.form.support.TextType;
import java.awt.TextField;
import java.util.HashMap;
import java.util.List;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;

public class FormView extends SwingView {

    protected Object data;
    protected String formName;
    
    public FormView(Handler handler, String formName) {
        super(handler);
        this.formName = formName;
    }

    @Override
    protected void prepare(HashMap<String, Object> model, JFrame frame) throws Exception {
        FormTypeInterface form = (FormTypeInterface) model.get(this.formName);
        
        if (form == null) {
            throw new Exception("null form");
        }

        this.data = form.getData();
        
        BindingGroup bindingGroup = new BindingGroup();
        JPanel panel = getPanel();

        if (form.getLabel() != null) {
            frame.setTitle(form.getLabel());
        }

        List<FormTypeInterface> children = form.getChildren();
        children.forEach((FormTypeInterface child) -> {
            if (child instanceof PasswordType) {
                 if (null != child.getLabel()) {
                    JLabel label = new JLabel(child.getLabel());
                    panel.add(label);
                }
                JPasswordField field = new JPasswordField(20);
                panel.add(field);
                bindingGroup.addBinding(Bindings.createAutoBinding(
                        AutoBinding.UpdateStrategy.READ_WRITE, 
                        data, 
                        ELProperty.create("${" + child.getName() + "}"), 
                        field, 
                        BeanProperty.create("text"), 
                        child.getName()));
            } else if (child instanceof TextType) {
                if (null != child.getLabel()) {
                    JLabel label = new JLabel(child.getLabel());
                    panel.add(label);
                }
                JTextField field = new JTextField((String) child.getData(), 20);
                add(field);
                bindingGroup.addBinding(Bindings.createAutoBinding(
                        AutoBinding.UpdateStrategy.READ_WRITE, data, 
                        ELProperty.create("${" + child.getName() + "}"), 
                        field, 
                        BeanProperty.create("text"), 
                        child.getName()));
            } else if (child instanceof CheckboxType) {
                 if (null != child.getLabel()) {
                    JLabel label = new JLabel(child.getLabel());
                    panel.add(label);
                }
                JCheckBox cb = new JCheckBox(child.getLabel(), (boolean) child.getData());
                panel.add(cb);
                bindingGroup.addBinding(Bindings.createAutoBinding(
                        AutoBinding.UpdateStrategy.READ_WRITE, data, 
                        ELProperty.create("${" + child.getName() + "}"), 
                        cb, 
                        BeanProperty.create("selected"), 
                        child.getName()));
            } else if (child instanceof ButtonType) {
                Action action = createAction(child.getLabel(), ((ButtonType) child).getAction(), frame);
                JButton button = new JButton(action);
                panel.add(button);
            } else {
                 if (null != child.getLabel()) {
                    JLabel label = new JLabel(child.getLabel());
                    panel.add(label);
                }
                JTextField field = new JTextField((String) child.getData(), 20);
                panel.add(field);
                bindingGroup.addBinding(Bindings.createAutoBinding(
                        AutoBinding.UpdateStrategy.READ_WRITE, data, 
                        ELProperty.create("${" + child.getName() + "}"), 
                        field, 
                        BeanProperty.create("text"), 
                        child.getName()));
            }
        });
        bindingGroup.bind();
    }

    @Override
    protected void display(HashMap<String, Object> model, JFrame frame) {
        frame.getContentPane().removeAll();
        super.display(model, frame);
    }
    
    public FormView bind(JTextField filed) {
        
        
        return this;
    }
}
