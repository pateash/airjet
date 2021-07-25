package live.ashish.airjet.view.parameter;

import live.ashish.airjet.model.JobParameter;
import live.ashish.airjet.model.JobParameterType;
import live.ashish.airjet.view.extension.JobParameterRenderer;
import live.ashish.airjet.view.extension.JobParameterRenderers;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class NodeParameterRenderer implements JobParameterRenderer {

    public static final JobParameterType NODE_PARAMETER = new JobParameterType("NodeParameterDefinition",
            "org.jvnet.jenkins.plugins.nodelabelparameter.NodeParameterDefinition");

    private final Map<JobParameterType, BiFunction<JobParameter, String, JobParameterComponent<String>>> converter = new HashMap<>();

    public NodeParameterRenderer() {
        converter.put(new JobParameterType("LabelParameterDefinition",
                        "org.jvnet.jenkins.plugins.nodelabelparameter.LabelParameterDefinition"),
                JobParameterRenderers::createTextField);
        converter.put(new JobParameterType("NodeParameterDefinition",
                        "org.jvnet.jenkins.plugins.nodelabelparameter.NodeParameterDefinition"),
                JobParameterRenderers::createComboBox);
    }

    @NotNull
    @Override
    public JobParameterComponent<String> render(@NotNull JobParameter jobParameter) {
        return converter.getOrDefault(jobParameter.getJobParameterType(), JobParameterRenderers::createErrorLabel)
                .apply(jobParameter, jobParameter.getDefaultValue());
    }

    @Override
    public boolean isForJobParameter(@NotNull JobParameter jobParameter) {
        return converter.containsKey(jobParameter.getJobParameterType());
    }
}
