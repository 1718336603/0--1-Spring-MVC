package com.mvc.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/3 14:32
 */
public class ModelAndView {

    /**
     * 页面路径
     */
    private String view;

    /**
     * 页面的data数据
     */
    private Map<String,Object> model=new HashMap<>();


    public ModelAndView setView(String view){
        this.view=view;
        return this;
    }

    public String getView() {
        return view;
    }

    public ModelAndView addObject(String attributeName,Object value){
        model.put(attributeName,value);
        return this;
    }

    public ModelAndView addAllObject(HashMap<String,?> modelMap){
        model.putAll(modelMap);
        return this;
    }

    public Map<String,Object> getModel(){
        return model;
    }
}
