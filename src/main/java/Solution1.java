import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.SearchAttribute;
import net.sf.ehcache.config.Searchable;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1 {

    public static void main(String args[]) {
        Configuration cacheManagerConfig = new Configuration();
        CacheConfiguration cacheConfig = new CacheConfiguration("myCache", 0).eternal(true);
        Searchable searchable = new Searchable();
        cacheConfig.addSearchable(searchable);
// Create attributes to use in queries.
        searchable.addSearchAttribute(new SearchAttribute().name("firstname"));
        searchable.addSearchAttribute(new SearchAttribute().name("lastname"));
        searchable.addSearchAttribute(new SearchAttribute().name("friends"));
// Use an expression for accessing values.
//        searchable.addSearchAttribute(new SearchAttribute()
//                .name("first_name")
//                .expression("value.getFirstName()"));
//        searchable.addSearchAttribute(new SearchAttribute().name("last_name").expression("value.getLastName()"));
//        searchable.addSearchAttribute(new SearchAttribute().name("zip_code").expression("value.getZipCode()"));
        CacheManager cacheManager = new CacheManager(cacheManagerConfig);
        cacheManager.addCache(new Cache(cacheConfig));
        Ehcache myCache = cacheManager.getEhcache("myCache");

        List<String> friends = new ArrayList<>();
        friends.add("me");
        friends.add("myself");

        List<String> friends2 = new ArrayList<>();
        friends2.add("me1");
        friends2.add("myself2");

        myCache.put(new Element(1, new Boy("aditya", "nehra", friends)));
        myCache.put(new Element(2, new Boy("aditya1", "nehra1", friends2)));
        myCache.put(new Element(3, new Boy("lucky","nehr",friends2)));

        Attribute<String> firstname = myCache.getSearchAttribute("firstname");
        Attribute<String> lastname = myCache.getSearchAttribute("lastname");
        //Attribute<List<String>> friend = myCache.getSearchAttribute("friends");
        MyAttribute<List<String>> friend = new MyAttribute<>(myCache.getSearchAttribute("friends"));

        Query query = myCache.createQuery();
        query.includeKeys();
        query.includeValues();
        query.addCriteria(firstname.ilike("adi*"));
        query.addCriteria(lastname.ilike("neh*"));
        query.addCriteria(friend.Contains("me1"));

        Results results = query.execute();
        System.out.println("Results ");

        for (Result r : results.all()) {
            System.out.println("Got: key[" + r.getKey() + "] value class [" + r.getValue().getClass() + "] value ]" + r.getValue() + "] with firstname " + ((Boy)r.getValue()).getFirstname());
        }


    }
}
