package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;

public class InvalidProperties {
    @IgnoreComparison
    private int id;

    private boolean available;


    private boolean marshmallows;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * invalid accessor for marshmallows field.
     *
     * @return
     */
    public boolean hasMarshmallows() {
        return this.marshmallows;
    }

    public void setHasMarshmallows(boolean hasMarshmallows) {
        marshmallows = hasMarshmallows;
    }
}
