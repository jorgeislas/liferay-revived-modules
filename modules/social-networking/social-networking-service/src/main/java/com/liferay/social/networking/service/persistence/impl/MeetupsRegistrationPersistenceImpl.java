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

package com.liferay.social.networking.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.social.networking.exception.NoSuchMeetupsRegistrationException;
import com.liferay.social.networking.model.MeetupsRegistration;
import com.liferay.social.networking.model.impl.MeetupsRegistrationImpl;
import com.liferay.social.networking.model.impl.MeetupsRegistrationModelImpl;
import com.liferay.social.networking.service.persistence.MeetupsRegistrationPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the meetups registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MeetupsRegistrationPersistenceImpl
	extends BasePersistenceImpl<MeetupsRegistration>
	implements MeetupsRegistrationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MeetupsRegistrationUtil</code> to access the meetups registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MeetupsRegistrationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByMeetupsEntryId;
	private FinderPath _finderPathWithoutPaginationFindByMeetupsEntryId;
	private FinderPath _finderPathCountByMeetupsEntryId;

	/**
	 * Returns all the meetups registrations where meetupsEntryId = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @return the matching meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId) {
		return findByMeetupsEntryId(
			meetupsEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the meetups registrations where meetupsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @return the range of matching meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end) {

		return findByMeetupsEntryId(meetupsEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator) {

		return findByMeetupsEntryId(
			meetupsEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByMeetupsEntryId;
				finderArgs = new Object[] {meetupsEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByMeetupsEntryId;
			finderArgs = new Object[] {
				meetupsEntryId, start, end, orderByComparator
			};
		}

		List<MeetupsRegistration> list = null;

		if (useFinderCache) {
			list = (List<MeetupsRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MeetupsRegistration meetupsRegistration : list) {
					if (meetupsEntryId !=
							meetupsRegistration.getMeetupsEntryId()) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_MEETUPSENTRYID_MEETUPSENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				list = (List<MeetupsRegistration>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups registration
	 * @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration findByMeetupsEntryId_First(
			long meetupsEntryId,
			OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException {

		MeetupsRegistration meetupsRegistration = fetchByMeetupsEntryId_First(
			meetupsEntryId, orderByComparator);

		if (meetupsRegistration != null) {
			return meetupsRegistration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("meetupsEntryId=");
		msg.append(meetupsEntryId);

		msg.append("}");

		throw new NoSuchMeetupsRegistrationException(msg.toString());
	}

	/**
	 * Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration fetchByMeetupsEntryId_First(
		long meetupsEntryId,
		OrderByComparator<MeetupsRegistration> orderByComparator) {

		List<MeetupsRegistration> list = findByMeetupsEntryId(
			meetupsEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups registration
	 * @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration findByMeetupsEntryId_Last(
			long meetupsEntryId,
			OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException {

		MeetupsRegistration meetupsRegistration = fetchByMeetupsEntryId_Last(
			meetupsEntryId, orderByComparator);

		if (meetupsRegistration != null) {
			return meetupsRegistration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("meetupsEntryId=");
		msg.append(meetupsEntryId);

		msg.append("}");

		throw new NoSuchMeetupsRegistrationException(msg.toString());
	}

	/**
	 * Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration fetchByMeetupsEntryId_Last(
		long meetupsEntryId,
		OrderByComparator<MeetupsRegistration> orderByComparator) {

		int count = countByMeetupsEntryId(meetupsEntryId);

		if (count == 0) {
			return null;
		}

		List<MeetupsRegistration> list = findByMeetupsEntryId(
			meetupsEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63;.
	 *
	 * @param meetupsRegistrationId the primary key of the current meetups registration
	 * @param meetupsEntryId the meetups entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next meetups registration
	 * @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 */
	@Override
	public MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
			long meetupsRegistrationId, long meetupsEntryId,
			OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException {

		MeetupsRegistration meetupsRegistration = findByPrimaryKey(
			meetupsRegistrationId);

		Session session = null;

		try {
			session = openSession();

			MeetupsRegistration[] array = new MeetupsRegistrationImpl[3];

			array[0] = getByMeetupsEntryId_PrevAndNext(
				session, meetupsRegistration, meetupsEntryId, orderByComparator,
				true);

			array[1] = meetupsRegistration;

			array[2] = getByMeetupsEntryId_PrevAndNext(
				session, meetupsRegistration, meetupsEntryId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MeetupsRegistration getByMeetupsEntryId_PrevAndNext(
		Session session, MeetupsRegistration meetupsRegistration,
		long meetupsEntryId,
		OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_MEETUPSENTRYID_MEETUPSENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(meetupsEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						meetupsRegistration)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<MeetupsRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the meetups registrations where meetupsEntryId = &#63; from the database.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 */
	@Override
	public void removeByMeetupsEntryId(long meetupsEntryId) {
		for (MeetupsRegistration meetupsRegistration :
				findByMeetupsEntryId(
					meetupsEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(meetupsRegistration);
		}
	}

	/**
	 * Returns the number of meetups registrations where meetupsEntryId = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @return the number of matching meetups registrations
	 */
	@Override
	public int countByMeetupsEntryId(long meetupsEntryId) {
		FinderPath finderPath = _finderPathCountByMeetupsEntryId;

		Object[] finderArgs = new Object[] {meetupsEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_MEETUPSENTRYID_MEETUPSENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MEETUPSENTRYID_MEETUPSENTRYID_2 =
		"meetupsRegistration.meetupsEntryId = ?";

	private FinderPath _finderPathFetchByU_ME;
	private FinderPath _finderPathCountByU_ME;

	/**
	 * Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or throws a <code>NoSuchMeetupsRegistrationException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @return the matching meetups registration
	 * @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration findByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException {

		MeetupsRegistration meetupsRegistration = fetchByU_ME(
			userId, meetupsEntryId);

		if (meetupsRegistration == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", meetupsEntryId=");
			msg.append(meetupsEntryId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}

		return meetupsRegistration;
	}

	/**
	 * Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration fetchByU_ME(long userId, long meetupsEntryId) {
		return fetchByU_ME(userId, meetupsEntryId, true);
	}

	/**
	 * Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId, meetupsEntryId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByU_ME, finderArgs, this);
		}

		if (result instanceof MeetupsRegistration) {
			MeetupsRegistration meetupsRegistration =
				(MeetupsRegistration)result;

			if ((userId != meetupsRegistration.getUserId()) ||
				(meetupsEntryId != meetupsRegistration.getMeetupsEntryId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_U_ME_USERID_2);

			query.append(_FINDER_COLUMN_U_ME_MEETUPSENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(meetupsEntryId);

				List<MeetupsRegistration> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByU_ME, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									userId, meetupsEntryId
								};
							}

							_log.warn(
								"MeetupsRegistrationPersistenceImpl.fetchByU_ME(long, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					MeetupsRegistration meetupsRegistration = list.get(0);

					result = meetupsRegistration;

					cacheResult(meetupsRegistration);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByU_ME, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (MeetupsRegistration)result;
		}
	}

	/**
	 * Removes the meetups registration where userId = &#63; and meetupsEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @return the meetups registration that was removed
	 */
	@Override
	public MeetupsRegistration removeByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException {

		MeetupsRegistration meetupsRegistration = findByU_ME(
			userId, meetupsEntryId);

		return remove(meetupsRegistration);
	}

	/**
	 * Returns the number of meetups registrations where userId = &#63; and meetupsEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @return the number of matching meetups registrations
	 */
	@Override
	public int countByU_ME(long userId, long meetupsEntryId) {
		FinderPath finderPath = _finderPathCountByU_ME;

		Object[] finderArgs = new Object[] {userId, meetupsEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_U_ME_USERID_2);

			query.append(_FINDER_COLUMN_U_ME_MEETUPSENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(meetupsEntryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_ME_USERID_2 =
		"meetupsRegistration.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_ME_MEETUPSENTRYID_2 =
		"meetupsRegistration.meetupsEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByME_S;
	private FinderPath _finderPathWithoutPaginationFindByME_S;
	private FinderPath _finderPathCountByME_S;

	/**
	 * Returns all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @return the matching meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status) {

		return findByME_S(
			meetupsEntryId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @return the range of matching meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end) {

		return findByME_S(meetupsEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator) {

		return findByME_S(
			meetupsEntryId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByME_S;
				finderArgs = new Object[] {meetupsEntryId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByME_S;
			finderArgs = new Object[] {
				meetupsEntryId, status, start, end, orderByComparator
			};
		}

		List<MeetupsRegistration> list = null;

		if (useFinderCache) {
			list = (List<MeetupsRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MeetupsRegistration meetupsRegistration : list) {
					if ((meetupsEntryId !=
							meetupsRegistration.getMeetupsEntryId()) ||
						(status != meetupsRegistration.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_ME_S_MEETUPSENTRYID_2);

			query.append(_FINDER_COLUMN_ME_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				qPos.add(status);

				list = (List<MeetupsRegistration>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups registration
	 * @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration findByME_S_First(
			long meetupsEntryId, int status,
			OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException {

		MeetupsRegistration meetupsRegistration = fetchByME_S_First(
			meetupsEntryId, status, orderByComparator);

		if (meetupsRegistration != null) {
			return meetupsRegistration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("meetupsEntryId=");
		msg.append(meetupsEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchMeetupsRegistrationException(msg.toString());
	}

	/**
	 * Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration fetchByME_S_First(
		long meetupsEntryId, int status,
		OrderByComparator<MeetupsRegistration> orderByComparator) {

		List<MeetupsRegistration> list = findByME_S(
			meetupsEntryId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups registration
	 * @throws NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration findByME_S_Last(
			long meetupsEntryId, int status,
			OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException {

		MeetupsRegistration meetupsRegistration = fetchByME_S_Last(
			meetupsEntryId, status, orderByComparator);

		if (meetupsRegistration != null) {
			return meetupsRegistration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("meetupsEntryId=");
		msg.append(meetupsEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchMeetupsRegistrationException(msg.toString());
	}

	/**
	 * Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	 */
	@Override
	public MeetupsRegistration fetchByME_S_Last(
		long meetupsEntryId, int status,
		OrderByComparator<MeetupsRegistration> orderByComparator) {

		int count = countByME_S(meetupsEntryId, status);

		if (count == 0) {
			return null;
		}

		List<MeetupsRegistration> list = findByME_S(
			meetupsEntryId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * @param meetupsRegistrationId the primary key of the current meetups registration
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next meetups registration
	 * @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 */
	@Override
	public MeetupsRegistration[] findByME_S_PrevAndNext(
			long meetupsRegistrationId, long meetupsEntryId, int status,
			OrderByComparator<MeetupsRegistration> orderByComparator)
		throws NoSuchMeetupsRegistrationException {

		MeetupsRegistration meetupsRegistration = findByPrimaryKey(
			meetupsRegistrationId);

		Session session = null;

		try {
			session = openSession();

			MeetupsRegistration[] array = new MeetupsRegistrationImpl[3];

			array[0] = getByME_S_PrevAndNext(
				session, meetupsRegistration, meetupsEntryId, status,
				orderByComparator, true);

			array[1] = meetupsRegistration;

			array[2] = getByME_S_PrevAndNext(
				session, meetupsRegistration, meetupsEntryId, status,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MeetupsRegistration getByME_S_PrevAndNext(
		Session session, MeetupsRegistration meetupsRegistration,
		long meetupsEntryId, int status,
		OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_ME_S_MEETUPSENTRYID_2);

		query.append(_FINDER_COLUMN_ME_S_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(meetupsEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						meetupsRegistration)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<MeetupsRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the meetups registrations where meetupsEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 */
	@Override
	public void removeByME_S(long meetupsEntryId, int status) {
		for (MeetupsRegistration meetupsRegistration :
				findByME_S(
					meetupsEntryId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(meetupsRegistration);
		}
	}

	/**
	 * Returns the number of meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @return the number of matching meetups registrations
	 */
	@Override
	public int countByME_S(long meetupsEntryId, int status) {
		FinderPath finderPath = _finderPathCountByME_S;

		Object[] finderArgs = new Object[] {meetupsEntryId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_ME_S_MEETUPSENTRYID_2);

			query.append(_FINDER_COLUMN_ME_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ME_S_MEETUPSENTRYID_2 =
		"meetupsRegistration.meetupsEntryId = ? AND ";

	private static final String _FINDER_COLUMN_ME_S_STATUS_2 =
		"meetupsRegistration.status = ?";

	public MeetupsRegistrationPersistenceImpl() {
		setModelClass(MeetupsRegistration.class);
	}

	/**
	 * Caches the meetups registration in the entity cache if it is enabled.
	 *
	 * @param meetupsRegistration the meetups registration
	 */
	@Override
	public void cacheResult(MeetupsRegistration meetupsRegistration) {
		entityCache.putResult(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey(),
			meetupsRegistration);

		finderCache.putResult(
			_finderPathFetchByU_ME,
			new Object[] {
				meetupsRegistration.getUserId(),
				meetupsRegistration.getMeetupsEntryId()
			},
			meetupsRegistration);

		meetupsRegistration.resetOriginalValues();
	}

	/**
	 * Caches the meetups registrations in the entity cache if it is enabled.
	 *
	 * @param meetupsRegistrations the meetups registrations
	 */
	@Override
	public void cacheResult(List<MeetupsRegistration> meetupsRegistrations) {
		for (MeetupsRegistration meetupsRegistration : meetupsRegistrations) {
			if (entityCache.getResult(
					MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
					MeetupsRegistrationImpl.class,
					meetupsRegistration.getPrimaryKey()) == null) {

				cacheResult(meetupsRegistration);
			}
			else {
				meetupsRegistration.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all meetups registrations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MeetupsRegistrationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the meetups registration.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MeetupsRegistration meetupsRegistration) {
		entityCache.removeResult(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(MeetupsRegistrationModelImpl)meetupsRegistration, true);
	}

	@Override
	public void clearCache(List<MeetupsRegistration> meetupsRegistrations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MeetupsRegistration meetupsRegistration : meetupsRegistrations) {
			entityCache.removeResult(
				MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
				MeetupsRegistrationImpl.class,
				meetupsRegistration.getPrimaryKey());

			clearUniqueFindersCache(
				(MeetupsRegistrationModelImpl)meetupsRegistration, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
				MeetupsRegistrationImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		MeetupsRegistrationModelImpl meetupsRegistrationModelImpl) {

		Object[] args = new Object[] {
			meetupsRegistrationModelImpl.getUserId(),
			meetupsRegistrationModelImpl.getMeetupsEntryId()
		};

		finderCache.putResult(
			_finderPathCountByU_ME, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByU_ME, args, meetupsRegistrationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		MeetupsRegistrationModelImpl meetupsRegistrationModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				meetupsRegistrationModelImpl.getUserId(),
				meetupsRegistrationModelImpl.getMeetupsEntryId()
			};

			finderCache.removeResult(_finderPathCountByU_ME, args);
			finderCache.removeResult(_finderPathFetchByU_ME, args);
		}

		if ((meetupsRegistrationModelImpl.getColumnBitmask() &
			 _finderPathFetchByU_ME.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				meetupsRegistrationModelImpl.getOriginalUserId(),
				meetupsRegistrationModelImpl.getOriginalMeetupsEntryId()
			};

			finderCache.removeResult(_finderPathCountByU_ME, args);
			finderCache.removeResult(_finderPathFetchByU_ME, args);
		}
	}

	/**
	 * Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	 *
	 * @param meetupsRegistrationId the primary key for the new meetups registration
	 * @return the new meetups registration
	 */
	@Override
	public MeetupsRegistration create(long meetupsRegistrationId) {
		MeetupsRegistration meetupsRegistration = new MeetupsRegistrationImpl();

		meetupsRegistration.setNew(true);
		meetupsRegistration.setPrimaryKey(meetupsRegistrationId);

		meetupsRegistration.setCompanyId(CompanyThreadLocal.getCompanyId());

		return meetupsRegistration;
	}

	/**
	 * Removes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupsRegistrationId the primary key of the meetups registration
	 * @return the meetups registration that was removed
	 * @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 */
	@Override
	public MeetupsRegistration remove(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException {

		return remove((Serializable)meetupsRegistrationId);
	}

	/**
	 * Removes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the meetups registration
	 * @return the meetups registration that was removed
	 * @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 */
	@Override
	public MeetupsRegistration remove(Serializable primaryKey)
		throws NoSuchMeetupsRegistrationException {

		Session session = null;

		try {
			session = openSession();

			MeetupsRegistration meetupsRegistration =
				(MeetupsRegistration)session.get(
					MeetupsRegistrationImpl.class, primaryKey);

			if (meetupsRegistration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMeetupsRegistrationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(meetupsRegistration);
		}
		catch (NoSuchMeetupsRegistrationException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MeetupsRegistration removeImpl(
		MeetupsRegistration meetupsRegistration) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(meetupsRegistration)) {
				meetupsRegistration = (MeetupsRegistration)session.get(
					MeetupsRegistrationImpl.class,
					meetupsRegistration.getPrimaryKeyObj());
			}

			if (meetupsRegistration != null) {
				session.delete(meetupsRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (meetupsRegistration != null) {
			clearCache(meetupsRegistration);
		}

		return meetupsRegistration;
	}

	@Override
	public MeetupsRegistration updateImpl(
		MeetupsRegistration meetupsRegistration) {

		boolean isNew = meetupsRegistration.isNew();

		if (!(meetupsRegistration instanceof MeetupsRegistrationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(meetupsRegistration.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					meetupsRegistration);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in meetupsRegistration proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MeetupsRegistration implementation " +
					meetupsRegistration.getClass());
		}

		MeetupsRegistrationModelImpl meetupsRegistrationModelImpl =
			(MeetupsRegistrationModelImpl)meetupsRegistration;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (meetupsRegistration.getCreateDate() == null)) {
			if (serviceContext == null) {
				meetupsRegistration.setCreateDate(now);
			}
			else {
				meetupsRegistration.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!meetupsRegistrationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				meetupsRegistration.setModifiedDate(now);
			}
			else {
				meetupsRegistration.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (meetupsRegistration.isNew()) {
				session.save(meetupsRegistration);

				meetupsRegistration.setNew(false);
			}
			else {
				meetupsRegistration = (MeetupsRegistration)session.merge(
					meetupsRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!MeetupsRegistrationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				meetupsRegistrationModelImpl.getMeetupsEntryId()
			};

			finderCache.removeResult(_finderPathCountByMeetupsEntryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByMeetupsEntryId, args);

			args = new Object[] {
				meetupsRegistrationModelImpl.getMeetupsEntryId(),
				meetupsRegistrationModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByME_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByME_S, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((meetupsRegistrationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByMeetupsEntryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					meetupsRegistrationModelImpl.getOriginalMeetupsEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByMeetupsEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMeetupsEntryId, args);

				args = new Object[] {
					meetupsRegistrationModelImpl.getMeetupsEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByMeetupsEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMeetupsEntryId, args);
			}

			if ((meetupsRegistrationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByME_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					meetupsRegistrationModelImpl.getOriginalMeetupsEntryId(),
					meetupsRegistrationModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByME_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByME_S, args);

				args = new Object[] {
					meetupsRegistrationModelImpl.getMeetupsEntryId(),
					meetupsRegistrationModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByME_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByME_S, args);
			}
		}

		entityCache.putResult(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey(),
			meetupsRegistration, false);

		clearUniqueFindersCache(meetupsRegistrationModelImpl, false);
		cacheUniqueFindersCache(meetupsRegistrationModelImpl);

		meetupsRegistration.resetOriginalValues();

		return meetupsRegistration;
	}

	/**
	 * Returns the meetups registration with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the meetups registration
	 * @return the meetups registration
	 * @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 */
	@Override
	public MeetupsRegistration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMeetupsRegistrationException {

		MeetupsRegistration meetupsRegistration = fetchByPrimaryKey(primaryKey);

		if (meetupsRegistration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMeetupsRegistrationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return meetupsRegistration;
	}

	/**
	 * Returns the meetups registration with the primary key or throws a <code>NoSuchMeetupsRegistrationException</code> if it could not be found.
	 *
	 * @param meetupsRegistrationId the primary key of the meetups registration
	 * @return the meetups registration
	 * @throws NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 */
	@Override
	public MeetupsRegistration findByPrimaryKey(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException {

		return findByPrimaryKey((Serializable)meetupsRegistrationId);
	}

	/**
	 * Returns the meetups registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the meetups registration
	 * @return the meetups registration, or <code>null</code> if a meetups registration with the primary key could not be found
	 */
	@Override
	public MeetupsRegistration fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MeetupsRegistration meetupsRegistration =
			(MeetupsRegistration)serializable;

		if (meetupsRegistration == null) {
			Session session = null;

			try {
				session = openSession();

				meetupsRegistration = (MeetupsRegistration)session.get(
					MeetupsRegistrationImpl.class, primaryKey);

				if (meetupsRegistration != null) {
					cacheResult(meetupsRegistration);
				}
				else {
					entityCache.putResult(
						MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
						MeetupsRegistrationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
					MeetupsRegistrationImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return meetupsRegistration;
	}

	/**
	 * Returns the meetups registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param meetupsRegistrationId the primary key of the meetups registration
	 * @return the meetups registration, or <code>null</code> if a meetups registration with the primary key could not be found
	 */
	@Override
	public MeetupsRegistration fetchByPrimaryKey(long meetupsRegistrationId) {
		return fetchByPrimaryKey((Serializable)meetupsRegistrationId);
	}

	@Override
	public Map<Serializable, MeetupsRegistration> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MeetupsRegistration> map =
			new HashMap<Serializable, MeetupsRegistration>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MeetupsRegistration meetupsRegistration = fetchByPrimaryKey(
				primaryKey);

			if (meetupsRegistration != null) {
				map.put(primaryKey, meetupsRegistration);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
				MeetupsRegistrationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MeetupsRegistration)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (MeetupsRegistration meetupsRegistration :
					(List<MeetupsRegistration>)q.list()) {

				map.put(
					meetupsRegistration.getPrimaryKeyObj(),
					meetupsRegistration);

				cacheResult(meetupsRegistration);

				uncachedPrimaryKeys.remove(
					meetupsRegistration.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
					MeetupsRegistrationImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the meetups registrations.
	 *
	 * @return the meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the meetups registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @return the range of meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the meetups registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findAll(
		int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the meetups registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupsRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of meetups registrations
	 */
	@Override
	public List<MeetupsRegistration> findAll(
		int start, int end,
		OrderByComparator<MeetupsRegistration> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<MeetupsRegistration> list = null;

		if (useFinderCache) {
			list = (List<MeetupsRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MEETUPSREGISTRATION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEETUPSREGISTRATION;

				sql = sql.concat(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<MeetupsRegistration>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the meetups registrations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MeetupsRegistration meetupsRegistration : findAll()) {
			remove(meetupsRegistration);
		}
	}

	/**
	 * Returns the number of meetups registrations.
	 *
	 * @return the number of meetups registrations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEETUPSREGISTRATION);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MeetupsRegistrationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the meetups registration persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByMeetupsEntryId = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMeetupsEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByMeetupsEntryId = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMeetupsEntryId",
			new String[] {Long.class.getName()},
			MeetupsRegistrationModelImpl.MEETUPSENTRYID_COLUMN_BITMASK |
			MeetupsRegistrationModelImpl.MODIFIEDDATE_COLUMN_BITMASK);

		_finderPathCountByMeetupsEntryId = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMeetupsEntryId",
			new String[] {Long.class.getName()});

		_finderPathFetchByU_ME = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_ME",
			new String[] {Long.class.getName(), Long.class.getName()},
			MeetupsRegistrationModelImpl.USERID_COLUMN_BITMASK |
			MeetupsRegistrationModelImpl.MEETUPSENTRYID_COLUMN_BITMASK);

		_finderPathCountByU_ME = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_ME",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByME_S = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByME_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByME_S = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByME_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			MeetupsRegistrationModelImpl.MEETUPSENTRYID_COLUMN_BITMASK |
			MeetupsRegistrationModelImpl.STATUS_COLUMN_BITMASK |
			MeetupsRegistrationModelImpl.MODIFIEDDATE_COLUMN_BITMASK);

		_finderPathCountByME_S = new FinderPath(
			MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByME_S",
			new String[] {Long.class.getName(), Integer.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(MeetupsRegistrationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_MEETUPSREGISTRATION =
		"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration";

	private static final String _SQL_SELECT_MEETUPSREGISTRATION_WHERE_PKS_IN =
		"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE meetupsRegistrationId IN (";

	private static final String _SQL_SELECT_MEETUPSREGISTRATION_WHERE =
		"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE ";

	private static final String _SQL_COUNT_MEETUPSREGISTRATION =
		"SELECT COUNT(meetupsRegistration) FROM MeetupsRegistration meetupsRegistration";

	private static final String _SQL_COUNT_MEETUPSREGISTRATION_WHERE =
		"SELECT COUNT(meetupsRegistration) FROM MeetupsRegistration meetupsRegistration WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "meetupsRegistration.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MeetupsRegistration exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MeetupsRegistration exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MeetupsRegistrationPersistenceImpl.class);

}