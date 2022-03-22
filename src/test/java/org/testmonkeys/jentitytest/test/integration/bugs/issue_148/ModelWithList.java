package org.testmonkeys.jentitytest.test.integration.bugs.issue_148;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.testmonkeys.jentitytest.framework.ChildEntityListComparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ModelWithList {

    public ModelWithList(String name, String... objects){
        this.name=name;
        this.objects=new ArrayList<>();
        this.objects.addAll(Arrays.asList(objects));
    }

    String name;
    @ChildEntityListComparison(ordered = false)
    List<String> objects;
}
