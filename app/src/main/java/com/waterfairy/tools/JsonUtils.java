package com.waterfairy.tools;

import com.google.gson.Gson;

/**
 * Created by water_fairy on 2017/4/25.
 * 995637517@qq.com
 */

public class JsonUtils {
    public static String objectToJson(Object object){
        Gson gson=new Gson();
       return gson.toJson(object);
    }
}
