package com.example.customlistviewsample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileAdapter extends BaseAdapter {
    // 전체 데이터셋을 담고 있는 ArrayList 객체 생성
    ArrayList<ProfileItem> dataset = new ArrayList<>();


    // 데이터셋의 전체 개수를 반환하는 메서드
    @Override
    public int getCount() {
        return dataset.size();
    }

    // 데이터셋의 position에 위치한 데이터 반환
    @Override
    public Object getItem(int position) {
        return dataset.get(position);
    }

    // 데이터셋의 position에 위치한 id값 반환
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 리스트뷰에 들어갈 item을 제작하는 메서드
    @Override
    public View getView(int position, // 위치정보(dataset의 인덱스번호와 일치)
                        View convertView, // 생성될 뷰 item의 뷰
                        ViewGroup parent) { // 부모 레이아웃 listview
        // View 생성 및 수정 작업을 하기 위한 정보들을
        // 취득하기 위해 사용되는 부분
        // activity에 포함되어있는 listview(ViewGroup parent)를
        // 통해서 activity가 할수 있는 작업(View객체화, 로드, 생성 ..)
        // 들에 대한 정보를 넘겨 받아 작업
        Context context = parent.getContext();
        // 한번도 생성되지 않은(뷰 객체화되지 않은) item에 대해서
        // 객체화를 진행하는 부분
        if(convertView == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE
                    );

            convertView = inflater.inflate(R.layout.item_profile,
                    parent,
                    false);
        }

        // item에 들어있는 View들을 참조하기 위해
        // convertview.find.... 를 사용함
        ImageView ivIcon = convertView.findViewById(R.id.ivIcon);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvMessage = convertView.findViewById(R.id.tvMessage);

        // item에 데이터를 삽입하는 과정
        ProfileItem item = dataset.get(position);
        ivIcon.setImageDrawable(item.getIcon());
        tvName.setText(item.getName());
        tvMessage.setText(item.getMessage());

        // 이벤트 처리...

        // 생성된 item을 반환환
        return convertView;
    }

    public void addProfileItemData(Drawable icon,
                                   String name,
                                   String message){
        dataset.add(new ProfileItem(icon, name, message));
    }
}
