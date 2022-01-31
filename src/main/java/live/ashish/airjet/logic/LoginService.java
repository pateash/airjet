package live.ashish.airjet.logic;

import live.ashish.airjet.model.Jenkins;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import live.ashish.airjet.JenkinsAppSettings;
import live.ashish.airjet.JenkinsSettings;
import org.jetbrains.annotations.NotNull;


public class LoginService {

    private static final Logger logger = Logger.getInstance(LoginService.class);
    private final Project project;

    public LoginService(final Project project) {
        this.project = project;
    }

    public void performAuthentication() {
        if(!ApplicationManager.getApplication().isDispatchThread()){
            logger.warn("LoginService.performAuthentication called from outside of EDT");
        }
        final JenkinsAppSettings settings = JenkinsAppSettings.getSafeInstance(project);
        final RequestManager requestManager = RequestManager.getInstance(project);

        final AuthenticationNotifier publisher = ApplicationManager.getApplication().getMessageBus()
                .syncPublisher(AuthenticationNotifier.USER_LOGGED_IN);
        if (!settings.isServerUrlSet()) {
            logger.warn("Jenkins server is not setup, authentication will not happen");
            publisher.emptyConfiguration();
            return;
        }
        final JenkinsSettings jenkinsSettings = JenkinsSettings.getSafeInstance(project);
        new Task.Backgroundable(project, "Authenticating jenkins", false, JenkinsLoadingTaskOption.INSTANCE) {

            private Jenkins jenkinsWorkspace;

            @Override
            public void onSuccess() {
                publisher.afterLogin(jenkinsWorkspace);
            }

            @Override
            public void onCancel() {
                publisher.loginCancelled();
            }

            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                try {
                    requestManager.authenticate(settings, jenkinsSettings);
                    jenkinsWorkspace = requestManager.loadJenkinsWorkspace(settings);
                } catch (Exception ex) {
                    publisher.loginFailed(ex);
                    indicator.cancel();
                }
            }
        }.queue();
    }

    public static LoginService getInstance(Project project) {
        return project.getService(LoginService.class);
    }
}
