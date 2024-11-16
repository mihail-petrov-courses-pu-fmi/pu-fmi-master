package org.example.entities;

public class ControllerMeta {

    private Class classReference;
    private String methodName;

    public ControllerMeta(Class classReference, String methodName) {
        this.classReference = classReference;
        this.methodName = methodName;
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
