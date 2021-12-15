
/*
 * Base class that represent an type
*/

public abstract class Type {
    private String typeName;

    /**
     * Constructor
     */
    public Type(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Get the node value
     */
    public String getTypeName() {
        return this.typeName;
    }
}
