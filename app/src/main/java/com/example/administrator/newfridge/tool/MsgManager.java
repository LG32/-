package com.example.administrator.newfridge.tool;

import java.util.HashMap;
import java.util.Map;

public class MsgManager implements MyHandlerMsg {

    private Map<String, Integer> msgMap;

    public MsgManager() {
        msgMap = new HashMap<> ();
        initValue ();
    }

    private void initValue() {
        msgMap.put ( "login", LOGIN_SUCCESS );
        msgMap.put ( "getiden", GETIDEN_SUCCESS );
        msgMap.put ( "register", REGISTER_SUCCESS );
    }

    public int getMsg(String key){
        return msgMap.get ( key );
    }
}
