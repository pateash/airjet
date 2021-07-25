package live.ashish.airjet.view.parameter;

import live.ashish.airjet.model.JobParameter;
import live.ashish.airjet.model.JobParameterType;
import live.ashish.airjet.view.extension.JobParameterRenderer;
import live.ashish.airjet.view.extension.JobParameterRenderers;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class DateParameterRenderer implements JobParameterRenderer {

    @NonNls
    private static final String TYPE_CLASS = "me.leejay.jenkins.dateparameter.DateParameterDefinition";

    static final JobParameterType DATE_PARAMETER = new JobParameterType("DateParameterDefinition", TYPE_CLASS);

    @NotNull
    @Override
    public JobParameterComponent<String> render(@NotNull JobParameter jobParameter) {
        return JobParameterRenderers.createTextField(jobParameter, jobParameter.getDefaultValue());
    }

    @Override
    public boolean isForJobParameter(@NotNull JobParameter jobParameter) {
        return DATE_PARAMETER.equals(jobParameter.getJobParameterType());
    }
}
