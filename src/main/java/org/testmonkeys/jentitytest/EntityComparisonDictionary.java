package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.comparison.ComparisonModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpascal on 6/7/2016.
 */
public class EntityComparisonDictionary {
    private static EntityComparisonDictionary instance;
    public static synchronized EntityComparisonDictionary getInstance(){
        if(instance==null)
            instance=new EntityComparisonDictionary();
        return instance;
    }

    private Map<Class,ComparisonModel> dictionary;



    private EntityComparisonDictionary(){
        dictionary=new HashMap<>();
    }

    public void addComparisonModel(Class clazz, ComparisonModel model){
        dictionary.put(clazz,model);
    }

    public ComparisonModel getComparisonModel(Class clazz){
        return dictionary.get(clazz);
    }
}
