package com.homyit.util;
/**
 * ����
 * @param content ��Ҫ���ܵ����� 
 * @param password  �������� 
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
                    KeyGenerator kgen = KeyGenerator.getInstance("AES");  //��������ָ���㷨��������Կ�� KeyGenerator ����
                    kgen.init(128, new SecureRandom(password.getBytes()));  //ʹ���û��ṩ�����Դ��ʼ������Կ��������ʹ�����ȷ������Կ��С��
                    SecretKey secretKey = kgen.generateKey();  //����һ����Կ
                    byte[] enCodeFormat = secretKey.getEncoded();  //���ػ��������ʽ����Կ���������Կ��֧�ֱ��룬�򷵻� null
                    SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
                    Cipher cipher = Cipher.getInstance("AES");// ����������  
                    byte[] byteContent = content.getBytes("utf-8");  
                    cipher.init(Cipher.ENCRYPT_MODE, key);// ����Կ��ʼ��
                    byte[] result = cipher.doFinal(byteContent);  //�������ֲ������ܻ�������ݣ����߽���һ���ಿ�ֲ���
                    return result; // ����  
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


    /**���� 
     * @param content  ���������� 
     * @param password ������Կ 
     * @return 
     */  
    public byte[] decrypt(byte[] content, String password) {  
            try {  
                     KeyGenerator kgen = KeyGenerator.getInstance("AES");  
                     kgen.init(128, new SecureRandom(password.getBytes()));  
                     SecretKey secretKey = kgen.generateKey();  
                     byte[] enCodeFormat = secretKey.getEncoded();  
                     SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
                     Cipher cipher = Cipher.getInstance("AES");// ����������  
                    cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��  
                    byte[] result = cipher.doFinal(content);  
                    return result; // ����  
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

    /**��������ת����16���� 
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


    /**��16����ת��Ϊ������ 
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
//     * ����������
//     * @param args
//     */
//    public static void main(String[] args){
//
//        Scanner scanner=new Scanner(System.in);
//        System.out.println("�������ģ�");
//        String content=scanner.nextLine();
//        String password = "12345678";  
//        //����  
//        System.out.println("����ǰ��" + content);  
//        byte[] encryptResult = encrypt(content, password);  
//        String encryptResultStr = parseByte2HexStr(encryptResult);  
//        System.out.println("���ܺ�" + encryptResultStr);  
//        //����  
//        byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);  
//        byte[] decryptResult = decrypt(decryptFrom,password);  
//        System.out.println("���ܺ�" + new String(decryptResult));  
//    }
}

