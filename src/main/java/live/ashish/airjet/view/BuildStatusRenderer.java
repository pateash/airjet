package live.ashish.airjet.view;

import live.ashish.airjet.model.BuildStatusEnum;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@FunctionalInterface
public interface BuildStatusRenderer {

    @NotNull
    Icon renderBuildStatus(@NotNull BuildStatusEnum buildStatus);
}
