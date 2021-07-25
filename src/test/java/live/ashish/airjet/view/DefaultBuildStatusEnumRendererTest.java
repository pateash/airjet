package live.ashish.airjet.view;

import com.intellij.icons.AllIcons;
import icons.JenkinsControlIcons;
import live.ashish.airjet.model.BuildStatusEnum;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultBuildStatusEnumRendererTest {

    private final DefaultBuildStatusEnumRenderer buildStatusEnumRenderer = new DefaultBuildStatusEnumRenderer();

    @Test
    public void renderBuildStatus() {
        assertThat(buildStatusEnumRenderer.renderBuildStatus(BuildStatusEnum.SUCCESS)).isEqualTo(JenkinsControlIcons.Job.BLUE);
        assertThat(buildStatusEnumRenderer.renderBuildStatus(BuildStatusEnum.STABLE)).isEqualTo(JenkinsControlIcons.Job.BLUE);
        assertThat(buildStatusEnumRenderer.renderBuildStatus(BuildStatusEnum.FAILURE)).isEqualTo(JenkinsControlIcons.Job.RED);
        assertThat(buildStatusEnumRenderer.renderBuildStatus(BuildStatusEnum.UNSTABLE)).isEqualTo(JenkinsControlIcons.Job.YELLOW);
        assertThat(buildStatusEnumRenderer.renderBuildStatus(BuildStatusEnum.NULL)).isEqualTo(JenkinsControlIcons.Job.GREY);
        assertThat(buildStatusEnumRenderer.renderBuildStatus(BuildStatusEnum.ABORTED)).isEqualTo(JenkinsControlIcons.Job.GREY);
        assertThat(buildStatusEnumRenderer.renderBuildStatus(BuildStatusEnum.FOLDER)).isEqualTo(AllIcons.Nodes.Folder);
        assertThat(buildStatusEnumRenderer.renderBuildStatus(BuildStatusEnum.RUNNING)).isEqualTo(JenkinsControlIcons.Job.GREY);
    }
}
