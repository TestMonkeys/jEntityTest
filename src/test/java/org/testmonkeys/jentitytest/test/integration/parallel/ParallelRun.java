package org.testmonkeys.jentitytest.test.integration.parallel;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

/**
 * Created by cpascal on 7/18/2017.
 */
public class ParallelRun {

    @Ignore
    @Test
    public void runAllTests() {
        Class<?>[] classes = { ParallelizableTest.class,
                ParallelizableTest2.class,
                ParallelizableTest.class,
                ParallelizableTest2.class,
                ParallelizableTest.class,
                ParallelizableTest2.class,
                ParallelizableTest.class,
                ParallelizableTest2.class,
                ParallelizableTest.class,
                ParallelizableTest2.class,
                ParallelizableTest.class,
                ParallelizableTest2.class,
                ParallelizableTest.class,
                ParallelizableTest2.class,
                ParallelizableTest.class };

        // ParallelComputer(true,true) will run all classes and methods
        // in parallel.  (First arg for classes, second arg for methods)
        JUnitCore.runClasses(new ParallelComputer(true, true), classes);
    }
}
