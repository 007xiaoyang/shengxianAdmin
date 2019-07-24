package com.hwhl.shengxian.util;

import java.util.UUID;

/**
 * Description: 生成账号与编号工具类工具类
 *
 * @Author: yang
 * @Date: 2018/5/18
 * @Version: 1.0
 */
public class GroupNumber {

    public GroupNumber(){

    }

    /**
     * 自动生成商家8位账号
     * @return
     */
    public static Integer getAccount(){
        int result = (int) ((Math.random() * 9 + 1) * 10000000);

        return result;
    }

    /**
     * 自动生成商家8位账号
     * @return
     */
    public static Integer getAccount6(){
        int result = (int) ((Math.random() * 9 + 1) * 100000);

        return result;
    }

    /**
     * 获取自动生成编号
     * @param number 从数据库里查出最后那条编号
     * @return
     */
    public static String getNumber( Integer number){
        return  generatedNumber(Integer.valueOf(number));
    }


    /**
     * 自动生成32位的token。
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }





    /**
     * 生成有规律的编号
     * @param num
     * @return
     */
    public static String generatedNumber(int num){

        num++;
        String result ;
        switch ((num+"").length()){
            case 1:
                result= "00"+num;
                break;
            default:
                result = ""+num;
                break;
        }
        return result;
    }



}
