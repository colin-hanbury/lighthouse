package screens.settings.recordings;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import data.recordings.RecordingFile;
import data.settings.SettingsItem;
import screens.common.viewfactory.LightHouseViewFactory;
import screens.settings.recordings.recordingfile.IRecordingFileView;

public class RecordingsRecyclerAdapter extends
        RecyclerView.Adapter<RecordingsRecyclerAdapter.RecordingsViewHolder>
        implements IRecordingFileView.Listener{

    private final Listener mListener;
    private final LightHouseViewFactory mLightHouseFactory;
    private final List<RecordingFile> mRecordingFiles;

    public interface Listener {
        void onRecordingFileClicked(RecordingFile recordingFile);
    }

    public RecordingsRecyclerAdapter(Listener listener, LightHouseViewFactory lightHouseViewFactory){
        mListener = listener;
        mLightHouseFactory = lightHouseViewFactory;
        mRecordingFiles = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecordingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IRecordingFileView iRecordingFileView = mLightHouseFactory.getRecordingFileView(parent);
        iRecordingFileView.registerListener(this);
        return new RecordingsViewHolder(iRecordingFileView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordingsViewHolder holder, int position) {
        holder.mIRecordingFileView.bindRecordingFile(mRecordingFiles.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecordingFiles.size();
    }

    @Override
    public void onRecordingFileClicked(RecordingFile recordingFile) {
        mListener.onRecordingFileClicked(recordingFile);
    }

    public class RecordingsViewHolder extends RecyclerView.ViewHolder {

        private final IRecordingFileView mIRecordingFileView;

        public RecordingsViewHolder(IRecordingFileView iRecordingFileView) {
            super(iRecordingFileView.getRootView());

            this.mIRecordingFileView = iRecordingFileView;
        }
    }
}
