package com.fesco.bookpay.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fesco.bookpay.activity.ChatActivity;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.ContactsListAdapter;
import com.fesco.bookpay.entity.ContactsChangeBean;
import com.fesco.bookpay.fragment.tabfragment.MyFragment;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gong.min on 2016/9/7.
 */
public class ContactsListFragment extends Fragment {

    private List<ContactsChangeBean.EmpsBean> listEmpsBean;

    private RecyclerView mRecyclerView;
    public static ContactsListFragment getInstance(List<ContactsChangeBean.EmpsBean> listEmpsBean) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(MyFragment.KEY, (Serializable) listEmpsBean);
        ContactsListFragment contancsListFragment = new ContactsListFragment();
        contancsListFragment.setArguments(bundle);
        return contancsListFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            listEmpsBean = (List<ContactsChangeBean.EmpsBean>) bundle.getSerializable(MyFragment.KEY);

        }

        if (mRecyclerView == null) {
            mRecyclerView =
                    (RecyclerView) inflater.inflate(R.layout.list_fragment, container, false);
        }
        return mRecyclerView;
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ContactsListAdapter  contactsListAdapter=new ContactsListAdapter(getActivity().getApplication(), listEmpsBean);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(contactsListAdapter);
        //   mRecyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        contactsListAdapter.setChatUiListenterCallBack(new ContactsListAdapter.ChatUiListenterCallBack() {


            @Override
            public void easeUiCallBack(String chatUserId,String emp_Name) {

                String user="zrfesco_"+chatUserId;

                if (!TextUtils.isEmpty(chatUserId)){
                    Intent chat = new Intent(getActivity(),ChatActivity.class);
                    chat.putExtra(EaseConstant.EXTRA_USER_ID,user);  //对方账号
                    chat.putExtra(EaseConstant.EXTRA_USER_NAME,emp_Name);  //对方账号
                    chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
                    startActivity(chat);
                }else{
                    Toast.makeText(getActivity(), "请输入要聊天的对方的账号", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



}
