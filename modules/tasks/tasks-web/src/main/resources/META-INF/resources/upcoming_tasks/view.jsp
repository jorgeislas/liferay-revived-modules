<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<div class="tasks-entries-container">
	<ul class="tasks-entries">

		<%
		List<TasksEntry> taskEntries = tasksEntryLocalService.getTasksEntries(0, user.getUserId(), 0, 0, TasksEntryConstants.STATUS_OPEN, new long[0], new long[0], 0, 10);

		for (TasksEntry tasksEntry : taskEntries) {
			String taskHREF = null;

			if (TasksEntryPermission.contains(permissionChecker, tasksEntry, ActionKeys.UPDATE)) {
				LiferayPortletURL liferayPortletURL = liferayPortletResponse.createLiferayPortletURL(TasksWebKeys.TASKS_PORTLET, PortletRequest.RENDER_PHASE);

				liferayPortletURL.setParameter("mvcPath", "/tasks/view_task.jsp");
				liferayPortletURL.setParameter("tasksEntryId", String.valueOf(tasksEntry.getTasksEntryId()));
				liferayPortletURL.setWindowState(LiferayWindowState.POP_UP);

				taskHREF = liferayPortletURL.toString();
			}

			String cssClass = "tasks-title";

			if (tasksEntry.getPriority() == 1) {
				cssClass = cssClass.concat(" high");
			}
			else if (tasksEntry.getPriority() == 2) {
				cssClass = cssClass.concat(" normal");
			}
			else {
				cssClass = cssClass.concat(" low");
			}
		%>

			<li class="<%= cssClass %>">
				<c:choose>
					<c:when test="<%= Validator.isNotNull(taskHREF) %>">
						<a href="javascript:;" onClick="Liferay.Tasks.openTask('<%= taskHREF %>');">
							<i class="icon-circle"></i>

							<%= HtmlUtil.escape(tasksEntry.getTitle()) %>
						</a>
					</c:when>
					<c:otherwise>
						<span>
							<i class="icon-circle"></i>

							<%= HtmlUtil.escape(tasksEntry.getTitle()) %>
						</span>
					</c:otherwise>
				</c:choose>
			</li>

		<%
		}
		%>

	</ul>

	<div class="view-all-tasks">

		<%
		long tasksPlid = PortalUtil.getPlidFromPortletId(group.getGroupId(), TasksWebKeys.TASKS_PORTLET);

		PortletURL portletURL = null;

		if (tasksPlid != 0) {
			portletURL = PortletURLFactoryUtil.create(request, TasksWebKeys.TASKS_PORTLET, tasksPlid, PortletRequest.RENDER_PHASE);
		}
		%>

		<c:if test="<%= portletURL != null %>">
			<a href="<%= portletURL %>"><liferay-ui:message key="view-all-tasks" /></a>
		</c:if>
	</div>
</div>

<aui:script use="liferay-tasks">
	Liferay.Tasks.initUpcomingTasks(
		{
			upcomingTasksListURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/upcoming_tasks/view.jsp" /></portlet:renderURL>'
		}
	);
</aui:script>