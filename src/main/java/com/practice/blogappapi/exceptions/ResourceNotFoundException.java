package com.practice.blogappapi.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;  //Represents the name of the resource that are not found
    String fieldName;  //Represents the specific field or attribute of the resource used in the search criteria
    long fieldValue;   //Represents the value of the field or attribute used in the search criteria

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {

        //The superclass uses String.format() to provide information about thr resource not being found including
        //the resource name,filed name,and field value
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
