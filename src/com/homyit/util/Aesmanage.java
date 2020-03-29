package com.homyit.util;
/**
 * 加密
 * @param content 需要加密的内容 
 * @param password  加密密码 
 * @author 1979
 *
 */
import java.io.UnsupportedEncodingException;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Aesmanage {
    
    public byte[] encrypt(String content, String password) {  
            try {             
                    KeyGenerator kgen = KeyGenerator.getInstance("AES");  //返回生成指定算法的秘密密钥的 KeyGenerator 对象。
                    kgen.init(128, new SecureRandom(password.getBytes()));  //使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小。
                    SecretKey secretKey = kgen.generateKey();  //生成一个密钥
                    byte[] enCodeFormat = secretKey.getEncoded();  //返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null
                    SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
                    Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
                    byte[] byteContent = content.getBytes("utf-8");  
                    cipher.init(Cipher.ENCRYPT_MODE, key);// 用密钥初始化
                    byte[] result = cipher.doFinal(byteContent);  //按单部分操作加密或解密数据，或者结束一个多部分操作
                    return result; // 加密  
            } catch (NoSuchAlgorithmException e) {  
                    e.printStackTrace();  
            } catch (NoSuchPaddingException e) {  
                    e.printStackTrace();  
            } catch (InvalidKeyException e) {  
                    e.printStackTrace();  
            } catch (UnsupportedEncodingException e) {  
                    e.printStackTrace();  
            } catch (IllegalBlockSizeException e) {  
                    e.printStackTrace();  
            } catch (BadPaddingException e) {  
                    e.printStackTrace();  
            }  
            return null;  
    }


    /**解密 
     * @param content  待解密内容 
     * @param password 解密密钥 
     * @return 
     */  
    public byte[] decrypt(byte[] content, String password) {  
            try {  
                     KeyGenerator kgen = KeyGenerator.getInstance("AES");  
                     kgen.init(128, new SecureRandom(password.getBytes()));  
                     SecretKey secretKey = kgen.generateKey();  
                     byte[] enCodeFormat = secretKey.getEncoded();  
                     SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
                     Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
                    cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
                    byte[] result = cipher.doFinal(content);  
                    return result; // 加密  
            } catch (NoSuchAlgorithmException e) {  
                    e.printStackTrace();  
            } catch (NoSuchPaddingException e) {  
                    e.printStackTrace();  
            } catch (InvalidKeyException e) {  
                    e.printStackTrace();  
            } catch (IllegalBlockSizeException e) {  
                    e.printStackTrace();  
            } catch (BadPaddingException e) {  
                    e.printStackTrace();  
            }  
            return null;  
    }

    /**将二进制转换成16进制 
     * @param buf 
     * @return 
     */  
    public String parseByte2HexStr(byte buf[]) {  
            StringBuffer sb = new StringBuffer();  
            for (int i = 0; i < buf.length; i++) {  
                    String hex = Integer.toHexString(buf[i] & 0xFF);  
                    if (hex.length() == 1) {  
                            hex = '0' + hex;  
                    }  
                    sb.append(hex.toUpperCase());  
            }  
            return sb.toString();  
    } 


    /**将16进制转换为二进制 
     * @param hexStr 
     * @return 
     */  
    public byte[] parseHexStr2Byte(String hexStr) {  
            if (hexStr.length() < 1)  
                    return null;  
            byte[] result = new byte[hexStr.length()/2];  
            for (int i = 0;i< hexStr.length()/2; i++) {  
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                    result[i] = (byte) (high * 16 + low);  
            }  
            return result;  
    }  
//
//    /**
//     * 主函数测试
//     * @param args
//     */
//    public static void main(String[] args){
//
//        Scanner scanner=new Scanner(System.in);
//        System.out.println("输入明文：");
//        String content=scanner.nextLine();
//        String password = "12345678";  
//        //加密  
//        System.out.println("加密前：" + content);  
//        byte[] encryptResult = encrypt(content, password);  
//        String encryptResultStr = parseByte2HexStr(encryptResult);  
//        System.out.println("加密后：" + encryptResultStr);  
//        //解密  
//        byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);  
//        byte[] decryptResult = decrypt(decryptFrom,password);  
//        System.out.println("解密后：" + new String(decryptResult));  
//    }
}

