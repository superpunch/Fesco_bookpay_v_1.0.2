package com.fesco.bookpay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.ContactsListAdapter;
import com.fesco.bookpay.entity.ContactsChangeBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gong.min on 2016/9/7.
 */
public class ContactsListFragment extends Fragment {

    private static final String KEY = "EXTRA";
    private List<ContactsChangeBean.EmpsBean> listEmpsBean;

    private RecyclerView mRecyclerView;

    public static ContactsListFragment getInstance(List<ContactsChangeBean.EmpsBean> listEmpsBean) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, (Serializable) listEmpsBean);
        ContactsListFragment contancsListFragment = new ContactsListFragment();
        contancsListFragment.setArguments(bundle);
        return contancsListFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            listEmpsBean = (List<ContactsChangeBean.EmpsBean>) bundle.getSerializable(KEY);
        }

        if (mRecyclerView == null) {
            mRecyclerView =
                    (RecyclerView) inflater.inflate(R.layout.list_fragment, container, false);
            System.out.println("mRecyclerView--------重复加载？？？？>>>>>>>"+listEmpsBean+"-----------");
        }
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new ContactsListAdapter(getActivity(), listEmpsBean));
     //   mRecyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
    }
}
