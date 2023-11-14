package org.testmonkeys.jentitytest;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@SuppressWarnings({"ClassWithTooManyDependents", "NonFinalUtilityClass"})
@Slf4j
public class Resources {

    public static final String ERR_NO_COMPARATOR_DEFINED_FOR_ANNOTATION = "err_no_comparator_defined_for_annotation";
    public static final String err_creating_strategy_for_annotation = "err_creating_strategy_for_annotation";
    public static final String err_creating_strategy = "err_creating_strategy";
    public static final String err_actual_and_expected_must_be_generic_Collections =
            "err_actual_and_expected_must_be_generic_Collections";
    public static final String err_actual_and_expected_must_be_of_type = "err_actual_and_expected_must_be_of_type";
    public static final String err_getting_beaninfo_from_class = "err_getting_beaninfo_from_class";
    public static final String err_annotation_null = "err_annotation_null";
    public static final String err_comparator_null = "err_comparator_null";
    public static final String err_result_processor_null = "err_result_processor_null";
    public static final String err_could_not_read_property = "err_could_not_read_property";
    public static final String size = "size";
    public static final String entity = "entity";
    public static final String NULL = "null";
    public static final String NOT_NULL = "not_null";
    public static final String NOT_NULL_with_object = "not_null_with_object";
    public static final String desc_datetime_precision = "desc_datetime_precision";
    public static final String desc_datetime_delta = "desc_datetime_delta";
    public static final String desc_following_props_did_not_match = "desc_following_props_did_not_match";
    public static final String desc_entities_same = "desc_entities_same";
    public static final String desc_entities_same_standalone ="desc_entities_same_standalone";
    public static final String desc_named_entities_same_standalone = "desc_named_entities_same_standalone";
    public static final String desc_item_not_in_list = "desc_item_not_in_list";
    public static final String standalone_desc_named_item_not_in_list = "standalone_desc_named_item_not_in_list";
    public static final String standalone_desc_item_not_in_list="standalone_desc_item_not_in_list";
    public static final String standalone_desc_lists_not_same = "standalone_desc_lists_not_same";
    public static final String standalone_desc_named_lists_not_same="standalone_desc_named_lists_not_same";
    public static final String desc_string_ignoring_chars = "desc_string_ignoring_chars";
    public static final String desc_string = "desc_string";
    public static final String comp_result = "comp_result";
    public static final String comp_result_light = "comp_result_light";
    public static final String err_actual_not_iterable = "err_actual_not_iterable";
    public static final String err_actual_list_empty = "err_actual_list_empty";
    public static final String err_actual_list_null = "err_actual_list_null";
    public static final String desc_item_in_list = "desc_item_in_list";
    //YAML parsing
    public static final String err_yaml_no_class_for_entity = "err_yaml_no_class_for_entity";
    public static final String err_yaml_no_class_for_strategy = "err_yaml_no_class_for_strategy";
    public static final String err_yaml_no_param_for_strategy = "err_yaml_no_param_for_strategy";
    public static final String err_yaml_invalid_param_value_for_strategy = "err_yaml_invalid_param_value_for_strategy";
    public static final String err_yaml_no_property_in_entity = "err_yaml_no_property_in_entity";
    @SuppressWarnings("HardCodedStringLiteral")
    private static final ResourceBundle bundle = ResourceBundle.getBundle("jentityStrings");


    public static synchronized String getString(String key) {
        log.trace("fetching localized string '{}'", key); //log
        return bundle.getString(key);
    }

    public static synchronized String getStandaloneEntitiesNotEqualAssertionMessage(String entityName, String expectedDescription){
        if (entityName!=null)
            return MessageFormat.format(Resources.getString(desc_named_entities_same_standalone),entityName, expectedDescription);
        return MessageFormat.format(Resources.getString(desc_entities_same_standalone), expectedDescription);
    }

    public static synchronized String getStandaloneListsNotEqualAssertionMessage(String entityName, String expectedDescription ){
        if (entityName!=null)
            return MessageFormat.format(Resources.getString(standalone_desc_named_lists_not_same),entityName,expectedDescription);
        return MessageFormat.format(Resources.getString(standalone_desc_lists_not_same),expectedDescription);
    }

    public static synchronized String getStandaloneEntityNotInListAssertionMessage(String entityName, String expectedDescription){
        if (entityName!=null)
            return MessageFormat.format(Resources.getString(standalone_desc_named_item_not_in_list), entityName,expectedDescription);
            return MessageFormat.format(Resources.getString(standalone_desc_item_not_in_list),expectedDescription);

    }
}
