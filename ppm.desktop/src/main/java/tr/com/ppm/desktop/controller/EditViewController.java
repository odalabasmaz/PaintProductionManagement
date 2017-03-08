package tr.com.ppm.desktop.controller;

import tr.com.ppm.desktop.model.common.AuditableEntity;

/**
 * Created by yoztas on 08.03.2017.
 */
public interface EditViewController<T> {
	enum ViewMode {NEW, EDIT}
	void updateEditView(T entity);
}
