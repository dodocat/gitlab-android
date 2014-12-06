package org.quanqi.gitlab.loader;

/**
 * Created by cindy on 10/14/14.
 */
public interface IPageLoader<T> extends ILoader<T> {
    public void load(CallBack<T> callBack, int page, int perPage);
}
