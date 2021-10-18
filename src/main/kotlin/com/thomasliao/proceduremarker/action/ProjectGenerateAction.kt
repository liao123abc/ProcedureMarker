package com.thomasliao.proceduremarker.action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.ex.ProjectManagerEx

class ProjectGenerateAction : DumbAwareAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val projectManagerEx = ProjectManagerEx.getInstanceEx()
        projectManagerEx.loadAndOpenProject("D:\\gitlab\\hydid")
    }
}