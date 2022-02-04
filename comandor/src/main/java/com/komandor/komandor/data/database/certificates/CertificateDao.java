package com.komandor.komandor.data.database.certificates;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface CertificateDao {
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Certificate certificate);
  
  @Query("select * from " + Certificate.TABLE_NAME + " where is_active = 1")
  Flowable<Certificate> getActiveCertificate();
  
  @Query("select * from " + Certificate.TABLE_NAME + " where is_local = 1")
  Flowable<List<Certificate>> getLocalCertificates();

  @Query("select * from " + Certificate.TABLE_NAME + " where cert_id = :cid")
  Flowable<Certificate> getCertificateByID(int cid);

  @Query("select base64 from " + Certificate.TABLE_NAME + " where cert_id = :cid")
  String getCertificateStringByIDSync(int cid);
  
  @Query("select * from " + Certificate.TABLE_NAME + " where contact_id = :contactID")
  Flowable<List<Certificate>> getCertificatesByContactID(int contactID);
}
