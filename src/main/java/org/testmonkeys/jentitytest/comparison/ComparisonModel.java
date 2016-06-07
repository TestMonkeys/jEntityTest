package org.testmonkeys.jentitytest.comparison;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by cpascal on 6/7/2016.
 */
public class ComparisonModel {
    private Map<PropertyDescriptor,Comparator> comparisonMap;

    public ComparisonModel(){
        comparisonMap=new HashMap<>();
    }

    public void setComparisonPoint(PropertyDescriptor propertyDescriptor, Comparator comparator){
        comparisonMap.put(propertyDescriptor,comparator);
    }

    public Set<PropertyDescriptor> getComparableProperties(){
        return  comparisonMap.keySet();
    }

    public Comparator getComparator(PropertyDescriptor propertyDescriptor) {
        return comparisonMap.get(propertyDescriptor);
    }
}
