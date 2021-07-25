package live.ashish.airjet.view.action;

import live.ashish.airjet.model.Job;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface JobAction {

    void execute(@NotNull Job job);
}
