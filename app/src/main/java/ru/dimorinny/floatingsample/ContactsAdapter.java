package ru.dimorinny.floatingsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created on 8/26/17
 * by nishon.tan@gmail.com
 */


public class ContactsAdapter extends
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public Button messageButton;


        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }

    private List<Contact> mContacts;

    private Context mContext;


    public ContactsAdapter(Context context, List<Contact> contacts) {
        mContacts = contacts;
        mContext = context;
    }


    private Context getContext() {
        return mContext;
    }


    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        View contactView = inflater.inflate(R.layout.item_contact, parent, false);


        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int position) {

        Contact contact = mContacts.get(position);

        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getName());
        Button button = viewHolder.messageButton;
        button.setText("Message");
    }


    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
