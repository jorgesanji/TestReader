package mino.com.sttapp.view.listphrases.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mino.com.sttapp.R;
import mino.com.sttapp.core.view.recyclerview.BaseAdapter;
import mino.com.sttapp.model.assets.Phrase;
import mino.com.sttapp.view.listphrases.adapter.viewholder.PhraseViewHolder;

/**
 * Created by jorgesanmartin on 2/26/16.
 */
public class PhrasesAdapter extends BaseAdapter<PhraseViewHolder, Phrase> {

    @Override
    public RecyclerView.ViewHolder getHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lay_row_phrase, parent, false);
        return new PhraseViewHolder(view).setClickListener(getClickListener()).setLongPressListener(getLongPresslistener());
    }

    @Override
    public void configItem(PhraseViewHolder holder, int position, boolean isLastItem) {
        holder.configureItem(getItem(position));
    }

    @Override
    public List filterBy(String query) {
        return null;
    }
}
