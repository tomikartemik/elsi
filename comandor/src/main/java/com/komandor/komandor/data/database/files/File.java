package com.komandor.komandor.data.database.files;

import com.komandor.komandor.data.api.model.response.MessageResponseModel;
import com.komandor.komandor.data.model.EncryptedFile;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = File.TABLE_NAME)
public class File {
    public static final String TABLE_NAME = "FILES";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    long id;

    @ColumnInfo(name = "type")
    int type;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "name_sign")
    String nameSign;

    @ColumnInfo(name = "file_id")
    String fileID;

    @ColumnInfo(name = "cms")
    String cms;

    @ColumnInfo(name = "file_path")
    String filePath;

    public File() {
    }

    @Ignore
    public File(MessageResponseModel message) {
        this.type = 0;
        this.name = message.getData();
        this.nameSign = message.getSign();
        this.fileID = message.getFileID();
        this.cms = message.getCms();
    }

    @Ignore
    public File(EncryptedFile file, String filePath) {
        this.type = 0;
        this.name = file.getEncryptedName();
        this.nameSign = file.getNameSign();
        this.cms = file.getCms();
        this.filePath = filePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSign() {
        return nameSign;
    }

    public void setNameSign(String nameSign) {
        this.nameSign = nameSign;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCms() {
        return cms;
    }

    public void setCms(String cms) {
        this.cms = cms;
    }
}
