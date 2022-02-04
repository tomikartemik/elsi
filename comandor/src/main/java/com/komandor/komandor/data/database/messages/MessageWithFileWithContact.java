package com.komandor.komandor.data.database.messages;

import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.data.database.files.File;
import com.komandor.komandor.data.database.users.User;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class MessageWithFileWithContact {

  @Embedded
  public Message message;

  @Relation(parentColumn = "pid", entityColumn = "pid")
  public List<Contact> contacts;

  @Relation(parentColumn = "file_id", entityColumn = "id")
  public List<File> files;


  public Message getMessage() {
    return message;
  }

  public boolean isBelongToUser() {
    return contacts.size() <= 0;
  }

  public Contact getContact() {
    if(contacts.size() > 0) {
      return contacts.get(0);
    }

    return null;
  }

  public File getFile() {
    if(files.size() == 1) {
      return files.get(0);
    }

    return null;
  }
}
