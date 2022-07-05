package com.komandor.komandor.extension

import android.content.Context
import android.util.Base64
import com.komandor.komandor.cryptopro.utils.ContainerAdapter
import org.bouncycastle.asn1.ASN1ObjectIdentifier
import org.bouncycastle.asn1.x500.RDN
import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.asn1.x500.style.BCStyle
import org.bouncycastle.asn1.x500.style.IETFUtils
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder
import timber.log.Timber
import java.io.ByteArrayInputStream
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.HashMap

fun String.decodeBase64Certificate(): X509Certificate {

            val encodedCert: ByteArray = Base64.decode(this, Base64.NO_WRAP)
            val inputStream = ByteArrayInputStream(encodedCert)
            val certFactory = CertificateFactory.getInstance("X.509")
            return certFactory.generateCertificate(inputStream) as X509Certificate

}


fun X509Certificate.encodeCertificate(): String = String(Base64.encode(this.encoded, Base64.NO_WRAP))


fun String.toCertificate(): X509Certificate? {
    // this - base64: String
    val decoded = Base64.decode(this, Base64.NO_WRAP)
    val inputStream = ByteArrayInputStream(decoded)
    return CertificateFactory.getInstance("X.509").generateCertificate(inputStream) as X509Certificate
}


fun X509Certificate.getClientName(): String {

    var x500Name: X500Name?
    try {
        x500Name = JcaX509CertificateHolder(this).subject
    } catch (e: java.lang.Exception) {
        Timber.d("e = $e")
        return "--"
        //e.printStackTrace()
        //      Crashlytics.logException(e);
    }
    //Timber.d("x500Name = $x500Name")
    var surname = "--"
    var givenName = "--"
    if (x500Name != null) {
        val surnameRDNs = x500Name.getRDNs(BCStyle.SURNAME)
        //Timber.d("surnameRDNs = $surnameRDNs")
        if (surnameRDNs.isNotEmpty()) {
            val surnameRDN: RDN = surnameRDNs!![0]
            //Timber.d("surnameRDN = $surnameRDN")
            surname = IETFUtils.valueToString(surnameRDN.first.value)
            //Timber.d("surname = $surname")
        }
        val givenNameRDNs = x500Name.getRDNs(BCStyle.GIVENNAME)
        if (givenNameRDNs.isNotEmpty()) {
            val givenNameRDN = givenNameRDNs[0]
            //Timber.d("givenNameRDN = $givenNameRDN")
            givenName = IETFUtils.valueToString(givenNameRDN.first.value)
        }
        return "$surname $givenName"
    } else {
        return "---"
    }
}

fun X509Certificate.getCompanyName(): String {
    var x500Name: X500Name?
    try {
        x500Name = JcaX509CertificateHolder(this).subject
    } catch (e: java.lang.Exception) {
        Timber.d("e = $e")
        return "--"

        //e.printStackTrace()
        //      Crashlytics.logException(e);
    }
    //Timber.d("x500Name = $x500Name")

    if (x500Name != null) {
        //val ogrnRDN = x500Name.getRDNs(ASN1ObjectIdentifier("1.2.643.100.1"))
        val ogrnRDN = getRDNs(x500Name, ASN1ObjectIdentifier("1.2.643.100.1"))
        //Timber.d("ogrnRDN = $ogrnRDN ${ogrnRDN.size}")

        if (ogrnRDN.size > 0) {
            val companyNameRDNs = x500Name.getRDNs(BCStyle.CN)
            //Timber.d("companyNameRDNs = $companyNameRDNs")

            if (companyNameRDNs.isNotEmpty()) {
                val companyNameRDN = companyNameRDNs[0]
                //Timber.d("companyNameRDN = $companyNameRDN")

                var companyName = IETFUtils.valueToString(companyNameRDN.first.value)
                companyName = companyName.replace("\"", "").replace("\\", "\"")
                return companyName
            }
        }
        return x500Name.toString()

    }
    return "---"

}

fun getRDNs(x500Name: X500Name, attributeType: ASN1ObjectIdentifier): Array<RDN?> {
    //Timber.d("attributeType = ${attributeType}")

    val res = arrayOfNulls<RDN>(x500Name.rdNs.size)
    var count = 0
    for (i in x500Name.getRDNs().indices) {
        val rdn: RDN = x500Name.getRDNs().get(i)
        //Timber.d("rdn = ${rdn}")

        if (rdn.isMultiValued) {
            val attr = rdn.typesAndValues
            //Timber.d("attr = $attr")

            for (j in attr.indices) {
                if (attr[j].type == attributeType) {
                    res[count++] = rdn
                    break
                }
            }
        } else {
            //Timber.d("rdn.first.type = ${rdn.first.type}")
            if (rdn.first.type == attributeType) {
                res[count++] = rdn
            }
        }
    }
    val tmp = arrayOfNulls<RDN>(count)
    System.arraycopy(res, 0, tmp, 0, tmp.size)
    return tmp
}
