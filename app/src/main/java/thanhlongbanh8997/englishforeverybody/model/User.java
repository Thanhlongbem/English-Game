package thanhlongbanh8997.englishforeverybody.model;

import java.net.URI;

import io.realm.RealmObject;

public class User extends RealmObject {
    private String userName;
    private String userPreposition;
    private String userArticle;
    private String userRelativeClause;
    private String userPassiveVoice;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserPreposition() {
        return userPreposition;
    }

    public void setUserPreposition(String userPreposition) {
        this.userPreposition = userPreposition;
    }

    public String getUserArticle() {
        return userArticle;
    }

    public void setUserArticle(String userArticle) {
        this.userArticle = userArticle;
    }

    public String getUserRelativeClause() {
        return userRelativeClause;
    }

    public void setUserRelativeClause(String userRelativeClause) {
        this.userRelativeClause = userRelativeClause;
    }

    public String getUserPassiveVoice() {
        return userPassiveVoice;
    }

    public void setUserPassiveVoice(String userPassiveVoice) {
        this.userPassiveVoice = userPassiveVoice;
    }
}
