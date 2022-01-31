/*
 * Copyright (c) 2013 David Boissier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package live.ashish.airjet;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.ChangeListDecorator;
import com.intellij.openapi.vcs.changes.LocalChangeList;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import live.ashish.airjet.model.Build;
import live.ashish.airjet.model.Job;
import live.ashish.airjet.view.BrowserPanel;

import java.util.Map;

public class WatchedJobsDecorator implements ChangeListDecorator {

    private Project project;

    public WatchedJobsDecorator(Project project) {
        this.project = project;
    }

    @Override
    public void decorateChangeList(LocalChangeList localChangeList, ColoredTreeCellRenderer coloredTreeCellRenderer, boolean b, boolean b2, boolean b3) {
        BrowserPanel browserPanel = BrowserPanel.getInstance(project);
        Map<String, Job> jobs = browserPanel.getWatched();
        if (jobs.containsKey(localChangeList.getName())) {
            Build build = jobs.get(localChangeList.getName()).getLastBuild();
            String status = build.getStatus().getStatus();
            if (build.isBuilding()) {
                status = "Running";
            }
            coloredTreeCellRenderer.append(String.format(" - last build %s: %s", build.getDisplayNumber(), status),
                    SimpleTextAttributes.GRAYED_ATTRIBUTES);
            coloredTreeCellRenderer.repaint();
        }
    }
}
