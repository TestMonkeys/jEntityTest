package org.testmonkeys.jentitytest.model;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.testmonkeys.jentitytest.EntityComparatorContext;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.exceptions.JEntityModelException;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.exceptions.StrategyInstantiationByAnnotationException;
import org.testmonkeys.jentitytest.exceptions.StrategyInstantiationException;
import org.testmonkeys.jentitytest.model.yaml.StrategyDefinition;

import java.beans.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testmonkeys.jentitytest.Resources.*;

@Slf4j
public class ReflectionUtils {

    /**
     * Gets the propertyDescriptors for provided class
     *
     * @param clazz entity class type
     * @return propertyDescriptor list
     */
    public PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        } catch (IntrospectionException e) {
            throw new JEntityModelException(MessageFormat.format(
                    Resources.getString(err_getting_beaninfo_from_class), clazz), e);
        }
        return beanInfo.getPropertyDescriptors();
    }

    /**
     * Initializes strategy from StrategyDefinition provided. This is used primarily for instantiating
     * Strategies configured in YAML.
     * Strategy is initialized with default constructor and parameters are assigned based on strategy properties
     *
     * @param strategy strategy to initialize
     * @param <T>      Expected type
     * @return instance of required strategy
     */
    public <T> T initializeStrategy(StrategyDefinition strategy) {
        log.trace("Starting initialization for yaml strategy {}", strategy.getStrategy()); //log

        Class<?> strategyClass = getStrategyTypeForName(strategy.getStrategy());
        T instance;
        try {

            log.trace("Initializing strategy {} using default constructor", strategyClass); //log
            instance = (T) strategyClass.getConstructor().newInstance();

        } catch (Exception e) {
            throw new StrategyInstantiationException(strategyClass, e);
        }
        fillParameters(strategyClass, instance, strategy.getParameters());
        return instance;
    }

    /**
     * Fills parameters to strategy properties.
     * Method will fail on any property name mismatch, or extra parameters than strategy properties available
     *
     * @param strategyType strategy class
     * @param strategy     strategy instance
     * @param parameters   parameters
     */
    private void fillParameters(Class<?> strategyType, Object strategy, Map<String, Object> parameters) {
        if (parameters == null || parameters.isEmpty())
            return;
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(strategyType);
        ensureAllParametersValidForStrategy(strategyType, parameters, propertyDescriptors);

        for (PropertyDescriptor property : propertyDescriptors) {
            setStrategyParameter(strategyType, strategy, property, parameters);
        }
    }

    private void ensureAllParametersValidForStrategy(Class<?> strategyType, Map<String, Object> parameters, PropertyDescriptor[] propertyDescriptors) {
        Set<String> strategyProperties = Arrays.stream(propertyDescriptors).map(FeatureDescriptor::getName).collect(Collectors.toSet());
        if (!strategyProperties.containsAll(parameters.keySet())) {
            Set<String> yamlParameters = parameters.keySet();
            yamlParameters.removeAll(strategyProperties);
            log.error("Strategy does not contain properties for parameters: {}", yamlParameters);
            throw new JEntityModelException(MessageFormat.format(Resources.getString(err_yaml_no_param_for_strategy), strategyType.getCanonicalName(), yamlParameters));
        }
    }

    @SneakyThrows
    private void setStrategyParameter(Class<?> strategyType, Object strategy, PropertyDescriptor property, Map<String, Object> parameters) {
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
     * initializes strategy using property annotations
     *
     * @param annotation property annotation
     * @param type       strategy type
     * @return strategy instance
     * @throws JEntityTestException when strategy initialization is impossible
     */
    public <T> T initializeStrategyByAnnotation(Annotation annotation, Class<?> type) {
        log.trace("Starting initialization for strategy {}", type); //log
        Constructor<?> annotationConstructor = getAnnotationConstructorCandidate(annotation, type);
        T instance;
        try {
            instance = instantiateObjectWithAnnotationConstructorIfAvailable(annotationConstructor, annotation, type);
        } catch (Exception e) {
            throw new StrategyInstantiationByAnnotationException(type, annotation, e);
        }
        return instance;
    }

    private Constructor<?> getAnnotationConstructorCandidate(Annotation annotation, Class<?> type) {
        Constructor<?>[] constructors = type.getDeclaredConstructors();
        Constructor<?> annotationConstructor = null;
        for (Constructor<?> candidate : constructors) {
            if ((candidate.getParameterCount() == 1)
                    && (annotation.annotationType().isAssignableFrom(candidate.getParameterTypes()[0]))) {
                annotationConstructor = candidate;
                break;
            }
        }
        return annotationConstructor;
    }

    private <T> T instantiateObjectWithAnnotationConstructorIfAvailable(Constructor<?> annotationConstructor, Annotation annotation, Class<?> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (annotationConstructor != null) {
            log.trace("Initializing comparator {} using constructor with annotation parameter", type); //log
            return (T) annotationConstructor.newInstance(annotation);
        } else {
            log.trace("Initializing comparator {} using default constructor", type); //log
            return (T) type.getConstructor().newInstance();
        }
    }

}
