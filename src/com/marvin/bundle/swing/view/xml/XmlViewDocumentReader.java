package com.marvin.bundle.swing.view.xml;

import com.marvin.bundle.swing.resources.view.SwingView;
import com.marvin.component.io.xml.XMLDocumentReader;
import com.marvin.component.io.xml.XMLReaderContext;
import com.marvin.component.mvc.view.ViewInterface;
import static com.marvin.component.routing.xml.XmlRouteDocumentReader.NAME_ATTRIBUTE;
import com.marvin.component.util.StringUtils;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlViewDocumentReader extends XMLDocumentReader {
    
    public static final String VIEW_ELEMENT        = "view";
    public static final String VIEWS_ELEMENT       = "views";
    
    public XmlViewDocumentReader(XMLReaderContext context) {
        super(context);
    }
    
    public void registerViews(Document doc, List<ViewInterface> views) {
        Element root = doc.getDocumentElement();
        doRegisterViews(root, views);
    }
    
    
    protected void doRegisterViews(Element root, List<ViewInterface> views) {

//        preProcessXml(root);
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseViewElement(ele, views);
            }
        }
//        postProcessXml(root); 

    }
    
    private void parseViewElement(Element ele, List<ViewInterface> views) {
        parseElement(ele);
        
        if (nodeNameEquals(ele, VIEW_ELEMENT)) {
            processView(ele, views);
        } else if (nodeNameEquals(ele, VIEWS_ELEMENT)) {
            // recurse
            doRegisterViews(ele, views);
        }
    }
    
    protected void processView(Element ele, List<ViewInterface> views) {
        String name = ele.getAttribute(NAME_ATTRIBUTE);
        
        if (StringUtils.hasLength(name)) {
            views.add(new SwingView(name));
        }
    }

}
