package com.example.administrator.newfridge.model;

import java.util.ArrayList;

/**
 * @author LG32
 * 2018/12/09
 * 存储icebox_id,单例模式
 */
public class IceBoxIdBean {

    private ArrayList<String> iceId_list = new ArrayList<> (  );

    private static IceBoxIdBean iceBoxIdBean;

    private IceBoxIdBean(){
    }

    public static IceBoxIdBean getIceBoxIdBean(){
        if (iceBoxIdBean == null){
            iceBoxIdBean = new IceBoxIdBean ();
            return iceBoxIdBean;
        }else
            return iceBoxIdBean;
    }

    public ArrayList<String> getIceId_list() {
        return iceId_list;
    }

    public void setIceId_list(ArrayList<String> iceId_list) {
        this.iceId_list = iceId_list;
    }

    public void add(String id){
        iceId_list.add ( id );
    }
}
