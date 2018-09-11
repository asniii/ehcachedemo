import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.SearchException;
import net.sf.ehcache.search.attribute.AttributeExtractor;
import net.sf.ehcache.search.expression.BaseCriteria;

import java.util.*;

import static javafx.scene.input.KeyCode.T;

public class Contains extends BaseCriteria {

    private final String attributeName;
    private final String regex;

    public Contains(String attributeName, String regex) {
        if ((attributeName == null) || (regex == null)) {
            throw new SearchException("Both the attribute name and regex must be non null.");
        }
        this.attributeName = attributeName;
        this.regex = regex;
    }

    public String getAttributeName() {
        return attributeName;
    }


    @Override
    public Set<Attribute<?>> getAttributes() {
        return Collections.<Attribute<?>>singleton(new Attribute(attributeName));
    }

    @Override
    public boolean execute(Element element, Map<String, AttributeExtractor> attributeExtractors) {
        List<String> value = (List<String>) getExtractor(attributeName, attributeExtractors).attributeFor(element, attributeName);
        if (value == null) {
            return false;
        }
        for(String s: value){
            if(s.contains(regex)){
                return true;
            }
        }
        return false;
    }
}
