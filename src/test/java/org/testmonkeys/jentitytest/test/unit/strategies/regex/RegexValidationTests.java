package org.testmonkeys.jentitytest.test.unit.strategies.regex;

import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.comparison.validations.RegexValidation;

import static org.junit.Assert.*;

public class RegexValidationTests {

    @Test
    public void regexValidation_defaultParameters() {
        RegexValidation comparator = new RegexValidation();
        assertEquals("regex", null, comparator.getRegExp());
        assertTrue("failOnNull", comparator.isFailOnNull());
    }

    @Test
    public void regexValidation_parameters() {
        RegexValidation comparator = new RegexValidation();
        comparator.setFailOnNull(false);
        comparator.setRegExp(".*");
        assertEquals("regex", ".*", comparator.getRegExp());
        assertFalse("failOnNull", comparator.isFailOnNull());
    }

    @Test
    public void regexValidation_acceptNulls_nullActual() {
        RegexValidation comparator = new RegexValidation();
        comparator.setFailOnNull(false);
        comparator.setRegExp(".*");
        ConditionalCheckResult result = comparator.runCheck(null, null, new ComparisonContext());
        assertEquals("status", Status.Passed, result.getStatus());

    }

    @Test
    public void regexValidation_rejectNulls_nullActual() {
        RegexValidation comparator = new RegexValidation();
        comparator.setFailOnNull(true);
        comparator.setRegExp(".*");
        ConditionalCheckResult result = comparator.runCheck(null, null, new ComparisonContext());
        assertEquals("status", Status.Failed, result.getStatus());

    }

    @Test
    public void regexValidation_acceptNulls_valueInActual() {
        RegexValidation comparator = new RegexValidation();
        comparator.setFailOnNull(false);
        comparator.setRegExp(".*");
        ConditionalCheckResult result = comparator.runCheck("string", null, new ComparisonContext());
        assertEquals("status", Status.Passed, result.getStatus());

    }

    @Test
    public void regexValidation_mismatch_regex() {
        RegexValidation comparator = new RegexValidation();
        comparator.setFailOnNull(false);
        comparator.setRegExp("a.v");
        ConditionalCheckResult result = comparator.runCheck("aaa", null, new ComparisonContext());
        assertEquals("status", Status.Failed, result.getStatus());

    }
}
