package org.quanqi.gitlab.models;

import com.google.gson.annotations.SerializedName;


import java.util.Date;

import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("GitlabUser")
public class GitlabUser extends BaseModel {
    public static String URL = "/users";
    public static String USERS_URL = "/users";
    public static String USER_URL = "/user"; // for sudo based ops

    @Column("id")
    @Key
    private int id;

    @Column("username")
    private String username;

    @Column("email")
    @Key
    private String email;

    @Column("name")
    private String name;

    @Column("skype")
    private String skype;

    @Column("linkedin")
    private String linkedin;

    @Column("twitter")
    private String twitter;

    @Column("provider")
    private String provider;

    @Column("state")
    private String state;

    @Column("blocked")
    private boolean blocked;

    @Column("privateToken")
    @SerializedName("private_token")
    private String privateToken;
    
    @SerializedName("color_scheme_id")
    private int colorSchemeId;
    
    //@SerializedName("provider")
    private String externProviderName;
    
    @SerializedName("website_url")
    private String websiteUrl;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("bio")
    private String bio;

    @SerializedName("dark_scheme")
    private boolean darkScheme;

    @SerializedName("theme_id")
    private Integer themeId;

    @SerializedName("extern_uid")
    private String externUid;

    @SerializedName("is_admin")
    private boolean isAdmin;

    @SerializedName("can_create_group")
    private boolean canCreateGroup;

    @SerializedName("can_create_project")
    private boolean canCreateProject;

    @SerializedName("can_create_team")
    private boolean canCreateTeam;
    
    @SerializedName("avatar_url")
    private String avatarUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

    public int getColorSchemeId() {
        return colorSchemeId;
    }

    public void setColorSchemeId(int colorSchemeId) {
        this.colorSchemeId = colorSchemeId;
    }

    public String getExternProviderName() {
        return externProviderName;
    }

    public void setExternProviderName(String externProviderName) {
        this.externProviderName = externProviderName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isDarkScheme() {
        return darkScheme;
    }

    public void setDarkScheme(boolean darkScheme) {
        this.darkScheme = darkScheme;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getExternUid() {
        return externUid;
    }

    public void setExternUid(String externUid) {
        this.externUid = externUid;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isCanCreateGroup() {
        return canCreateGroup;
    }

    public void setCanCreateGroup(boolean canCreateGroup) {
        this.canCreateGroup = canCreateGroup;
    }

    public boolean isCanCreateProject() {
        return canCreateProject;
    }

    public void setCanCreateProject(boolean canCreateProject) {
        this.canCreateProject = canCreateProject;
    }

    public boolean isCanCreateTeam() {
        return canCreateTeam;
    }

    public void setCanCreateTeam(boolean canCreateTeam) {
        this.canCreateTeam = canCreateTeam;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
