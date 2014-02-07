/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author kommusoft
 */
public abstract class NameBase implements Name {
    
    private String name;
    
    protected NameBase() {
        this("");
    }
    
    protected NameBase(String name) {
        this.setName(name);
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
