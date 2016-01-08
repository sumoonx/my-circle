package com.seu.mycircurt.commodity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;
import com.seu.mycircurt.ui.BaseActivity;

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
public class MyReleaseActivity extends BaseActivity implements View.OnClickListener{
    private ImageView backClick;
    private ListView listView;
    private MyAdapter myAdapter;
    private List<Map<String,String>> listData = new ArrayList<Map<String,String>>();
    public static final String name="MyReleaseName";
    public static final String description="MyReleaseDescription";
    public static final String date="MyReleaseDate";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rls);
        initView();
        initListView();
    }

    /**
     * 该方法主要是完成对于除ListView控件的所有初始化操作：
     * 绑定id、添加监听器等
     */
    private void initView(){
        backClick = (ImageView) findViewById(R.id.my_rls_back);
        backClick.setOnClickListener(this);
    }
    /**
     * 该方法主要是完成对ListView控件的所有初始化操作：
     * 绑定id、添加监听器、绑定adapter等
     */
    private void initListView(){
        listView = (ListView)findViewById(R.id.my_rls_list);
        initListData();
        myAdapter = new MyAdapter(this,listData,R.layout.list_my_rls,
                new String[]{name,description,date},new int[]{R.id.my_rls_list_name,R.id.my_rls_list_content,R.id.my_rls_list_date});
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new MyOnItemClickListener());
    }

    /**
     * 该方法主要是虚构一堆数据出来调试使用
     */
    private void initListData(){
        for(int ii=0;ii<10;ii++)
        {
            Map<String,String> map = new HashMap<String,String>();
            map.put(name,"麦兜"+ii);
            map.put(description,"麦太儿子"+ii);
            map.put(date,"2016-1-8");
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
            case R.id.my_rls_back:
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
    class MyAdapter extends SimpleAdapter{
        private List<Map<String, String>> mItemList;
        public MyAdapter(Context context, List<? extends Map<String, String>> data,
                         int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            mItemList = (List<Map<String, String>>) data;
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
            Map<String ,String> map = mItemList.get(position);
            View view = super.getView(position, convertView, parent);
            //int image  =  map.get("" + position);
            //ImageView imageview = (ImageView)view.findViewById(R.id.myrls);
            //imageview.setBackgroundResource(image);
            TextView name = (TextView)view.findViewById(R.id.my_rls_list_name);
            name.setText(map.get(MyReleaseActivity.name));
            TextView date = (TextView)view.findViewById(R.id.my_rls_list_date);
            date.setText(map.get(MyReleaseActivity.date));
            TextView description = (TextView)view.findViewById(R.id.my_rls_list_content);
            description.setText(map.get(MyReleaseActivity.description));
            return view;
        }
        public void freshData(List<Map<String, String>> newItemList){
            mItemList = newItemList;
        }
    }
    /**
     * 下面这个类就是一个实现了OnItemClickListener接口的类
     * 覆写了onItemClick方法；用于获取点击行的相关信息
     * @warring 该类的定义不可以移动到外面去，因为它需要使用外部类的listData数据
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(myAdapter.getCount()==position+1){
                HashMap<String ,String> map = new HashMap<String, String>();
                TextView mTextView = (TextView)view.findViewById(R.id.my_rls_list_name);
                map.put(MyReleaseActivity.name, mTextView.getText().toString()+"1");
                mTextView = (TextView)view.findViewById(R.id.my_rls_list_content);
                map.put(MyReleaseActivity.description, mTextView.getText().toString()+"1");
                mTextView = (TextView)view.findViewById(R.id.my_rls_list_date);
                map.put(MyReleaseActivity.date, mTextView.getText().toString());
                listData.add(map);
                freshListData();
            }
        }
    }
}
