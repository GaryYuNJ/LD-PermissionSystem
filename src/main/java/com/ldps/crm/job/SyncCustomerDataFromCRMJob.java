package com.ldps.crm.job;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ldps.crm.dao.CRMCustmemberModelMapper;
import com.ldps.crm.model.CRMCustmemberModel;
import com.ldps.dao.CustomerModelMapper;
import com.ldps.model.CustomerModel;
import com.ldps.service.ICustomerService;

/*
	定时获取CRM新注册、修改用户
	
	oracle数据库中文编码是iso-8859-1，需要转成GBK才能存储到mysql
*/
public class SyncCustomerDataFromCRMJob {
	private static Logger logger = Logger
			.getLogger(SyncCustomerDataFromCRMJob.class);
	
	@Resource
	private CRMCustmemberModelMapper cRMCustmemberDao;
	
	@Resource
	CustomerModelMapper customerDao;

	@Resource
	ICustomerService iCustomerService;
	
	public void syncData(){
		CustomerModel cModel = customerDao.selectLatestRecord();
		if(null != cModel && null != cModel.getCmMaintdate()){
			Date date = cModel.getCmMaintdate();
			List<CRMCustmemberModel> crmCusModels = cRMCustmemberDao.selectByMaintdate(date);
			//System.out.println("~~~~"+crmCusModels.size()+"~~~~");
			//循环处理。新增或者更新到mysql数据库
			if(null == crmCusModels || crmCusModels.size() >0){
				//防止job交叉运行，每次最多处理100个，大概需要1分钟时间。
				int i = 0;
				for(CRMCustmemberModel crmCusModel : crmCusModels){
					i ++;
					if(i >= 100){
						break;
					}
					
					cModel = new CustomerModel();
					cModel.setCmMemid(crmCusModel.getCmmemid());
					cModel.setCmAdd1(crmCusModel.getCmadd1());
					cModel.setCmAdd2(crmCusModel.getCmadd2());
					cModel.setCmAdd3(crmCusModel.getCmadd3());
					cModel.setCmAdd4(crmCusModel.getCmadd4());
					cModel.setCmAddr(crmCusModel.getCmaddr());
					cModel.setCmAstro(crmCusModel.getCmastro());
					cModel.setCmBirthday(crmCusModel.getCmbirthday());
					cModel.setCmBirthtype(crmCusModel.getCmbirthtype());
					
					cModel.setCmChr1(crmCusModel.getCmchr1());
					cModel.setCmChr10(crmCusModel.getCmchr10());
					cModel.setCmChr11(crmCusModel.getCmchr11());
					cModel.setCmChr12(crmCusModel.getCmchr12());
					cModel.setCmChr13(crmCusModel.getCmchr13());
					cModel.setCmChr14(crmCusModel.getCmchr14());
					cModel.setCmChr15(crmCusModel.getCmchr15());
					cModel.setCmChr16(crmCusModel.getCmchr16());
					cModel.setCmChr17(crmCusModel.getCmchr17());
					cModel.setCmChr18(crmCusModel.getCmchr18());
					cModel.setCmChr19(crmCusModel.getCmchr19());
					cModel.setCmChr2(crmCusModel.getCmchr2());
					cModel.setCmChr21(crmCusModel.getCmchr21());
					cModel.setCmChr22(crmCusModel.getCmchr22());
					cModel.setCmChr23(crmCusModel.getCmchr23());
					cModel.setCmChr24(crmCusModel.getCmchr24());
					cModel.setCmChr25(crmCusModel.getCmchr25());
					cModel.setCmChr26(crmCusModel.getCmchr26());
					cModel.setCmChr27(crmCusModel.getCmchr27());
					cModel.setCmChr28(crmCusModel.getCmchr28());
					cModel.setCmChr29(crmCusModel.getCmchr29());
					cModel.setCmChr3(crmCusModel.getCmchr3());
					cModel.setCmChr30(crmCusModel.getCmchr30());
					cModel.setCmChr31(crmCusModel.getCmchr31());
					cModel.setCmChr32(crmCusModel.getCmchr32());
					cModel.setCmChr33(crmCusModel.getCmchr33());
					cModel.setCmChr34(crmCusModel.getCmchr34());
					cModel.setCmChr35(crmCusModel.getCmchr35());
					cModel.setCmChr36(crmCusModel.getCmchr36());
					cModel.setCmChr37(crmCusModel.getCmchr37());
					cModel.setCmChr38(crmCusModel.getCmchr38());
					cModel.setCmChr39(crmCusModel.getCmchr39());
					cModel.setCmChr4(crmCusModel.getCmchr4());
					cModel.setCmChr41(crmCusModel.getCmchr41());
					cModel.setCmChr42(crmCusModel.getCmchr42());
					cModel.setCmChr43(crmCusModel.getCmchr43());
					cModel.setCmChr44(crmCusModel.getCmchr44());
					cModel.setCmChr45(crmCusModel.getCmchr45());
					cModel.setCmChr47(crmCusModel.getCmchr47());
					cModel.setCmChr48(crmCusModel.getCmchr48());
					cModel.setCmChr49(crmCusModel.getCmchr49());
					cModel.setCmChr5(crmCusModel.getCmchr5());
					cModel.setCmChr50(crmCusModel.getCmchr50());
					cModel.setCmChr6(crmCusModel.getCmchr6());
					cModel.setCmChr7(crmCusModel.getCmchr7());
					cModel.setCmChr8(crmCusModel.getCmchr8());
					cModel.setCmChr9(crmCusModel.getCmchr9());
					
					cModel.setCmCompany(crmCusModel.getCmcompany());
					cModel.setCmCustid(crmCusModel.getCmcustid());
					cModel.setCmCzz(null == crmCusModel.getCmczz() ? null: crmCusModel.getCmczz().longValue());
					cModel.setCmDecjf(null == crmCusModel.getCmdecjf() ? null: crmCusModel.getCmdecjf().longValue());
					cModel.setCmDhisjf(null == crmCusModel.getCmdhisjf() ? null: crmCusModel.getCmdhisjf().longValue());
					cModel.setCmDkjf(null == crmCusModel.getCmdkjf() ? null:  crmCusModel.getCmdkjf().longValue());
					
					cModel.setCmEmail(crmCusModel.getCmemail());
					cModel.setCmFax(crmCusModel.getCmfax());
					cModel.setCmFlag1(crmCusModel.getCmflag1());
					cModel.setCmFlag2(crmCusModel.getCmflag2());
					cModel.setCmFlag3(crmCusModel.getCmflag3());
					cModel.setCmFstdate(crmCusModel.getCmfstdate());
					cModel.setCmGrade(crmCusModel.getCmgrade());
					cModel.setCmIdno(crmCusModel.getCmidno());
					cModel.setCmIdtype(crmCusModel.getCmidtype());
					cModel.setCmIsemployee(crmCusModel.getCmisemployee());
					cModel.setCmIsowner(crmCusModel.getCmisowner());
					
					cModel.setCmJfa(null == crmCusModel.getCmjfa() ? null: crmCusModel.getCmjfa().longValue());
					cModel.setCmJfb(null == crmCusModel.getCmjfb() ? null: crmCusModel.getCmjfb().longValue());
					cModel.setCmJfc(null == crmCusModel.getCmjfc() ? null: crmCusModel.getCmjfc().longValue());
					cModel.setCmJfd(null == crmCusModel.getCmjfd() ? null: crmCusModel.getCmjfd().longValue() );
					cModel.setCmJfe(null == crmCusModel.getCmjfe() ? null: crmCusModel.getCmjfe().longValue());
					cModel.setCmJff(null == crmCusModel.getCmjff() ? null: crmCusModel.getCmjff().longValue());
					cModel.setCmJfxfxe(null == crmCusModel.getCmjfxfxe()? null:  crmCusModel.getCmjfxfxe().longValue());
					cModel.setCmJjdate(crmCusModel.getCmjjdate());
					cModel.setCmJtjs(crmCusModel.getCmjtjs());
					cModel.setCmKhdate(crmCusModel.getCmkhdate());
					cModel.setCmLczhye(null == crmCusModel.getCmlczhye()? null:  crmCusModel.getCmlczhye().longValue());
					cModel.setCmLmykjf(null == crmCusModel.getCmlmykjf()? null:  crmCusModel.getCmlmykjf().longValue());
					cModel.setCmLunar(crmCusModel.getCmlunar());
					
					cModel.setCmLunarchn(null == crmCusModel.getCmlunarchn()? null : this.Convert8859P1ToGBK(crmCusModel.getCmlunarchn()));
					
					cModel.setCmLxtype1(crmCusModel.getCmlxtype1());
					cModel.setCmLxtype2(crmCusModel.getCmlxtype2());
					cModel.setCmLxtype3(crmCusModel.getCmlxtype3());
					cModel.setCmLxtype4(crmCusModel.getCmlxtype4());
					cModel.setCmLxtype5(crmCusModel.getCmlxtype5());
					
					cModel.setCmMaintdate(crmCusModel.getCmmaintdate());
					cModel.setCmMaintor(crmCusModel.getCmmaintor());
					cModel.setCmMemid(crmCusModel.getCmmemid());
					cModel.setCmMkt(crmCusModel.getCmmkt());
					cModel.setCmMobile1(crmCusModel.getCmmobile1());
					cModel.setCmMobile2(crmCusModel.getCmmobile2());
					
					cModel.setCmN1(null == crmCusModel.getCmn1()? null:  crmCusModel.getCmn1().longValue());
					cModel.setCmN2(null == crmCusModel.getCmn2()? null:  crmCusModel.getCmn2().longValue());
					cModel.setCmN3(null == crmCusModel.getCmn3()? null:  crmCusModel.getCmn3().longValue());
					cModel.setCmN4(null == crmCusModel.getCmn4()? null:  crmCusModel.getCmn4().longValue());
					cModel.setCmN5(null == crmCusModel.getCmn5()? null:  crmCusModel.getCmn5().longValue());
					cModel.setCmN6(null == crmCusModel.getCmn6()? null:  crmCusModel.getCmn6().longValue());
					cModel.setCmN7(null == crmCusModel.getCmn7()? null:  crmCusModel.getCmn7().longValue());
					cModel.setCmN8(null == crmCusModel.getCmn8()? null:  crmCusModel.getCmn8().longValue());
					cModel.setCmN9(null == crmCusModel.getCmn9()? null:  crmCusModel.getCmn9().longValue());
					
					cModel.setCmName(null == crmCusModel.getCmname()? null : this.Convert8859P1ToGBK(crmCusModel.getCmname()));
					
					cModel.setCmNum1(null == crmCusModel.getCmnum1()? null:  crmCusModel.getCmnum1().longValue());
					cModel.setCmNum2(null == crmCusModel.getCmnum2()? null:  crmCusModel.getCmnum2().longValue());
					cModel.setCmNum3(null == crmCusModel.getCmnum3()? null:  crmCusModel.getCmnum3().longValue());
					cModel.setCmNum4(null == crmCusModel.getCmnum4()? null:  crmCusModel.getCmnum4().longValue());
					cModel.setCmNum5(null == crmCusModel.getCmnum5()? null:  crmCusModel.getCmnum5().longValue());
					cModel.setCmNum6(null == crmCusModel.getCmnum6()? null:  crmCusModel.getCmnum6().longValue());
					cModel.setCmNum7(null == crmCusModel.getCmnum7()? null:  crmCusModel.getCmnum7().longValue());
					cModel.setCmNum8(null == crmCusModel.getCmnum8()? null:  crmCusModel.getCmnum8().longValue());
					cModel.setCmNum9(null == crmCusModel.getCmnum9()? null:  crmCusModel.getCmnum9().longValue());
					cModel.setCmNum10(null == crmCusModel.getCmnum10()? null:  crmCusModel.getCmnum10().longValue());
					cModel.setCmNum11(null == crmCusModel.getCmnum11()? null:  crmCusModel.getCmnum11().longValue());
					cModel.setCmNum12(null == crmCusModel.getCmnum12()? null:  crmCusModel.getCmnum12().longValue());
					cModel.setCmNum13(null == crmCusModel.getCmnum13()? null:  crmCusModel.getCmnum13().longValue());
					cModel.setCmNum14(null == crmCusModel.getCmnum14()? null:  crmCusModel.getCmnum14().longValue());
					cModel.setCmNum15(null == crmCusModel.getCmnum15()? null:  crmCusModel.getCmnum15().longValue());
					cModel.setCmNum16(null == crmCusModel.getCmnum16()? null:  crmCusModel.getCmnum16().longValue());
					cModel.setCmNum17(null == crmCusModel.getCmnum17()? null:  crmCusModel.getCmnum17().longValue());
					cModel.setCmNum18(null == crmCusModel.getCmnum18()? null:  crmCusModel.getCmnum18().longValue());
					cModel.setCmNum19(null == crmCusModel.getCmnum19()? null:  crmCusModel.getCmnum19().longValue());
					cModel.setCmNum20(null == crmCusModel.getCmnum20()? null:  crmCusModel.getCmnum20().longValue());
					
					cModel.setCmPtname(crmCusModel.getCmptname());
					cModel.setCmPwd(crmCusModel.getCmpwd());
					cModel.setCmQyjs(crmCusModel.getCmqyjs());
					cModel.setCmReferee(crmCusModel.getCmreferee());
					cModel.setCmRelation(crmCusModel.getCmrelation());
					cModel.setCmSex(crmCusModel.getCmsex());
					cModel.setCmSjdate(crmCusModel.getCmsjdate());
					cModel.setCmSsqy(crmCusModel.getCmssqy());
					cModel.setCmSx(crmCusModel.getCmsx());
					cModel.setCmTel(crmCusModel.getCmtel());
					cModel.setCmTotjf(null == crmCusModel.getCmtotjf()? null:  crmCusModel.getCmtotjf().longValue());
					cModel.setCmXfdate(crmCusModel.getCmxfdate());
					cModel.setCmXfje(null == crmCusModel.getCmxfje()? null:  crmCusModel.getCmxfje().longValue());
					cModel.setCmZip(crmCusModel.getCmzip());
					
					//添加、更新用户信息。以cmmemid为主键
					if(-1 == iCustomerService.addCustomer(cModel)){
						iCustomerService.updateCustomerById(cModel);
					}
				}
			}else{
				logger.warn("SyncCustomerDataFromCRMJob CRMCustmemberModelMapper.selectByMaintdate() return null. ");
			}
		}else{
			logger.error("SyncCustomerDataFromCRMJob CRMCustmemberModelMapper selectLatestRecord  is null.");
		}
	}
	
	//oracle数据库中文编码是iso-8859-1，需要转成utf-8才能存储到mysql
	public static String Convert8859P1ToGBK(String s)
    {
		String result =null;
		try {
			byte[] buf = s.getBytes("ISO-8859-1");
			result = new String(buf,"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }
}
