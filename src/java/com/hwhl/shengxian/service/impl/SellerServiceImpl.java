package com.hwhl.shengxian.service.impl;

import com.hwhl.shengxian.entity.Binding;
import com.hwhl.shengxian.entity.Business;
import com.hwhl.shengxian.entity.Template;
import com.hwhl.shengxian.mapper.SellerMapper;
import com.hwhl.shengxian.service.SellerService;
import com.hwhl.shengxian.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * Description:
 *
 * @Author: yang
 * @Date: 2018/6/23
 * @Version: 1.0
 */
@Service
@Component
public class SellerServiceImpl implements SellerService {

    private static final Logger logger =Logger.getLogger(SellerServiceImpl.class);
    @Autowired
    private SellerMapper sellerMapper;


    @Override
    public Integer saveSeller(String phone, String store_name, String name, String illustrated, String remarks) throws NullPointerException, Exception {

        Integer sid = sellerMapper.selectIdByPhone( phone);
        if (sid != null){
            throw new NullPointerException("账号已经注册过了");
        }
        String num = null;
        //查询最后一条编号
        String number = sellerMapper.findBusinessLastNumber();
        if (number == null){
            num = "1001";
        }else {
           num= GroupNumber.getNumber(Integer.valueOf(number));
        }
        String pwd = PasswordMD5.EncoderByMd5("123456" + Global.PASSWORDKEY);
        Business business = new Business(num,phone,pwd , UUID.randomUUID().toString() ,name,store_name,illustrated,remarks,new Date());
        Integer count = sellerMapper.saveSeller(business);

        //添加商家盘点状态
        sellerMapper.addClerk(business.getId());

        //添加商家采购模板
        sellerMapper.addTemplate(new Template(business.getId(),"采购单","供应商名称","供应商电话",0));
        //添加商家采购退货模板
        sellerMapper.addTemplate(new Template(business.getId(),"采购退货单","供应商名称","供应商电话",1));
        //添加商家销售模板
        sellerMapper.addTemplate(new Template(business.getId(),"销售单","客户名称","客户电话",2));
        //添加商家销售退货模板
        sellerMapper.addTemplate(new Template(business.getId(),"销售退货单","客户名称","客户电话",3));

        //添加模板2
        sellerMapper.addTemplateTwo(business.getId() , "采购单" ,0 ,null  );
        sellerMapper.addTemplateTwo(business.getId() , "采购退货单" ,1 ,null  );
        sellerMapper.addTemplateTwo(business.getId() , "销售单" ,2 ,null  );
        sellerMapper.addTemplateTwo(business.getId() , "销售退货单" ,3 ,null  );

        //添加模板3
        sellerMapper.addTemplateThree(business.getId() , "采购单" ,0 ,null  );
        sellerMapper.addTemplateThree(business.getId() , "采购退货单" ,1 ,null  );
        sellerMapper.addTemplateThree(business.getId() , "销售单" ,2 ,null  );
        sellerMapper.addTemplateThree(business.getId() , "销售退货单" ,3 ,null  );

        //添加模板5
        sellerMapper.addTemplateFive(business.getId() , "采购单" ,0 ,null  );
        sellerMapper.addTemplateFive(business.getId() , "采购退货单" ,1 ,null  );
        sellerMapper.addTemplateFive(business.getId() , "销售单" ,2 ,null  );
        sellerMapper.addTemplateFive(business.getId() , "销售退货单" ,3 ,null  );

        //注册商家默认添加15种方案名称
        for (int i = 1;i <16 ; i++){
            sellerMapper.addBusinessScheme(i,business.getId(),String.valueOf(i));
        }

        return count;
    }

    @Override
    public Integer updateAdopt(Integer id) throws Exception{
        return sellerMapper.updateAdopt(id);
    }

    @Override
    public Integer updateSeller(Business business) {
        //通过商家id查询手机号
        String phone = sellerMapper.findPhone(business.getId());
        //判断手机号是否修改过
        if (!phone.equals(business.getPhone())){
            //查询修改过的手机号是否存在
            Integer id = sellerMapper.phoneIsExist(business.getPhone());
            if(id != null ){
                return -2;
            }
        }
        if (business.getDuration() != null ){
            business.setOpen(1);
        }
        if (business.getIs_disable() == 1 || business.getIs_del() == 1){
            business.setStatus(1);
        }



        return sellerMapper.updateSeller(business);
    }


    @Override
    public Integer deleteSeller(Integer id) {
        return sellerMapper.deleteSeller(id);
    }


    @Override
    public Page findSellerList(Integer pageNo,Integer  business_id , String storeName ) {
        int pageNum=1;
        if(pageNo!=null&&pageNo!=0){
            pageNum=pageNo;
        }
        Integer totalCount = sellerMapper.findSellerCount(business_id,storeName);
        Page page = new Page(pageNum,totalCount);
        List<HashMap> hashMaps = sellerMapper.findSellerList(business_id,storeName,page.getStartIndex(), page.getPageSize());
        for (HashMap hash: hashMaps ) {
            //判断使用期限是否小于0
            if (Integer.valueOf(hash.get("days").toString()) < 0){
                //小于0则自动禁用店铺
                sellerMapper.updateBusines(Integer.valueOf(hash.get("id").toString()));
            }
        }
        page.setRecords(hashMaps);
        return page;
    }

    @Override
    public Business findSellerById(Integer id) {
        return sellerMapper.findSellerById(id);
    }


    @Override
    public Integer addSellerSendInformation(Integer business_id, String message) {
        return sellerMapper.addSellerSendInformation(business_id,message,new Date());
    }

    @Override
    public Page selectCustomerListById(Integer id, Integer pageNo, String name) {
        int pageNum=1;
        if ( pageNo != null && pageNo != 0){
            pageNum=pageNo;
        }
        Integer totalCount = sellerMapper.selectCustomerListCountById(id, name);
        Page page = new Page(pageNum,totalCount);
        List<HashMap> bindings = sellerMapper.selectCustomerListById(id, name, page.getStartIndex(), page.getPageSize());
        page.setRecords(bindings);
        return page;
    }

    @Override
    public Integer selectCustomerListCountById(Integer id, String account) {
        return sellerMapper.selectCustomerListCountById(id,account);
    }

    @Override
    public String findSellerAccountById(Integer id) {
        return sellerMapper.findSellerAccountById(id);
    }

    @Override
    public Binding selectBindingById(Integer id) {
        return sellerMapper.selectBindingById(id);
    }


    @Override
    public Integer upateDurationJurisdiction(Integer uid, Integer jur) {
        return sellerMapper.upateDurationJurisdiction(uid,jur);
    }

    @Override
    public List<HashMap> license(Integer id) {
        return sellerMapper.license(id);
    }

    @Override
    public Integer addLicense(Integer bid, String img) {
        return sellerMapper.addLicense(bid,img);
    }

    @Override
    public Integer deleteLicense(Integer id) {
        return sellerMapper.deleteLicense(id);
    }

    @Override
    public Integer updateBusinessSource(Integer id, Integer source) throws Exception {
        return sellerMapper.updateBusinessSource(id,source);
    }
}
