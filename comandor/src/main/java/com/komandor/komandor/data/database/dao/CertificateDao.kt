package com.komandor.komandor.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.komandor.komandor.data.database.model.Certificate
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CertificateDao: BaseDao<Certificate>() {

    @Query("select * from CERTIFICATES where is_active = 1")
    abstract fun getActiveCertificate(): Flow<Certificate?>?

    @Query("select * from CERTIFICATES where is_local = 1")
    abstract fun getLocalCertificates(): Flow<List<Certificate?>?>?

    @Query("select * from CERTIFICATES where cert_id = :cid")
    abstract fun getCertificateByID(cid: Int): Flow<Certificate?>?

    @Query("select base64 from CERTIFICATES where cert_id = :cid")
    abstract fun getCertificateStringByIDSync(cid: Int): String?

    @Query("select * from CERTIFICATES where contact_id = :contactID")
    abstract fun getCertificatesByContactID(contactID: Int): Flow<List<Certificate?>?>?
}