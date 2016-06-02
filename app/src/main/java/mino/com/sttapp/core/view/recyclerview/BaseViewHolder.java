package mino.com.sttapp.core.view.recyclerview;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 2/26/16.
 */
public abstract class BaseViewHolder<I> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private BaseAdapter.CLickListener cLickListener;
    private BaseAdapter.LongPressListener longPressListener;

    public BaseViewHolder(View itemView) {
        this(itemView, true, false);
    }

    public BaseViewHolder(View itemView, boolean isClickable, boolean longPress) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        if (isClickable) {
            itemView.setOnClickListener(this);
        }

        if (longPress) {
            itemView.setOnLongClickListener(this);
        }
    }

    public abstract void configureItem(I item);

    public BaseAdapter.CLickListener getcLickListener() {
        return cLickListener;
    }

    public BaseViewHolder setClickListener(BaseAdapter.CLickListener listener) {
        this.cLickListener = listener;
        return this;
    }

    public BaseAdapter.LongPressListener getLongPressListener() {
        return longPressListener;
    }

    public BaseViewHolder setLongPressListener(BaseAdapter.LongPressListener longPressListener) {
        this.longPressListener = longPressListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (getcLickListener() != null) {
            getcLickListener().onItemSelected(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (getLongPressListener() != null) {
            Vibrator vibrator = (Vibrator) itemView.getContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(250);
            getLongPressListener().onItemSelected(getAdapterPosition());
        }
        return false;
    }

    public Context getContext() {
        return itemView.getContext();
    }

}