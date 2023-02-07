/*
Copyright [2020] [https://www.xiaonuo.vip]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Snowy采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：

1.请不要删除和修改根目录下的LICENSE文件。
2.请不要删除和修改Snowy源码头部的版权声明。
3.请保留源码和相关描述文件的项目出处，作者声明等。
4.分发源码时候，请注明软件出处 https://gitee.com/xiaonuobase/snowy
5.在修改包名，模块名称，项目代码等时，请注明软件出处 https://gitee.com/xiaonuobase/snowy
6.若您的项目无法满足以上几点，可申请商业授权，获取Snowy商业授权许可，请在官网购买授权，地址为 https://www.xiaonuo.vip
 */
package com.flipped.mall.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * 加密工具类
 * 非对称加密
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
public class CryptogramUtil {


    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";


    public static Map<String, String> createKeys(int keySize) throws Exception {
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        // 初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        // 生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();

        // 得到公钥
        PublicKey publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        // 得到私钥
        PrivateKey privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());


        // 保存密钥到文件
        File publicKeyFile = new File("public.key");
        savePublicKey2File(publicKey, publicKeyFile);

        File privateKeyFile = new File("private.key");
        savePrivateKey2File(privateKey, privateKeyFile);


        Map<String, String> keyPairMap = new HashMap<>(4);
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);
        keyPairMap.put("publicKeyPath", publicKeyFile.getPath());
        keyPairMap.put("privateKeyPath", privateKeyFile.getPath());
        return keyPairMap;
    }


    /**
     * 提取公钥的比特编码经过Base64转换后保存到文件，注意公钥的比特编码是X.509格式
     *
     * @param publicKey  公钥
     * @param outPutFile 文件
     */
    public static void savePublicKey2File(PublicKey publicKey, File outPutFile) {
        byte[] publicKeyByte = Base64.encodeBase64(publicKey.getEncoded());

        try (FileOutputStream fos = new FileOutputStream(outPutFile)) {
            fos.write(publicKeyByte);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提取私钥的比特编码经过Base64转换后保存到文件，注意私钥的比特编码是pkcs8格式
     *
     * @param privateKey 私钥
     * @param outPutFile 文件
     */
    public static void savePrivateKey2File(PrivateKey privateKey, File outPutFile) {
        byte[] privateKeyByte = Base64.encodeBase64(privateKey.getEncoded());
        try (FileOutputStream fos = new FileOutputStream(outPutFile)) {
            fos.write(privateKeyByte);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 从文件中获取公钥（公钥的比特编码是X.509格式）
     *
     * @param certPath 公钥路径
     * @return 公钥
     */
    public static RSAPublicKey getPublicKeyByFile(String certPath) {
        try (FileInputStream fis = new FileInputStream(certPath)) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }

            byte[] publicKeyByte = Base64.decodeBase64(bos.toByteArray());
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyByte);
            return (RSAPublicKey) KeyFactory.getInstance(RSA_ALGORITHM).generatePublic(x509EncodedKeySpec);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从文件中获取私钥（私钥的比特编码是pkcs8格式）
     *
     * @param keyPath 私钥路径
     * @return 私钥
     */
    public static RSAPrivateKey getPrivateKeyByFile(String keyPath) {
        try (FileInputStream fis = new FileInputStream(keyPath)) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }

            byte[] privateKeyByte = Base64.decodeBase64(bos.toByteArray());
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
            return (RSAPrivateKey) KeyFactory.getInstance(RSA_ALGORITHM).generatePrivate(pkcs8EncodedKeySpec);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 得到公钥
     *
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws NoSuchAlgorithmException, InvalidKeySpecException
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        return (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
    }

    /**
     * 得到私钥
     *
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws NoSuchAlgorithmException, InvalidKeySpecException
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
    }

    /**
     * 公钥加密
     *
     * @param data      数据
     * @param publicKey 公钥
     * @return 加密后的数据
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     *
     * @param data       加密后的数据
     * @param privateKey 私钥
     * @return 解密后的数据
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     *
     * @param data       数据
     * @param privateKey 私钥
     * @return 加密后的数据
     */

    public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     *
     * @param data      加密后的数据
     * @param publicKey 公钥
     * @return 解密后的数据
     */

    public static String publicDecrypt(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int decryptMode, byte[] data, int keySize) {
        int maxBlock;
        if (decryptMode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int offSet = 0;
            byte[] buff;
            int i = 0;
            while (data.length > offSet) {
                if (data.length - offSet > maxBlock) {
                    buff = cipher.doFinal(data, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(data, offSet, data.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> keyMap = CryptogramUtil.createKeys(1024);
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        String publicKeyPath = keyMap.get("publicKeyPath");
        String privateKeyPath = keyMap.get("privateKeyPath");
        log.info("公钥{}", publicKey);
        log.info("私钥{}", privateKey);
        log.info("公钥文件路径{}", publicKeyPath);
        log.info("私钥文件路径{}", privateKeyPath);

        String str = "ladfhikhifja";
        log.info("明文{}", str);

        // 公钥加密
        String encodedData = CryptogramUtil.publicEncrypt(str, CryptogramUtil.getPublicKey(publicKey));
        log.info("密文{}", encodedData);
        // 私钥解密
        String decodedData = CryptogramUtil.privateDecrypt(encodedData, CryptogramUtil.getPrivateKey(privateKey));
        log.info("解密后文字{}", decodedData);


        // 从文件中获取公钥密钥加密解密
        String encodedFileData = CryptogramUtil.publicEncrypt(str, CryptogramUtil.getPublicKeyByFile(publicKeyPath));
        log.info("密文{}", encodedFileData);
        // 私钥解密
        String decodedFileData = CryptogramUtil.privateDecrypt(encodedFileData, CryptogramUtil.getPrivateKeyByFile(privateKeyPath));
        log.info("解密后文字{}", decodedFileData);

    }


}
