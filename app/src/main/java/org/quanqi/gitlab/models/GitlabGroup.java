package org.quanqi.gitlab.models;

import com.google.gson.annotations.SerializedName;


public class GitlabGroup {

    public static final String URL = "/groups";

    private Integer _id;
    private String _name;
    private String _path;

    @SerializedName("ldap_cn")
    private String _ldapCn;

    @SerializedName("ldap_access")
    private Integer _ldapAccess;

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getPath() {
        return _path;
    }

    public void setPath(String path) {
        _path = path;
    }

    public String getLdapCn() {
        return _ldapCn;
    }

    public void setLdapCn(String ldapCn) {
        this._ldapCn = ldapCn;
    }

    public GitlabAccessLevel getLdapAccess() {
        return GitlabAccessLevel.fromAccessValue(_ldapAccess);
    }

    public void setLdapAccess(GitlabAccessLevel ldapGitlabAccessLevel) {
        this._ldapAccess = ldapGitlabAccessLevel.accessValue;
    }
}
