package com.komandor.komandor.utils;


import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.util.Base64;

import com.komandor.komandor.data.api.model.request.SecretKeyRequestModel;
import com.komandor.komandor.data.database.certificates.Certificate;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.data.model.DecryptedSessionKey;
import com.komandor.komandor.data.model.EncryptedFile;
import com.komandor.komandor.data.model.EncryptedMessage;
import com.komandor.komandor.data.model.EncryptedSessionKey;
import com.objsys.asn1j.runtime.Asn1BerDecodeBuffer;
import com.objsys.asn1j.runtime.Asn1BerEncodeBuffer;
import com.objsys.asn1j.runtime.Asn1Null;
import com.objsys.asn1j.runtime.Asn1ObjectIdentifier;
import com.objsys.asn1j.runtime.Asn1OctetString;
import com.objsys.asn1j.runtime.Asn1Type;
import com.objsys.asn1j.runtime.Asn1UTCTime;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.DigestInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import ru.CryptoPro.AdES.AdESConfig;
import ru.CryptoPro.AdES.tools.AlgorithmUtility;
import ru.CryptoPro.JCP.ASN.CertificateExtensions.GeneralName;
import ru.CryptoPro.JCP.ASN.CertificateExtensions.GeneralNames;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.CMSVersion;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.CertificateChoices;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.CertificateSet;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.ContentInfo;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.DigestAlgorithmIdentifier;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.DigestAlgorithmIdentifiers;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.EncapsulatedContentInfo;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.IssuerAndSerialNumber;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.SignatureAlgorithmIdentifier;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.SignatureValue;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.SignedData;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.SignerIdentifier;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.SignerInfo;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.SignerInfos;
import ru.CryptoPro.JCP.ASN.Gost28147_89_EncryptionSyntax.Gost28147_89_EncryptedKey;
import ru.CryptoPro.JCP.ASN.Gost28147_89_EncryptionSyntax.Gost28147_89_Key;
import ru.CryptoPro.JCP.ASN.Gost28147_89_EncryptionSyntax.Gost28147_89_MAC;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.ALL_PKIX1Explicit88Values;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.AlgorithmIdentifier;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Attribute;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.CertHash;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.CertificateSerialNumber;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.ESSCertIDv2;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.IssuerSerial;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Name;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.SigningCertificateV2;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Time;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88._SeqOfESSCertIDv2;
import ru.CryptoPro.JCP.JCP;
import ru.CryptoPro.JCP.KeyStore.JCPPrivateKeyEntry;
import ru.CryptoPro.JCP.params.CryptParamsSpec;
import ru.CryptoPro.JCP.params.JCPProtectionParameter;
import ru.CryptoPro.JCP.params.OID;
import ru.CryptoPro.JCP.spec.GostCipherSpec;
import ru.CryptoPro.JCP.tools.Array;
import ru.CryptoPro.JCSP.CSPConfig;
import ru.CryptoPro.JCSP.JCSP;
import ru.CryptoPro.reprov.RevCheck;
import ru.CryptoPro.ssl.android.util.cpSSLConfig;
import ru.cprocsp.ACSP.tools.common.Infrastructure;

public class CryptoUtils {
  public static final String STORE_TYPE = JCSP.HD_STORE_NAME;
  public static final String PROVIDER = JCSP.PROVIDER_NAME;
  
  private static CryptoUtils instance;
  private static String cryptoProPath;
  private static String keysFolderPath;
  private static String defaultContainersStoragePath;
  
  private static final int RND_LENGTH = 8;
  private static final String RANDOM_ALG = "CPRandom";
  private static final String CIPHER_ALG = "GOST28147";
  private static final String CIPHER_KEY_ALG_PARAMS = "/SIMPLE_EXPORT/NoPadding";
  private static final String CIPHER_DATA_ALG_PARAMS = "/CFB/NoPadding";
  
  private static final String CMS_SIGNATURE_ALGORITHM = JCP.GOST_EL_SIGN_NAME;
  
  public static final String STR_CMS_OID_SIGNED = "1.2.840.113549.1.7.2";
  public static final String STR_CMS_OID_DATA = "1.2.840.113549.1.7.1";
  public static final String STR_CMS_OID_CONT_TYP_ATTR = "1.2.840.113549.1.9.3";
  public static final String STR_CMS_OID_DIGEST_ATTR = "1.2.840.113549.1.9.4";
  public static final String STR_CMS_OID_SIGN_TYM_ATTR = "1.2.840.113549.1.9.5";
  
  public static final String DIGEST_OID = JCP.GOST_DIGEST_OID;
  public static final String SIGN_OID = JCP.GOST_EL_KEY_OID;
  
  public static final String DIGEST_OID_2012_256 = JCP.GOST_DIGEST_2012_256_OID;
  public static final String SIGN_OID_2012_256 = JCP.GOST_PARAMS_SIG_2012_256_KEY_OID;
  
  public static void initCryptoPro(Context context) {
    CSPConfig.init(context);
    initJavaProviders(context);
  }
  
  /*
  -----------------------------------------------------------------------------------------------
  Работа с данными
  -----------------------------------------------------------------------------------------------
  */
  
  public static String sign(PrivateKey pk, byte[] data) {
    Signature signature;
    try {
      signature = Signature.getInstance(JCP.CRYPTOPRO_SIGN_NAME, JCSP.PROVIDER_NAME);
      signature.initSign(pk);
      signature.update(data);
      
      return SystemUtils.encodeBase64(signature.sign());
    } catch (Exception e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
      
      return null;
    }
  }

