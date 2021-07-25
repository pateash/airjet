package live.ashish.airjet.view.parameter;

import live.ashish.airjet.model.JobParameter;
import live.ashish.airjet.model.JobParameterType;
import live.ashish.airjet.view.extension.JobParameterRenderer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class HiddenParameterRenderer implements JobParameterRenderer {

    static final JobParameterType TYPE = new JobParameterType("WHideParameterDefinition",
            "com.wangyin.parameter.WHideParameterDefinition");

    @NotNull
    @Override
    public JobParameterComponent<String> render(@NotNull JobParameter jobParameter) {
        return new JobParameterComponent<>(jobParameter, new JLabel(), false);
    }

    @Override
    public boolean isForJobParameter(@NotNull JobParameter jobParameter) {
        return TYPE.equals(jobParameter.getJobParameterType());
    }
}
