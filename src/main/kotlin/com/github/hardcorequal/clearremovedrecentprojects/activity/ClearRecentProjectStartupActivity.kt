package com.github.hardcorequal.clearremovedrecentprojects.activity


import com.intellij.ide.RecentProjectListActionProvider
import com.intellij.ide.RecentProjectsManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.util.io.FileUtil

class ClearRecentProjectsAction: StartupActivity, DumbAware {

    override fun runActivity(project: Project) {
        val files = RecentProjectListActionProvider.getInstance().getActions()
            .map { it.templatePresentation.description }
            .filter { !FileUtil.exists(it) }

        files.forEach { RecentProjectsManager.getInstance().removePath(it) }
    }
}

