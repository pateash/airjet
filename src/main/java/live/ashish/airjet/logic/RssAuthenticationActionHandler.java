package live.ashish.airjet.logic;

import live.ashish.airjet.model.Jenkins;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBusConnection;

public class RssAuthenticationActionHandler implements AuthenticationNotifier, Disposable {
    private final Project project;
    private final MessageBusConnection connection;

    public RssAuthenticationActionHandler(final Project project) {
        this.project = project;
        connection = ApplicationManager.getApplication().getMessageBus().connect();
    }

    public void subscribe() {
        connection.subscribe(USER_LOGGED_IN, this);
    }

    public static RssAuthenticationActionHandler getInstance(Project project) {
        return project.getService(RssAuthenticationActionHandler.class);
    }

    @Override
    public void emptyConfiguration() {
        // nothing to do
    }

    @Override
    public void afterLogin(Jenkins jenkinsWorkspace) {
        RssLogic rssLogic = RssLogic.getInstance(project);
        rssLogic.initScheduledJobs();
    }

    @Override
    public void loginCancelled() {
        //nothing to do
    }

    @Override
    public void loginFailed(Exception ex) {
        //nothing to do
    }

    @Override
    public void dispose() {
        connection.disconnect();
    }
}
