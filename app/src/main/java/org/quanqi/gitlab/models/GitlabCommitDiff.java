package org.quanqi.gitlab.models;

import com.google.gson.annotations.SerializedName;


public class GitlabCommitDiff {

    public final static String URL = "/diff";

    @SerializedName("diff")
    private String _diff;

    @SerializedName("new_path")
    private String _newPath;

    @SerializedName("old_path")
    private String _oldPath;

    @SerializedName("a_mode")
    private String _aMode;

    @SerializedName("b_mode")
    private String _bMode;

    @SerializedName("new_file")
    private boolean _newFile;

    @SerializedName("renamed_file")
    private boolean _renamedFile;

    @SerializedName("deleted_file")
    private boolean _deletedFile;

    public String getDiff() {
        return _diff;
    }

    public void setDiff(String diff) {
        _diff = diff;
    }

    public String getNewPath() {
        return _newPath;
    }

    public void setNewPath(String newPath) {
        _newPath = newPath;
    }

    public String getOldPath() {
        return _oldPath;
    }

    public void setOldPath(String oldPath) {
        _oldPath = oldPath;
    }

    public String getAMode() {
        return _aMode;
    }

    public void setAMode(String aMode) {
        _aMode = aMode;
    }

    public String getBMode() {
        return _bMode;
    }

    public void setBMode(String bMode) {
        _bMode = bMode;
    }

    public boolean getNewFile() {
        return _newFile;
    }

    public void setNewFile(boolean newFile) {
        _newFile = newFile;
    }

    public boolean getRenamedFile() {
        return _renamedFile;
    }

    public void setRenamedFile(boolean renamedFile) {
        _renamedFile = renamedFile;
    }

    public boolean getDeletedFile() {
        return _deletedFile;
    }

    public void setDeletedFile(boolean deletedFile) {
        _deletedFile = deletedFile;
    }
}
