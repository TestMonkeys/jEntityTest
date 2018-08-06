package org.testmonkeys.jentitytest.test.integration.propertyTypeTests;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;
import org.testmonkeys.jentitytest.test.integration.propertyTypeTests.models.TypesEntityModel;

@SuppressWarnings({"BooleanConstructorCall", "UnnecessaryBoxing"})
public class TypeTest {

    @Test
    public void integerPosTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setIntegerField(new Integer(9));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setIntegerField(new Integer(9));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void integerNegTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setIntegerField(new Integer(9));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setIntegerField(new Integer(90));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void characterPosTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setCharacterField('o');

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setCharacterField('o');

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void characterNegTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setCharacterField('i');

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setCharacterField('r');

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void floatPosTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setFloatField(new Float(10.5));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setFloatField(new Float(10.5));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void floatNegTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setFloatField(new Float(10.5));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setFloatField(new Float(15.5));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void stringPosTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setStringField("jentity");

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setStringField("jentity");

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void stringNegTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setStringField("jentity");

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setStringField("Jentity");

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void doublePosTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setDoubleField(new Double(25.8));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setDoubleField(new Double(25.8));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void doubleNegTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setDoubleField(new Double(85.8));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setDoubleField(new Double(78.8));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void longPosTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setLongField(new Long(78));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setLongField(new Long(78));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void longNegTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setLongField(new Long(56));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setLongField(new Long(55));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void shortPosTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setShortField(new Short("7"));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setShortField(new Short("7"));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void shortNegTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setShortField(new Short("8"));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setShortField(new Short("9"));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void booleanPosTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setBooleanField(new Boolean(true));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setBooleanField(new Boolean(true));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void booleanNegTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setBooleanField(new Boolean(true));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setBooleanField(new Boolean(false));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test
    public void bytePosTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setByteField(new Byte("7"));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setByteField(new Byte("7"));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }

    @Test(expected = AssertionError.class)
    public void byteNegTypeTest(){
        TypesEntityModel actualEntity = new TypesEntityModel();
        actualEntity.setByteField(new Byte("9"));

        TypesEntityModel expectedEntity = new TypesEntityModel();
        expectedEntity.setByteField(new Byte("8"));

        Assert.assertThat(actualEntity, Entity.isEqualTo(expectedEntity));
    }
}
