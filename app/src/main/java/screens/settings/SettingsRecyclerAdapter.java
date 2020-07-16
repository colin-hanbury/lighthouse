package screens.settings;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import data.settings.SettingsItem;
import hanbury.colin.networking.R;
import screens.common.viewfactory.LightHouseViewFactory;
import screens.settings.settingsitem.ISettingsItemView;

public class SettingsRecyclerAdapter extends
        RecyclerView.Adapter<SettingsRecyclerAdapter.SettingsViewHolder>
        implements ISettingsItemView.Listener {

    private final Listener mListener;
    private final LightHouseViewFactory mLightHouseViewFactory;
    private  List<SettingsItem> mItemList;
    private final String TAG = "SettingsItemsAdapter";

    @Override
    public void onSettingItemClicked(SettingsItem item) {
        mListener.onItemClicked(item);
    }

    public interface Listener {
        void onItemClicked(SettingsItem item);
    }

    public SettingsRecyclerAdapter(Listener listener, LightHouseViewFactory lightHouseViewFactory){
        this.mListener = listener;
        this.mLightHouseViewFactory = lightHouseViewFactory;
        this.mItemList = new ArrayList<>();
        populateList();
    }

    private void populateList(){
        Log.i(TAG, "populating items");
        mItemList.add(new SettingsItem(R.drawable.ic_account_light,"Account"));
        mItemList.add(new SettingsItem(R.drawable.ic_cloud_light,"Recent Recordings"));
        mItemList.add(new SettingsItem(R.drawable.ic_saved_light,"Saved Recordings"));
        mItemList.add(new SettingsItem(R.drawable.ic_notifications_light,"Notifications"));
        mItemList.add(new SettingsItem(R.drawable.ic_contact_light,"Contact Us"));
        mItemList.add(new SettingsItem(R.drawable.ic_terms_light,"Terms of Use"));
        mItemList.add(new SettingsItem(R.drawable.ic_privacy_light,"Privacy Policy"));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ISettingsItemView iSettingsItemView = mLightHouseViewFactory.getSettingsItemView(parent);
        iSettingsItemView.registerListener(this);
        Log.i(TAG, "view holder init");
        return new SettingsViewHolder(iSettingsItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        Log.i(TAG, "bind setting item");
        holder.mISettingsItemView.bindSettingItem(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class SettingsViewHolder extends RecyclerView.ViewHolder {

        private final ISettingsItemView mISettingsItemView;

        public SettingsViewHolder(ISettingsItemView iSettingsItemView) {
            super(iSettingsItemView.getRootView());

            this.mISettingsItemView = iSettingsItemView;
        }
    }

}
