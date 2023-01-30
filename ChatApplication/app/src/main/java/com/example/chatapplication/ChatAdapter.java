package com.example.chatapplication;

import android.content.Context;
// import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ChatData> mDataset;
    private String myNickName;
    // 각 데이터 항목의 뷰에 대한 참조 제공
    // 복잡한 데이터 항목에는 항목당 둘 이상의 뷰가 필요할 수 있으며,
    // 사용자는 뷰 홀더에 있는 데이터 항목에 대한 모든 뷰에 액세스 권한을 제공
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // 이 경우 각 데이터 항목은 문자열이어야만 함
        public TextView TextView_nickname;
        public TextView TextView_msg;
        public View rootView;
        public MyViewHolder(View v) {
            super(v);
            TextView_nickname = v.findViewById(R.id.TextView_nickname);
            TextView_msg = v.findViewById(R.id.TextView_msg);
            rootView = v;

        }


    }

    // 적절한 생성자 제공(데이터 세트의 종류에 따라 다름)
    public ChatAdapter(List<ChatData> myDataset, Context context, String myNickName) {
        //{"1","2"}
        mDataset = myDataset;
        this.myNickName = myNickName;
    }

    // 새 뷰 생성 (레이아웃 관리자 호출)
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // 새 뷰 생성
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_chat, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

        // 뷰 내용 변경 (레이아웃 관리자 호출)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - 이 위치에 데이터 세트에서 요소 가져오기
        // - 뷰의 내용을 해당 요소로 변경
        ChatData chat = mDataset.get(position);

        holder.TextView_nickname.setText(chat.getNickname());
        holder.TextView_msg.setText(chat.getMsg());

        if(chat.getNickname().equals(this.myNickName)) {
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else {
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }

    }

    // 데이터 세트의 크기 반환 (레이아웃 관리자 호출)
    @Override
    public int getItemCount() {

        //삼항 연산자
        return mDataset == null ? 0 :  mDataset.size();
    }

    public ChatData getChat(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat) {
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1); //갱신
    }

}
