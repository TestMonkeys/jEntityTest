package org.testmonkeys.jentitytest.hamcrest.matchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.BaseMatcher;
import org.testmonkeys.jentitytest.Resources;

import java.text.MessageFormat;

import static org.testmonkeys.jentitytest.Resources.desc_entities_same;


@SuppressWarnings("WeakerAccess")
public abstract class AbstractJEntityMatcher<T> extends BaseMatcher<T> {
    protected ResultProcessor resultProcessor;

    protected AbstractJEntityMatcher() {
        resultProcessor = new DefaultResultOutput();
    }

    public void setResultProcessor(ResultProcessor resultProcessor) {
        this.resultProcessor = resultProcessor;
    }

    protected String generateDescriptionFor(String resourceMessage, Object expected) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return MessageFormat.format(Resources.getString(resourceMessage), objectMapper.writeValueAsString(expected));
        } catch (JsonProcessingException e) {
            return MessageFormat.format(Resources.getString(desc_entities_same), expected);
        }
    }
}
