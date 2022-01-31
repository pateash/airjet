package live.ashish.airjet.view.parameter;

import live.ashish.airjet.model.JobParameter;
import live.ashish.airjet.model.JobParameterType;
import live.ashish.airjet.view.extension.JobParameterRenderer;
import live.ashish.airjet.view.extension.JobParameterRenderers;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class PersistentParameterRenderer implements JobParameterRenderer {

    static final JobParameterType BOOLEAN = new JobParameterType("PersistentBooleanParameterDefinition",
            "com.gem.persistentparameter.PersistentBooleanParameterDefinition");

    static final JobParameterType STRING = new JobParameterType("PersistentStringParameterDefinition",
            "com.gem.persistentparameter.PersistentStringParameterDefinition");

    static final JobParameterType CHOICE = new JobParameterType("PersistentChoiceParameterDefinition",
            "com.gem.persistentparameter.PersistentChoiceParameterDefinition");

    static final JobParameterType TEXT = new JobParameterType("PersistentTextParameterDefinition",
            "com.gem.persistentparameter.PersistentTextParameterDefinition");

    private final Map<JobParameterType, BiFunction<JobParameter, String, JobParameterComponent<?>>> converter = new HashMap<>();

    public PersistentParameterRenderer() {
        converter.put(BOOLEAN, JobParameterRenderers::createCheckBox);
        converter.put(STRING, JobParameterRenderers::createTextField);
        converter.put(TEXT, JobParameterRenderers::createTextArea);
        converter.put(CHOICE, JobParameterRenderers::createComboBox);
    }

    @NotNull
    @Override
    public JobParameterComponent<?> render(@NotNull JobParameter jobParameter) {
        final JobParameterType jobParameterType = jobParameter.getJobParameterType();
        final String defaultValue = jobParameter.getDefaultValue();
        return converter.getOrDefault(jobParameterType, JobParameterRenderers::createErrorLabel).apply(jobParameter, defaultValue);
    }

    @Override
    public boolean isForJobParameter(@NotNull JobParameter jobParameter) {
        return converter.containsKey(jobParameter.getJobParameterType());
    }
}
