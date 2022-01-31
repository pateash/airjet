package live.ashish.airjet.view.parameter;

import live.ashish.airjet.model.JobParameter;
import live.ashish.airjet.model.JobParameterType;
import com.intellij.openapi.vfs.VirtualFile;
import live.ashish.airjet.view.extension.JobParameterRenderer;
import live.ashish.airjet.view.extension.JobParameterRenderers;
import org.jetbrains.annotations.NotNull;

public class PatchParameterRenderer implements JobParameterRenderer {

    static final JobParameterType TYPE = new JobParameterType("PatchParameterDefinition",
            "org.jenkinsci.plugins.patch.PatchParameterDefinition");

    @NotNull
    @Override
    public JobParameterComponent<VirtualFile> render(@NotNull JobParameter jobParameter) {
        return JobParameterRenderers.createFileUpload(jobParameter, jobParameter.getDefaultValue());
    }

    @Override
    public boolean isForJobParameter(@NotNull JobParameter jobParameter) {
        return TYPE.equals(jobParameter.getJobParameterType());
    }
}
