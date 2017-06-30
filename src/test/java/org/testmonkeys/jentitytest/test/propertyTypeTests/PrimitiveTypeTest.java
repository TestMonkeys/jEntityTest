package org.testmonkeys.jentitytest.test.propertyTypeTests;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;
import org.testmonkeys.jentitytest.test.propertyTypeTests.models.TypesEntityModel;

public class PrimitiveTypeTest {

    @Test
    public void intPosTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setIntField(2);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setIntField(2);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void intNegTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setIntField(2);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setIntField(4);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void charPosTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setCharField('t');

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setCharField('t');

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void charNegTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setCharField('s');

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setCharField('t');

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void primFloatPosTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimFloatField(9.9f);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimFloatField(9.9f);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void primFloatNegTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimFloatField(0.9f);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimFloatField(1.3f);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void primDoublePosTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimDoubleField(0.9);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimDoubleField(0.9);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void primDoubleNegTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimDoubleField(1.8);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimDoubleField(1.89);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void primLongPosTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimLongField(15);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimLongField(15);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void primLongNegTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimLongField(78);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimLongField(79);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void primShortPosTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimShortField((short) 3);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimShortField((short)3);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void primShortNegTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimShortField((short)7);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimShortField((short)8);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void primBooleanPosTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimBooleanField(false);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimBooleanField(false);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void primBooleanNegTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimBooleanField(false);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimBooleanField(true);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void primBytePosTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimByteField((byte) 4);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimByteField((byte) 4);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void primByteNegTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setPrimByteField((byte) 8);

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setPrimByteField((byte) 7);

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

}