  public static boolean verifySign(byte[] data, byte[] bSignature, X509Certificate certificate) {
    boolean result = false;
    try {
      Signature signature = Signature.getInstance(JCP.CRYPTOPRO_SIGN_NAME, JCSP.PROVIDER_NAME);
      signature.initVerify(certificate);

      signature.update(data);
      result = signature.verify(bSignature);
    } catch (Exception e) {
      new KomandorException(e).printStackTrace();
    }
    return result;
  }

  public static boolean verifyCMS(byte[] data, byte[] cms, X509Certificate certificate) {
    try {
      final Asn1BerDecodeBuffer asnBuf = new Asn1BerDecodeBuffer(cms);
      final ContentInfo all = new ContentInfo();
      all.decode(asnBuf);

      final SignedData signedData = (SignedData) all.content;
      if (signedData.encapContentInfo.eContent != null) {
        data = signedData.encapContentInfo.eContent.value;
      }

      OID digestOid = null;

      final DigestAlgorithmIdentifier digestAlgorithmIdentifier =
          new DigestAlgorithmIdentifier(
              new OID(DIGEST_OID).value);

      for (int i = 0; i < signedData.digestAlgorithms.elements.length; i++) {
        if (signedData.digestAlgorithms.elements[i].algorithm
            .equals(digestAlgorithmIdentifier.algorithm)) {
          digestOid = new OID(signedData.digestAlgorithms
              .elements[i].algorithm.value);
          break;
        } // if
      } // for

      final OID eContTypeOID = new OID(signedData.encapContentInfo.eContentType.value);
      if (signedData.certificates != null) {

        // Проверка на вложенных сертификатах.

        // Validation on certificates founded in the signature

        for (int i = 0; i < signedData.certificates.elements.length; i++) {

          final Asn1BerEncodeBuffer encBuf = new Asn1BerEncodeBuffer();
          signedData.certificates.elements[i].encode(encBuf);

          final CertificateFactory cf = CertificateFactory.getInstance("X.509");
          final X509Certificate cert = (X509Certificate) cf
              .generateCertificate(encBuf.getInputStream());

          for (int j = 0; j < signedData.signerInfos.elements.length; j++) {

            // Verify signer info

            final SignerInfo info = signedData.signerInfos.elements[j];
            if (!digestOid.equals(new OID(info.digestAlgorithm.algorithm.value))) {
              throw new Exception("It isn't signed on certificate.");
            } // if

            return verifyOnCert(cert, signedData.signerInfos.elements[j], data, eContTypeOID, true);

          }
        }

      } else if (certificate != null) {

        // Проверка на указанных сертификатах.

        // Certificates for validation not found in the signature. Try verify on specified certificates...

        for (int j = 0; j < signedData.signerInfos.elements.length; j++) {

          // Verify signer info

          final SignerInfo info = signedData.signerInfos.elements[j];
          if (!digestOid.equals(new OID(info.digestAlgorithm.algorithm.value))) {
            throw new Exception("It isn't signed on certificate.");
          }

          return verifyOnCert(certificate, signedData.signerInfos.elements[j], data, eContTypeOID, true);
        }
      }
    } catch (Exception e) {
      new KomandorException(e).printStackTrace();
    }
    return false;
  }

  public static String createCMSSign(byte[] data, java.security.cert.Certificate[] certs, PrivateKey[] keys, boolean detached) throws KomandorException{
    try {
      final ContentInfo all = new ContentInfo();
      all.contentType = new Asn1ObjectIdentifier(
          new OID(STR_CMS_OID_SIGNED).value);

      final SignedData cms = new SignedData();
      all.content = cms;
      cms.version = new CMSVersion(1);

      cms.digestAlgorithms = new DigestAlgorithmIdentifiers(1);
      final DigestAlgorithmIdentifier a = new DigestAlgorithmIdentifier(
          new OID(DIGEST_OID).value);
      a.parameters = new Asn1Null();
      cms.digestAlgorithms.elements[0] = a;

      if (detached) {
        // Открепленная подпись
        cms.encapContentInfo = new EncapsulatedContentInfo(
            new Asn1ObjectIdentifier(
                new OID(STR_CMS_OID_DATA).value), null);
      } else {
        // Прикрепленная подпись
        cms.encapContentInfo = new EncapsulatedContentInfo(
            new Asn1ObjectIdentifier(new OID(STR_CMS_OID_DATA).value),
            new Asn1OctetString(data));
      }

      // Сертификаты.
      // Enumerate certificates

      final int nCerts = certs.length;
      cms.certificates = new CertificateSet(nCerts);
      cms.certificates.elements = new CertificateChoices[nCerts];

      for (int i = 0; i < cms.certificates.elements.length; i++) {

        final ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Certificate certificate =
            new ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Certificate();

        final Asn1BerDecodeBuffer decodeBuffer =
            new Asn1BerDecodeBuffer(certs[i].getEncoded());

        certificate.decode(decodeBuffer);
        cms.certificates.elements[i] = new CertificateChoices();
        cms.certificates.elements[i].set_certificate(certificate);

      }

      final Signature signature = Signature.getInstance(CMS_SIGNATURE_ALGORITHM, JCSP.PROVIDER_NAME);

      byte[] sign;

      // Подписанты (те кто подписывает).

      final int nSigners = keys.length;
      cms.signerInfos = new SignerInfos(nSigners);
      for (int i = 0; i < cms.signerInfos.elements.length; i++) {

        // Create signer info

        cms.signerInfos.elements[i] = new SignerInfo();
        cms.signerInfos.elements[i].version = new CMSVersion(1);
        cms.signerInfos.elements[i].sid = new SignerIdentifier();

        // Add certificate info

        final byte[] encodedName = ((X509Certificate) certs[i])
            .getIssuerX500Principal().getEncoded();

        final Asn1BerDecodeBuffer nameBuf =
            new Asn1BerDecodeBuffer(encodedName);

        final Name name = new Name();
        name.decode(nameBuf);

        final CertificateSerialNumber num = new CertificateSerialNumber(
            ((X509Certificate) certs[i]).getSerialNumber());
        cms.signerInfos.elements[i].sid.set_issuerAndSerialNumber(
            new IssuerAndSerialNumber(name, num));

        cms.signerInfos.elements[i].digestAlgorithm =
            new DigestAlgorithmIdentifier(
                new OID(DIGEST_OID).value);

        cms.signerInfos.elements[i].digestAlgorithm.parameters = new Asn1Null();

        String keyAlgOid = AlgorithmUtility.keyAlgorithmName2KeyAlgorithmOid(
            keys[0].getAlgorithm());

        cms.signerInfos.elements[i].signatureAlgorithm =
            new SignatureAlgorithmIdentifier(new OID(keyAlgOid).value);

        cms.signerInfos.elements[i].signatureAlgorithm.parameters = new Asn1Null();

        signature.initSign(keys[i]);
        signature.update(data);
        sign = signature.sign();

        cms.signerInfos.elements[i].signature = new SignatureValue(sign);

      }

      final Asn1BerEncodeBuffer asnBuf = new Asn1BerEncodeBuffer();
      all.encode(asnBuf, true);

      return SystemUtils.encodeBase64(asnBuf.getMsgCopy());
    } catch (Exception e) {
      throw new KomandorException(e);
    }
  }
  
