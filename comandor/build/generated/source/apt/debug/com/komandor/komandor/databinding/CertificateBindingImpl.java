package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CertificateBindingImpl extends CertificateBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback11;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CertificateBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private CertificateBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[0]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.rootCertificateLi.setTag(null);
        this.tvCertLiLowerText.setTag(null);
        this.tvCertLiUpperText.setTag(null);
        setRootTag(root);
        // listeners
        mCallback11 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.certificate == variableId) {
            setCertificate((com.komandor.komandor.ui.auth.cert_list.CertListItemViewModel) variable);
        }
        else if (BR.onItemClickListener == variableId) {
            setOnItemClickListener((com.komandor.komandor.ui.auth.cert_list.CertListAdapter.OnCertificateItemClickListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCertificate(@Nullable com.komandor.komandor.ui.auth.cert_list.CertListItemViewModel Certificate) {
        this.mCertificate = Certificate;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.certificate);
        super.requestRebind();
    }
    public void setOnItemClickListener(@Nullable com.komandor.komandor.ui.auth.cert_list.CertListAdapter.OnCertificateItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
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
        java.lang.String certificateCompanyName = null;
        int certificateIsCompanyVVISIBLEVGONE = 0;
        boolean certificateIsCompany = false;
        java.lang.String certificateClientName = null;
        int certificateIsCompanyGBOTTOMGCENTERVERTICAL = 0;
        java.lang.String certificateIsCompanyCertificateCompanyNameCertificateClientName = null;
        com.komandor.komandor.ui.auth.cert_list.CertListItemViewModel certificate = mCertificate;
        com.komandor.komandor.ui.auth.cert_list.CertListAdapter.OnCertificateItemClickListener onItemClickListener = mOnItemClickListener;

        if ((dirtyFlags & 0x5L) != 0) {



                if (certificate != null) {
                    // read certificate.isCompany
                    certificateIsCompany = certificate.isCompany();
                    // read certificate.clientName
                    certificateClientName = certificate.getClientName();
                }
            if((dirtyFlags & 0x5L) != 0) {
                if(certificateIsCompany) {
                        dirtyFlags |= 0x10L;
                        dirtyFlags |= 0x40L;
                        dirtyFlags |= 0x100L;
                }
                else {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                        dirtyFlags |= 0x80L;
                }
            }


                // read certificate.isCompany ? V.VISIBLE : V.GONE
                certificateIsCompanyVVISIBLEVGONE = ((certificateIsCompany) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                // read certificate.isCompany ? G.BOTTOM : G.CENTER_VERTICAL
                certificateIsCompanyGBOTTOMGCENTERVERTICAL = ((certificateIsCompany) ? (android.view.Gravity.BOTTOM) : (android.view.Gravity.CENTER_VERTICAL));
        }
        // batch finished

        if ((dirtyFlags & 0x100L) != 0) {

                if (certificate != null) {
                    // read certificate.companyName
                    certificateCompanyName = certificate.getCompanyName();
                }
        }

        if ((dirtyFlags & 0x5L) != 0) {

                // read certificate.isCompany ? certificate.companyName : certificate.clientName
                certificateIsCompanyCertificateCompanyNameCertificateClientName = ((certificateIsCompany) ? (certificateCompanyName) : (certificateClientName));
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.rootCertificateLi.setOnClickListener(mCallback11);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCertLiLowerText, certificateClientName);
            this.tvCertLiLowerText.setVisibility(certificateIsCompanyVVISIBLEVGONE);
            this.tvCertLiUpperText.setGravity(certificateIsCompanyGBOTTOMGCENTERVERTICAL);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCertLiUpperText, certificateIsCompanyCertificateCompanyNameCertificateClientName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // onItemClickListener
        com.komandor.komandor.ui.auth.cert_list.CertListAdapter.OnCertificateItemClickListener onItemClickListener = mOnItemClickListener;
        // certificate.alias
        java.lang.String certificateAlias = null;
        // certificate
        com.komandor.komandor.ui.auth.cert_list.CertListItemViewModel certificate = mCertificate;
        // onItemClickListener != null
        boolean onItemClickListenerJavaLangObjectNull = false;
        // certificate != null
        boolean certificateJavaLangObjectNull = false;



        onItemClickListenerJavaLangObjectNull = (onItemClickListener) != (null);
        if (onItemClickListenerJavaLangObjectNull) {



            certificateJavaLangObjectNull = (certificate) != (null);
            if (certificateJavaLangObjectNull) {


                certificateAlias = certificate.getAlias();

                onItemClickListener.onCertificateItemClick(certificateAlias);
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): certificate
        flag 1 (0x2L): onItemClickListener
        flag 2 (0x3L): null
        flag 3 (0x4L): certificate.isCompany ? V.VISIBLE : V.GONE
        flag 4 (0x5L): certificate.isCompany ? V.VISIBLE : V.GONE
        flag 5 (0x6L): certificate.isCompany ? G.BOTTOM : G.CENTER_VERTICAL
        flag 6 (0x7L): certificate.isCompany ? G.BOTTOM : G.CENTER_VERTICAL
        flag 7 (0x8L): certificate.isCompany ? certificate.companyName : certificate.clientName
        flag 8 (0x9L): certificate.isCompany ? certificate.companyName : certificate.clientName
    flag mapping end*/
    //end
}