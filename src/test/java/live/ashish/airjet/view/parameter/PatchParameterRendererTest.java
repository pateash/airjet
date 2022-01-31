package live.ashish.airjet.view.parameter;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import live.ashish.airjet.model.BuildInJobParameter;
import live.ashish.airjet.model.JobParameter;
import live.ashish.airjet.model.JobParameterType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PatchParameterRendererTest implements JobParameterTest {

    private final PatchParameterRenderer jobParameterRenderer = new PatchParameterRenderer();

    @Test
    public void render() {
        final JobParameter jobParameter = createJobParameter(PatchParameterRenderer.TYPE);
        JobParameterComponent<?> jobParameterComponent = jobParameterRenderer.render(jobParameter);
        assertThat(jobParameterComponent.getViewElement()).isInstanceOf(TextFieldWithBrowseButton.class);
        assertThat(jobParameterComponent.getJobParameter()).isEqualTo(jobParameter);

        jobParameterComponent = jobParameterRenderer.render(createJobParameter(BuildInJobParameter.ChoiceParameterDefinition));
        assertThat(jobParameterComponent.getViewElement()).isInstanceOf(TextFieldWithBrowseButton.class);
    }

    @Test
    public void isForJobParameter() {
        assertThat(jobParameterRenderer.isForJobParameter(createJobParameter(PatchParameterRenderer.TYPE)))
                .isTrue();
        assertThat(jobParameterRenderer.isForJobParameter(createJobParameter(new JobParameterType("PatchParameterDefinition",
                "org.jenkinsci.plugins.patch2.PatchParameterDefinition"))))
                .isFalse();
        assertThat(jobParameterRenderer.isForJobParameter(createJobParameter(BuildInJobParameter.ChoiceParameterDefinition)))
                .isFalse();
    }

}