  public static List<SecretKeyRequestModel> createSessionKeys(PrivateKey pk, List<Certificate> certificates) throws KomandorException {
    List<SecretKeyRequestModel> keys = new ArrayList<>();
    try {
      /* Генерирование симметричного ключа с параметрами шифрования из контрольной панели*/
      final KeyGenerator keyGen = KeyGenerator.getInstance(CIPHER_ALG, JCSP.PROVIDER_NAME);
      final SecretKey simm = keyGen.generateKey();
      
      final byte[] iv = new byte[RND_LENGTH];
      final SecureRandom random = SecureRandom.getInstance(RANDOM_ALG, JCSP.PROVIDER_NAME);
      random.nextBytes(iv);
      final byte[] ivLength = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(iv.length).array();
      
      Cipher cipher = Cipher.getInstance(CIPHER_ALG + CIPHER_KEY_ALG_PARAMS, JCSP.PROVIDER_NAME);
      
      for (Certificate certificate : certificates) {
        X509Certificate cert = decodeBase64Certificate(certificate.getBase64());
        
        /* Генерирование начальной синхропосылки для выработки ключа согласования*/
        final byte[] sv = new byte[RND_LENGTH];
        random.nextBytes(sv);
        
        /* Выработка ключа согласования c SV*/
        SecretKey senderAgree = generateKeyAgreement(pk, cert, sv);
        
        /*Зашифрование симметричного ключа на ключе согласования*/
        
        cipher.init(Cipher.WRAP_MODE, senderAgree, new IvParameterSpec(sv));
        final byte[] wrappedKey = cipher.wrap(simm);
        
        byte[] simpleKeyBLOBHeader = {
            0x01,
            0x20,
            0x00, 0x00,
            0x1e, 0x66, 0x00, 0x00,
            (byte) 0xfd, 0x51, 0x4a, 0x37,
            0x1e, 0x66, 0x00, 0x00,
        };
        
        final Asn1BerDecodeBuffer buf = new Asn1BerDecodeBuffer(wrappedKey);
        final Gost28147_89_EncryptedKey ek = new Gost28147_89_EncryptedKey();
        
        ek.decode(buf);
        byte[] encryptedKey = ek.encryptedKey.value;
        byte[] macKey = ek.macKey.value;
        
        byte[] encryptedParams = {
            0x30, 0x0B, 0x06, 0x09, 0x2A, (byte) 0x85, 0x03, 0x07, 0x01, 0x02, 0x05, 0x01, 0x01
        };
        
        int blobLength = simpleKeyBLOBHeader.length + sv.length + encryptedKey.length + macKey.length + encryptedParams.length;
        final byte[] bBlobLength = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(blobLength).array();
        
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(bBlobLength);
        baos.write(simpleKeyBLOBHeader);
        baos.write(sv);
        baos.write(encryptedKey);
        baos.write(macKey);
        baos.write(encryptedParams);
        baos.write(ivLength);
        baos.write(iv);
        
        byte[] sessionKey = baos.toByteArray();
        
        SecretKeyRequestModel key = new SecretKeyRequestModel(certificate.getPid(), certificate.getCid(), sessionKey);
        keys.add(key);
      }
    } catch (Exception e) {
      throw new KomandorException(e);
    }
    
    return keys;
  }
  
