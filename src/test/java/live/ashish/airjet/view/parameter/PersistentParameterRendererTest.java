package live.ashish.airjet.view.parameter;

import live.ashish.airjet.model.BuildInJobParameter;
import live.ashish.airjet.model.JobParameter;
import live.ashish.airjet.model.JobParameterType;
import live.ashish.airjet.view.extension.JobParameterRenderers;
import org.junit.Test;

import javax.swing.*;

import static org.assertj.core.api.Assertions.*;

public class PersistentParameterRendererTest implements JobParameterTest {

    private final PersistentParameterRenderer jobParameterRenderer = new PersistentParameterRenderer();

    @Test
    public void render() {
        final JobParameter jobParameter = createJobParameter(PersistentParameterRenderer.BOOLEAN);
        JobParameterComponent<?> jobParameterComponent = jobParameterRenderer.render(jobParameter);
        assertThat(jobParameterComponent.getViewElement()).isInstanceOf(JCheckBox.class);
        assertThat(jobParameterComponent.getJobParameter()).isEqualTo(jobParameter);

        jobParameterComponent = jobParameterRenderer.render(createJobParameter(PersistentParameterRenderer.STRING));
        assertThat(jobParameterComponent.getViewElement()).isInstanceOf(JTextField.class);

        jobParameterComponent = jobParameterRenderer.render(createJobParameter(PersistentParameterRenderer.TEXT));
        assertThat(jobParameterComponent.getViewElement()).isInstanceOf(JTextArea.class);

        jobParameterComponent = jobParameterRenderer.render(createJobParameter(PersistentParameterRenderer.CHOICE));
        assertThat(jobParameterComponent.getViewElement()).isInstanceOf(JComboBox.class);

        jobParameterComponent = jobParameterRenderer.render(createJobParameter(BuildInJobParameter.ChoiceParameterDefinition));
        assertThat(jobParameterComponent.getViewElement()).isInstanceOf(JobParameterRenderers.ErrorLabel.class);
    }

    @Test
    public void isForJobParameter() {
        assertThat(jobParameterRenderer.isForJobParameter(createJobParameter(PersistentParameterRenderer.BOOLEAN)))
                .isTrue();
        assertThat(jobParameterRenderer.isForJobParameter(createJobParameter(PersistentParameterRenderer.STRING)))
                .isTrue();
        assertThat(jobParameterRenderer.isForJobParameter(createJobParameter(PersistentParameterRenderer.TEXT)))
                .isTrue();
        assertThat(jobParameterRenderer.isForJobParameter(createJobParameter(PersistentParameterRenderer.CHOICE)))
                .isTrue();
        assertThat(jobParameterRenderer.isForJobParameter(createJobParameter(new JobParameterType("PersistentStringParameterDefinition",
                "com.gem.persistentparameterInvalid.PersistentStringParameterDefinition"))))
                .isFalse();
        assertThat(jobParameterRenderer.isForJobParameter(createJobParameter(BuildInJobParameter.ChoiceParameterDefinition)))
                .isFalse();
    }

}
