package com.hwhl.shengxian.service.impl;

import com.hwhl.shengxian.entity.Binding;
import com.hwhl.shengxian.entity.Customer;
import com.hwhl.shengxian.mapper.CustomerMapper;
import com.hwhl.shengxian.service.CustomerSerivice;
import com.hwhl.shengxian.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/25
 * @Version: 1.0
 */
@Service
public class CustomerSerivceImpl implements CustomerSerivice {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<HashMap> customer() {
        return customerMapper.customer();
    }

    @Override
    public Page jumpCoustomerList(Integer pageNo ,Integer cid , String phone) {
        int pageNum=1;
        if ( pageNo != null && pageNo != 0){
            pageNum=pageNo;
        }
        Integer tatolCount = customerMapper.jumpCoustomerListCount(cid,phone);
        Page page = new Page(pageNum,tatolCount);
        List<HashMap> hashMaps = customerMapper.jumpCoustomerList(cid ,phone,page.getStartIndex(),page.getPageSize());
        page.setRecords(hashMaps);
        return page;
    }

    @Override
    public String selectUserPhone(Integer id) {
        return customerMapper.selectUserPhone(id);
    }

    @Override
    public Page customerBindingBusiness(Integer pageNo,Integer cid ,String name ) throws Exception{
        int pageNum=1;
        if ( pageNo != null && pageNo != 0){
            pageNum=pageNo;
        }
        Integer tatolCount = customerMapper.customerBindingBusinessCount(cid,name);
        Page page = new Page(pageNum,tatolCount,20);
        List<HashMap> hashMaps = customerMapper.customerBindingBusiness(cid, name, page.getStartIndex(), page.getPageSize());
        page.setRecords(hashMaps);
        return page;
    }

    @Override
    public Integer deleteCustomer(Integer id) throws Exception{
        return customerMapper.deleteCustomer(id);
    }

    @Override
    public Integer addCustomer(Customer customer) throws NullPointerException,Exception {
        //查询号码是注册过了
        Integer cid = customerMapper.selectIdByPhone(customer.getPhone());
        if (cid != null){
            throw new NullPointerException("手机号码已经注册过了");
        }
        String pwd = PasswordMD5.EncoderByMd5(customer.getPassword() + Global.PASSWORDKEY);
        customer.setPassword(pwd);//加密
        customer.setCate_time(new Date());
        return customerMapper.addCustomer(customer);
    }

    @Override
    public Integer resetPassword(Integer id) throws Exception {
        String pwd = PasswordMD5.EncoderByMd5("123456" + Global.PASSWORDKEY);
        return customerMapper.resetPassword(id,pwd);
    }

    @Override
    public Integer addPushContent(Integer push_id, String title, String content, Integer type) throws Exception {
        return customerMapper.addPushContent(push_id,title,content,type,new Date());
    }

    @Override
    public Integer updateDisable(Integer id, Integer is_disable) throws Exception {
        return customerMapper.updateDisable(id,is_disable);
    }

    @Override
    public Integer updatePower(Integer id, Integer power) throws Exception {
        return customerMapper.updatePower(id,power);
    }
}