  public static DecryptedSessionKey decryptSessionKey(PrivateKey pk, EncryptedSessionKey encryptedSessionKey) throws KomandorException {
    SecretKey key_ = null;
    byte[] iv = null;
    try {
      int sizeLength = 4;
      byte[] bKey = Base64.decode(encryptedSessionKey.getEncryptedKey(), Base64.NO_WRAP);
      
      // Разбираем Simple Key BLOB
      // byte[] bSimpleBlob = {
      //    // Начало Header (16 байт)
      //    0x01, // bType = SIMPLEBLOB
      //    0x20, // bVersion = 0x20
      //    0x00, 0x00, // reserved
      //    0x23, 0x2e, 0x00, 0x00, // KeyAlg = ALG_SID_GR3410EL
      //    0x4d, 0x41, 0x47, 0x31, // Magic = GR3410_1_MAGIC
      //    0x1e, 0x66, 0x00, 0x00, // EncryptKeyAlgId = CALG_G28147
      //    // Конец Header
      //
      //    0x76, 0xee, 0xb4, 0x6b, 0x1b, 0x10, 0x36, 0xeb, // bUKM (8 байт)
      //    // pbEncryptedKey (32 байта)
      //    0x5e, 0x70, 0x73, 0x5f, 0x36, 0x98, 0xb4, 0x35,
      //    0x5b, 0x45, 0x03, 0x7f, 0xa7, 0xce, 0x00, 0x97,
      //    0x11, 0x5e, 0x45, 0xc6, 0x58, 0x59, 0x94, 0x72,
      //    0x66, 0x42, 0x06, 0x3f, 0x72, 0x3a, 0xb4, 0x9e,
      //    0x8c, 0x86, 0x08, 0x84, // pbMacKey (4 байта)
      //    0x30, 0x09, 0x06, 0x07, 0x2a, 0x85, 0x03, 0x02, 0x02, 0x1f, 0x01 // bEncryptionParamSet (13 байт)
      //  };
      
      byte[] bBlobLength = SystemUtils.reverseByteArray(Arrays.copyOfRange(bKey, 0, sizeLength));
      int blobLength = ByteBuffer.wrap(bBlobLength).getInt();
      
      byte[] bSimpleBlob = Arrays.copyOfRange(bKey, sizeLength, sizeLength + blobLength);
      byte[] bBlobHeader = Arrays.copyOfRange(bSimpleBlob, 0, 16);
      byte[] sv = Arrays.copyOfRange(bSimpleBlob, 16, 24);
      byte[] bEncryptedKey = Arrays.copyOfRange(bSimpleBlob, 24, 24 + 32);
      byte[] bMacKey = Arrays.copyOfRange(bSimpleBlob, 56, 56 + 4);
      byte[] bEncryptedParams = Arrays.copyOfRange(bSimpleBlob, 60, blobLength);
      
      // Получаем IV вектор
      int IVStart = sizeLength + blobLength;
      byte[] bIVLength = SystemUtils.reverseByteArray(Arrays.copyOfRange(bKey, IVStart, IVStart + sizeLength));
      int IVLength = ByteBuffer.wrap(bIVLength).getInt();
      iv = Arrays.copyOfRange(bKey, IVStart + sizeLength, IVStart + sizeLength + IVLength);
      
      // Получаем зашифрованный ключ
      final Gost28147_89_EncryptedKey ek = new Gost28147_89_EncryptedKey();
      ek.encryptedKey = new Gost28147_89_Key(bEncryptedKey);
      ek.macKey = new Gost28147_89_MAC(bMacKey);
      final Asn1BerEncodeBuffer ebuf = new Asn1BerEncodeBuffer();
      ek.encode(ebuf);
      final byte[] wrap = ebuf.getMsgCopy();
      
      // Генерируем ключ согласования
      SecretKey responderAgree = generateKeyAgreement(pk, encryptedSessionKey.getCertificate(), sv);
      
      if (responderAgree == null) {
        throw new KomandorException("Key agreement is null");
      }
      
      // Расшифровываем ключ
      Cipher cipher = Cipher.getInstance(CIPHER_ALG + CIPHER_KEY_ALG_PARAMS);
      cipher.init(Cipher.UNWRAP_MODE, responderAgree);
      key_ = (SecretKey) cipher.unwrap(wrap, null, Cipher.SECRET_KEY);
    } catch (Exception e) {
      throw new KomandorException(e);
    }
    return new DecryptedSessionKey(key_, iv);
  }
  
  private static byte[] cipherData(byte[] data, DecryptedSessionKey sessionKey) throws KomandorException {
    GostCipherSpec ss = getCipherSpec(sessionKey.getIV());
    try {
      Cipher cipher = Cipher.getInstance(CIPHER_ALG + CIPHER_DATA_ALG_PARAMS, JCSP.PROVIDER_NAME);
      cipher.init(Cipher.ENCRYPT_MODE, sessionKey.getKey(), ss, null);
      
      return cipher.doFinal(data);
    } catch (Exception e) {
      throw new KomandorException(e);
    }
  }
  
  private static byte[] decipherData(byte[] data, DecryptedSessionKey sessionKey)throws KomandorException {
    GostCipherSpec ss = getCipherSpec(sessionKey.getIV());
    try {
      Cipher cipher = Cipher.getInstance(CIPHER_ALG + CIPHER_DATA_ALG_PARAMS, JCSP.PROVIDER_NAME);
      cipher.init(Cipher.DECRYPT_MODE, sessionKey.getKey(), ss, null);
      
      return cipher.doFinal(data);
    } catch (Exception e) {
      throw new KomandorException(e);
    }
  }

  public static EncryptedMessage encryptMessage(PrivateKey pk, int cid, String message, EncryptedSessionKey encryptedSessionKey) {
    DecryptedSessionKey sessionKey = decryptSessionKey(pk, encryptedSessionKey);
    return encryptMessage(pk, cid, message, sessionKey);
  }

  public static EncryptedMessage encryptMessage(PrivateKey pk, int cid, String message, DecryptedSessionKey sessionKey) {
    try {
      String encryptedMessage;
      String signature;

      byte[] bEncryptedMessage = cipherData(message.getBytes(), sessionKey);
      encryptedMessage = SystemUtils.encodeBase64(bEncryptedMessage);

      signature = sign(pk, message.getBytes());


      return new EncryptedMessage(cid, encryptedMessage, signature);
    } catch (KomandorException e) {
      e.printStackTrace();
    }

    return null;
  }
  
