/**
 * *******************************************************************************
 * Copyright (c) 2008 - 2018 Profactor GmbH, TU Wien ACIN, fortiss GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerhard Ebenhofer, Alois Zoitl, Ingo Hegny, Monika Wenger, Martin Jobst
 *      - initial API and implementation and/or initial documentation
 * *******************************************************************************
 */
package org.eclipse.fordiac.ide.model.data;

import org.eclipse.fordiac.ide.model.libraryElement.CompilerInfo;

/** <!-- begin-user-doc --> A representation of the model object '<em><b>Any Derived Type</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.fordiac.ide.model.data.AnyDerivedType#getCompilerInfo <em>Compiler Info</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.model.data.DataPackage#getAnyDerivedType()
 * @model
 * @generated */
public interface AnyDerivedType extends AnyType {
	/** Returns the value of the '<em><b>Compiler Info</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @return the value of the '<em>Compiler Info</em>' containment reference.
	 * @see #setCompilerInfo(CompilerInfo)
	 * @see org.eclipse.fordiac.ide.model.data.DataPackage#getAnyDerivedType_CompilerInfo()
	 * @model containment="true" resolveProxies="true" required="true"
	 * @generated */
	CompilerInfo getCompilerInfo();

	/** Sets the value of the '{@link org.eclipse.fordiac.ide.model.data.AnyDerivedType#getCompilerInfo <em>Compiler
	 * Info</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Compiler Info</em>' containment reference.
	 * @see #getCompilerInfo()
	 * @generated */
	void setCompilerInfo(CompilerInfo value);

} // AnyDerivedType
