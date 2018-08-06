package org.testmonkeys.jentitytest;

import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"ClassWithTooManyDependents", "NonFinalUtilityClass"})
public class Resources {

    private static final Logger LOG = LoggerFactory.getLogger(Resources.class);

    @SuppressWarnings("HardCodedStringLiteral")

    private static final ResourceBundle bundle=ResourceBundle.getBundle("jentityStrings");


    public static final String ERR_NO_COMPARATOR_DEFINED_FOR_ANNOTATION="err_no_comparator_defined_for_annotation";
    public static final String err_creating_comparator_for_annotation="err_creating_comparator_for_annotation";
    public static final String err_actual_and_expected_must_be_generic_Collections=
            "err_actual_and_expected_must_be_generic_Collections";
    public static final String err_actual_and_expected_must_be_of_type="err_actual_and_expected_must_be_of_type";
    public static final String err_getting_beaninfo_from_class="err_getting_beaninfo_from_class";
    public static final String err_annotation_null="err_annotation_null";
    public static final String err_comparator_null="err_comparator_null";
    public static final String err_result_processor_null="err_result_processor_null";
    public static final String err_could_not_read_property="err_could_not_read_property";

    public static final String size="size";
    public static final String entity="entity";
    public static final String NULL="null";
    public static final String NOT_NULL="not_null";

    public static final String desc_datetime_precision = "desc_datetime_precision";
    public static final String desc_datetime_delta = "desc_datetime_delta";
    public static final String desc_following_props_did_not_match="desc_following_props_did_not_match";
    public static final String desc_entities_same="desc_entities_same";
    public static final String desc_string_ignoring_chars="desc_string_ignoring_chars";
    public static final String desc_string="desc_string";
    public static final String comp_result="comp_result";


    public static synchronized String getString(String key){
        LOG.trace("fetching localized string '{}'",key); //LOG
        return bundle.getString(key);
    }
}
