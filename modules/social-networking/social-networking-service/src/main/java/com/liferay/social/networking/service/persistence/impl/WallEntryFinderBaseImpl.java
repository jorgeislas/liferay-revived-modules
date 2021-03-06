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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.social.networking.model.WallEntry;
import com.liferay.social.networking.service.persistence.WallEntryPersistence;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WallEntryFinderBaseImpl extends BasePersistenceImpl<WallEntry> {

	public WallEntryFinderBaseImpl() {
		setModelClass(WallEntry.class);
	}

	/**
	 * Returns the wall entry persistence.
	 *
	 * @return the wall entry persistence
	 */
	public WallEntryPersistence getWallEntryPersistence() {
		return wallEntryPersistence;
	}

	/**
	 * Sets the wall entry persistence.
	 *
	 * @param wallEntryPersistence the wall entry persistence
	 */
	public void setWallEntryPersistence(
		WallEntryPersistence wallEntryPersistence) {

		this.wallEntryPersistence = wallEntryPersistence;
	}

	@BeanReference(type = WallEntryPersistence.class)
	protected WallEntryPersistence wallEntryPersistence;

}