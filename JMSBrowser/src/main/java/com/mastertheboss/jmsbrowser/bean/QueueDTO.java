
package com.mastertheboss.jmsbrowser.bean;

/**
 *
 * @author francesco
 */
public class QueueDTO {
String name;
String entry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return entry;
    }
    
}
