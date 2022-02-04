package com.komandor.komandor;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.komandor.komandor.databinding.CertValidationBindingImpl;
import com.komandor.komandor.databinding.CertificateBindingImpl;
import com.komandor.komandor.databinding.ChatBindingImpl;
import com.komandor.komandor.databinding.ChatItemBindingImpl;
import com.komandor.komandor.databinding.ChatsBindingImpl;
import com.komandor.komandor.databinding.ContactBindingImpl;
import com.komandor.komandor.databinding.ContactInfoBindingImpl;
import com.komandor.komandor.databinding.ContactsBindingImpl;
import com.komandor.komandor.databinding.MessageInfoDefaultBindingImpl;
import com.komandor.komandor.databinding.MessageInfoFileBindingImpl;
import com.komandor.komandor.databinding.ProfileBindingImpl;
import com.komandor.komandor.databinding.ReceivedFileBindingImpl;
import com.komandor.komandor.databinding.ReceivedMessageBindingImpl;
import com.komandor.komandor.databinding.RegistrationBindingImpl;
import com.komandor.komandor.databinding.SentFileBindingImpl;
import com.komandor.komandor.databinding.SentMessageBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRCERTIFICATEVALIDATION = 1;

  private static final int LAYOUT_FRCHAT = 2;

  private static final int LAYOUT_FRCHATS = 3;

  private static final int LAYOUT_FRCONTACTINFO = 4;

  private static final int LAYOUT_FRCONTACTS = 5;

  private static final int LAYOUT_FRMESSAGEINFODEFAULT = 6;

  private static final int LAYOUT_FRMESSAGEINFOFILE = 7;

  private static final int LAYOUT_FRPROFILE = 8;

  private static final int LAYOUT_FRREGISTRATION = 9;

  private static final int LAYOUT_LICERTIFICATE = 10;

  private static final int LAYOUT_LICHAT = 11;

  private static final int LAYOUT_LICONTACT = 12;

  private static final int LAYOUT_LIRECEIVEDFILE = 13;

  private static final int LAYOUT_LIRECEIVEDMESSAGE = 14;

  private static final int LAYOUT_LISENTFILE = 15;

  private static final int LAYOUT_LISENTMESSAGE = 16;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(16);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.fr_certificate_validation, LAYOUT_FRCERTIFICATEVALIDATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.fr_chat, LAYOUT_FRCHAT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.fr_chats, LAYOUT_FRCHATS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.fr_contact_info, LAYOUT_FRCONTACTINFO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.fr_contacts, LAYOUT_FRCONTACTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.fr_message_info_default, LAYOUT_FRMESSAGEINFODEFAULT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.fr_message_info_file, LAYOUT_FRMESSAGEINFOFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.fr_profile, LAYOUT_FRPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.fr_registration, LAYOUT_FRREGISTRATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.li_certificate, LAYOUT_LICERTIFICATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.li_chat, LAYOUT_LICHAT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.li_contact, LAYOUT_LICONTACT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.li_received_file, LAYOUT_LIRECEIVEDFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.li_received_message, LAYOUT_LIRECEIVEDMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.li_sent_file, LAYOUT_LISENTFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.komandor.komandor.R.layout.li_sent_message, LAYOUT_LISENTMESSAGE);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRCERTIFICATEVALIDATION: {
          if ("layout/fr_certificate_validation_0".equals(tag)) {
            return new CertValidationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fr_certificate_validation is invalid. Received: " + tag);
        }
        case  LAYOUT_FRCHAT: {
          if ("layout/fr_chat_0".equals(tag)) {
            return new ChatBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fr_chat is invalid. Received: " + tag);
        }
        case  LAYOUT_FRCHATS: {
          if ("layout/fr_chats_0".equals(tag)) {
            return new ChatsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fr_chats is invalid. Received: " + tag);
        }
        case  LAYOUT_FRCONTACTINFO: {
          if ("layout/fr_contact_info_0".equals(tag)) {
            return new ContactInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fr_contact_info is invalid. Received: " + tag);
        }
        case  LAYOUT_FRCONTACTS: {
          if ("layout/fr_contacts_0".equals(tag)) {
            return new ContactsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fr_contacts is invalid. Received: " + tag);
        }
        case  LAYOUT_FRMESSAGEINFODEFAULT: {
          if ("layout/fr_message_info_default_0".equals(tag)) {
            return new MessageInfoDefaultBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fr_message_info_default is invalid. Received: " + tag);
        }
        case  LAYOUT_FRMESSAGEINFOFILE: {
          if ("layout/fr_message_info_file_0".equals(tag)) {
            return new MessageInfoFileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fr_message_info_file is invalid. Received: " + tag);
        }
        case  LAYOUT_FRPROFILE: {
          if ("layout/fr_profile_0".equals(tag)) {
            return new ProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fr_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_FRREGISTRATION: {
          if ("layout/fr_registration_0".equals(tag)) {
            return new RegistrationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fr_registration is invalid. Received: " + tag);
        }
        case  LAYOUT_LICERTIFICATE: {
          if ("layout/li_certificate_0".equals(tag)) {
            return new CertificateBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for li_certificate is invalid. Received: " + tag);
        }
        case  LAYOUT_LICHAT: {
          if ("layout/li_chat_0".equals(tag)) {
            return new ChatItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for li_chat is invalid. Received: " + tag);
        }
        case  LAYOUT_LICONTACT: {
          if ("layout/li_contact_0".equals(tag)) {
            return new ContactBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for li_contact is invalid. Received: " + tag);
        }
        case  LAYOUT_LIRECEIVEDFILE: {
          if ("layout/li_received_file_0".equals(tag)) {
            return new ReceivedFileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for li_received_file is invalid. Received: " + tag);
        }
        case  LAYOUT_LIRECEIVEDMESSAGE: {
          if ("layout/li_received_message_0".equals(tag)) {
            return new ReceivedMessageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for li_received_message is invalid. Received: " + tag);
        }
        case  LAYOUT_LISENTFILE: {
          if ("layout/li_sent_file_0".equals(tag)) {
            return new SentFileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for li_sent_file is invalid. Received: " + tag);
        }
        case  LAYOUT_LISENTMESSAGE: {
          if ("layout/li_sent_message_0".equals(tag)) {
            return new SentMessageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for li_sent_message is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(12);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "onItemClickListener");
      sKeys.put(2, "onMessageItemClickListener");
      sKeys.put(3, "V");
      sKeys.put(4, "v");
      sKeys.put(5, "chat");
      sKeys.put(6, "vm");
      sKeys.put(7, "contact");
      sKeys.put(8, "certificate");
      sKeys.put(9, "message");
      sKeys.put(10, "onDownloadFileClickListener");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(16);

    static {
      sKeys.put("layout/fr_certificate_validation_0", com.komandor.komandor.R.layout.fr_certificate_validation);
      sKeys.put("layout/fr_chat_0", com.komandor.komandor.R.layout.fr_chat);
      sKeys.put("layout/fr_chats_0", com.komandor.komandor.R.layout.fr_chats);
      sKeys.put("layout/fr_contact_info_0", com.komandor.komandor.R.layout.fr_contact_info);
      sKeys.put("layout/fr_contacts_0", com.komandor.komandor.R.layout.fr_contacts);
      sKeys.put("layout/fr_message_info_default_0", com.komandor.komandor.R.layout.fr_message_info_default);
      sKeys.put("layout/fr_message_info_file_0", com.komandor.komandor.R.layout.fr_message_info_file);
      sKeys.put("layout/fr_profile_0", com.komandor.komandor.R.layout.fr_profile);
      sKeys.put("layout/fr_registration_0", com.komandor.komandor.R.layout.fr_registration);
      sKeys.put("layout/li_certificate_0", com.komandor.komandor.R.layout.li_certificate);
      sKeys.put("layout/li_chat_0", com.komandor.komandor.R.layout.li_chat);
      sKeys.put("layout/li_contact_0", com.komandor.komandor.R.layout.li_contact);
      sKeys.put("layout/li_received_file_0", com.komandor.komandor.R.layout.li_received_file);
      sKeys.put("layout/li_received_message_0", com.komandor.komandor.R.layout.li_received_message);
      sKeys.put("layout/li_sent_file_0", com.komandor.komandor.R.layout.li_sent_file);
      sKeys.put("layout/li_sent_message_0", com.komandor.komandor.R.layout.li_sent_message);
    }
  }
}