  public static String decryptMessage(PrivateKey pk, Message encryptedMessage, DecryptedSessionKey sessionKey) {
    String decryptedMessage = "";
    try {
      String message = encryptedMessage.getData();
    
      if (sessionKey.getKey() == null) {
        throw new KomandorException("CRYPTO", "Secret key is null");
      }
    
      byte[] bDecodedMessage = SystemUtils.decodeBase64(message);
      byte[] bDecr = decipherData(bDecodedMessage, sessionKey);
    
      decryptedMessage = new String(bDecr);
    } catch (KomandorException e) {
      e.printStackTrace();
    }
  
    return decryptedMessage;
  }

  public static EncryptedFile encryptFile(String filePath, int cid, X509Certificate certificate, PrivateKey privateKey,  EncryptedSessionKey encryptedSessionKey) {
    try {
      File file = new File(filePath);
      byte[] data = Array.readFile(file);

      java.security.cert.Certificate[] certs = new java.security.cert.Certificate[]{certificate};
      PrivateKey[] keys = new PrivateKey[]{privateKey};

      String encodedCMS = createCMSSign(data, certs, keys, true);

      DecryptedSessionKey sessionKey = decryptSessionKey(privateKey, encryptedSessionKey);

      EncryptedMessage encryptedName = encryptMessage(privateKey, cid, file.getName(), sessionKey);

      String encryptedFile = SystemUtils.encodeBase64(cipherData(data, sessionKey));

      return new EncryptedFile(cid, encryptedName.getMessage(), encryptedName.getSign(), encryptedFile, encodedCMS);
    } catch (Exception e) {
      new KomandorException(e).printStackTrace();
    }

    return null;
  }

  public static byte[] decryptFile(String encryptedFile, PrivateKey privateKey, DecryptedSessionKey sessionKey) {
    try {
      byte[] bEncryptedData = SystemUtils.decodeBase64(encryptedFile);
      return decipherData(bEncryptedData, sessionKey);
    } catch (Exception e) {
      new KomandorException(e).printStackTrace();
    }

    return null;
  }
  
  public static String decryptString(PrivateKey pk, String encryptedMessage, DecryptedSessionKey sessionKey) {
    String decryptedMessage = "";
    try {
      if (sessionKey.getKey() == null) {
        throw new KomandorException("CRYPTO", "Secret key is null");
      }
      
      byte[] bDecodedMessage = SystemUtils.decodeBase64(encryptedMessage);
      byte[] bDecr = decipherData(bDecodedMessage, sessionKey);
      
      decryptedMessage = new String(bDecr);
    } catch (KomandorException e) {
      e.printStackTrace();
    }
    
    return decryptedMessage;
  }

//  public static DecryptedFile decryptFile(EncryptedFile encryptedFile, EncryptedSessionKey encryptedSessionKey) {
//    try {
//      DecryptedSessionKey sessionKey = decryptSessionKey(encryptedSessionKey);
//      DecryptedFile decryptedFile = decryptFileName(encryptedFile, encryptedSessionKey);
//
//      byte[] bEncryptedData = decodeBase64(encryptedFile.getEncryptedData());
//      byte[] bFileData = decipherData(bEncryptedData, sessionKey);
//      String data = new String(bFileData);
//
//      File file = SystemUtils.saveFile(decryptedFile.getDecryptedName(), data);
//
//      assert file != null;
//      return new DecryptedFile(file.getName(), encryptedFile.getNameSign(), file.getPath(), encryptedFile.getCms(), encryptedFile.getCid());
//    } catch (Exception e) {
//      e.printStackTrace();
//      Crashlytics.logException(e);
//    }
//
//    return null;
//  }
  
  /*
  -----------------------------------------------------------------------------------------------
  Работа с сертификатами
  -----------------------------------------------------------------------------------------------
  */
  
