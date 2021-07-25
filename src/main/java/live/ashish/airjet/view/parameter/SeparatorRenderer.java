package live.ashish.airjet.view.parameter;

import live.ashish.airjet.model.JobParameter;
import live.ashish.airjet.model.JobParameterType;
import live.ashish.airjet.view.extension.JobParameterRenderer;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.util.Optional;

/**
 * Render _class: jenkins.plugins.parameter_separator.ParameterSeparatorDefinition
 */
public class SeparatorRenderer implements JobParameterRenderer {

    @Nonnull
    @Override
    public Optional<JLabel> createLabel(@NotNull JobParameter jobParameter) {
        return Optional.empty();
    }

    @NotNull
    @Override
    public JobParameterComponent<String> render(@NotNull JobParameter jobParameter) {
        return new JobParameterComponent<>(jobParameter, new JSeparator());
    }

    @Override
    public boolean isForJobParameter(@NotNull JobParameter jobParameter) {
        return Optional.of(jobParameter).map(JobParameter::getJobParameterType)
                .map(JobParameterType::getName)
                .filter("ParameterSeparatorDefinition"::equals).isPresent();
    }
}
