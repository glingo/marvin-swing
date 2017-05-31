package com.marvin.bundle.swing.view.xml;

import com.marvin.component.io.IResource;
import com.marvin.component.io.xml.XMLReader;
import com.marvin.component.mvc.view.ViewInterface;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlViewReader extends XMLReader {
    
    protected List<ViewInterface> views;
    protected XmlViewDocumentReader documentReader;
    
    public XmlViewReader() {
        super();
        this.documentReader = new XmlViewDocumentReader(this.context);
        this.views = new ArrayList<>();
    }

    @Override
    protected void doRead(InputSource inputSource, IResource resource) throws Exception {
        Document doc = doLoadDocument(inputSource, resource);
        readView(doc, resource);
    }
    
    public void readView(Document doc, IResource resource) {
        this.documentReader.registerViews(doc, views);
    }
    
}
