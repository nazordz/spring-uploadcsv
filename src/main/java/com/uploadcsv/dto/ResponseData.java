package com.uploadcsv.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseData {
    
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private Object payload;
}
