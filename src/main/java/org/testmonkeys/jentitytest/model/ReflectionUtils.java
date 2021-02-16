package org.testmonkeys.jentitytest.model;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.testmonkeys.jentitytest.EntityComparatorContext;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractCheck;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.exceptions.*;
import org.testmonkeys.jentitytest.model.yaml.StrategyDefinition;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testmonkeys.jentitytest.Resources.*;

@Slf4j
public class ReflectionUtils {

    public BeanInfo getBeanInfo(Class clazz) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        } catch (IntrospectionException e) {
            throw new JEntityModelException(MessageFormat.format(
                    Resources.getString(err_getting_beaninfo_from_class), clazz), e);
        }
        return beanInfo;
    }

    public <T> T initializeStrategy(StrategyDefinition strategy) {
        log.trace("Starting initialization for yaml strategy {}", strategy.getStrategy()); //LOG

        Class<?> strategyClass = getStrategyTypeForName(strategy.getStrategy());
        T instance = null;
        try {

            log.trace("Initializing strategy {} using default constructor", strategyClass); //LOG
            instance = (T) strategyClass.getConstructor().newInstance();

        } catch (Exception e) {
            throw new StrategyInstantiationException(strategyClass, e);
        }
        fillParameters(strategyClass, instance, strategy.getParameters());
        return instance;
    }

    @SneakyThrows
    private void fillParameters(Class<?> strategyType, Object strategy, Map<String, Object> parameters) {
        if (parameters == null || parameters.isEmpty())
            return;
        BeanInfo beanInfo = getBeanInfo(strategyType);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Set<String> strategyProperties = Arrays.stream(propertyDescriptors).map(x -> x.getName()).collect(Collectors.toSet());

        if (!strategyProperties.containsAll(parameters.keySet())) {
            Set<String> yamlParameters = parameters.keySet();
            yamlParameters.removeAll(strategyProperties);
            log.error("Strategy does not contain properties for parameters: {}", yamlParameters);
            throw new JEntityModelException(MessageFormat.format(Resources.getString(err_yaml_no_param_for_strategy), strategyType.getCanonicalName(), yamlParameters));
        }

        for (PropertyDescriptor property : propertyDescriptors) {
            Object parameterValue = parameters.get(property.getName());
            if (parameterValue != null) {
                log.trace("setting parameter {} for strategy {} to '{}'", property.getName(), strategyType.getCanonicalName(), parameterValue);
                try {
                    property.getWriteMethod().invoke(strategy, parameterValue);
                } catch (IllegalArgumentException e) {
                    throw new JEntityModelException(MessageFormat.format(Resources.getString(err_yaml_invalid_param_value_for_strategy), strategyType.getCanonicalName(), property.getName(), parameterValue));
                }
            }
        }
    }

    private Class<?> getStrategyTypeForName(String strategyName) {
        Map<String, Class<?>> strategyShortNames = EntityComparatorContext.getInstance().getStrategyShortNames();
        try {
            return strategyShortNames.containsKey(strategyName) ? strategyShortNames.get(strategyName) : Class.forName(strategyName);
        } catch (ClassNotFoundException e) {
            throw new JEntityModelException(MessageFormat.format(Resources.getString(err_yaml_no_class_for_strategy),
                    strategyName));
        }

    }

    public Class<?> getEntityTypeForName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new JEntityModelException(MessageFormat.format(Resources.getString(err_yaml_no_class_for_entity),
                    name));
        }
    }

    /**
     * @param annotation
     * @param type
     * @return
     * @throws JEntityTestException
     */
    public Comparator initializeComparator(Annotation annotation, Class<? extends Comparator> type) {
        log.trace("Starting initialization for comparator {}", type); //LOG
        Constructor<?>[] constructors = type.getDeclaredConstructors();
        Constructor<?> annotationConstructor = null;
        for (Constructor<?> candidate : constructors) {
            if ((candidate.getParameterCount() == 1)
                    && (annotation.annotationType().isAssignableFrom(candidate.getParameterTypes()[0]))) {
                annotationConstructor = candidate;
                break;
            }
        }
        //noinspection OverlyBroadCatchBlock
        try {
            if (annotationConstructor != null) {
                log.trace("Initializing comparator {} using constructor with annotation parameter", type); //LOG
                return (Comparator) annotationConstructor.newInstance(annotation);
            } else {
                log.trace("Initializing comparator {} using default constructor", type); //LOG
                return type.getConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new ComparatorInstantiationByAnnotationException(type, annotation, e);
        }
    }

    /**
     * @param annotation
     * @param type
     * @return
     * @throws JEntityTestException
     */
    public <T> T initializeCheck(Annotation annotation, Class<? extends AbstractCheck> type) {
        log.trace("Starting initialization for comparator {}", type); //LOG
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor annotationConstructor = null;
        for (Constructor candidate : constructors) {
            if ((candidate.getParameterCount() == 1)
                    && (annotation.annotationType().isAssignableFrom(candidate.getParameterTypes()[0]))) {
                annotationConstructor = candidate;
                break;
            }
        }
        //noinspection OverlyBroadCatchBlock
        try {
            if (annotationConstructor != null) {
                log.trace("Initializing comparator {} using constructor with annotation parameter", type); //LOG
                return (T) annotationConstructor.newInstance(annotation);
            } else {
                log.trace("Initializing comparator {} using default constructor", type); //LOG
                return (T) type.getConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new CheckInstantiationByAnnotationException(type, annotation, e);
        }
    }
}
