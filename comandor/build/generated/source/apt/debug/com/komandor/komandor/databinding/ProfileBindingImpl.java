package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ProfileBindingImpl extends ProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_profile_avatar, 15);
        sViewsWithIds.put(R.id.btn_profile_load_photo, 16);
        sViewsWithIds.put(R.id.profile_SurnameText, 17);
        sViewsWithIds.put(R.id.profile_NameText, 18);
        sViewsWithIds.put(R.id.profile_PatronymicText, 19);
        sViewsWithIds.put(R.id.profile_SNILSText, 20);
        sViewsWithIds.put(R.id.profile_CompanyText, 21);
        sViewsWithIds.put(R.id.profile_OGRNText, 22);
        sViewsWithIds.put(R.id.profile_INNText, 23);
        sViewsWithIds.put(R.id.profile_PhoneText, 24);
        sViewsWithIds.put(R.id.profile_EmailText, 25);
        sViewsWithIds.put(R.id.profile_LicenseText, 26);
        sViewsWithIds.put(R.id.profile_License, 27);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    private final android.widget.LinearLayout mboundView14;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }
    private ProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (com.google.android.material.button.MaterialButton) bindings[16]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.TextView) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[26]
            , (com.google.android.material.button.MaterialButton) bindings[13]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[9]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[8]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[17]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[0]
            );
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView14 = (android.widget.LinearLayout) bindings[14];
        this.mboundView14.setTag(null);
        this.profileCompany.setTag(null);
        this.profileCompanyLayout.setTag(null);
        this.profileEmail.setTag(null);
        this.profileINN.setTag(null);
        this.profileLogOutButton.setTag(null);
        this.profileName.setTag(null);
        this.profileOGRN.setTag(null);
        this.profileOGRNLayout.setTag(null);
        this.profilePatronymic.setTag(null);
        this.profilePhone.setTag(null);
        this.profileSNILS.setTag(null);
        this.profileSurname.setTag(null);
        this.rlProfileRefreshLayout.setTag(null);
        setRootTag(root);
        // listeners
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
        if (BR.vm == variableId) {
            setVm((com.komandor.komandor.ui.main.profile.ProfileViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable com.komandor.komandor.ui.main.profile.ProfileViewModel Vm) {
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVmUser((androidx.lifecycle.MutableLiveData<com.komandor.komandor.data.database.users.User>) object, fieldId);
            case 1 :
                return onChangeVmIsLoading((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVmUser(androidx.lifecycle.MutableLiveData<com.komandor.komandor.data.database.users.User> VmUser, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmIsLoading(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
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
        com.komandor.komandor.data.database.users.User vmUserGetValue = null;
        androidx.lifecycle.MutableLiveData<com.komandor.komandor.data.database.users.User> vmUser = null;
        com.komandor.komandor.ui.main.profile.ProfileViewModel vm = mVm;
        java.lang.String vmUserGetInn = null;
        java.lang.String vmUserGetPhone = null;
        boolean vmUserGetTypeConstNP = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = false;
        boolean vmUserGetTypeConstLP = false;
        int vmUserGetTypeConstNPVGONEVVISIBLE = 0;
        int vmUserGetTypeConstLPVVISIBLEVGONE = 0;
        int vmUserJavaLangObjectNullVVISIBLEVGONE = 0;
        int vmUserJavaLangObjectNullVGONEVVISIBLE = 0;
        java.lang.String vmUserGetSnils = null;
        android.view.View.OnClickListener vmOnLogOutClickListener = null;
        boolean vmUserJavaLangObjectNull = false;
        java.lang.String vmUserGetCompany = null;
        java.lang.String vmUserGetEmail = null;
        java.lang.String vmUserGetOgrn = null;
        int vmUserGetType = 0;
        java.lang.Boolean vmIsLoadingGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsLoading = null;
        java.lang.String vmUserGetSurname = null;
        java.lang.String vmUserGetName = null;
        androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener vmOnRefreshListener = null;
        java.lang.String vmUserGetPatronymic = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (vm != null) {
                        // read vm.user
                        vmUser = vm.getUser();
                    }
                    updateLiveDataRegistration(0, vmUser);


                    if (vmUser != null) {
                        // read vm.user.getValue()
                        vmUserGetValue = vmUser.getValue();
                    }


                    if (vmUserGetValue != null) {
                        // read vm.user.getValue().getInn()
                        vmUserGetInn = vmUserGetValue.getInn();
                        // read vm.user.getValue().getPhone()
                        vmUserGetPhone = vmUserGetValue.getPhone();
                        // read vm.user.getValue().getSnils()
                        vmUserGetSnils = vmUserGetValue.getSnils();
                        // read vm.user.getValue().getCompany()
                        vmUserGetCompany = vmUserGetValue.getCompany();
                        // read vm.user.getValue().getEmail()
                        vmUserGetEmail = vmUserGetValue.getEmail();
                        // read vm.user.getValue().getOgrn()
                        vmUserGetOgrn = vmUserGetValue.getOgrn();
                        // read vm.user.getValue().getType()
                        vmUserGetType = vmUserGetValue.getType();
                        // read vm.user.getValue().getSurname()
                        vmUserGetSurname = vmUserGetValue.getSurname();
                        // read vm.user.getValue().getName()
                        vmUserGetName = vmUserGetValue.getName();
                        // read vm.user.getValue().getPatronymic()
                        vmUserGetPatronymic = vmUserGetValue.getPatronymic();
                    }
                    // read vm.user.getValue() != null
                    vmUserJavaLangObjectNull = (vmUserGetValue) != (null);
                if((dirtyFlags & 0xdL) != 0) {
                    if(vmUserJavaLangObjectNull) {
                            dirtyFlags |= 0x200L;
                            dirtyFlags |= 0x800L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                            dirtyFlags |= 0x400L;
                    }
                }


                    // read vm.user.getValue().getType() == Const.NP
                    vmUserGetTypeConstNP = (vmUserGetType) == (com.komandor.komandor.utils.Constants.NP);
                    // read vm.user.getValue().getType() == Const.LP
                    vmUserGetTypeConstLP = (vmUserGetType) == (com.komandor.komandor.utils.Constants.LP);
                    // read vm.user.getValue() != null ? V.VISIBLE : V.GONE
                    vmUserJavaLangObjectNullVVISIBLEVGONE = ((vmUserJavaLangObjectNull) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                    // read vm.user.getValue() != null ? V.GONE : V.VISIBLE
                    vmUserJavaLangObjectNullVGONEVVISIBLE = ((vmUserJavaLangObjectNull) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                if((dirtyFlags & 0xdL) != 0) {
                    if(vmUserGetTypeConstNP) {
                            dirtyFlags |= 0x20L;
                    }
                    else {
                            dirtyFlags |= 0x10L;
                    }
                }
                if((dirtyFlags & 0xdL) != 0) {
                    if(vmUserGetTypeConstLP) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }


                    // read vm.user.getValue().getType() == Const.NP ? V.GONE : V.VISIBLE
                    vmUserGetTypeConstNPVGONEVVISIBLE = ((vmUserGetTypeConstNP) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                    // read vm.user.getValue().getType() == Const.LP ? V.VISIBLE : V.GONE
                    vmUserGetTypeConstLPVVISIBLEVGONE = ((vmUserGetTypeConstLP) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0xcL) != 0) {

                    if (vm != null) {
                        // read vm.onLogOutClickListener
                        vmOnLogOutClickListener = vm.getOnLogOutClickListener();
                        // read vm.onRefreshListener
                        vmOnRefreshListener = vm.getOnRefreshListener();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (vm != null) {
                        // read vm.isLoading
                        vmIsLoading = vm.getIsLoading();
                    }
                    updateLiveDataRegistration(1, vmIsLoading);


                    if (vmIsLoading != null) {
                        // read vm.isLoading.getValue()
                        vmIsLoadingGetValue = vmIsLoading.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsLoadingGetValue);
            }
        }
        // batch finished
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            this.mboundView1.setVisibility(vmUserJavaLangObjectNullVVISIBLEVGONE);
            this.mboundView14.setVisibility(vmUserJavaLangObjectNullVGONEVVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.profileCompany, vmUserGetCompany);
            this.profileCompanyLayout.setVisibility(vmUserGetTypeConstLPVVISIBLEVGONE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.profileEmail, vmUserGetEmail);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.profileINN, vmUserGetInn);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.profileName, vmUserGetName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.profileOGRN, vmUserGetOgrn);
            this.profileOGRNLayout.setVisibility(vmUserGetTypeConstNPVGONEVVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.profilePatronymic, vmUserGetPatronymic);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.profilePhone, vmUserGetPhone);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.profileSNILS, vmUserGetSnils);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.profileSurname, vmUserGetSurname);
        }
        if ((dirtyFlags & 0xcL) != 0) {
            // api target 1

            this.profileLogOutButton.setOnClickListener(vmOnLogOutClickListener);
            this.rlProfileRefreshLayout.setOnRefreshListener(vmOnRefreshListener);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            this.rlProfileRefreshLayout.setRefreshing(androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm.user
        flag 1 (0x2L): vm.isLoading
        flag 2 (0x3L): vm
        flag 3 (0x4L): null
        flag 4 (0x5L): vm.user.getValue().getType() == Const.NP ? V.GONE : V.VISIBLE
        flag 5 (0x6L): vm.user.getValue().getType() == Const.NP ? V.GONE : V.VISIBLE
        flag 6 (0x7L): vm.user.getValue().getType() == Const.LP ? V.VISIBLE : V.GONE
        flag 7 (0x8L): vm.user.getValue().getType() == Const.LP ? V.VISIBLE : V.GONE
        flag 8 (0x9L): vm.user.getValue() != null ? V.VISIBLE : V.GONE
        flag 9 (0xaL): vm.user.getValue() != null ? V.VISIBLE : V.GONE
        flag 10 (0xbL): vm.user.getValue() != null ? V.GONE : V.VISIBLE
        flag 11 (0xcL): vm.user.getValue() != null ? V.GONE : V.VISIBLE
    flag mapping end*/
    //end
}