package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.bundle.swing.action.ApplicationAction;
import com.marvin.component.form.FormTypeInterface;
import com.marvin.component.form.support.ButtonType;
import com.marvin.component.form.support.CheckboxType;
import com.marvin.component.form.support.PasswordType;
import java.awt.TextField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;

public class FormView extends SwingView {

//    protected Map<String, JComponent> components = new HashMap<>();
    protected Object data;
//    private JButton submit;
//    private JButton cancel;

    public FormView(Handler handler) {
        super(handler);
    }

    @Override
    protected void prepare(HashMap<String, ?> model, JFrame frame) throws Exception {
        FormTypeInterface form = (FormTypeInterface) model.get("form");

        if (form == null) {
            throw new Exception("null form");
        }
        
        this.data = form.getData();

//        this.data = new LoginForm("Login", "Password");
//        LoginForm data = form.getData();

        JPanel panel = getPanel();

        BindingGroup bindingGroup = new BindingGroup();

        if (form.getLabel() != null) {
            frame.setTitle(form.getLabel());
        }

        List<FormTypeInterface> children = form.getChildren();
        children.forEach((FormTypeInterface child) -> {
            if (child instanceof PasswordType) {
//               this.components.put(child.getName() + "_label", new JLabel(child.getLabel()));
                JPasswordField field = new JPasswordField(20);
                panel.add(field);
                bindingGroup.addBinding(Bindings.createAutoBinding(
                        AutoBinding.UpdateStrategy.READ_WRITE, 
                        data, 
                        ELProperty.create("${" + child.getName() + "}"), 
                        field, 
                        BeanProperty.create("text"), 
                        child.getName()));
            } else if (child instanceof TextField) {
                JTextField field = new JTextField((String) child.getData(), 20);
                panel.add(field);
                bindingGroup.addBinding(Bindings.createAutoBinding(
                        AutoBinding.UpdateStrategy.READ_WRITE, data, 
                        ELProperty.create("${" + child.getName() + "}"), 
                        field, 
                        BeanProperty.create("text"), 
                        child.getName()));
            } else if (child instanceof CheckboxType) {
                JCheckBox cb = new JCheckBox(child.getLabel(), (boolean) child.getData());
                panel.add(cb);
                bindingGroup.addBinding(Bindings.createAutoBinding(
                        AutoBinding.UpdateStrategy.READ_WRITE, data, 
                        ELProperty.create("${" + child.getName() + "}"), 
                        cb, 
                        BeanProperty.create("selected"), 
                        child.getName()));
            } else if (child instanceof ButtonType) {
                Action action = new ApplicationAction(child.getLabel(), ((ButtonType) child).getAction(), getHandler(), frame);
                panel.add(new JButton(action));
            } else {
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

        JButton look = new JButton("Take a look");
        look.addActionListener((java.awt.event.ActionEvent evt) -> {
            System.out.println(data);
        });
        panel.add(look);

        bindingGroup.bind();

//        String label = "Password :";
//        
//        this.passwordLabel = new JLabel("Password :");
//        this.passwordField = new JPasswordField();
//        
//        this.getPanel().add(this.passwordLabel);
//        this.getPanel().add(this.passwordField);
//        
//        this.usernameLabel = new JLabel("Username :");
//        this.usernameField = new JTextField();
//        
//        this.getPanel().add(this.usernameLabel);
//        this.getPanel().add(this.usernameField);
//        Action connectAction = new ApplicationAction("Se connecter", "/login", getHandler(), frame);
//        Action cancelAction = new ApplicationAction("Anuler", "/", getHandler(), frame);
//        this.submit = new JButton(connectAction);
//        this.cancel = new JButton(cancelAction);
//        
//        this.getPanel().add(this.submit);
//        this.getPanel().add(this.cancel);
    }

    @Override
    protected void display(HashMap<String, ?> model, JFrame frame) {
        frame.getContentPane().removeAll();
        super.display(model, frame);
    }
}
