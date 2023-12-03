package org.example;

import java.lang.reflect.Method;

public class ClassMethodComposition {

        public Class classReference;
        public Method methodReference;

        public ClassMethodComposition(Class classReference, Method methodReference) {
            this.classReference = classReference;
            this.methodReference = methodReference;
        }

    public Class getClassReference() {
        return classReference;
    }

    public void setClassReference(Class classReference) {
        this.classReference = classReference;
    }

    public Method getMethodReference() {
        return methodReference;
    }

    public void setMethodReference(Method methodReference) {
        this.methodReference = methodReference;
    }
}
