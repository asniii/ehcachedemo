import net.sf.ehcache.Cache;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.expression.Criteria;

public class MyAttribute<T>  {
    /**
     * Construct a new attribute instance. Instances are normally obtained from a specific {@link Cache} however
     *
     * @param attributeName the name of search attribute
     */

    Attribute<T> attribute;

    public MyAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    /**
     * Create a criteria where this attribute's toString() matches the given expression
     * See {@link net.sf.ehcache.search.expression.ILike} for the expression syntax
     *
     * @param regex
     * @return criteria instance
     */
    public Criteria Contains(String regex) {
        return new Contains(attribute.getAttributeName(), regex);
    }
}
