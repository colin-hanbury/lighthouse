package screens.settings.recordings;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import data.recordings.Recording;
import screens.common.viewfactory.LightHouseViewFactory;
import screens.settings.recordings.recordingpreview.IRecordingPreviewView;

public class RecordingsRecyclerAdapter extends
        RecyclerView.Adapter<RecordingsRecyclerAdapter.RecordingsViewHolder>
        implements IRecordingPreviewView.Listener{

    private final Listener mListener;
    private final LightHouseViewFactory mLightHouseFactory;
    private final List<Recording> mRecordings;



    public interface Listener {
        void onRecordingFileClicked(Recording recording);
    }

    public RecordingsRecyclerAdapter(Listener listener, LightHouseViewFactory lightHouseViewFactory){
        mListener = listener;
        mLightHouseFactory = lightHouseViewFactory;
        mRecordings = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecordingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IRecordingPreviewView iRecordingPreviewView = mLightHouseFactory.getRecordingFileView(parent);
        iRecordingPreviewView.registerListener(this);
        return new RecordingsViewHolder(iRecordingPreviewView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordingsViewHolder holder, int position) {
        holder.mIRecordingPreviewView.bindRecordingFile(mRecordings.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecordings.size();
    }

    @Override
    public void onRecordingClicked(Recording recording) {
        mListener.onRecordingFileClicked(recording);
    }

    public class RecordingsViewHolder extends RecyclerView.ViewHolder {

        private final IRecordingPreviewView mIRecordingPreviewView;

        public RecordingsViewHolder(IRecordingPreviewView iRecordingPreviewView) {
            super(iRecordingPreviewView.getRootView());

            this.mIRecordingPreviewView = iRecordingPreviewView;
        }
    }
}
