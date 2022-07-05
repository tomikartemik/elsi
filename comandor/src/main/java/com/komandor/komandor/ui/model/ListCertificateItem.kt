package com.komandor.komandor.ui.model

import java.security.cert.X509Certificate

data class ListCertificateItem(val  alias:String, val certificate: X509Certificate, val clientName:String, val clientCompany: String)