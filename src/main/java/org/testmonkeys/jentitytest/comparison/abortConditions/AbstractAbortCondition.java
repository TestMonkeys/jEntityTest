package org.testmonkeys.jentitytest.comparison.abortConditions;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractCheck;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Base class for Abort Condition Checks, acts as a wrapper and makes all abort conditions implement the strategy
 * pattern
 */
public abstract class AbstractAbortCondition extends AbstractCheck {



}
