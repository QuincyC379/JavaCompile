package com.zzb.chn.util;

import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Date;

import com.cninsure.core.utils.StringUtil;

/**
 * 
 * @author shiguiwu
 * @date 2017年3月16日
 * 
 * <p> 
 * 身份证合法性校验 
 * </p> 
 *  
 * <pre> 
 * --15位身份证号码：第7、8位为出生年份(两位数)，第9、10位为出生月份，第11、12位代表出生日期，第15位代表性别，奇数为男，偶数为女。 
 * --18位身份证号码：第7、8、9、10位为出生年份(四位数)，第11、第12位为出生月份，第13、14位代表出生日期，第17位代表性别，奇数为男，偶数为女。 
 *    最后一位为校验位 
 *    2 驾照  513701190812
 *6 组织代码证  915002247592635277 (不通过)   79004449-6    75580104-1  91350200MA346QPX9Y    68493118-X
 *	8 社会信用代码证   91510108357986247F  91150207MAOMW7JT90(通过)  91320681786305622L（通过）
 *	9税务登记证  91320611321161489C
 *	10 营业执照  91411422567282854G  914201046953430259
 */
public class ParaValidatorUtils {

	/** 
     * <pre> 
     * 省、直辖市代码表： 
     *     11 : 北京  12 : 天津  13 : 河北       14 : 山西  15 : 内蒙古   
     *     21 : 辽宁  22 : 吉林  23 : 黑龙江  31 : 上海  32 : 江苏   
     *     33 : 浙江  34 : 安徽  35 : 福建       36 : 江西  37 : 山东   
     *     41 : 河南  42 : 湖北  43 : 湖南       44 : 广东  45 : 广西      46 : 海南   
     *     50 : 重庆  51 : 四川  52 : 贵州       53 : 云南  54 : 西藏   
     *     61 : 陕西  62 : 甘肃  63 : 青海       64 : 宁夏  65 : 新疆   
     *     71 : 台湾   
     *     81 : 香港  82 : 澳门   
     *     91 : 国外 
     * </pre> 
     */
	private static String cityCode[] = { "11", "12", "13", "14", "15", "21",  
            "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42",  
            "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",  
            "63", "64", "65", "71", "81", "82", "91" };
	/** 
     * 每位加权因子 
     */  
    private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,  
            8, 4, 2 };
    /** 
     * 验证所有的身份证的合法性 
     *  
     * @param idcard 
     *            身份证 
     * @return 合法返回true，否则返回false 
     */
    public static boolean isValidatedAllIdcard(String idcard) {  
        if (idcard == null || "".equals(idcard)) {  
            return false;  
        }  
        if (idcard.length() == 15) {  
            return validate15IDCard(idcard);  
        }
        return validate18Idcard(idcard);  
    }
    
    /** 
     * <p> 
     * 判断18位身份证的合法性 
     * </p> 
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。 
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。 
     * <p> 
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。 
     * </p> 
     * <p> 
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码； 
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码； 
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性； 
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。 
     * </p> 
     * <p> 
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 
     * 2 1 6 3 7 9 10 5 8 4 2 
     * </p> 
     * <p> 
     * 2.将这17位数字和系数相乘的结果相加。 
     * </p> 
     * <p> 
     * 3.用加出来和除以11，看余数是多少 
     * </p> 
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 
     * 2。 
     * <p> 
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。 
     * </p> 
     *  
     * @param idcard 
     * @return 
     */  
    public static boolean validate18Idcard(String idcard) {  
        if (idcard == null) {  
            return false;  
        }  
  
        // 非18位为假  
        if (idcard.length() != 18) {  
            return false;  
        }  
        // 获取前17位  
        String idcard17 = idcard.substring(0, 17);  
  
        // 前17位全部为数字  
        if (!isDigital(idcard17)) {  
            return false;  
        }  
  
        String provinceid = idcard.substring(0, 2);  
        // 校验省份  
        if (!checkProvinceid(provinceid)) {  
            return false;  
        }  
  
        // 校验出生日期  
        String birthday = idcard.substring(6, 14);  
  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
  
        try {  
            Date birthDate = sdf.parse(birthday);  
            String tmpDate = sdf.format(birthDate);  
            if (!tmpDate.equals(birthday)) {// 出生年月日不正确  
                return false;  
            }  
  
        } catch (ParseException e1) {  
  
            return false;  
        }  
  
        // 获取第18位  
        String idcard18Code = idcard.substring(17, 18);  
  
        char c[] = idcard17.toCharArray();  
  
        int bit[] = converCharToInt(c);  
  
        int sum17 = 0;  
  
        sum17 = getPowerSum(bit);  
  
        // 将和值与11取模得到余数进行校验码判断  
        String checkCode = getCheckCodeBySum(sum17);  
        if (null == checkCode) {  
            return false;  
        }  
        // 将身份证的第18位与算出来的校码进行匹配，不相等就为假  
        if (!idcard18Code.equalsIgnoreCase(checkCode)) {  
            return false;  
        }  
       idcard = idcard.toUpperCase();
       return true;  
    }
    /** 
     * 校验15位身份证 
     *  
     * <pre> 
     * 只校验省份和出生年月日 
     * </pre> 
     *  
     * @param idcard 
     * @return 
     */  
    public static boolean validate15IDCard(String idcard) {  
        if (idcard == null) {  
            return false;  
        }  
        // 非15位为假  
        if (idcard.length() != 15) {  
            return false;  
        }  
  
        // 15全部为数字  
        if (!isDigital(idcard)) {  
            return false;  
        }  
  
        String provinceid = idcard.substring(0, 2);  
        // 校验省份  
        if (!checkProvinceid(provinceid)) {  
            return false;  
        }  
  
        String birthday = idcard.substring(6, 12);  
  
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");  
  
        try {  
            Date birthDate = sdf.parse(birthday);  
            String tmpDate = sdf.format(birthDate);  
            if (!tmpDate.equals(birthday)) {// 身份证日期错误  
                return false;  
            }  
  
        } catch (ParseException e1) {  
  
            return false;  
        }  
        idcard = idcard.toUpperCase();
        return true;  
    }
    /** 
     * 将15位的身份证转成18位身份证 
     *  
     * @param idcard 
     * @return 
     */  
    public static String convertIdcarBy15bit(String idcard) {  
        if (idcard == null) {  
            return null;  
        }  
  
        // 非15位身份证  
        if (idcard.length() != 15) {  
            return null;  
        }  
  
        // 15全部为数字  
        if (!isDigital(idcard)) {  
            return null;  
        }  
  
        String provinceid = idcard.substring(0, 2);  
        // 校验省份  
        if (!checkProvinceid(provinceid)) {  
            return null;  
        }  
  
        String birthday = idcard.substring(6, 12);  
  
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");  
  
        Date birthdate = null;  
        try {  
            birthdate = sdf.parse(birthday);  
            String tmpDate = sdf.format(birthdate);  
            if (!tmpDate.equals(birthday)) {// 身份证日期错误  
                return null;  
            }  
  
        } catch (ParseException e1) {  
            return null;  
        }  
  
        Calendar cday = Calendar.getInstance();  
        cday.setTime(birthdate);  
        String year = String.valueOf(cday.get(Calendar.YEAR));  
  
        String idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);  
  
        char c[] = idcard17.toCharArray();  
        String checkCode = "";  
  
        // 将字符数组转为整型数组  
        int bit[] = converCharToInt(c);  
  
        int sum17 = 0;  
        sum17 = getPowerSum(bit);  
  
        // 获取和值与11取模得到余数进行校验码  
        checkCode = getCheckCodeBySum(sum17);  
  
        // 获取不到校验位  
        if (null == checkCode) {  
            return null;  
        }  
        // 将前17位与第18位校验码拼接  
        idcard17 += checkCode;  
        return idcard17.toUpperCase();  
    }
    /** 
     * 校验省份 
     *  
     * @param provinceid 
     * @return 合法返回TRUE，否则返回FALSE 
     */  
    private static boolean checkProvinceid(String provinceid) {  
        for (String id : cityCode) {  
            if (id.equals(provinceid)) {  
                return true;  
            }  
        }  
        return false;  
    }
    /** 
     * 数字验证 
     *  
     * @param str 
     * @return 
     */  
    private static boolean isDigital(String str) {  
        return str.matches("^[0-9]*$");  
    }
    /** 
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值 
     *  
     * @param bit 
     * @return 
     */  
    private static int getPowerSum(int[] bit) {  
  
        int sum = 0;  
  
        if (power.length != bit.length) {  
            return sum;  
        }  
  
        for (int i = 0; i < bit.length; i++) {  
            for (int j = 0; j < power.length; j++) {  
                if (i == j) {  
                    sum = sum + bit[i] * power[j];  
                }  
            }  
        }  
        return sum;  
    }
    /** 
     * 将和值与11取模得到余数进行校验码判断 
     *  
     * @param checkCode 
     * @param sum17 
     * @return 校验位 
     */  
    private static String getCheckCodeBySum(int sum17) {  
        String checkCode = null;  
        switch (sum17 % 11) {  
        case 10:  
            checkCode = "2";  
            break;  
        case 9:  
            checkCode = "3";  
            break;  
        case 8:  
            checkCode = "4";  
            break;  
        case 7:  
            checkCode = "5";  
            break;  
        case 6:  
            checkCode = "6";  
            break;  
        case 5:  
            checkCode = "7";  
            break;  
        case 4:  
            checkCode = "8";  
            break;  
        case 3:  
            checkCode = "9";  
            break;  
        case 2:  
            checkCode = "x";  
            break;  
        case 1:  
            checkCode = "0";  
            break;  
        case 0:  
            checkCode = "1";  
            break;  
        }  
        return checkCode;  
    }
    /** 
     * 将字符数组转为整型数组 
     *  
     * @param c 
     * @return 
     * @throws NumberFormatException 
     */  
    private static int[] converCharToInt(char[] c) throws NumberFormatException {  
        int[] a = new int[c.length];  
        int k = 0;  
        for (char temp : c) {  
            a[k++] = Integer.parseInt(String.valueOf(temp));  
        }  
        return a;  
    }
    /**
     * 户口本的校验
     * @param value
     * @return
     */
    public static boolean checkHouseholdRegister(String value) {
    	String regex = "^\\d{9}$";//440111198909205458 不正确，校验不过211002197110282016
    	if(!value.matches(regex)) {
    		return false;
    	}
    	value.toUpperCase();
    	return true;
    }
    /**
     * 驾照的校验
     * @param value
     * @return
     */
    public static boolean checkDrivingLicense(String value) {
        String regex = "^[A-Za-z0-9]{7,18}$";//440101022322 校验通过
        if(!value.matches(regex)) {
        	return false;
        }
        value.toUpperCase();
    	return true;
    }
    /**
     * 校验军官证，士兵证
     * @param value
     * @return
     */
    public static boolean checkSoldierCard(String value) {
    	String regex = "^[\u4e00-\u9fa5]{1}\u5b57\u7b2c[A-Z0-9]{7,8}[\u53f7]$";
//    	41000  校验不通过
    	if(!value.matches(regex)) {
    		return false;
    	}
    	value.toUpperCase();
    	return true;
    }
    /**
     * 护照的校验
     * @param value
     * @return
     */
    public static boolean checkPassPort(String value) {
    	String regex = "^(P\\d{7}|G\\d{8}|S\\d{7,8}|D\\d+|1[4,5]\\d{7})$";
//    	612127197407122011 校验不通过H0076167301/P1129065
    	if(!value.matches(regex)) {
    		return false;
    	}
    	value.toUpperCase();
    	return true;
    }
    /**
     * 港澳同胞回乡证校验
     * @param value
     * @return
     */
    public static boolean checkHKMK(String value) {
//    	635131414489	05874629	H0848406901	K117932（2） 校验通过
    	String regex = "^([A-Za-z0-9]{8,11}\\([A-Za-z0-9]\\))$";
        String regex2 = "^([A-Za-z0-9]{8,11})$";
        if(!value.matches(regex) && !value.matches(regex2)) {
        	return false;
        }
        value.toUpperCase();
    	return true;
    }
    /**
     * 组织代码证校验
     * @param value
     * @return
     */
    public static boolean checkOrgCodeCert(String value) {
    	String regex = "^([A-Za-z0-9]{8})-[A-Za-z0-9]$";//58465610-9 校验通过
    	if(!value.matches(regex)) {
    		return false;
    	}
    	value.toUpperCase();
    	return true;
    }
    /**
     * 社会信用代码证
     * @param value
     * @return
     */
    public static boolean checkCreditNo(String value) {
//    	91441 90061 83620 62J 校验通过 91 150207 MAOMW 7JT90
        String regex = "^[a-zA-Z0-9]{2}\\d{6}[a-zA-Z0-9]{10}$";
        if(!value.matches(regex)) {
        	return false;
        }
        value.toUpperCase();
    	return true;
    }
    /**
     * 对名字的校验
     * @param value
     * @return
     */
    public static boolean checkName(String value) {
    	if(value.replaceAll(" ","").length() <= 1) {
    		return false;
    	}
    	return true;
    }
    /**
     * 车牌号的校验
     * @param value
     * @return
     * 
     * ^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1,2}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4,5}[A-Z0-9挂学警港澳]{1}$
     */
    public static boolean checkCarLicenseNo(String value) {
    	String regex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1,2}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4,5}[A-Z0-9挂学警港澳电绿]{1}$";
    	if(!value.matches(regex)) {
    		return false;
    	}
    	value.toUpperCase();
    	return true;
    }
    /**
     * 营业执照的校验 前端无校验
     * @param value
     * @return
     */
    public static boolean checkBusinessLicense(String value) {
    	
    	return true;
    }
    /**
     * 对税务登记证的校验 前端无校验
     * @param value
     * @return
     */
    public static boolean checkTaxRegistrationCertificate(String value) {
    	
    	return true;
    }
    
    public static void main(String[] args) throws Exception {  
        String idcard15 = "21050219910810091x";  
        String idcard18 = "44018319880229443X";//  
//        String a = idcard15.toUpperCase();
//        isValidatedAllIdcard(idcard15);
        // 15位身份证  
        System.out.println("sda  ".length()<=16);  
        // 18位身份证  
//        System.out.println(a);  
        // 15位身份证转18位身份证  
//        System.out.println(convertIdcarBy15bit(idcard15));  
        System.out.println("(");
        int x = 2;
        System.out.println(x++);
        
        System.out.println(x);
    }  
	
}
