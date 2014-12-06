package org.quanqi.gitlab.models;

import se.emilsjolander.sprinkles.annotations.Table;

@Table("GitlabSession")
public class GitlabSession extends GitlabUser {
    public static final String URL = "/session";
}
