package com.seu.mycircurt.social;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 * @author yqman on 2016/1/8
 * e-mail evanman0618@163.com
 * @version v0.0
 */
public class MyMessageActivity extends Activity implements View.OnClickListener{
    private ImageView backclick;
    private ListView listView;
    private MyAdapter myAdapter;
    private List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
    public static final String image="MyImage";
    public static final String name="MyName";
    public static final String description="MyDescription";
    public static final String message="MyMessage";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg);
        initView();
        initListView();
    }

    /**
     * 该方法主要是完成对于除ListView控件的所有初始化操作：
     * 绑定id、添加监听器等
     */
    private void initView(){
        backclick = (ImageView) findViewById(R.id.my_msg_back);
        backclick.setOnClickListener(this);
    }
    /**
     * 该方法主要是完成对ListView控件的所有初始化操作：
     * 绑定id、添加监听器、绑定adapter等
     */
    private void initListView(){
        listView = (ListView)findViewById(R.id.my_msg_list);
        initListData();
        myAdapter = new MyAdapter(this,listData,R.layout.list_my_msg,
                new String[]{image,name,message,description},
                new int[]{R.id.my_msg_list_head,R.id.my_msg_list_id,R.id.my_msg_list_resp,R.id.my_msg_list_detail});
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new MyOnItemClickListener());
    }

    /**
     * 该方法主要是虚构一堆数据出来调试使用
     */
    private void initListData(){
        for(int ii=0;ii<10;ii++)
        {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put(image,R.drawable.head);
            map.put(name,"麦兜"+ii);
            map.put(description,"麦太儿子"+ii);
            map.put(message,"便宜点卖了吧，都烂了");
            listData.add(map);
        }
    }

    /**
     * 该方法用于刷新ListView
     */
    private void freshListData(){
        myAdapter.freshData(listData);
        myAdapter.notifyDataSetChanged();
        listView.setSelection(myAdapter.getCount() - 1);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_msg_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Logger.d("click back");
        finish();
    }
    /**
     * 下面这个类就是一个继承自SimpleAdapter的适配器
     * 覆写了int getCount()、Object getItem(int pos)、long getItemId(int pos)、View getView（）
     * 四个方法；
     * 然后写了一个freshData方法用于更新MyAdapter中的mItemList域；
     * @warring 该类的定义可以移动到外面去，即可以不是一个内部类
     */
    class MyAdapter extends SimpleAdapter {
        private List<Map<String, Object>> mItemList;
        public MyAdapter(Context context, List<? extends Map<String,Object>> data,
                         int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            mItemList = (List<Map<String, Object>>) data;
        }
        public int getCount() {
            return mItemList.size();
        }

        public Object getItem(int pos) {
            return pos;
        }

        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Map<String ,Object> map = mItemList.get(position);
            View view = super.getView(position, convertView, parent);
            int image  =  (Integer)map.get(MyMessageActivity.image);
            ImageView imageview = (ImageView)view.findViewById(R.id.my_msg_list_head);
            imageview.setBackgroundResource(image);
            TextView name = (TextView)view.findViewById(R.id.my_msg_list_id);
            name.setText((String)map.get(MyMessageActivity.name));
            TextView date = (TextView)view.findViewById(R.id.my_msg_list_resp);
            date.setText((String)map.get(MyMessageActivity.message));
            TextView description = (TextView)view.findViewById(R.id.my_msg_list_detail);
            description.setText((String)map.get(MyMessageActivity.description));
            return view;
        }
        public void freshData(List<Map<String, Object>> newItemList){
            mItemList = newItemList;
        }
    }
    /**
     * 下面这个类就是一个实现了OnItemClickListener接口的类
     * 覆写了onItemClick方法；用于获取点击行的相关信息
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String ,String> map = new HashMap<String, String>();
                TextView mTextView = (TextView)view.findViewById(R.id.my_msg_list_id);
                map.put(MyMessageActivity.name, mTextView.getText().toString()+"1");
                mTextView = (TextView)view.findViewById(R.id.my_msg_list_detail);
                map.put(MyMessageActivity.description, mTextView.getText().toString()+"1");
                mTextView = (TextView)view.findViewById(R.id.my_msg_list_resp);
                map.put(MyMessageActivity.message, mTextView.getText().toString());
                Toast.makeText(MyMessageActivity.this, ""+map.get(MyMessageActivity.name)+" "+
                        map.get(MyMessageActivity.message), Toast.LENGTH_SHORT).show();
        }
    }
}
