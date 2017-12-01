package com.spring.boot.blog.util;

public class FileFormat {

    public boolean isValid(String contentType, String... allowTypes) {  
        if (null == contentType || "".equals(contentType)) {  
            return false;  
        }  
        for (String type : allowTypes) {  
            if (contentType.indexOf(type) > -1) {  
                return true;  
            }  
        }  
        return false;  
    }  
    
}
