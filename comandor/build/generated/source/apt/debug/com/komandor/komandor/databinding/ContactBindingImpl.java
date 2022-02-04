package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ContactBindingImpl extends ContactBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.ImageView mboundView3;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback6;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ContactBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ContactBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.ImageView) bindings[3];
        this.mboundView3.setTag(null);
        setRootTag(root);
        // listeners
        mCallback6 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.contact == variableId) {
            setContact((com.komandor.komandor.ui.main.contacts.ContactItemViewModel) variable);
        }
        else if (BR.V == variableId) {
            setV((android.view.View) variable);
        }
        else if (BR.onItemClickListener == variableId) {
            setOnItemClickListener((com.komandor.komandor.ui.main.contacts.ContactsAdapter.OnContactItemClickListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setContact(@Nullable com.komandor.komandor.ui.main.contacts.ContactItemViewModel Contact) {
        this.mContact = Contact;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.contact);
        super.requestRebind();
    }
    public void setV(@Nullable android.view.View V) {
        this.mV = V;
    }
    public void setOnItemClickListener(@Nullable com.komandor.komandor.ui.main.contacts.ContactsAdapter.OnContactItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.onItemClickListener);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.komandor.komandor.ui.main.contacts.ContactItemViewModel contact = mContact;
        boolean contactIsKomandor = false;
        java.lang.String contactLowerString = null;
        int contactIsKomandorVVISIBLEVGONE = 0;
        java.lang.String contactUpperString = null;
        com.komandor.komandor.ui.main.contacts.ContactsAdapter.OnContactItemClickListener onItemClickListener = mOnItemClickListener;

        if ((dirtyFlags & 0x9L) != 0) {



                if (contact != null) {
                    // read contact.isKomandor
                    contactIsKomandor = contact.isKomandor();
                    // read contact.lowerString
                    contactLowerString = contact.getLowerString();
                    // read contact.upperString
                    contactUpperString = contact.getUpperString();
                }
            if((dirtyFlags & 0x9L) != 0) {
                if(contactIsKomandor) {
                        dirtyFlags |= 0x20L;
                }
                else {
                        dirtyFlags |= 0x10L;
                }
            }


                // read contact.isKomandor ? View.VISIBLE : View.GONE
                contactIsKomandorVVISIBLEVGONE = ((contactIsKomandor) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback6);
        }
        if ((dirtyFlags & 0x9L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, contactUpperString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, contactLowerString);
            this.mboundView3.setVisibility(contactIsKomandorVVISIBLEVGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // onItemClickListener
        com.komandor.komandor.ui.main.contacts.ContactsAdapter.OnContactItemClickListener onItemClickListener = mOnItemClickListener;
        // contact
        com.komandor.komandor.ui.main.contacts.ContactItemViewModel contact = mContact;
        // contact != null
        boolean contactJavaLangObjectNull = false;
        // contact.id
        int contactId = 0;
        // onItemClickListener != null
        boolean onItemClickListenerJavaLangObjectNull = false;



        onItemClickListenerJavaLangObjectNull = (onItemClickListener) != (null);
        if (onItemClickListenerJavaLangObjectNull) {



            contactJavaLangObjectNull = (contact) != (null);
            if (contactJavaLangObjectNull) {


                contactId = contact.getId();

                onItemClickListener.onContactItemClick(contactId);
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): contact
        flag 1 (0x2L): V
        flag 2 (0x3L): onItemClickListener
        flag 3 (0x4L): null
        flag 4 (0x5L): contact.isKomandor ? View.VISIBLE : View.GONE
        flag 5 (0x6L): contact.isKomandor ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}