package com.marvin.bundle.swing.container;

import com.marvin.bundle.swing.SwingBundle;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.extension.Extension;
import com.marvin.component.container.xml.XMLDefinitionReader;
import java.util.logging.Logger;
import com.marvin.component.resource.ResourceService;
import com.marvin.component.resource.loader.ClasspathResourceLoader;
import com.marvin.component.resource.reference.ResourceReference;
import com.marvin.component.xml.XMLReader;
import java.util.Map;

public class SwingExtension extends Extension {
    
    private static final Logger LOG = Logger.getLogger(SwingExtension.class.getName());
    
    @Override
    public void load(Map<String, Object> configs, ContainerBuilder builder) {
        ResourceService service = ResourceService.builder()
                .with(ResourceReference.CLASSPATH, new ClasspathResourceLoader(SwingBundle.class))
                .build();
        XMLReader reader = new XMLDefinitionReader(service, builder);

        reader.read("resources/config/services.xml");
    }
    
}