  public static Map<String, X509Certificate> getLocalCertificates() {
    Map<String, X509Certificate> certificates = new HashMap<>();
    
    try {
      KeyStore keyStore = getKeyStore();
      
      Enumeration<String> aliases = keyStore.aliases();
      
      while (aliases.hasMoreElements()) {
        String alias = aliases.nextElement();
        X509Certificate certificate = (X509Certificate) keyStore.getCertificate(alias);
        String clientName = getClientName(certificate);
        
        if (clientName != null) {
          certificates.put(alias, certificate);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
    }
    
    return certificates;
  }
  
  public static Boolean hasCertificates() {
    try {
      return getKeyStore().aliases().hasMoreElements();
    } catch (Exception e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
    }
    
    return false;
  }
  
  public static X509Certificate getCertificateByAlias(String alias) {
    try {
      KeyStore keyStore = getKeyStore();
      return (X509Certificate) keyStore.getCertificate(alias);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String signCertificate(PrivateKey pk, X509Certificate certificate) {
    try {
      return sign(pk, certificate.getEncoded());
    } catch (Exception e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
      
      return null;
    }
  }
  
  public static String getClientName(X509Certificate certificate) {
    if (certificate == null) {
      return null;
    }
    
    X500Name x500Name = null;
    try {
      x500Name = new JcaX509CertificateHolder(certificate).getSubject();
    } catch (Exception e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
    }
    
    assert x500Name != null;
    RDN surnameRDN = x500Name.getRDNs(BCStyle.SURNAME)[0];
    RDN givenNameRDN = x500Name.getRDNs(BCStyle.GIVENNAME)[0];
    
    String surname = IETFUtils.valueToString(surnameRDN.getFirst().getValue());
    String givenName = IETFUtils.valueToString(givenNameRDN.getFirst().getValue());
    
    return surname + " " + givenName;
  }
  
  public static String getCompanyName(X509Certificate certificate) {
    if (certificate == null) {
      return "";
    }
    
    X500Name x500Name = null;
    try {
      x500Name = new JcaX509CertificateHolder(certificate).getSubject();
    } catch (Exception e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
    }
    
    assert x500Name != null;
    RDN[] ogrnRDN = x500Name.getRDNs(new ASN1ObjectIdentifier("1.2.643.100.1"));
    
    if (ogrnRDN.length == 0) {
      return "";
    }
    
    RDN companyNameRDN = x500Name.getRDNs(BCStyle.CN)[0];
    
    String companyName = IETFUtils.valueToString(companyNameRDN.getFirst().getValue());
    companyName = companyName.replace("\"", "").replace("\\", "\"");
    
    return companyName;
  }
  
  public static X509Certificate decodeBase64Certificate(String encodedCertificateString) {
    X509Certificate certificate = null;
    try {
      if (encodedCertificateString != null) {
        byte[] encodedCert = Base64.decode(encodedCertificateString, Base64.NO_WRAP);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(encodedCert);
        
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        
        certificate = (X509Certificate) certFactory.generateCertificate(inputStream);
      }
    } catch (CertificateException e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
    }
    return certificate;
  }
  
  public static String encodeCertificate(X509Certificate certificate) throws KomandorException {
    try {
      return SystemUtils.encodeBase64(certificate.getEncoded());
    } catch (Exception e) {
      throw new KomandorException(e);
    }
  }

  private static boolean verifyOnCert(X509Certificate cert, SignerInfo info, byte[] data, OID eContentTypeOID, boolean needSortSignedAttributes) throws Exception {
    // Подпись.
    final byte[] sign = info.signature.value;

    if (info.signedAttrs != null) {
      // Присутствуют аттрибуты подписи (Signed Attributes).
      final Attribute[] signAttrElem = info.signedAttrs.elements;

      // Проверка аттрибута signing-certificateV2.
      final Asn1ObjectIdentifier signingCertificateV2Oid = new Asn1ObjectIdentifier(
          (new OID(ALL_PKIX1Explicit88Values.id_aa_signingCertificateV2)).value);
      Attribute signingCertificateV2Attr = null;

      for (Attribute aSignAttrElem : signAttrElem) {
        final Asn1ObjectIdentifier oid = aSignAttrElem.type;
        if (oid.equals(signingCertificateV2Oid)) {
          signingCertificateV2Attr = aSignAttrElem;
          break;
        } // if
      } // for

      if (signingCertificateV2Attr != null) {

        SigningCertificateV2 signingCertificateV2 = (SigningCertificateV2)
            signingCertificateV2Attr.values.elements[0];
        _SeqOfESSCertIDv2 essCertIDv2s = signingCertificateV2.certs;

        for (int s = 0; s < essCertIDv2s.elements.length; s++) {

          ESSCertIDv2 essCertIDv2 = essCertIDv2s.elements[s];

          CertHash expectedCertHash = essCertIDv2.certHash;
          AlgorithmIdentifier expectedHashAlgorithm = essCertIDv2.hashAlgorithm;

          IssuerSerial expectedIssuerSerial = essCertIDv2.issuerSerial;
          Asn1BerEncodeBuffer encodedExpectedIssuerSerial = new Asn1BerEncodeBuffer();
          expectedIssuerSerial.encode(encodedExpectedIssuerSerial);

          OID expectedHashAlgorithmOid = new OID(expectedHashAlgorithm.algorithm.value);
          CertHash actualCertHash = new CertHash(digest(cert.getEncoded(), expectedHashAlgorithmOid.toString()));

          ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Certificate certificate =
              new ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Certificate();
          Asn1BerDecodeBuffer decodeBuffer =
              new Asn1BerDecodeBuffer(cert.getEncoded());
          certificate.decode(decodeBuffer);

          GeneralName[] issuerName = new GeneralName[1];
          issuerName[0] = new GeneralName(GeneralName._DIRECTORYNAME,
              certificate.tbsCertificate.issuer);
          GeneralNames issuerNames = new GeneralNames(issuerName);

          IssuerSerial actualIssuerSerial = new IssuerSerial(issuerNames,
              certificate.tbsCertificate.serialNumber);
          Asn1BerEncodeBuffer encodedActualIssuerSerial = new Asn1BerEncodeBuffer();
          actualIssuerSerial.encode(encodedActualIssuerSerial);

          if (!(Arrays.equals(actualCertHash.value, expectedCertHash.value) &&
              Arrays.equals(encodedActualIssuerSerial.getMsgCopy(),
                  encodedActualIssuerSerial.getMsgCopy()))) {

            // Certificate stored in signing-certificateV2 is not equal to
            return false;
          }
        }
      }

      // Проверка аттрибута content-type.

      final Asn1ObjectIdentifier contentTypeOid =
          new Asn1ObjectIdentifier((new OID(STR_CMS_OID_CONT_TYP_ATTR)).value);
      Attribute contentTypeAttr = null;

      for (int r = 0; r < signAttrElem.length; r++) {
        final Asn1ObjectIdentifier oid = signAttrElem[r].type;
        if (oid.equals(contentTypeOid)) {
          contentTypeAttr = signAttrElem[r];
          break;
        } // if
      } // for

      if (contentTypeAttr == null) {
        throw new Exception("content-type attribute isn't not presented.");
      } // if

      if (!new Asn1ObjectIdentifier(eContentTypeOID.value)
          .equals(contentTypeAttr.values.elements[0])) {
        throw new Exception("content-type attribute OID is not " +
            "equal to eContentType OID.");
      } // if

      // Проверка аттрибута message-digest.

      final Asn1ObjectIdentifier messageDigestOid =
          new Asn1ObjectIdentifier((new OID(STR_CMS_OID_DIGEST_ATTR)).value);

      Attribute messageDigestAttr = null;

      for (int r = 0; r < signAttrElem.length; r++) {
        final Asn1ObjectIdentifier oid = signAttrElem[r].type;
        if (oid.equals(messageDigestOid)) {
          messageDigestAttr = signAttrElem[r];
          break;
        } // if
      } // for

      if (messageDigestAttr == null) {
        throw new Exception("message-digest attribute is not presented.");
      } // if

      final Asn1Type open = messageDigestAttr.values.elements[0];
      final Asn1OctetString hash = (Asn1OctetString) open;
      final byte[] md = hash.value;

      // Вычисление messageDigest.

      final byte[] dm = digest(data, DIGEST_OID);

      if (!Array.toHexString(dm).equals(Array.toHexString(md))) {
        throw new Exception("Verification of message-digest attribute failed.");
      } // if

      // Проверка аттрибута signing-time.

      final Asn1ObjectIdentifier signTimeOid = new Asn1ObjectIdentifier(
          (new OID(STR_CMS_OID_SIGN_TYM_ATTR)).value);

      Attribute signTimeAttr = null;

      for (int r = 0; r < signAttrElem.length; r++) {
        final Asn1ObjectIdentifier oid = signAttrElem[r].type;
        if (oid.equals(signTimeOid)) {
          signTimeAttr = signAttrElem[r];
          break;
        } // if
      } // for

      if (signTimeAttr != null) {
        // Проверка (необязательно).
        Time sigTime = (Time) signTimeAttr.values.elements[0];
        Asn1UTCTime time = (Asn1UTCTime) sigTime.getElement();
      }

      //данные для проверки подписи
      final Asn1BerEncodeBuffer encBufSignedAttr = new Asn1BerEncodeBuffer();
      info.signedAttrs.needSortSignedAttributes = needSortSignedAttributes;
      info.signedAttrs.encode(encBufSignedAttr);

      data = encBufSignedAttr.getMsgCopy();
    }


    // Проверяем подпись.

    Signature signature = Signature.getInstance(CMS_SIGNATURE_ALGORITHM, JCSP.PROVIDER_NAME);

    signature.initVerify(cert);
    signature.update(data);

    boolean verified = signature.verify(sign);

    // Если подпись некорректна, но нас есть подписанные аттрибуты,
    // то пробуем проверить подпись также, отключив сортировку аттрибутов
    // перед кодированием в байтовый массив.

    if (!verified && info.signedAttrs != null && needSortSignedAttributes) {
      return verifyOnCert(cert, info, data, eContentTypeOID, false);
    }

    return verified;
  }

  private static byte[] digest(byte[] bytes, String digestAlgorithmName) throws Exception {

    final ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
    final MessageDigest digest = MessageDigest.getInstance(digestAlgorithmName);
    final DigestInputStream digestStream = new DigestInputStream(stream, digest);

    while (digestStream.available() != 0) {
      digestStream.read();
    }

    return digest.digest();
  }

  /*
  -----------------------------------------------------------------------------------------------
  Работа с контейнерами
  -----------------------------------------------------------------------------------------------
  */
  
  public static void initCryptoProContainers(Context context) {
    initDefaultFolder();
    initContainer(context);
  }
  
  private static void initDefaultFolder() {
    File externalStorageDirectory = Environment.getExternalStorageDirectory();
    defaultContainersStoragePath = externalStorageDirectory.getAbsolutePath() + File.separator + Constants.DEFAULT_CONTAINERS_STORAGE_DIRECTORY;
    File defaultContainersStorageDir = new File(defaultContainersStoragePath);
    
    if (!defaultContainersStorageDir.exists()) {
      defaultContainersStorageDir.mkdir();
    }
  }
  
  private static void initContainer(Context context) {
    
    if (cryptoProPath == null) {
      cryptoProPath = context.getApplicationInfo().dataDir + File.separator + "cprocsp";
    }
    
    
    if (keysFolderPath == null) {
      keysFolderPath = cryptoProPath + File.separator + "keys" + File.separator + Infrastructure.userName2Dir(context);
    }
    
    File keysFolder = new File(keysFolderPath);
    if (keysFolder.exists()) {
      SystemUtils.deleteFile(keysFolder, true);
    }
    
    keysFolder.mkdirs();
  }
  
  public static Boolean hasUserContainers() {
    File defaultContainersStorage = new File(defaultContainersStoragePath);
    File[] containers = defaultContainersStorage.listFiles();
    
    return containers != null && containers.length > 0;
  }
  
  public static void importContainers() throws Exception {
    File[] containers = new File(defaultContainersStoragePath).listFiles();
    
    for (File dir : containers) {
      File[] files = dir.listFiles();
      String dirName = dir.getName();
      File containerFolder = new File(keysFolderPath, dirName);
      containerFolder.mkdir();
      
      for (File file : files) {
        String fileName = file.getName();
        String ext = fileName.substring(fileName.lastIndexOf('.'));
        
        if (ext.equals(".key")) {
          FileInputStream fis = new FileInputStream(file);
          FileOutputStream fos = new FileOutputStream(containerFolder.getAbsolutePath() + File.separator + fileName);
          
          byte[] buffer = new byte[1024];
          int length;
          while ((length = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, length);
          }
          
          fis.close();
          fos.close();
        }
      }
    }
  }
  
  
  /*
  -----------------------------------------------------------------------------------------------
     Работа с хранилищем ключей
  -----------------------------------------------------------------------------------------------
  */
  
  private static KeyStore getKeyStore() throws Exception {
    KeyStore keyStore = KeyStore.getInstance(STORE_TYPE, JCSP.PROVIDER_NAME);
    keyStore.load(null);
    
    return keyStore;
  }
  
  public static PrivateKey loadPrivateKey(String alias, String password) {
    JCPPrivateKeyEntry entry;
    KeyStore.ProtectionParameter params = new JCPProtectionParameter(password.toCharArray(), true);
    
    try {
      KeyStore keyStore = getKeyStore();
      entry = (JCPPrivateKeyEntry) keyStore.getEntry(alias, params);
    } catch (Exception e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
      
      return null;
    }
    
    return entry.getPrivateKey();
  }
  
  private static SecretKey generateKeyAgreement(PrivateKey pk, X509Certificate partnerCert, byte[] sv) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
    final IvParameterSpec ivspec = new IvParameterSpec(sv);
    final KeyAgreement keyAgree = KeyAgreement.getInstance(pk.getAlgorithm(), JCSP.PROVIDER_NAME);
    keyAgree.init(pk, ivspec, null);
    keyAgree.doPhase(partnerCert.getPublicKey(), true);
    return keyAgree.generateSecret(CIPHER_ALG);
  }
  
  private static SecretKey generateKeyAgreement(PrivateKey pk, PublicKey partnerPublicKey, byte[] sv) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
    final IvParameterSpec ivspec = new IvParameterSpec(sv);
    final KeyAgreement keyAgree = KeyAgreement.getInstance(pk.getAlgorithm(), JCSP.PROVIDER_NAME);
    keyAgree.init(pk, ivspec, null);
    keyAgree.doPhase(partnerPublicKey, true);
    return keyAgree.generateSecret(CIPHER_ALG);
  }
  
  private static GostCipherSpec getCipherSpec(byte[] iv) {
    CryptParamsSpec pp = CryptParamsSpec.getInstance(CryptParamsSpec.Rosstandart_TC26_Z);
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
    return new GostCipherSpec(ivParameterSpec, pp);
  }
  
  
  /**
   * Добавление нативного провайдера Java CSP,
   * SSL-провайдера и Revocation-провайдера в
   * список Security. Инициализируется JCPxml,
   * CAdES.
   * <p>
   * Происходит один раз при инициализации.
   * Возможно только после инициализации в CSPConfig!
   */
  private static void initJavaProviders(Context context) {
    
    // Загрузка Java CSP (хеш, подпись, шифрование,
    // генерация контейнеров).
    
    if (Security.getProvider(JCSP.PROVIDER_NAME) == null) {
      Security.addProvider(new JCSP());
    } // if
    
    // Загрузка Java TLS (TLS).
    
    // Необходимо переопределить свойства, чтобы использовались
    // менеджеры из cpSSL, а не Harmony.
    
    Security.setProperty("ssl.KeyManagerFactory.algorithm",
        ru.CryptoPro.ssl.android.Provider.KEYMANGER_ALG);
    Security.setProperty("ssl.TrustManagerFactory.algorithm",
        ru.CryptoPro.ssl.android.Provider.KEYMANGER_ALG);
    
    Security.setProperty("ssl.SocketFactory.provider",
        "ru.CryptoProModule.ssl.android.SSLSocketFactoryImpl");
    Security.setProperty("ssl.ServerSocketFactory.provider",
        "ru.CryptoProModule.ssl.android.SSLServerSocketFactoryImpl");
    
    if (Security.getProvider(ru.CryptoPro.ssl.android.Provider.PROVIDER_NAME) == null) {
      Security.addProvider(new ru.CryptoPro.ssl.android.Provider());
    } // if
    
    // Провайдер хеширования, подписи, шифрования по умолчанию.
    cpSSLConfig.setDefaultSSLProvider(JCSP.PROVIDER_NAME);
    
    // Загрузка Revocation Provider (CRL, OCSP).
    
    if (Security.getProvider(RevCheck.PROVIDER_NAME) == null) {
      Security.addProvider(new RevCheck());
    } // if
    
    // Инициализация XML DSig (хеш, подпись).
//    XmlInit.init();
    
    // %%% Параметры для Java TLS и CAdES API %%%
    
    // Провайдер CAdES API по умолчанию.
    AdESConfig.setDefaultProvider(JCSP.PROVIDER_NAME);
    
    // Отключаем проверку цепочки штампа времени (CAdES-T),
    // чтобы не требовать него CRL.
    
    System.setProperty("ru.CryptoProModule.CAdES.validate_tsp", "false");
    
    
    // Включаем возможность онлайновой проверки статуса
    // сертификата.

//    System.setProperty("com.sun.security.enableCRLDP", "true");
//    System.setProperty("com.ibm.security.enableCRLDP", "true");
    
    // Настройки TLS для генерации контейнера и выпуска сертификата
    // в УЦ 2.0, т.к. обращение к УЦ 2.0 будет выполняться по протоколу
    // HTTPS и потребуется авторизация по сертификату. Указываем тип
    // хранилища с доверенным корневым сертификатом, путь к нему и пароль.

//    final String trustStorePath = context.getApplicationInfo().dataDir + File.separator +
//        BKSTrustStore.STORAGE_DIRECTORY + File.separator + BKSTrustStore.STORAGE_FILE_TRUST;
//
//    final String trustStorePassword = String.valueOf(BKSTrustStore.STORAGE_PASSWORD);
//
//    System.setProperty("javax.net.ssl.trustStoreType", BKSTrustStore.STORAGE_TYPE);
//    System.setProperty("javax.net.ssl.trustStore", trustStorePath);
//    System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
  }
  
}
