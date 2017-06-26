package com.example.administrator.recyclerviewdemo;

import com.example.swiperefreshrecyclerview.HHBaseListRecyclerViewFragment;
import com.example.swiperefreshrecyclerview.HHBaseRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class ListFragmentDemo extends HHBaseListRecyclerViewFragment<DataModel> {
    private int page_size = 30;//每页大小

    /**
     * 异步获取数据
     *
     * @param pageIndex 页码
     * @param callBack  异步获取数据后的回调  callback.onResponse(list); callback.onFailure(string);
     */
    @Override
    protected void getListDataInThread(int pageIndex, final NetCallBack<DataModel> callBack) {
        //OkHttp get请求
        String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/" + page_size + "/" + pageIndex;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败回调
                callBack.onFailure(e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                //解析json数据
                List<DataModel> modelList = new ArrayList<>();
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++) {
                        DataModel model = new DataModel();
                        JSONObject jsonObject = (JSONObject) array.get(i);
                        model.setUrl(jsonObject.getString("url"));
                        modelList.add(model);
                    }
                    //成功回调
                    callBack.onResponse(modelList);
                } catch (Exception e) {
                    //失败回调
                    callBack.onFailure(e.getMessage().toString());
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 设置recyclerview 的adapter
     */
    @Override
    protected HHBaseRecyclerViewAdapter<DataModel> instanceAdapter(List<DataModel> list) {
        return new MyAdapterDemo(getContext(), list);
    }

    /**
     * 设置item间隔
     *
     * @return
     */
    @Override
    protected int setItemDecoration() {
        return 10;
    }

    /**
     * 设置每页大小
     *
     * @return
     */
    @Override
    protected int setPageSize() {
        return page_size;
    }

    /**
     * 设置LayoutManager类型，默认2
     * 【
     * 0：LinearLayoutManager ，
     * 1：GridLayoutManager，
     * 2：StaggeredGridLayoutManager
     * 】
     * 设置1、2时，setCount（）方法，设置列数，默认2
     *
     * @return
     */
    @Override
    protected int setLayoutManagerType() {
        return 2;
    }

    /**
     * 设置每行列数，默认2
     */
    @Override
    protected int setCount() {
        return 2;
    }

}
