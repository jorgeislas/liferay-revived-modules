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

package com.liferay.powwow.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.service.PowwowParticipantLocalService;
import com.liferay.powwow.service.persistence.PowwowMeetingFinder;
import com.liferay.powwow.service.persistence.PowwowMeetingPersistence;
import com.liferay.powwow.service.persistence.PowwowParticipantPersistence;
import com.liferay.powwow.service.persistence.PowwowServerPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the powwow participant local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.powwow.service.impl.PowwowParticipantLocalServiceImpl}.
 * </p>
 *
 * @author Shinn Lok
 * @see com.liferay.powwow.service.impl.PowwowParticipantLocalServiceImpl
 * @generated
 */
public abstract class PowwowParticipantLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   PowwowParticipantLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PowwowParticipantLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.powwow.service.PowwowParticipantLocalServiceUtil</code>.
	 */

	/**
	 * Adds the powwow participant to the database. Also notifies the appropriate model listeners.
	 *
	 * @param powwowParticipant the powwow participant
	 * @return the powwow participant that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PowwowParticipant addPowwowParticipant(
		PowwowParticipant powwowParticipant) {

		powwowParticipant.setNew(true);

		return powwowParticipantPersistence.update(powwowParticipant);
	}

	/**
	 * Creates a new powwow participant with the primary key. Does not add the powwow participant to the database.
	 *
	 * @param powwowParticipantId the primary key for the new powwow participant
	 * @return the new powwow participant
	 */
	@Override
	@Transactional(enabled = false)
	public PowwowParticipant createPowwowParticipant(long powwowParticipantId) {
		return powwowParticipantPersistence.create(powwowParticipantId);
	}

	/**
	 * Deletes the powwow participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param powwowParticipantId the primary key of the powwow participant
	 * @return the powwow participant that was removed
	 * @throws PortalException if a powwow participant with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PowwowParticipant deletePowwowParticipant(long powwowParticipantId)
		throws PortalException {

		return powwowParticipantPersistence.remove(powwowParticipantId);
	}

	/**
	 * Deletes the powwow participant from the database. Also notifies the appropriate model listeners.
	 *
	 * @param powwowParticipant the powwow participant
	 * @return the powwow participant that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PowwowParticipant deletePowwowParticipant(
		PowwowParticipant powwowParticipant) {

		return powwowParticipantPersistence.remove(powwowParticipant);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			PowwowParticipant.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return powwowParticipantPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return powwowParticipantPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return powwowParticipantPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return powwowParticipantPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return powwowParticipantPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public PowwowParticipant fetchPowwowParticipant(long powwowParticipantId) {
		return powwowParticipantPersistence.fetchByPrimaryKey(
			powwowParticipantId);
	}

	/**
	 * Returns the powwow participant with the primary key.
	 *
	 * @param powwowParticipantId the primary key of the powwow participant
	 * @return the powwow participant
	 * @throws PortalException if a powwow participant with the primary key could not be found
	 */
	@Override
	public PowwowParticipant getPowwowParticipant(long powwowParticipantId)
		throws PortalException {

		return powwowParticipantPersistence.findByPrimaryKey(
			powwowParticipantId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			powwowParticipantLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PowwowParticipant.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("powwowParticipantId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			powwowParticipantLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(PowwowParticipant.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"powwowParticipantId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			powwowParticipantLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PowwowParticipant.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("powwowParticipantId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return powwowParticipantLocalService.deletePowwowParticipant(
			(PowwowParticipant)persistedModel);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return powwowParticipantPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the powwow participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow participants
	 * @param end the upper bound of the range of powwow participants (not inclusive)
	 * @return the range of powwow participants
	 */
	@Override
	public List<PowwowParticipant> getPowwowParticipants(int start, int end) {
		return powwowParticipantPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of powwow participants.
	 *
	 * @return the number of powwow participants
	 */
	@Override
	public int getPowwowParticipantsCount() {
		return powwowParticipantPersistence.countAll();
	}

	/**
	 * Updates the powwow participant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param powwowParticipant the powwow participant
	 * @return the powwow participant that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PowwowParticipant updatePowwowParticipant(
		PowwowParticipant powwowParticipant) {

		return powwowParticipantPersistence.update(powwowParticipant);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			PowwowParticipantLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		powwowParticipantLocalService = (PowwowParticipantLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PowwowParticipantLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PowwowParticipant.class;
	}

	protected String getModelClassName() {
		return PowwowParticipant.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				powwowParticipantPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@Reference
	protected PowwowMeetingPersistence powwowMeetingPersistence;

	@Reference
	protected PowwowMeetingFinder powwowMeetingFinder;

	protected PowwowParticipantLocalService powwowParticipantLocalService;

	@Reference
	protected PowwowParticipantPersistence powwowParticipantPersistence;

	@Reference
	protected PowwowServerPersistence powwowServerPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}