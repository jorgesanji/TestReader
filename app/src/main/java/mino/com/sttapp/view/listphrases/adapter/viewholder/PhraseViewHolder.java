package mino.com.sttapp.view.listphrases.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import mino.com.sttapp.R;
import mino.com.sttapp.core.view.recyclerview.BaseViewHolder;
import mino.com.sttapp.model.assets.Phrase;

/**
 * Created by jorgesanmartin on 2/26/16.
 */
public class PhraseViewHolder extends BaseViewHolder<Phrase> {

    // Vars
    private Phrase phrase;

    // Views
    @Bind(R.id.title)
    TextView mTitle;

    @Bind(R.id.subtitle)
    TextView mSubtitle;

    public PhraseViewHolder(View itemView) {
        super(itemView, true, true);
//        itemView.setOnCreateContextMenuListener(mOnCreateContextMenuListener);
    }

    @Override
    public void configureItem(Phrase item) {
        this.phrase = item;
        mSubtitle.setText(String.format(getContext().getString(R.string.list_text), getAdapterPosition()));
//        mTitle.setText(item.getInstructions());
//        mSubtitle.setText(item.getText());
    }

//    private final View.OnCreateContextMenuListener mOnCreateContextMenuListener = new View.OnCreateContextMenuListener() {
//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            if (phrase != null) {
//                MenuItem myActionItem = menu.add("My Context Action");
//                myActionItem.setOnMenuItemClickListener(mOnMyActionClickListener);
//            }
//        }
//    };
//
//    private final MenuItem.OnMenuItemClickListener mOnMyActionClickListener = new MenuItem.OnMenuItemClickListener() {
//        @Override
//        public boolean onMenuItemClick(MenuItem item) {
//
//            return true;
//        }
//    };
}
