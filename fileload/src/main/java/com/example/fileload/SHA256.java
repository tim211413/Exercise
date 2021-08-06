package com.example.fileload;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public SHA256() {
        storeString = "";
    }
    String storeString = "";
    static byte[] bt;
    /**
     * SHA加密
     *
     * @param strSrc
     *            明文
     * @return 加密之後的密文
     */
    public static String shaEncrypt(String strSrc) {

        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");// 將此換成SHA-1、SHA-512、SHA-384等參數
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public void setSrc(String strSrc) {
        storeString += strSrc;
    }

    public long getbtSize() {
        return bt.length;
    }
    public String shaEncryptFirst() {

        MessageDigest md = null;
        String strDes = null;
        bt = storeString.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");// 將此換成SHA-1、SHA-512、SHA-384等參數
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * byte數組轉換為16進制字符串
     *
     * @param bts
     *            數據源
     * @return 16進制字符串
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
