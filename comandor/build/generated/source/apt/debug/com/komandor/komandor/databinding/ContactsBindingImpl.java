package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ContactsBindingImpl extends ContactsBinding  {

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
    private final androidx.swiperefreshlayout.widget.SwipeRefreshLayout mboundView0;
    @NonNull
    private final androidx.recyclerview.widget.RecyclerView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ContactsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ContactsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            );
        this.mboundView0 = (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (androidx.recyclerview.widget.RecyclerView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
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
            setVm((com.komandor.komandor.ui.main.contacts.ContactsViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable com.komandor.komandor.ui.main.contacts.ContactsViewModel Vm) {
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVmHasPermissions((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeVmIsLoading((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeVmContacts((androidx.lifecycle.LiveData<java.util.List<com.komandor.komandor.data.database.contacts.Contact>>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVmHasPermissions(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmHasPermissions, int fieldId) {
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
    private boolean onChangeVmContacts(androidx.lifecycle.LiveData<java.util.List<com.komandor.komandor.data.database.contacts.Contact>> VmContacts, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
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
        com.komandor.komandor.ui.main.contacts.ContactsViewModel vm = mVm;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmHasPermissionsGetValue = false;
        com.komandor.komandor.ui.main.contacts.ContactsAdapter.OnContactItemClickListener vmOnContactItemClickListener = null;
        int vmHasPermissionsVGONEVVISIBLE = 0;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmHasPermissions = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = false;
        java.lang.Boolean vmHasPermissionsGetValue = null;
        java.lang.Boolean vmIsLoadingGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsLoading = null;
        java.util.List<com.komandor.komandor.data.database.contacts.Contact> vmContactsGetValue = null;
        int vmHasPermissionsVVISIBLEVGONE = 0;
        androidx.lifecycle.LiveData<java.util.List<com.komandor.komandor.data.database.contacts.Contact>> vmContacts = null;
        androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener vmOnRefreshListener = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x1cL) != 0) {

                    if (vm != null) {
                        // read vm.onContactItemClickListener
                        vmOnContactItemClickListener = vm.getOnContactItemClickListener();
                        // read vm.contacts
                        vmContacts = vm.getContacts();
                    }
                    updateLiveDataRegistration(2, vmContacts);


                    if (vmContacts != null) {
                        // read vm.contacts.getValue()
                        vmContactsGetValue = vmContacts.getValue();
                    }
            }
            if ((dirtyFlags & 0x19L) != 0) {

                    if (vm != null) {
                        // read vm.hasPermissions
                        vmHasPermissions = vm.getHasPermissions();
                    }
                    updateLiveDataRegistration(0, vmHasPermissions);


                    if (vmHasPermissions != null) {
                        // read vm.hasPermissions.getValue()
                        vmHasPermissionsGetValue = vmHasPermissions.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.hasPermissions.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmHasPermissionsGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmHasPermissionsGetValue);
                if((dirtyFlags & 0x19L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmHasPermissionsGetValue) {
                            dirtyFlags |= 0x40L;
                            dirtyFlags |= 0x100L;
                    }
                    else {
                            dirtyFlags |= 0x20L;
                            dirtyFlags |= 0x80L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.hasPermissions.getValue()) ? V.GONE : V.VISIBLE
                    vmHasPermissionsVGONEVVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxVmHasPermissionsGetValue) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.hasPermissions.getValue()) ? V.VISIBLE : V.GONE
                    vmHasPermissionsVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmHasPermissionsGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x1aL) != 0) {

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
            if ((dirtyFlags & 0x18L) != 0) {

                    if (vm != null) {
                        // read vm.onRefreshListener
                        vmOnRefreshListener = vm.getOnRefreshListener();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x18L) != 0) {
            // api target 1

            this.mboundView0.setOnRefreshListener(vmOnRefreshListener);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            this.mboundView0.setRefreshing(androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            this.mboundView1.setVisibility(vmHasPermissionsVVISIBLEVGONE);
            this.mboundView2.setVisibility(vmHasPermissionsVGONEVVISIBLE);
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            com.komandor.komandor.widgets.binding_adapters.RecyclerViewBindingAdapter.configureRecyclerView(this.mboundView1, vmContactsGetValue, vmOnContactItemClickListener);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm.hasPermissions
        flag 1 (0x2L): vm.isLoading
        flag 2 (0x3L): vm.contacts
        flag 3 (0x4L): vm
        flag 4 (0x5L): null
        flag 5 (0x6L): androidx.databinding.ViewDataBinding.safeUnbox(vm.hasPermissions.getValue()) ? V.GONE : V.VISIBLE
        flag 6 (0x7L): androidx.databinding.ViewDataBinding.safeUnbox(vm.hasPermissions.getValue()) ? V.GONE : V.VISIBLE
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(vm.hasPermissions.getValue()) ? V.VISIBLE : V.GONE
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(vm.hasPermissions.getValue()) ? V.VISIBLE : V.GONE
    flag mapping end*/
    //end
}