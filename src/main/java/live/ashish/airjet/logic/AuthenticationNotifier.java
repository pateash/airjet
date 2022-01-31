package live.ashish.airjet.logic;

import live.ashish.airjet.model.Jenkins;
import com.intellij.util.messages.Topic;

public interface AuthenticationNotifier {
    Topic<AuthenticationNotifier> USER_LOGGED_IN = Topic.create("User Logged In", AuthenticationNotifier.class);

    void emptyConfiguration();

    void afterLogin(Jenkins jenkinsWorkspace);

    void loginCancelled();

    void loginFailed(Exception ex);
}
