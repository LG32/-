package com.example.administrator.newfridge.tool;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonTool implements MyHandlerMsg {

    private int msgNum;
    private String json;

    public JsonTool(String json, int msgNum) {
        this.json = json;
        this.msgNum = msgNum;
    }

    public String judgeMsg() {
        switch (msgNum) {

            case REQUEST_SUCCESS:
                return getCode ( json );
            default:
                return null;
        }
    }

    private String getCode(String json) {
        try {
            JsonObject jsonObject = (JsonObject) new JsonParser ().parse ( json );
            return jsonObject.get ( "code" ).getAsString ();
        }catch (Exception e){
            e.printStackTrace ();
            return "404";
        }
    }

}
