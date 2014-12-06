package org.quanqi.gitlab.loader;

/**
 * Created by cindy on 10/14/14.
 */
public interface ILoader <T> {
    public void load(CallBack<T> callBack);
}
