package com.newsworld;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<HeadLine> list,String  message);
    void onError(String message);

}
