package org.example.entities;

import java.util.HashMap;

public class ControllerMeta {

    private Class classReference;
    private String methodName;
    private HashMap<Integer, String> pathVariableIndex;

    public ControllerMeta(Class classReference, String methodName) {

        this.classReference     = classReference;
        this.methodName         = methodName;
        this.pathVariableIndex  = new HashMap<>();
    }

    public ControllerMeta(Class classReference, String methodName, HashMap<Integer, String> pathVariableIndex) {
        this.classReference     = classReference;
        this.methodName         = methodName;
        this.pathVariableIndex  = pathVariableIndex;
    }

    public HashMap<Integer, String> getPathVariableIndex() {
        return this.pathVariableIndex;
    }

    public Class getClassReference() {
        return classReference;
    }

    public void setClassReference(Class classReference) {
        this.classReference = classReference;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
